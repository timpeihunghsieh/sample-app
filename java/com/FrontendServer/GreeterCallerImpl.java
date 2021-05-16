package com.frontendservice;

import com.google.inject.Inject;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import com.greeterservice.HelloRequest;
import com.greeterservice.HelloReply;
import com.greeterservice.GreeterGrpc;

public class GreeterCallerImpl implements GreeterCaller {
  private final ManagedChannel channel;
  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  @Inject
  public GreeterCallerImpl() {
    final String kHost = "greeter-service";
    final int kPort = 80;
    channel = ManagedChannelBuilder.forAddress(kHost, kPort)
        .usePlaintext()
        .build();
    blockingStub = GreeterGrpc.newBlockingStub(channel);
  }

  @Override
  public String sayHello(String name) {
    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloReply response;
    try {
      response = blockingStub.sayHello(request);
      return response.getMessage();
    } catch (StatusRuntimeException e) {
      String msg = "RPC failed: " + e.getStatus();
      return msg;
    }
  }
}
