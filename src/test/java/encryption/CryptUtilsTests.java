package test.crypt;

import app.crypt.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class CryptUtilsTests {

  private CryptUtils cutil = new CryptUtils();

  public void genKey_AES_ReturnAESKey() throws NoSuchAlgorithmException {
    assertNotNull(cutil.genAESKey(), "AES Key should be initialized");
  }

  public void genKey_RSA_ReturnRSAKeyPair() throws NoSuchAlgorithmException {
    KeyPair RSAKey = cutil.genRSAKey();
    assertNotNull(RSAKey, "RSA Key should be initialized");
    assertNotNull(RSAKey.getPrivate(), "KeyPair should have a private key");
    assertNotNull(RSAKey.getPublic(), "KeyPair should have a public key");
  }

  public void genSalt() {
    assertNotNull(cutil.genSalt(), "Salt should be initialized");
  }

  public void genIV() {
    assertNotNull(cutil.genIV(), "IV should be initialized");
  }

  public void encrypt_Plaintext_ReturnCiphertext() {
    CryptUtils cutil =  new CryptUtils();
    String res = cutil.encrypt("This is the plaintext");
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", res, "Ciphertext should not equal plaintext");
  }

  public void decrypt_(){
  }

  //public void encrypt_Salt(){
  //}

  //public void decrypt_Salt_(){
  //}

  //public void encrypt_IV_(){
  //}

  //public void decryptIV_(){
  //}

  //public void encrypt_IVSalt_(){
  //}

  //public void decryptIVSALT_(){
  //}

  //public void encodeBase64_(){
  //}

  //public void decodeBase64_(){
  //}

  //public void encodeBase64_Salt(){
  //}

  //public void decodeBase64_Salt(){
  //}

  //public void encodeBase64_IV(){
  //}

  //public void decodeBase64_IV(){
  //}

  //public void encodeBase64_IVSalt(){
  //}

  //public void decodeBase64_IVSalt(){
  //}

  //public void getKey_() {
  //}

  //public void getSalt_() {
  //}
  
  //public void getKey_FromKS() {
  //}

  //public void getSalt_FromKS() {
  //}



}
