package test.crypt.cipher.rsa;

import app.crypt.utils.*;
import app.crypt.cipher.rsa.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class RSACipherTests {

  private RSACipher rsa = new RSACipher();

  @Test
  public void genKey_RSA_ReturnRSAKeyPair() throws NoSuchAlgorithmException {
    KeyPair RSAKey = rsa.genKeyPair();
    assertNotNull(RSAKey, "RSA Key should be initialized");
    assertNotNull(RSAKey.getPrivate(), "KeyPair should have a private key");
    assertNotNull(RSAKey.getPublic(), "KeyPair should have a public key");
  }
}
