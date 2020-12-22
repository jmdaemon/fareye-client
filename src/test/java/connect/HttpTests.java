package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockserver.junit.jupiter.*;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;
import static org.mockserver.matchers.Times.exactly;

public class HttpTests {

  private static ClientAndServer mockServer;

  private static final String mockServerURL = "http://127.0.0.1"; 
  private static final String mockServerPORT = "1080";
  private static final String getFrom = "http://webcode.me";
  private static final String postTo  = "https://httpbin.org/post";

  @BeforeAll
  public static void startMockServer() {
      mockServer = startClientAndServer(1080);
  }

  @AfterAll
  public static void stopMockServer() {
      mockServer.stop();
  } 

  public void createPingExpectation() {
    new ClientAndServer("localhost", 1080)
      .when(
          request()
          .withMethod("GET"),
          //.withPath("/index.html"),
          exactly(1))
      .respond(
          response()
          .withStatusCode(200)
          .withBody("Successfully pinged server")
          );
  }

  @Test
  public void sendGET_ToSite_ReturnResponse() throws Exception {
    Http conn = new Http();
    createPingExpectation();
    String response = conn.get(mockServerURL + ":" + mockServerPORT);
    assertNotNull(response);
  }

  //@Test
  //public void sendPOST_ToSite_ReturnResponse() throws Exception {
    //Http conn = new Http(); 
    //var objectMapper = new ObjectMapper(); 
    //String requestBody = objectMapper.writeValueAsString(values);
    //String response = conn.post(postTo, requestBody);
    //assertNotNull(response);
  //}
  
}
