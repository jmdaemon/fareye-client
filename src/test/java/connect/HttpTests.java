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
import static org.mockserver.model.Parameter.*;
import static org.mockserver.model.ParameterBody.*;
import static org.mockserver.model.JsonBody.*;
import static org.mockserver.matchers.MatchType.*;

public class HttpTests {
  private static ClientAndServer mockServer;
  private Http conn;

  private static final String HTTP_SERVER_ADDRESS = "http://127.0.0.1:1080";
  private static final String ADDRESS_PARAMS = HTTP_SERVER_ADDRESS + "/?username=112233&password=aabbcc";

  @BeforeAll
  public static void startMockServer() { mockServer = startClientAndServer(1080); }

  @AfterAll
  public static void stopMockServer() { mockServer.stop(); } 

  @BeforeEach
  public void startUp() {
    conn = new Http();
  }

  public void createGetExpectation() {
    this.mockServer
      .when(
          request()
          .withMethod("GET"),
          exactly(1))
      .respond(
          response()
          .withStatusCode(200)
          .withBody("Successfully pinged server")
          );
  }

  public void createPostExpectation() {
    this.mockServer
      .when(
          request()
          .withMethod("POST"),
          exactly(1))
      .respond(
          response()
          .withStatusCode(200)
          .withBody("Successfully sent POST request")
          );
  }

  public void createLoginExpectation() {
    this.mockServer
      .when(
          request()
          .withMethod("GET")
          .withQueryStringParameters(
              param("username", "112233"),
              param("password", "aabbcc")),
          exactly(1))
      .respond(
          response()
          .withStatusCode(200)
          .withBody(
            json(
              "{" + System.lineSeparator() + 
                "   \"username\": 007," + System.lineSeparator() +
                "   \"password\": \"StrongPassword\"," + System.lineSeparator() +
                "   \"balance\": 0.0," + System.lineSeparator() + 
                "   \"name\": [\"Travis\", \" \", \"Bickle\"]" + System.lineSeparator() +
                "}"))

          );
  }

  //public void createPostExpectation() {
    //new ClientAndServer("localhost", 1080)
      //.when(
          //request()
          //.withMethod("POST")
          //.withPath("/login")
          //.withBody("{username: 'foo', password: 'bar'}"),
          //exactly(1))
      //.respond(
          //response()
          //.withStatusCode(302)
          //.withCookie("sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW")
          //.withHeader("Location", "https://www.mock-server.com")
          //);
  //}

  @Test
  public void sendGET_ToSite_ReturnResponse() throws Exception {
    createGetExpectation();
    String response = conn.get(HTTP_SERVER_ADDRESS);
    assertNotNull(response);
    assertEquals("Successfully pinged server", response);
  }

  @Test
  public void sendPOST_Localhost_ReturnResponse() throws Exception {
    createPostExpectation();
    String response = conn.post(HTTP_SERVER_ADDRESS, "");
    assertNotNull(response);
    assertEquals("Successfully sent POST request", response);
  }

  @Test
  public void sendGET_LocalhostLogin_ReturnResponse() throws Exception {
    createLoginExpectation();
    String response = conn.get(ADDRESS_PARAMS);
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
