package app.connect;

import static app.security.SSLManager.*;
import app.security.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static java.util.Map.entry;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;

import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.Headers.*;
import static org.mockserver.model.Header.*;
import static org.mockserver.model.Parameter.*;
import static org.mockserver.model.ParameterBody.*;
import static org.mockserver.model.JsonBody.*;
import static org.mockserver.matchers.MatchType.*;

public class HttpsTests {
  private static ClientAndServer mockServer;

  private SSLManager sm;
  private Certificate certAuth;
  private Https conn;
  private Map<String, String> params;

  private static final String CLIENT_KEYSTORE = "crypt/keytool/client_truststore.jks";
  private static final String CA_CERT         = "crypt/keytool/ca/cacert.pem";
  private static final String PASSWORD        = "password";
  private static final String SERVER_ADDRESS  = "https://localhost:1080";
  private static final String ADDRESS_PARAMS  = SERVER_ADDRESS + "/?username=112233&password=aabbcc";
  private static final String HTTP_SERVER_ADDRESS = "http://127.0.0.1:1080";

  @BeforeAll
  public static void startMockServer() { mockServer = startClientAndServer(1080); }

  @AfterAll
  public static void stopMockServer() { mockServer.stop(); }

  @BeforeEach
  public void setUp() throws Exception {
    this.certAuth = loadCertificates(CA_CERT);
    this.sm = new SSLManager();
    this.conn = new Https();
    params = Map.ofEntries(
        entry("username", "123456"),
        entry("password", "AABBCC"),
        entry("balance", "0.0"),
        entry("firstName", "Richard"),
        entry("middleName", " "),
        entry("lastName", "Lewis"));
  }

  public void createSecureGetExpectation() {
    this.mockServer
      .withSecure(true)
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

  public void createSecurePostExpectation() {
    this.mockServer
      .withSecure(true)
      .when(
          request()
          .withMethod("POST"),
          exactly(1))
      .respond(
          response()
          .withStatusCode(200)
          .withBody("Sent POST to mockServer")
          );
  }

  public void createSecureLoginExpectation() {
    this.mockServer
      .withSecure(true)
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
                "   \"username\": 112233," + System.lineSeparator() +
                "   \"password\": \"aabbcc\"," + System.lineSeparator() +
                "   \"balance\": 0.0," + System.lineSeparator() +
                "   \"name\": [\"Patrick\", \" \", \"Bateman\"]" + System.lineSeparator() +
                "}")
            )
          );
  }

  public void createSecureSignUpExpectation() {
    this.mockServer
      .withSecure(true)
      .when(
          request()
          .withMethod("POST")
          .withPath("/register")
          .withHeaders(
            header("Content-Type", "application/x-www-form-urlencoded"))
          .withBody(
            params(
              param("username", "123456"),
              param("password", "AABBCC"),
              param("balance", "0.0"),
              param("firstName", "Richard"),
              param("middleName", " "),
              param("lastName", "Lewis")
              )),
          exactly(1))
      .respond(
          response()
          .withStatusCode(200)
          .withBody("Successfully registered new user")
            );
  }

  //public void createSecureSignUpExpectationJSON() {
    //this.mockServer
      //.withSecure(true)
      //.when(
          //request()
          //.withMethod("POST")
          //.withPath("/register")
          //.withBody(
              //json(
                //"{" + System.lineSeparator() +
                //"   \"username\": 123456," + System.lineSeparator() +
                //"   \"password\": \"AABBCC\"," + System.lineSeparator() +
                //"   \"balance\": 0.0," + System.lineSeparator() +
                //"   \"name\": [\"Richard\", \" \", \"Lewis\"]" + System.lineSeparator() +
                //"}", STRICT
                //)),
          //exactly(1))
      //.respond(
          //response()
          //.withStatusCode(200)
          //.withBody("Successfully registered new user")
            //);
  //}
  //public void createSecureLoginExpectationJSON() {
  //}

  @Test
  public void createSSLContext_FromTMF_ReturnsContext() throws Exception {
    assertNotNull(createSSLContext(sm.createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
  }

  @Test
  public void sendPOST_ToSite_ReturnResponse() throws Exception {
    createSecurePostExpectation();
    String response = conn.postWithSSL(SERVER_ADDRESS, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
    assertNotNull(response);
    assertEquals("Sent POST to mockServer", response);
    assertEquals(200, conn.getResponseCode());
  }

  @Test
  public void getWithSSL_Localhost_ReturnsResult() throws Exception {
    createSecureGetExpectation();
    String response = conn.getWithSSL(SERVER_ADDRESS, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
    assertNotNull(response);
    assertEquals("Successfully pinged server", response);
    assertEquals(200, conn.getResponseCode());
  }

  @Test
  public void sendGET_LocalhostWithLogin_ReturnResponse() throws Exception {
    createSecureLoginExpectation();
    String response = conn.getWithSSL(ADDRESS_PARAMS, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
    System.out.println(response);
    assertNotNull(response);
  }

  @Test
  public void sendPOST_LocalhostWithLogin_ReturnResponse() throws Exception {
    createSecureSignUpExpectation();
    String response = conn.sendPostCreds(SERVER_ADDRESS + "/register", params, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
    System.out.println(response);
    assertNotNull(response);
    assertEquals("Successfully registered new user", response);
    assertEquals(200, conn.getResponseCode());
  }

  //@Test
  //public void sendPOST_LocalhostWithLoginJSON_ReturnResponse {
    //createSecureSignUpExpectationJSON();
    //String response = conn.sendPostCredsJSON(SERVER_ADDRESS + "/register", params, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
    //System.out.println(response);
    //assertNotNull(response);
    //assertEquals("Successfully registered new user", response);
    //assertEquals(200, conn.getResponseCode());
  //}

}
