package com.sampleapp.greeterservice;

public interface Greeter {
  public HelloReply sayHello(HelloRequest request);
}