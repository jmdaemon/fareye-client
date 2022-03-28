package app.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;

public class SSLManager {
  private static final String TMF_ALGORITHM = TrustManagerFactory.getDefaultAlgorithm();
  private static final String KEYSTORE_TYPE = KeyStore.getDefaultType();

  public static Certificate loadCertificates(String certPath) throws CertificateException, FileNotFoundException, IOException {
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    Certificate certAuth = cf.generateCertificate(new BufferedInputStream(new FileInputStream(certPath)));
    return certAuth;
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
