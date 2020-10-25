package crypt.test;
import crypt.utils.*;

//import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.*; 
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;



public class CryptUtilsTests {
private static final String ENCODING="UTF-8";

  @Test
  void genRandomNonce_ShouldReturnRN_WhenCalled() {
    int bytes=16;
    byte[] nonce = crypt.utils.CryptUtils.getRandomNonce(bytes);
    assertTrue((nonce != null), "Random nonce should not be null");
    }

  @Test
  void genKey_ShouldReturnAESKey_WhenCalled() throws NoSuchAlgorithmException{
    int size = 256;
    SecretKey key = crypt.utils.CryptUtils.getAESKey(size);
    assertTrue((key != null), "AES Key should not be null");

  }

  void getAESKeyFromPassword_ShouldReturnAESKey_WhenCalled() throws NoSuchAlgorithmException, InvalidKeySpecException {
    char[] password = "Password".toCharArray(); 
    byte[] salt = new byte[8];
    try { 
      salt = "Salt".getBytes(ENCODING); 
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }

    SecretKey key = crypt.utils.CryptUtils.getAESKeyFromPassword(password, salt);
    assertTrue(( key != null), "AES Key should not be null");
  }

  public static void main() {
/*
		String plainText = "SELECT * FROM USERS WHERE acct_number LIKE '' AND password LIKE ''";
		System.out.println("Original Text  : " + plainText);
        SecretKey key = Encryption.genKey();
        byte[] IV = Encryption.genIV();
        byte[] cipherText = encrypt(plainText.getBytes(), key,  IV);
        System.out.println("Encrypted Text : "+Base64.getEncoder().encodeToString(cipherText));
        
        String decryptedText = decrypt(cipherText,key, IV);
        System.out.println("DeCrypted Text : "+ decryptedText);
        */
  } 
} 
