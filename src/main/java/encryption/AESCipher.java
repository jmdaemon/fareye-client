package app.crypt.cipher.aes;

import app.crypt.utils.*;

import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESCipher extends CryptUtils {

  public AESCipher() {

  }

  private final int AES_KEY_LENGTH = 256;

  public SecretKey genKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(AES_KEY_LENGTH, SecureRandom.getInstanceStrong());
    return keyGen.generateKey();
  }

  public String encrypt(String plaintext) { 
    return "This is not the plaintext";
  }
}
