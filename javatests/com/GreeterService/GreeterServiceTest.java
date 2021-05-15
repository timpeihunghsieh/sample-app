package com.greeterservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.greeterclient.GreeterClient;

/**
 * A simple integration test that runs the server at startup.
 */
public class GreeterServiceTest {

  static GreeterServer server;
  GreeterClient client;

  @BeforeClass
  public static void init() throws Exception {
    server = new GreeterServer();
    server.start();
  }

  @AfterClass
  public static void destroy() throws InterruptedException {
    server.stop();
  }

  @Before
  public void setUp() throws Exception {
    client = new GreeterClient("localhost", 50051);
  }

  @After
  public void tearDown() throws InterruptedException {
    client.shutdown();
  }

  @Test
  public void testGreet() throws InterruptedException {
    String msg = client.greet("world");
    assertEquals("Blocking message should return correct string",
        "Hello world", msg);
  }
}