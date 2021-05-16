package com.frontendservice;

import com.google.inject.Inject;

public class FrontendLogicImpl implements FrontendLogic {
  private GreeterCaller greeter_caller;

  @Inject
  public FrontendLogicImpl(GreeterCaller greeter_caller) {
    this.greeter_caller = greeter_caller;
  }

  @Override
  public String getReply(String requestValue) {
    final String kName = "Tim";
    String greeter_response = greeter_caller.sayHello(kName);

    return "Sample frontend response. " +
        "Request: " + requestValue + "\n" + 
        "Greeter response: " + greeter_response;
  }
}
