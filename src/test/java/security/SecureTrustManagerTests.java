package test.security;
//package app.security;

import app.security.*;
import static app.security.SecureTrustManager.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

import java.security.cert.Certificate;
import java.security.KeyStore;

public class SecureTrustManagerTests {
  private static final String certPath = "load-der.crt";
  private SecureTrustManager stm;
  private Certificate certAuth;

  @BeforeEach
  public void setUp() throws Exception {
    this.certAuth = loadCertificates(certPath);
    this.stm = new SecureTrustManager();
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
}
