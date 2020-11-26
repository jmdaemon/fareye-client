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
  private final int AES_KEY_LENGTH = 256;

  public AESCipher() { }

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
  
  private Cipher initCipher(int cipherMode, byte[] iv, SecretKey key) throws Exception {
    Cipher result = Cipher.getInstance(AES_ALGORITHM);
    result.init(cipherMode, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    return result;
  }

  public byte[] encrypt(byte[] plaintext, Data data) throws Exception { 
    Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, data.getIV(), data.getKey());
    byte[] result = cipher.doFinal(plaintext);
    return result;
  }

  public String encryptWithHeader(byte[] plaintext, Data data) throws Exception {
    byte[] ciphertext = encrypt(plaintext, data);
    byte[] result = data.genHeader(ciphertext);
    return data.encodeBase64(result);
    }

  //// TODO: Update documentation with information from current state
  //// Assume key is not generated from password
  //public String decrypt(byte[] ciphertext, byte[] iv, SecretKey key) throws Exception { 
    //Cipher cipher = initCipher(Cipher.DECRYPT_MODE, iv, key);
    //byte[] result = cipher.doFinal(ciphertext);
    //return new String(result, UTF_8);
  //}

  //// Test should not be aware of implementation details
  //// Assume key is not generated from password
  //public String decryptIV(String ciphertextWithIV, SecretKey key) throws Exception { 
    //byte[] ciphertext = decodeCiphertext(ciphertextWithIV);
    //String result = decrypt(ciphertext, this.iv, key);
    //return result;
  //}

  //// Assume key is generated from password
  //public String decryptSalt(String pswd, String ciphertextWithHeader) throws Exception {
    //byte[] ciphertext = decodeCiphertext(ciphertextWithHeader);
    //String result = decrypt(ciphertext, this.iv, genPswdKey(pswd, this.salt));
    //return result;
  //}

  //public String decryptWithHeader(byte[] ciphertextWithIV, String pswd) throws Exception { 
    //String result = decrypt(parseHeader(ciphertextWithIV, pswd), iv, key);
    //return result;
  //}

}
