package com.frontendservice;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class FrontendServletTest {
  private FrontendServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  private final String REQUEST_PARAM = "r";

  @Mock
  private FrontendLogic frontendLogic;

  @Before
  public void setUp() {
    servlet = new FrontendServlet(frontendLogic);
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  public void testRequest()
      throws ServletException, IOException {
    String expectedRequestValue = "Magic Request value";
    String expectedReturnValue = "Magic Return value";
    Mockito.when(frontendLogic.getReply(expectedRequestValue))
        .thenReturn(expectedReturnValue);

    request.addParameter(REQUEST_PARAM, expectedRequestValue);
    servlet.doGet(request, response);

    assertTrue("Response does not contain return value",
        response.getContentAsString().contains(expectedReturnValue));
  }
}