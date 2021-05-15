package greeter;

import java.io.IOException;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class GreeterServer {
  private GreeterRpcServer greeterRpcServer;
  private GreeterJettyServer greeterJettyServer;

  public GreeterServer() {
    this.greeterRpcServer = new GreeterRpcServer(50051);
    this.greeterJettyServer = new GreeterJettyServer(8080);
  }

  public void start() throws IOException {
    this.greeterRpcServer.start();
    try {
      this.greeterJettyServer.start();
    } catch (Exception e) {
      // ignore
    }


    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM
        // shutdown hook.
        System.err.println(
            "*** shutting down gRPC server since JVM is shutting down");
        GreeterServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  public void stop() {
    if (this.greeterRpcServer != null) {
      this.greeterRpcServer.stop();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon
   * threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (this.greeterRpcServer != null) {
      this.greeterRpcServer.blockUntilShutdown();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args)
      throws IOException, InterruptedException {
    final GreeterServer server = new GreeterServer();
    server.start();
    server.blockUntilShutdown();
  }
}