package app.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.KeyManagerFactory;
import java.security.KeyManagementException;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;
import java.security.UnrecoverableKeyException;

public class SSLManager { 
  private static final String TMF_ALGORITHM = TrustManagerFactory.getDefaultAlgorithm();
  private static final String KEYSTORE_TYPE = KeyStore.getDefaultType();

  public static Certificate loadCertificates(String certPath) throws CertificateException, FileNotFoundException, IOException {
    CertificateFactory cf = CertificateFactory.getInstance("X.509"); 
    Certificate certAuth = cf.generateCertificate(new BufferedInputStream(new FileInputStream(certPath)));
    return certAuth;
  }

  public static KeyStore createKeyStore(Certificate certAuth) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
    KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
    keyStore.load(null, null);
    keyStore.setCertificateEntry("ca", certAuth); 
    return keyStore;
  }

  public static TrustManagerFactory createTrustManager(KeyStore keyStore) throws KeyStoreException, NoSuchAlgorithmException {
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TMF_ALGORITHM);
    tmf.init(keyStore);
    return tmf;
  } 

  public static SSLContext createSSLContext(TrustManagerFactory tmf) throws KeyManagementException, NoSuchAlgorithmException {
    SSLContext context = SSLContext.getInstance("TLS");
    context.init(null, tmf.getTrustManagers(), null); 
    return context;
  }

  public static SSLContext initSSL(String certPath) throws Exception {
    Certificate cert = loadCertificates(certPath);
    KeyStore keyStore = createKeyStore(cert);
    TrustManagerFactory tmf = createTrustManager(keyStore);
    SSLContext context = createSSLContext(tmf);
    return context;
  }

  public static KeyStore createKeyStore(String clientKeystore, String keyStorePassword, Certificate certAuth) 
      throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
    KeyStore result = KeyStore.getInstance(KeyStore.getDefaultType());
    result.load((SSLManager.class.getResourceAsStream(clientKeystore)), keyStorePassword.toCharArray());
    result.setCertificateEntry("ca", certAuth);
    return result;
    }

  public static SSLContext createSSLContext(KeyStore keyStore, String password) 
      throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509"); 
    kmf.init(keyStore, password.toCharArray());
    tmf.init(keyStore);

    SSLContext result = SSLContext.getInstance("TLS");
    result.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null); 
    return result;
  } 
}