package app.crypt.datafactory;

import app.crypt.utils.*;
import app.crypt.cipher.aes.*;
import app.crypt.data.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class DataFactory {

  public static Data createDataIV() throws NoSuchAlgorithmException { 
  return new Data(AESCipher.genIV(), null, AESCipher.genKey());
}
  public static Data createDataSalt() throws NoSuchAlgorithmException {
  return new Data(AESCipher.genIV(), AESCipher.genSalt(), AESCipher.genKey());
}


}
