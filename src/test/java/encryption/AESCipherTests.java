package test.crypt.cipher.aes;

import app.crypt.utils.*;
import app.crypt.cipher.aes.*;


import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESCipherTests {
  private AESCipher aes = new AESCipher();

  @Test
  public void genKey_AES_ReturnAESKey() throws NoSuchAlgorithmException {
    assertNotNull(aes.genKey(), "AES Key should be initialized");
  }
}
