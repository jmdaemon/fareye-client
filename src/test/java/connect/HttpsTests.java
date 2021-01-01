package app.connect;

import static app.security.SSLManager.*;
import app.security.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

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

public class HttpsTests { 
  private static ClientAndServer mockServer;

  private SSLManager sm;
  private Certificate certAuth;

  private static final String CLIENT_KEYSTORE = "./config/keytool/client_truststore.jks";
  private static final String caCert = "config/keytool/ca/cacert.pem";
  private static final char[] PASSWORD="password".toCharArray();
  private static final String SERVER_ADDRESS = "https://localhost:1080";

  @BeforeAll
  public static void startMockServer() { mockServer = startClientAndServer(1080); } 

  @AfterAll
  public static void stopMockServer() { mockServer.stop(); } 

  @BeforeEach
  public void setUp() throws Exception {
    this.certAuth = loadCertificates(caCert);
    this.sm = new SSLManager();
  } 

  public void createSecureGetExpectation() {
    this.mockServer
      .withSecure(true)
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

  //public void createSecureLoginExpectation() {
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

  //@Test
  //public void sendPOST_ToSite_ReturnResponse() throws Exception {
    //Https conn = new Https(); 
    ////var objectMapper = new ObjectMapper(); 
    ////String requestBody = objectMapper.writeValueAsString(values);
    ////String response = conn.post(postTo, requestBody);
    ////System.out.println(response);
    //Keystore ks = createKeyStore(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth);
    //SSLContext context = createSSLContext(ks, PASSWORD);
    //conn.postWithSSL("http://localhost:1080");
    //assertNotNull(response);
  //}

  @Test
  public void getWithSSL_Localhost_ReturnsResult() throws Exception {
    Https conn = new Https();
    createSecureGetExpectation();
    //System.out.println("mockServer Response: " + result);
    assertNotNull(conn.getWithSSL(SERVER_ADDRESS, createSSLContext(createKeyStore(CLIENT_KEYSTORE, PASSWORD, certAuth), PASSWORD)));
  }
}
