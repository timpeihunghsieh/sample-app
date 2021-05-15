package greeter;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

// import greeter.Greeter;
// import greeter.HelloRequest;
// import greeter.HelloReply;

//import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.Counter;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class GreeterServer {

  static final Counter requests_total = Counter.build()
      .name("requests_total").help("Total Incoming requests.").register();

  private static final Logger logger = Logger.getLogger(GreeterServer.class.getName());

  /* The port on which the server should run */
  private final int port;
  private Server server;

  private org.eclipse.jetty.server.Server jetty_server;

  public GreeterServer() {
    this(50051);
  }

  public GreeterServer(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    server = ServerBuilder.forPort(port)
        .addService(new GreeterImpl())
        .build()
        .start();
    logger.info("Server started, listening on " + port);


    try {
      jetty_server = new org.eclipse.jetty.server.Server(8080);
      ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/");
      jetty_server.setHandler(context);
      context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
      jetty_server.start();
    } catch(Exception e) {
      // ignore
    }


    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        GreeterServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  public void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final GreeterServer server = new GreeterServer();
    server.start();
    server.blockUntilShutdown();
  }

  private class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
      requests_total.inc();
      HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}