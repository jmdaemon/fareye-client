package app.crypt.cipher.aes;

import app.crypt.utils.*;
import app.crypt.data.*;

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
  private final int ITERATION_COUNT = 65536;
  private static final int AES_KEY_LENGTH = 256;

  private Data data;

  public AESCipher() { }

  public static SecretKey genKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(AES_KEY_LENGTH, SecureRandom.getInstanceStrong());
    return keyGen.generateKey();
  }

  public byte[] genPswdHash(String pswd) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeySpec spec = new PBEKeySpec(pswd.toCharArray(), getSalt(), ITERATION_COUNT, AES_KEY_LENGTH);
    SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
    byte[] result = factory.generateSecret(spec).getEncoded();
    return result;
  }

  public SecretKey genPswdKey(String pswd) throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecretKey result = new SecretKeySpec(genPswdHash(pswd), "AES");
    return result;
  }
  
  private Cipher initCipher(int cipherMode, Data data) throws Exception {
    Cipher result = Cipher.getInstance(AES_ALGORITHM);
    result.init(cipherMode, data.key, new GCMParameterSpec(TAG_LENGTH_BIT, data.iv));
    return result;
  }

  public byte[] encrypt(byte[] plaintext) throws Exception { 
    Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, this.data);
    byte[] result = cipher.doFinal(plaintext);
    return result;
  }

  public String encryptWithHeader(byte[] plaintext) throws Exception {
    byte[] ciphertext = encrypt(plaintext);
    byte[] result = data.genHeader(ciphertext);
    return data.encodeBase64(result);
    }

  public String decrypt(byte[] ciphertext) throws Exception { 
    Cipher cipher = initCipher(Cipher.DECRYPT_MODE, this.data);
    byte[] result = cipher.doFinal(ciphertext);
    return new String(result, UTF_8);
  }

  public String decryptIV(String ciphertextWithIV) throws Exception { 
    byte[] ciphertext = data.decodeCiphertext(ciphertextWithIV);
    String result = decrypt(ciphertext);
    return result;
  }

  public String decryptSalt(String pswd, String ciphertextWithHeader, Data data) throws Exception {
    byte[] ciphertext = data.decodeCiphertext(ciphertextWithHeader, IV_LENGTH, SALT_LENGTH);
    data.setKey(genPswdKey(pswd, data.getSalt()));
    String result = decrypt(ciphertext, data);
    return result;
  }

  public static Data createDataIV() throws NoSuchAlgorithmException { 
  return new Data(AESCipher.genIV(), null, AESCipher.genKey());
  }

  public static Data createDataSalt() throws NoSuchAlgorithmException {
    return new Data(AESCipher.genIV(), AESCipher.genSalt(), AESCipher.genKey());
  }

  public byte[] getSalt() { return data.getSalt(); }
}
