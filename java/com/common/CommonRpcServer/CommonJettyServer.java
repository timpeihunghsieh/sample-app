package com.common.commonrpcserver;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.prometheus.client.exporter.MetricsServlet;

public class CommonJettyServer {
  private org.eclipse.jetty.server.Server jettyServer;
  private int port;

  public CommonJettyServer(int port) {
    this.port = port;
  }

  public void start() throws Exception {
    jettyServer = new org.eclipse.jetty.server.Server(this.port);
      ServletContextHandler context =
          new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/");
      jettyServer.setHandler(context);
      context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
      jettyServer.start();
  }
}