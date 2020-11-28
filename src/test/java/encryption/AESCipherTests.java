package test.crypt.cipher.aes;

import app.crypt.utils.*;
import app.crypt.data.*;
import app.crypt.datafactory.*;
import app.crypt.cipher.aes.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class AESCipherTests {
  private AESCipher cipher = new AESCipher();

  @Test
  public void genKey_AES_ReturnAESKey() throws NoSuchAlgorithmException {
    assertNotNull(cipher.genKey(), "AES Key should be initialized");
  }

  @Test
  public void genKeyPswd_AES_ReturnAESKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
    assertNotNull(cipher.genPswdKey("This is the user password", cipher.genSalt()), "AES Key should be initialized");
  }
  
  @Test
  public void encrypt_Plaintext_ReturnAESCiphertext() throws Exception {
    cipher.createDataIV();
    byte[] res = cipher.encrypt("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_IVPlaintext_ReturnAESCiphertext() throws Exception {
    cipher.createDataIV();
    String res = cipher.encryptWithHeader("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_SaltPlaintext_ReturnAESCiphertext() throws Exception {
    cipher.createDataSalt();
    String res = cipher.encryptWithHeader(cipher.genPswdHash("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  @Test
  public void decrypt_Ciphertext_ReturnPlaintext() throws Exception {
    cipher.createDataIV();
    byte[] ciphertext = cipher.encrypt("This is the plaintext");
    assertNotNull(cipher.decrypt(ciphertext), "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", cipher.decrypt(ciphertext), "Decrypted plaintext should equal the original plaintext");
  }

  @Test 
  public void decrypt_IVCiphertext_ReturnPlaintext() throws Exception {
    cipher.createDataSalt();
    String res = cipher.decryptIV(cipher.encryptWithHeader("This is the plaintext"));
    assertNotNull(res, "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", res, "Decrypted plaintext should equal the original plaintext");
  }

  @Test 
  public void decrypt_SaltCiphertext_ReturnPlaintext() throws Exception {
    cipher.createDataSalt();
    cipher.setKey(cipher.genPswdKey("password"));

    String ciphertext = cipher.encryptWithHeader("This is the plaintext");
    assertNotNull(cipher.decryptSalt("password", ciphertext), "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", cipher.decryptSalt("password", ciphertext), "Decrypted plaintext should equal the original plaintext");
  }
  
}
