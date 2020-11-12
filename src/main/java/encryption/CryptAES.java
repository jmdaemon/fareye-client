package crypt.AES;
import crypt.utils.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CryptAES { 
  private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
  private static final int TAG_LENGTH_BIT = 128;
  private static final int IV_LENGTH_BYTE = 12;
  private static final int AES_KEY_BIT = 256;

  private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception { // AES-GCM needs GCMParameterSpec 
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText; 
    }

    public static byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws Exception { // prefix IV length + IV bytes to cipher text
      byte[] cipherText = encrypt(pText, secret, iv);
      byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
              .put(iv)
              .put(cipherText)
              .array();
      return cipherTextWithIv;
    }

    public static String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {
      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
      cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
      byte[] plainText = cipher.doFinal(cText);
      return new String(plainText, UTF_8);
    }

    public static String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {
      ByteBuffer bb = ByteBuffer.wrap(cText);
      byte[] iv = new byte[IV_LENGTH_BYTE];
      bb.get(iv); //bb.get(iv, 0, iv.length);

      byte[] cipherText = new byte[bb.remaining()];
      bb.get(cipherText);

      String plainText = decrypt(cipherText, secret, iv);
      return plainText;
}

    public static String encryptWithSalt(byte[] pText, String password) throws Exception {
        byte[] salt = crypt.utils.CryptoUtils.getRandomNonce(SALT_LENGTH_BYTE);
        byte[] iv = crypt.utils.CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);

        SecretKey aesKeyFromPassword = crypt.utils.CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

        byte[] cipherText = cipher.doFinal(pText);
        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                .put(iv) // prefix IV and Salt to cipher text
                .put(salt)
                .put(cipherText)
                .array();
        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
    }

    private static String decrypt(String cText, String password) throws Exception {
        byte[] decode = Base64.getDecoder().decode(cText.getBytes(UTF_8));
        ByteBuffer bb = ByteBuffer.wrap(decode);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);

        byte[] salt = new byte[SALT_LENGTH_BYTE];
        bb.get(salt);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);
        // TODO: Obtain SecretKey from Java Keystore

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cipherText);

        return new String(plainText, UTF_8);

    }

public static void main(String[] args) { 

  }
}
