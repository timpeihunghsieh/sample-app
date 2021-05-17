package com.sampleapp.greeterservice;

import com.google.inject.Inject;

public class GreeterImpl implements Greeter {

  @Inject
  public GreeterImpl() {}

  @Override
  public HelloReply sayHello(HelloRequest request) {
    HelloReply reply = HelloReply.newBuilder().setMessage(
        "Hello " + request.getName()).build();
    return reply;
  }
}
