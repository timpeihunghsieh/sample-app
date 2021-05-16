package com.frontendservice;

import com.google.inject.Inject;

public class FrontendLogicImpl implements FrontendLogic {
  @Inject
  public FrontendLogicImpl() {}

  @Override
  public String getReply(String requestValue) {
    return "Sample frontend response. Request: " + requestValue;
  }
}
