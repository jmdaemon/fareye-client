package app.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
import java.security.KeyManagementException;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;

public class SecureTrustManager { 
  private static final String TMF_ALGORITHM = TrustManagerFactory.getDefaultAlgorithm(); // TrustManagerFactoryAlgorithm
  private static final String KEYSTORE_TYPE = KeyStore.getDefaultType();

  public static Certificate loadCertificates(String certPath) throws CertificateException, FileNotFoundException, IOException {
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    InputStream caInput = new BufferedInputStream(new FileInputStream(certPath));
    Certificate certAuth = null;
    try {
        certAuth = cf.generateCertificate(caInput);
        System.out.println("ca=" + ((X509Certificate) certAuth).getSubjectDN());
    } catch (Exception e ) {
      e.printStackTrace();
    } finally {
        caInput.close();
    }
    return certAuth;
  }

  public KeyStore createKeyStore(Certificate certAuth) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
    KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
    keyStore.load(null, null);
    keyStore.setCertificateEntry("ca", certAuth); 
    return keyStore;
  }

  public void createTrustManager(KeyStore keyStore) throws KeyStoreException, NoSuchAlgorithmException {
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TMF_ALGORITHM);
    tmf.init(keyStore);
  } 

  public void createSSLContext(TrustManagerFactory tmf) throws KeyManagementException, NoSuchAlgorithmException {
    SSLContext context = SSLContext.getInstance("TLS");
    context.init(null, tmf.getTrustManagers(), null); 
  }

  //public void connectWithSSL() { 
    //URL url = new URL("https://certs.cac.washington.edu/CAtest/");
    //HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
    //urlConnection.setSSLSocketFactory(context.getSocketFactory());
    //InputStream in = urlConnection.getInputStream();
    //copyInputStreamToOutputStream(in, System.out); 
  //} 
}
