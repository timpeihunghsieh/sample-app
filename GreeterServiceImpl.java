package greeter;

import io.grpc.stub.StreamObserver;

// import greeter.Greeter;
// import greeter.HelloRequest;
// import greeter.HelloReply;

import io.prometheus.client.Counter;

public class GreeterServiceImpl extends GreeterGrpc.GreeterImplBase {

  static final Counter requestsTotal = Counter.build()
      .name("requests_total").help("Total Incoming requests.").register();

  @Override
  public void sayHello(HelloRequest req,
                       StreamObserver<HelloReply> responseObserver) {
    requestsTotal.inc();
    HelloReply reply = HelloReply.newBuilder().setMessage(
        "Hello " + req.getName()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}