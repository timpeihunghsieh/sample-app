package com.sampleapp.greeterservice;

import java.io.IOException;

import com.common.commonrpcserver.CommonServer;

public class GreeterServer extends CommonServer {
  public GreeterServer() {
    super(new GreeterRpcImpl());
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