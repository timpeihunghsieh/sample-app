package com.greeterservice;

import io.grpc.stub.StreamObserver;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.prometheus.client.Counter;

public class GreeterRpcImpl extends GreeterGrpc.GreeterImplBase {

  static final Counter requestsTotal = Counter.build()
      .name("requests_total").help("Total Incoming requests.").register();
  private Injector injector;

  public GreeterRpcImpl() {
    this.injector = Guice.createInjector(new GreeterModule());
  }

  @Override
  public void sayHello(HelloRequest req,
                       StreamObserver<HelloReply> responseObserver) {
    requestsTotal.inc();

    Greeter greeter = this.injector.getInstance(GreeterImpl.class);
    HelloReply reply = greeter.sayHello(req);

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}