package greeter;

import java.io.IOException;

//import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.prometheus.client.exporter.MetricsServlet;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class GreeterServer {
  private GreeterRpcServer greeter_rpc_server;
  private org.eclipse.jetty.server.Server jetty_server;

  public GreeterServer() {
    this.greeter_rpc_server = new GreeterRpcServer(50051);
  }

  public void start() throws IOException {
    this.greeter_rpc_server.start();


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
    if (this.greeter_rpc_server != null) {
      this.greeter_rpc_server.stop();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (this.greeter_rpc_server != null) {
      this.greeter_rpc_server.blockUntilShutdown();
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
}