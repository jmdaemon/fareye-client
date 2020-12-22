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

  private static final String mockServerURL = "localhost";
  private static final String getFrom = "http://webcode.me";
  private static final String postTo  = "https://httpbin.org/post";
  private HashMap<String, String> values;

  @BeforeAll
  public static void startMockServer() {
      mockServer = startClientAndServer(1080);
  }

  @AfterAll
  public static void stopMockServer() {
      mockServer.stop();
  } 

  //@BeforeEach
  //private void setUp() { 
    //this.values = new HashMap<String, String>() {{ 
      //put("name", "John Doe"); 
      //put ("occupation", "gardener"); 
    //}};
  //}

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
    //String response = conn.get(getFrom);
    //String response = conn.get(mockServerURL + ":1080");
    String response = conn.get("http://127.0.0.1:1080");
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
