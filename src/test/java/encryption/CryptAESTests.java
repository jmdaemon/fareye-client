package crypt.test.AES;
//import bin.pkg.crypt.AES.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CryptAESTests {
  private static final String ENCODING = "UTF-8";

  void encrypt_ShouldReturnCiphertext_WhenGivenParameters() {
    
  }

  void encryptWithPrefixIV_ShouldReturnCTWithIV_WhenGivenParameters() throws Exception {
    byte[] plainText = "Hello, World!".getBytes(ENCODING);
    SecretKey key = crypt.CryptUtils.getAESKey(16);

    byte[] cipherText = crypt.AES.encryptWithPrefixIV(plainText, key);  


  }
public static void main(String[] args) {

  //String OUTPUT_FORMAT = "%-30s:%s";

  //String pText = "Hello World AES-GCM, Welcome to Cryptography!";

  //// encrypt and decrypt need the same key.
  //// get AES 256 bits (32 bytes) key
  //SecretKey secretKey = CryptUtils.getAESKey(AES_KEY_BIT);

  //// encrypt and decrypt need the same IV.
  //// AES-GCM needs IV 96-bit (12 bytes)
  //byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);

  //byte[] encryptedText = EncryptorAesGcm.encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);

  //System.out.println("\n------ AES GCM Encryption ------");
  //System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
  //System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));
  //System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", CryptoUtils.hex(iv)));
  //System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", CryptoUtils.hex(encryptedText)));
  //System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));

  //System.out.println("\n------ AES GCM Decryption ------");
  //System.out.println(String.format(OUTPUT_FORMAT, "Input (hex)", CryptoUtils.hex(encryptedText)));
  //System.out.println(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));
  //System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));

  //String decryptedText = EncryptorAesGcm.decryptWithPrefixIV(encryptedText, secretKey);

  //System.out.println(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));
  }
}
