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
    byte[] res = cipher.encryptWithHeader( "This is the plaintext".getBytes(cipher.UTF_8), cipher.genIV(), null, cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_SaltPlaintext_ReturnAESCiphertext() throws Exception {
    byte[] salt = cipher.genSalt();
    byte[] res = cipher.encryptWithHeader( cipher.genPswdHash("This is the plaintext", salt), cipher.genIV(), salt, cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
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

    byte[] plaintext = "This is the plaintext".getBytes(cipher.UTF_8);
    byte[] iv = cipher.genIV();
    SecretKey key = cipher.genKey();

    cipher.setAll(iv, null, key);

    byte[] ciphertextWithIV = cipher.encrypt(plaintext, iv, key);


    System.out.println(" In Unit Test ");
    System.out.println("IV: " + Arrays.toString(iv));
    //System.out.println("Key: " + Arrays.toString(iv));
    System.out.println("Key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
    System.out.println("ciphertextWithIV: " + Arrays.toString(ciphertextWithIV));
    String res = cipher.decryptIV(ciphertextWithIV, key);

    assertNotNull(res, "Decrypted plaintext should not be empty");
    assertEquals("This is the plaintext", res, "Decrypted plaintext should equal the original plaintext");
  }

  //@Test 
  //public void decrypt_SaltCiphertext_ReturnPlaintext() throws Exception {
    //cipher.setAll(cipher.genIV(), cipher.genSalt(), cipher.genKey());
    //byte[] ciphertext = cipher.encrypt("This is the plaintext".getBytes(cipher.UTF_8), cipher.getIV(), cipher.getKey());

    //assertNotNull(cipher.decrypt(ciphertext, cipher.getIV(), cipher.getKey()), "Decrypted plaintext should not be empty");
    //assertEquals("This is the plaintext", cipher.decrypt(ciphertext, cipher.getIV(), cipher.genSalt(), cipher.getKey()), "Decrypted plaintext should equal the original plaintext");
  //}

  //public void encrypt_IV_(){
  //}

  //public void decryptIV_(){
  //}

  //public void encrypt_IVSalt_(){
  //}

  //public void decryptIVSALT_(){
  //}

}
