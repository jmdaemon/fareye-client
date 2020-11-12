package crypt.test.AES;
import crypt.AES.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CryptAESTests {
  private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
  private static final int TAG_LENGTH_BIT = 128;
  private static final int IV_LENGTH_BYTE = 12;
  private static final int AES_KEY_BIT = 256;

  private static final String ENCODING = "UTF-8";

  void encrypt_ShouldReturnCiphertext_WhenGivenParameters() {
    
  }

  void encryptWithPrefixIV_ShouldReturnCTWithIV_WhenGivenParameters() throws Exception {
    byte[] plainText = "Hello, World!".getBytes(ENCODING);
    SecretKey key = CryptUtils.getAESKey(AES_KEY_BIT); // AES 256 bits (32 bytes) key
    byte[] iv = CryptUtils.getRandomNonce(IV_LENGTH_BYTE); // AES-GCM needs IV 96-bit (12 bytes)

    byte[] cipherText = CryptAES.encryptWithPrefixIV(plainText, key, iv);  
    assert(plainText != cipherText);

    byte[] decryptedText = (CryptAES.decryptWithPrefixIV(cipherText, key)).getBytes(ENCODING);
    assert(decryptedText != cipherText);
  }

public static void main(String[] args) {
  }
}
