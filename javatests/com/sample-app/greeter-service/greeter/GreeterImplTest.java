package com.sampleapp.greeterservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GreeterImplTest {

  @Test
  public void testSayHello() throws Exception {
    GreeterImpl greeter = new GreeterImpl();
    HelloRequest request = HelloRequest.newBuilder()
        .setName("World!").build();
    HelloReply reply = greeter.sayHello(request);
    assertEquals(reply.getMessage(), "Hello World!");
  }
}