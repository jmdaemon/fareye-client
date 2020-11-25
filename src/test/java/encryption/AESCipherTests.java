package test.crypt.cipher.aes;

import app.crypt.utils.*;
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
    byte[] res = cipher.encrypt("This is the plaintext".getBytes(cipher.UTF_8), cipher.genIV(), cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_IVPlaintext_ReturnAESCiphertext() throws Exception {
    String res = cipher.encryptWithHeader( "This is the plaintext".getBytes(cipher.UTF_8), cipher.genIV(), null, cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_SaltPlaintext_ReturnAESCiphertext() throws Exception {
    byte[] salt = cipher.genSalt();
    String res = cipher.encryptWithHeader( cipher.genPswdHash("This is the plaintext", salt), cipher.genIV(), salt, cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  @Test
  public void decrypt_Ciphertext_ReturnPlaintext() throws Exception {
    cipher.setAll(cipher.genIV(), null, cipher.genKey());
    byte[] ciphertext = cipher.encrypt("This is the plaintext".getBytes(cipher.UTF_8), cipher.getIV(), cipher.getKey());

    assertNotNull(cipher.decrypt(ciphertext, cipher.getIV(), cipher.getKey()), "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", cipher.decrypt(ciphertext, cipher.getIV(), cipher.getKey()), "Decrypted plaintext should equal the original plaintext");
  }

  @Test 
  public void decrypt_IVCiphertext_ReturnPlaintext() throws Exception {
    SecretKey key = cipher.genKey();
    String ciphertextWithIV = cipher.encryptWithHeader("This is the plaintext".getBytes(cipher.UTF_8), cipher.genIV(), cipher.genSalt(), key);
    String res = cipher.decryptIV(ciphertextWithIV, key);

    assertNotNull(res, "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", res, "Decrypted plaintext should equal the original plaintext");
  }

  @Test 
  public void decrypt_SaltCiphertext_ReturnPlaintext() throws Exception {

    byte[] salt = cipher.genSalt();
    SecretKey aesKeyFromPswd = cipher.genPswdKey("password", salt);

    cipher.setAll(cipher.genIV(), salt, aesKeyFromPswd);

    byte[] plaintext = "This is the plaintext".getBytes(cipher.UTF_8);
    String ciphertext = cipher.encryptWithHeader(plaintext, cipher.getIV(), salt, aesKeyFromPswd);

    assertNotNull(cipher.decryptSalt("password", ciphertext), "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", cipher.decryptSalt("password", ciphertext), "Decrypted plaintext should equal the original plaintext");
  }
  
}
