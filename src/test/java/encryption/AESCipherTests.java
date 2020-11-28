package test.crypt.cipher.aes;

import static app.crypt.utils.CryptUtils.*;
import app.crypt.data.*;
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
  private AESCipher cipherIV = new AESCipher(CIPHER_MODE.IV_ONLY);
  private AESCipher cipherSalt = new AESCipher(CIPHER_MODE.IV_SALT);

  @Test
  public void genKey_AES_ReturnAESKey() throws NoSuchAlgorithmException {
    assertNotNull(cipher.genKey(), "AES Key should be initialized");
  }

  @Test
  public void genKeyPswd_AES_ReturnAESKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
    assertNotNull(cipherSalt.genPswdKey("This is the user password", cipherSalt.getSalt()), "AES Key should be initialized");
  }
  
  @Test
  public void encrypt_Plaintext_ReturnAESCiphertext() throws Exception {
    byte[] res = cipherIV.encrypt("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", bytesToString(res), "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_IVPlaintext_ReturnAESCiphertext() throws Exception {
    String res = cipherIV.encryptWithHeader("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_SaltPlaintext_ReturnAESCiphertext() throws Exception {
    String res = cipherSalt.encryptWithHeader( bytesToString(cipherSalt.genPswdHash("This is the plaintext", cipherSalt.getSalt())) );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  @Test
  public void decrypt_Ciphertext_ReturnPlaintext() throws Exception {
    byte[] ciphertext = cipher.encrypt("This is the plaintext");
    assertNotNull(cipher.decrypt(ciphertext), "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", cipher.decrypt(ciphertext), "Decrypted plaintext should equal the original plaintext");
  }

  @Test 
  public void decrypt_IVCiphertext_ReturnPlaintext() throws Exception {
    String res = cipherSalt.decryptWithHeader(cipherSalt.encryptWithHeader("This is the plaintext"));
    assertNotNull(res, "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", res, "Decrypted plaintext should equal the original plaintext");
  }

  @Test 
  public void decrypt_SaltCiphertext_ReturnPlaintext() throws Exception {
    AESCipher cipherSaltPass = new AESCipher("password");
    String ciphertext = cipherSaltPass.encryptWithHeader("This is the plaintext");
    assertNotNull(cipherSaltPass.decryptWithHeader(ciphertext), "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", cipherSaltPass.decryptWithHeader(ciphertext), "Decrypted plaintext should equal the original plaintext");
  }
  
}
