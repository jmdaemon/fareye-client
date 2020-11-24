package app.crypt.cipher.aes;

import app.crypt.utils.*;

import java.util.Arrays;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


public class AESCipher extends CryptUtils {

  private static final String AES_ALGORITHM = "AES/GCM/NoPadding";
  private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
  private static final int TAG_LENGTH_BIT = 128;
  private final int AES_KEY_LENGTH = 256;

  private final int ITERATION_COUNT = 65536;

  private byte[] iv;
  private byte[] salt;
  private SecretKey key;

  public AESCipher() {

  }

  public SecretKey genKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(AES_KEY_LENGTH, SecureRandom.getInstanceStrong());
    return keyGen.generateKey();
  }

  public byte[] genPswdHash(String pswd, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeySpec spec = new PBEKeySpec(pswd.toCharArray(), salt, ITERATION_COUNT, AES_KEY_LENGTH);
    SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
    byte[] result = factory.generateSecret(spec).getEncoded();
    return result;
  }

  public SecretKey genPswdKey(String pswd, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] pswdHash = genPswdHash(pswd, salt);
    SecretKey result = new SecretKeySpec(pswdHash, "AES");
    return result;
  }

  public byte[] genHeader(byte[] iv, byte[] salt, byte[] ciphertext) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream(); 
    output.write(iv);
    if (salt != null) { output.write(salt); }
    output.write(ciphertext);
    byte[] result = output.toByteArray();
    return result;
  }

  public byte[] parseHeader(byte[] ciphertextWithPrefix, String pswd) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] iv = Arrays.copyOfRange(ciphertextWithPrefix, 0, IV_LENGTH);
    byte[] salt = null;
    SecretKey key = null;

    if (IV_LENGTH + SALT_LENGTH < ciphertextWithPrefix.length) {
      salt = Arrays.copyOfRange(ciphertextWithPrefix, IV_LENGTH, SALT_LENGTH);
    } 

    if (pswd != "") {
      key = genPswdKey(pswd, salt);
    } 

    setAll(iv, salt, key);
    byte[] result = Arrays.copyOfRange(ciphertextWithPrefix, SALT_LENGTH, ciphertextWithPrefix.length);
    return result;
  }

  public byte[] headerIV(byte[] ciphertextWithPrefix) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] iv = Arrays.copyOfRange(ciphertextWithPrefix, 0, IV_LENGTH);
    byte[] result = Arrays.copyOfRange(ciphertextWithPrefix, IV_LENGTH, ciphertextWithPrefix.length - 1);
    return result;
  }

  public byte[] headerSalt(byte[] ciphertextWithPrefix, String pswd) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] iv = Arrays.copyOfRange(ciphertextWithPrefix, 0, IV_LENGTH);
    byte[] salt = Arrays.copyOfRange(ciphertextWithPrefix, IV_LENGTH, SALT_LENGTH);
    SecretKey key = genPswdKey(pswd, salt);
    setAll(iv, salt, key);
    
    byte[] result = Arrays.copyOfRange(ciphertextWithPrefix, SALT_LENGTH, ciphertextWithPrefix.length);
    return result;
  }

  public byte[] headerBB(byte[] ciphertextWithPrefix, byte[] iv)  {
      ByteBuffer bb = ByteBuffer.wrap(ciphertextWithPrefix);
      //iv = new byte[IV_LENGTH];
      bb.get(iv); //bb.get(iv, 0, iv.length);
      this.iv = iv;

      byte[] result = new byte[bb.remaining()];
      bb.get(result);
      return result;

  }
  
  public String encodeBase64(byte[] ciphertext) {
    String result = Base64.getEncoder().encodeToString(ciphertext);
    return result;
  }

  public byte[] decodeBase64(String ciphertext) {
    byte[] result = Base64.getDecoder().decode(ciphertext.getBytes(UTF_8));
    return result;
  }

  private Cipher initCipher(int cipherMode, byte[] iv, SecretKey key) throws Exception {
    Cipher result = Cipher.getInstance(AES_ALGORITHM);
    result.init(cipherMode, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    return result;
  }

  public byte[] encrypt(byte[] plaintext, byte[] iv, SecretKey key) throws Exception { 
    Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, iv, key);
    byte[] result = cipher.doFinal(plaintext);
    return result;
  }

  public String encryptWithHeader(byte[] plaintext, byte[] iv, byte[] salt, SecretKey key) throws Exception {
    byte[] ciphertext = encrypt(plaintext, iv, key);
    byte[] result = genHeader(iv, salt, ciphertext);
    return encodeBase64(result);
    }

  // TODO: Update documentation with information from current state
  // Assume key is not generated from password
  public String decrypt(byte[] ciphertext, byte[] iv, SecretKey key) throws Exception { 
    Cipher cipher = initCipher(Cipher.DECRYPT_MODE, iv, key);
    byte[] result = cipher.doFinal(ciphertext);
    return new String(result, UTF_8);
  }

  public void testPrint(byte[] decodedCiphertext, byte[] iv, byte[] salt, SecretKey key) {
    // Formatting
    String IV = Arrays.toString(iv);
    String SALT = Arrays.toString(salt);
    String DECODED_CIPHERTEXT = Arrays.toString(decodedCiphertext);
    String KEY = Base64.getEncoder().encodeToString(key.getEncoded());

    System.out.println(" In AES Cipher ");
    System.out.println("IV: " + IV);
    System.out.println("Salt: " + SALT);
    System.out.println("Key: " + KEY);
    System.out.println("Decoded Ciphertext: " + DECODED_CIPHERTEXT);
  }

  // Test should not be aware of implementation details
  // Assume key is not generated from password
  public String decryptIV(String ciphertextWithIV, SecretKey key) throws Exception { 
    byte[] decodedCiphertext = decodeBase64(ciphertextWithIV);

    ByteBuffer bb = ByteBuffer.wrap(decodedCiphertext);
    byte[] iv = new byte[IV_LENGTH];
    bb.get(iv);
    byte[] salt = new byte[SALT_LENGTH];
    bb.get(salt);
    byte[] ciphertext = new byte[bb.remaining()];
    bb.get(ciphertext);

    testPrint(ciphertext, iv, salt, key);
    String result = null;
    try {
    result = decrypt(ciphertext, iv, key);
    } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    return result;
  }

  // Assume key is generated from password
  //public String decryptSalt(String pswd, byte[] ciphertextWithHeader) throws Exception {
    //String result = decrypt(parseHeader(ciphertextWithHeader, pswd), iv, key);
    //return result;
  //}

  public String decryptWithHeader(byte[] ciphertextWithIV, String pswd) throws Exception { 
    String result = decrypt(parseHeader(ciphertextWithIV, pswd), iv, key);
    return result;
  }

  public void setIV(byte[] iv)      { this.iv = iv; }
  public void setSalt(byte[] salt)  { this.salt = salt; }
  public void setKey(SecretKey key) { this.key = key; }
  public void setAll(byte[] iv, byte[] salt, SecretKey key) {
    setIV(iv);
    setSalt(salt);
    setKey(key);
  }

  public byte[] getIV()     { return this.iv; } 
  public byte[] getSalt()   { return this.salt; } 
  public SecretKey getKey() { return this.key; }
  
}
