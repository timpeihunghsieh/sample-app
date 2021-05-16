package com.frontendservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.prometheus.client.exporter.MetricsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class FrontendServer {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    Injector injector = Guice.createInjector(new FrontendModule());

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    context.addServlet(new ServletHolder(
        new FrontendServlet(injector.getInstance(FrontendLogic.class))), "/*");
    context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");

    server.start();
  }
}