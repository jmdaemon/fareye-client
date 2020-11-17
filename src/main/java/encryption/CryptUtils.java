package app.crypt;

import java.util.Arrays;

import java.security.KeyPairGenerator;
import java.security.KeyPair;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptUtils {
  private String plaintext;
  private final int IV_LENGTH = 12;
  private final int SALT_LENGTH = 16;
  private final int AES_KEY_LENGTH = 256;
  private final int RSA_KEY_LENGTH = 2048;

  public CryptUtils() {
    this.plaintext = null;
  }

  private byte[] genRandomBytes(int len) {
    byte[] randomBytes = new byte[len];
    new SecureRandom().nextBytes(randomBytes);
    return randomBytes;
  }

  public byte[] genIV() {
    return genRandomBytes(IV_LENGTH);
  }

  public byte[] genSalt() {
    return genRandomBytes(SALT_LENGTH);
  }

  public SecretKey genAESKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(AES_KEY_LENGTH, SecureRandom.getInstanceStrong());
    return keyGen.generateKey();
  }

  public KeyPair genRSAKey() throws NoSuchAlgorithmException {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(RSA_KEY_LENGTH, SecureRandom.getInstanceStrong());
    return keyGen.genKeyPair();
  }

  public String encrypt(String plaintext) { 
    return "This is not the plaintext";
  }

}
