package app.crypt.cipher.aes;

import app.crypt.utils.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESCipher extends CryptUtils {

  private static final String ALGORITHM = "AES/GCM/NoPadding";
  private static final int TAG_LENGTH_BIT = 128;
  private final int AES_KEY_LENGTH = 256;

  public AESCipher() {

  }

  public SecretKey genKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(AES_KEY_LENGTH, SecureRandom.getInstanceStrong());
    return keyGen.generateKey();
  }

  public byte[] encrypt(byte[] plaintext, SecretKey key, byte[] iv) throws Exception { 
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    byte[] result = cipher.doFinal(plaintext);
    return result;
  }
}
