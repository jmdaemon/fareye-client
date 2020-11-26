package app.crypt.data;

import app.crypt.utils.*;

import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Data extends CryptUtils {
  private static byte[] iv;
  private static byte[] salt;
  private static SecretKey key;

  public Data () { }

  public Data(byte[] iv, byte[] salt, SecretKey key) { 
    Data.iv = iv;
    Data.salt = salt;
    Data.key = key;
  }

  public static byte[] genHeader(byte[] ciphertext) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream(); 
    output.write(Data.iv);
    if (Data.salt != null) { output.write(Data.salt); }
    output.write(ciphertext);
    byte[] result = output.toByteArray();
    return result;
  }

  public static byte[] parseHeader(byte[] decodedCiphertext, int IV_LENGTH, int SALT_LENGTH) throws NoSuchAlgorithmException, InvalidKeySpecException {
    ByteBuffer bb = ByteBuffer.wrap(decodedCiphertext);
    byte[] iv = new byte[IV_LENGTH];
    bb.get(iv);
    iv = iv;

    byte[] salt = new byte[SALT_LENGTH];
    bb.get(salt);
    salt = salt;

    byte[] result = new byte[bb.remaining()];
    bb.get(result);
    return result;
  }

  public static String encodeBase64(byte[] ciphertext) {
    String result = Base64.getEncoder().encodeToString(ciphertext);
    return result;
  }

  public static byte[] decodeBase64(String ciphertext) {
    byte[] result = Base64.getDecoder().decode(ciphertext.getBytes(UTF_8));
    return result;
  }

  public static byte[] decodeCiphertext(String ciphertextWithHeader, int IV_LENGTH, int SALT_LENGTH) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] decodedCiphertext = decodeBase64(ciphertextWithHeader);
    byte[] result = parseHeader(decodedCiphertext, IV_LENGTH, SALT_LENGTH); 
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

  public static byte[] getIV()      { return Data.iv; } 
  public static byte[] getSalt()    { return Data.salt; } 
  public static SecretKey getKey()  { return Data.key; }


}
