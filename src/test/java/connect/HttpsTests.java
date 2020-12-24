package app.connect;

import static app.security.SecureTrustManager.*;
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

  //private static final String certPath = "config/keytool/certs/server/server-cert.pem";
  //private static final String certPath = "config/keytool/ca/cacert.pem";
  private static final String clientCert = "config/keytool/certs/client/client-cert.pem";
  private static final String serverCert = "config/keytool/certs/server/server-cert.pem";
  private static final String caCert = "config/keytool/ca/cacert.pem";

  private SecureTrustManager stm;
  private Certificate certAuth;

  //private static final char[] PASSWORD="password".toCharArray();
  private static final char[] PASSWORD="password".toCharArray();

  private static final String getFrom = "http://webcode.me";
  private static final String postTo  = "https://httpbin.org/post";

  //@BeforeAll
  @BeforeEach
  //public static void startMockServer() {
  public void startMockServer() {
      //HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
      //HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory().sslContext().getSocketFactory());
      //mockServer = ClientAndServer.startClientAndServer(443);
      //System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,TLSv1.3");
      //System.setProperty("https.protocols", "TLSv1");
      //mockServer = ClientAndServer.startClientAndServer("arch", 1080);
      //mockServer = startClientAndServer("arch", 1080);
      mockServer = startClientAndServer(1080);
      //mockServer = createServer().startClientAndServer();
      //mockServer = ClientAndServer.startClientAndServer(PortFactory.findFreePort());
  } 

  @BeforeEach
  public void setUp() throws Exception {
    //this.certAuth = loadCertificates(certPath);
    this.certAuth = loadCertificates(caCert);
    this.stm = new SecureTrustManager();
  } 

  //@AfterAll
  @AfterEach
  //public static void stopMockServer() {
  public void stopMockServer() {
      mockServer.stop();
  } 

  public void createSecurePingExpectation() {
    //new ClientAndServer("localhost", 1080)
    //new ClientAndServer("localhost", 443)
    //new ClientAndServer("server", 1080)
    //new ClientAndServer("arch", 1080)
    //new ClientAndServer(1080)
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

  //@BeforeEach
  //private void setUp() { 
    //this.values = new HashMap<String, String>() {{ 
      //put("name", "John Doe"); 
      //put ("occupation", "gardener"); 
    //}};
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
    //var objectMapper = new ObjectMapper(); 
    //String requestBody = objectMapper.writeValueAsString(values);
    //String response = conn.post(postTo, requestBody);
    ////System.out.println(response);
    //assertNotNull(response);
  //}

  @Test
  public void getWithSSL_Localhost_ReturnsResult() throws Exception {
    Https conn = new Https();
    //String result = conn.ping("https://google.com");
    createSecurePingExpectation();
    String KEYSTORE = "./config/keytool/client_truststore.jks";
    //String KEYSTORE = "./config/keytool/server_keystore.jks";

    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load((this.getClass().getClassLoader().getResourceAsStream(KEYSTORE)), PASSWORD);
    ks.setCertificateEntry("ca", certAuth);
    //ks.load(new FileInputStream("config/keytool/server_keystore.jks"), PASSWORD);
    //ks.load(new FileInputStream("./config/keytool/client_truststore.jks"), PASSWORD);
    //ks.load(new FileInputStream("./config/keytool/server_keystore.jks"), PASSWORD);

    //KeyStore keyStore = stm.createKeyStore(certAuth);
    //KeyStore keyStore = stm.createKeyStore(certPath);
    //Certificate client = loadCertificates(clientCert);
    //KeyStore keyStore = stm.createKeyStore(clientCert);
    //KeyStore keyStore = stm.createKeyStore(client);
    //TrustManagerFactory tmf = createTrustManager(ks);

    //KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyStore.getDefaultType());
    //KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");


    kmf.init(ks, PASSWORD);
    //TrustManagerFactory tmf = createTrustManager(keyStore);
    //TrustManagerFactory tmf = createTrustManager(ks);
    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
    //TrustManagerFactory tmf = TrustManagerFactory.getInstance(TMF_ALGORITHM);
    tmf.init(ks);

    //SSLContext context = createSSLContext(tmf);

    SSLContext context = SSLContext.getInstance("TLS");
    context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null); 
    //context.init();

    //String result = conn.getWithSSL("https://127.0.0.1:1080", context);
    //String result = conn.getWithSSL("https://127.0.0.1:1080", context);
    //String result = conn.getWithSSL("https://client:1080", context);
    String result = conn.getWithSSL("https://localhost:1080", context);
    //String result = conn.getWithSSL("https://arch:1080", context);
    //String result = conn.getWithSSL("https://127.0.0.1:1080", context);
    //String result = conn.getWithSSL("https://server:1080", context);
    //String result = conn.getWithSSL("https://localhost:1080", context);
    //String result = conn.getWithSSL("https://127.0.0.1:443", context);
    assertNotNull(result);
  }

}
