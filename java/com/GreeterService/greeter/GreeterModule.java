package com.greeterservice;

import com.google.inject.AbstractModule;

public class GreeterModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Greeter.class).to(GreeterImpl.class);
  }
}