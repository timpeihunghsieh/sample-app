package greeter;

//import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.prometheus.client.exporter.MetricsServlet;

public class GreeterJettyServer {
  private org.eclipse.jetty.server.Server jetty_server;
  private int port;

  public GreeterJettyServer(int port) {
    this.port = port;
  }

  public void start() throws Exception {
    jetty_server = new org.eclipse.jetty.server.Server(this.port);
      ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/");
      jetty_server.setHandler(context);
      context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
      jetty_server.start();
  }
}