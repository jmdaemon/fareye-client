package test.crypt.cipher.aes;

import app.crypt.utils.*;
import app.crypt.cipher.aes.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESCipherTests {
  private AESCipher cipher = new AESCipher();

  @Test
  public void genKey_AES_ReturnAESKey() throws NoSuchAlgorithmException {
    assertNotNull(cipher.genKey(), "AES Key should be initialized");
  }
  
  @Test
  public void encrypt_Plaintext_ReturnAESCiphertext() {
    String res = cipher.encrypt("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }
}
