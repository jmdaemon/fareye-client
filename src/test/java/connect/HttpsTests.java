package app.connect;

import static app.security.SSLManager.*;
import app.security.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

//import java.util.Map; 
//import java.util.HashMap;
import java.io.FileInputStream;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.socket.PortFactory;
import org.mockserver.socket.tls.KeyStoreFactory;

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

public class HttpsTests { 
  private static ClientAndServer mockServer;

  private SSLManager sm;
  private Certificate certAuth;
  private Https conn;

  private static final String CLIENT_KEYSTORE = "config/keytool/client_truststore.jks";
  private static final String CA_CERT         = "config/keytool/ca/cacert.pem";
  private static final String PASSWORD        = "password";
  private static final String SERVER_ADDRESS  = "https://localhost:1080";
  //private static final String ADDRESS_PARAMS  = "https://localhost?username=112233&password=\"aabbcc\":1080";
  private static final String ADDRESS_PARAMS  = "https://localhost:1080/?username=112233&password=aabbcc";
  //private static final String ADDRESS_PARAMS  = "https://localhost:1080";

  @BeforeAll
  public static void startMockServer() { mockServer = startClientAndServer(1080); } 

  @AfterAll
  public static void stopMockServer() { mockServer.stop(); } 

  @BeforeEach
  public void setUp() throws Exception {
    this.certAuth = loadCertificates(CA_CERT);
    this.sm = new SSLManager();
    this.conn = new Https();
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
          //.withPath("/login")
          //.withHeaders(
            //header("Content-Type", "application/x-www-form-urlencoded"))
          //.withBody(
            //params(
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

  //public void createSecureLoginExpectationJSON() {
  //}

  //@Test
  //public void ping_Google_ReturnsResult() throws Exception {
    //Https conn = new Https();
    ////String result = conn.ping("https://google.com");
    //String result = conn.ping("127.0.0.1:1080");
    //assertNotNull(result);
  //}

  //@Test
  //public void sendGET_ToSite_ReturnResponse() throws Exception {
    //Https conn = new Https();
    //String response = conn.get(getFrom);
    //System.out.println(response);
    //assertNotNull(response);
  //}

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
    //Map<String, String> params = new HashMap<String, String>{{"username", "112233"}, {"password", "aabbcc"}};
    String response = conn.getWithSSL(ADDRESS_PARAMS, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD));
    assertNotNull(response);
  }
}
