package app.crypt.utils;

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

  public String encrypt(String plaintext) { 
    return "This is not the plaintext";
  }

}
