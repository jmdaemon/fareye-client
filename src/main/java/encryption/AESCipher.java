package app.crypt.cipher.aes;

import app.crypt.utils.*;

import java.nio.ByteBuffer;
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

  public byte[] prefixIV(byte [] iv, byte[] ciphertext) {
    byte[] result = ByteBuffer.allocate(iv.length + ciphertext.length)
      .put(iv) 
      .put(ciphertext)
      .array();
    return result;
  }

  public byte[] prefixSalt(byte[] iv, byte[] salt, byte[] ciphertext) {
    byte[] result = ByteBuffer.allocate(iv.length + salt.length + ciphertext.length)
      .put(iv) 
      .put(salt) 
      .put(ciphertext) 
      .array();
    return result;
  }

  private Cipher initCipherEncrypt(byte[] iv, SecretKey key) throws Exception {
    Cipher result = Cipher.getInstance(AES_ALGORITHM);
    result.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    return result;
  }

  private Cipher initCipherDecrypt(byte[] iv, SecretKey key) throws Exception {
    Cipher result = Cipher.getInstance(AES_ALGORITHM);
    result.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    return result;
  }

  public byte[] encrypt(byte[] plaintext, byte[] iv, SecretKey key) throws Exception { 
    Cipher cipher = initCipherEncrypt(iv, key);
    byte[] result = cipher.doFinal(plaintext);
    return result;
  }

  public byte[] encryptWithIV(byte[] plaintext, byte[] iv, SecretKey key) throws Exception {
    byte[] ciphertext = encrypt(plaintext, iv, key);
    byte[] result = prefixIV(iv, ciphertext);
    return result;
    }

  public byte[] encryptWithSalt(byte[] pswdHash, byte[] iv, byte[] salt, SecretKey key) throws Exception { 
    byte[] ciphertext = encrypt(pswdHash, iv, key);
    byte[] result = prefixSalt(iv, salt, ciphertext);
    return result;
  }

  public String decrypt(byte[] ciphertext, byte[] iv, SecretKey key) throws Exception { 
    Cipher cipher = initCipherDecrypt(iv, key);
    byte[] result = cipher.doFinal(ciphertext);
    return new String(result, UTF_8);
  }
  
}
