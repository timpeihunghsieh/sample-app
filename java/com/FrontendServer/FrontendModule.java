package com.frontendservice;

import com.google.inject.AbstractModule;

public class FrontendModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(FrontendLogic.class).to(FrontendLogicImpl.class);
  }
}