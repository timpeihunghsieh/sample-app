package com.sampleapp.greeterservice;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * A simple client that requests a greeting from the {@link
 * HelloWorldServer}.
 */
public class GreeterClient {
  private static final Logger logger =
      Logger.getLogger(GreeterClient.class.getName());

  private final ManagedChannel channel;
  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  /** Construct client connecting to HelloWorld server at {@code host:port}. */
  public GreeterClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .build();
    blockingStub = GreeterGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /** Say hello to server. */
  public String greet(String name) {
    logger.info("Will try to greet " + name + " ...");
    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloReply response;
    try {
      response = blockingStub.sayHello(request);
      logger.info("Greeting: " + response.getMessage());
      return response.getMessage();
    } catch (StatusRuntimeException e) {
      String msg = "RPC failed: " + e.getStatus();
      logger.log(Level.WARNING, msg);
      return msg;
    }
  }

  /**
   * Greet server. If provided, the first element of {@code args} is
   * the name to use in the greeting.
   */
  public static void main(String[] args) throws Exception {
    GreeterClient client = new GreeterClient("localhost", 50051);
    try {
      /* Access a service running on the local machine on port 50051 */
      String user = "world";
      if (args.length > 0) {
        user = args[0]; /* Use the arg as the name to greet if provided */
      }
      client.greet(user);
    } finally {
      client.shutdown();
    }
  }

}