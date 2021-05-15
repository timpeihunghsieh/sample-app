package com.common.commonrpcserver;

import java.io.IOException;

import io.grpc.BindableService;

/**
 * Server that manages startup/shutdown of a {@code CommonServer} server.
 */
public class CommonServer {
  private CommonRpcServer commonRpcServer;
  private CommonJettyServer commonJettyServer;

  public CommonServer(BindableService rpcService) {
    this.commonRpcServer = new CommonRpcServer(50051, rpcService);
    this.commonJettyServer = new CommonJettyServer(8080);
  }

  public void start() throws IOException {
    this.commonRpcServer.start();
    try {
      this.commonJettyServer.start();
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
        CommonServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  public void stop() {
    if (this.commonRpcServer != null) {
      this.commonRpcServer.stop();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon
   * threads.
   */
  public void blockUntilShutdown() throws InterruptedException {
    if (this.commonRpcServer != null) {
      this.commonRpcServer.blockUntilShutdown();
    }
  }
}