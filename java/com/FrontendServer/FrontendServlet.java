package com.frontendservice;

import com.google.inject.Inject;
import io.prometheus.client.Counter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontendServlet extends HttpServlet {
  private FrontendLogic frontendLogic;

  static final Counter requests_total = Counter.build()
    .name("requests_total").help("Total Incoming requests.").register();
  static final Counter requests_redir = Counter.build()
    .name("requests_redir").help("Number of redirect requests.").register();
  static final Counter requests_insert = Counter.build()
    .name("requests_insert").help("Number of insertion requests.").register();
  static final Counter requests_default = Counter.build()
    .name("requests_default").help("Number of default or invalid requests.").register();

  @Inject
  public FrontendServlet(FrontendLogic frontendLogic) {
    super();
    this.frontendLogic = frontendLogic;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String requestValue = req.getParameter("r");
    String reply = frontendLogic.getReply(requestValue);
    resp.setStatus(200);
    resp.getWriter().println(reply);
  }
}