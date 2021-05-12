package greeter;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

// import greeter.Greeter;
// import greeter.HelloRequest;
// import greeter.HelloReply;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class GreeterServer {
  private static final Logger logger = Logger.getLogger(GreeterServer.class.getName());

  /* The port on which the server should run */
  private final int port;
  private Server server;

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
      HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}