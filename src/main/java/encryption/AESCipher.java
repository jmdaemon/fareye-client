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
  private static final int ITERATION_COUNT = 65536;
  private static final int AES_KEY_LENGTH = 256;

  private Data data;

  public AESCipher() { }

  public static SecretKey genKey() throws NoSuchAlgorithmException {
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
    SecretKey result = new SecretKeySpec(genPswdHash(pswd, salt), "AES");
    return result;
  }
  
  private Cipher initCipher(int cipherMode) throws Exception {
    Cipher result = Cipher.getInstance(AES_ALGORITHM);
    result.init(cipherMode, getKey(), new GCMParameterSpec(TAG_LENGTH_BIT, getIV()));
    return result;
  }

  public byte[] encrypt(String plaintext) throws Exception { 
    Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
    byte[] result = cipher.doFinal(CryptUtils.stringToBytes(plaintext));
    return result;
  }

  public String encryptWithHeader(String plaintext) throws Exception {
    byte[] ciphertext = encrypt(plaintext);
    byte[] result = data.genHeader(ciphertext);
    return data.encodeBase64(result);
    }

  public String decrypt(byte[] ciphertext) throws Exception { 
    Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
    byte[] result = cipher.doFinal(ciphertext);
    return new String(result, UTF_8);
  }

  public String decryptIV(String ciphertextWithIV) throws Exception { 
    byte[] ciphertext = data.decodeCiphertext(ciphertextWithIV);
    String result = decrypt(ciphertext);
    return result;
  }

  public String decryptSalt(String pswd, String ciphertextWithHeader) throws Exception {
    byte[] ciphertext = data.decodeCiphertext(ciphertextWithHeader);
    data.setKey(genPswdKey(pswd, this.data.getSalt()));
    String result = decrypt(ciphertext);
    return result;
  }

  public void createDataIV() throws NoSuchAlgorithmException { 
    this.data = new Data(genIV(), null, genKey());
  }

  public void createDataSalt() throws NoSuchAlgorithmException {
    this.data = new Data(genIV(), genSalt(), genKey());
  }

  public void createData(String pswd) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] salt = genSalt();
    this.data = new Data (genIV(), salt, genPswdKey(pswd, salt));
  }
 
  public byte[] getSalt()   { return data.getSalt();  }
  public byte[] getIV()     { return data.getIV();    }
  public SecretKey getKey() { return data.getKey();   }

  public void setKey(SecretKey key) { data.setKey(key); }
}
