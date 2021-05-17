package com.sampleapp.frontendservice;

import com.google.inject.Inject;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import com.sampleapp.greeterservice.HelloRequest;
import com.sampleapp.greeterservice.HelloReply;
import com.sampleapp.greeterservice.GreeterGrpc;

public class GreeterCallerImpl implements GreeterCaller {
  private final ManagedChannel channel;
  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  @Inject
  public GreeterCallerImpl() {
    final String kHost = "greeter-server";
    final int kPort = 50051;
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
