package com.common.commonrpcserver;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class CommonRpcServer {

  private static final Logger logger = Logger.getLogger(
      CommonRpcServer.class.getName());
  private BindableService rpcService;

  /* The port on which the server should run */
  private final int port;
  private Server server;

  public CommonRpcServer(int port, BindableService rpcService) {
    this.port = port;
    this.rpcService = rpcService;
  }

  public void start() throws IOException {
    server = ServerBuilder.forPort(port)
        .addService(this.rpcService)
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