package greeter;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GreeterRpcServer {

  private static final Logger logger = Logger.getLogger(
      GreeterRpcServer.class.getName());

  /* The port on which the server should run */
  private final int port;
  private Server server;

  public GreeterRpcServer(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    server = ServerBuilder.forPort(port)
        .addService(new GreeterServiceImpl())
        .build()
        .start();
    logger.info("Server started, listening on " + port);
  }

  public void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon
   * threads.
   */
  public void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
}