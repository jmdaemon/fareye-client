package test.security;

import static app.security.SecureTrustManager.*;
import app.security.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.KeyStore;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.socket.PortFactory;
import org.mockserver.socket.tls.KeyStoreFactory;

public class SecureTrustManagerTests {
  private static ClientAndServer mockServer;

  private static final String certPath = "config/keytool/certs/server/server-cert.pem";
  private SecureTrustManager stm;
  private Certificate certAuth;

  @BeforeAll
  public static void startMockServer() {
      HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
      mockServer = ClientAndServer.startClientAndServer(PortFactory.findFreePort());
  } 

  @BeforeEach
  public void setUp() throws Exception {
    this.certAuth = loadCertificates(certPath);
    this.stm = new SecureTrustManager();
  }

  @AfterAll
  public static void stopMockServer() {
      mockServer.stop();
  }

  @Test
  public void loadCertificates_FromFile_ReturnsCertAuth() throws Exception {
    Certificate newCertAuth = stm.loadCertificates(certPath);
    assertNotNull(newCertAuth);
  }

  @Test
  public void createKeyStore_FromCert_ReturnsKeyStore() throws Exception {
    KeyStore keyStore = stm.createKeyStore(certAuth);
    assertNotNull(keyStore);
  }

  @Test
  public void createTrustManager_FromKeyStore_ReturnsTMF() throws Exception {
    KeyStore keyStore = stm.createKeyStore(certAuth);
    TrustManagerFactory tmf = createTrustManager(keyStore);
    assertNotNull(tmf.getTrustManagers());
  }

  @Test
  public void createSSLContext_FromTMF_ReturnsContext() throws Exception {
    KeyStore keyStore = stm.createKeyStore(certAuth);
    TrustManagerFactory tmf = createTrustManager(keyStore);
    SSLContext context = createSSLContext(tmf);
    assertNotNull(context);
  }
}
