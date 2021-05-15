package greeter;

import java.io.IOException;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class GreeterServer {
  private GreeterRpcServer greeter_rpc_server;
  private GreeterJettyServer greeter_jetty_server;

  public GreeterServer() {
    this.greeter_rpc_server = new GreeterRpcServer(50051);
    this.greeter_jetty_server = new GreeterJettyServer(8080);
  }

  public void start() throws IOException {
    this.greeter_rpc_server.start();
    try {
      this.greeter_jetty_server.start();
    } catch (Exception e) {
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