package app.crypt.utils;

import java.util.Arrays;

import java.security.SecureRandom;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CryptUtils {
  private String plaintext;
  private final int IV_LENGTH = 12;
  private final int SALT_LENGTH = 16;

  public static final Charset UTF_8 = StandardCharsets.UTF_8;

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

}
