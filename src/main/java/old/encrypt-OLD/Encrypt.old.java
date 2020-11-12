import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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

// TODO: Create Keystore, Keystore methods and tests.

public class CryptoUtils 
{
//	private static byte[] data;
	public static SecretKey key;
	public static SecretKeySpec keySpec;
	public static IvParameterSpec ivSpec;
	public static byte[] IV = new byte[16];
	
	public static SecretKey genKey() throws NoSuchAlgorithmException { //Assert that an AES key is generated
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		SecretKey genKey = keyGenerator.generateKey();
    key = genKey;

		return key;
	}

    public static SecretKey getAESKey() throws NoSuchAlgorithmException { // 256 bits AES secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }

    public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException { 
        // AES key derived from a password
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        // iterationCount = 65536 
        // keyLength = 256 
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

	public static byte[] genIV() { // Assert that an Initialization Vector is returned
        // byte[] genIV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        // IV = genIV;
        // IV = genIV;
        return IV;
	}

  
  public static byte[] getRandomNonce(int size) { // 16 bytes IV
        byte[] nonce = new byte[size];
        new SecureRandom().nextBytes(nonce);
        return nonce;
  }
	
	public static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception { // Assert that the plaintext is encrypted
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES"); 
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec); //Initialize Cipher for ENCRYPT_MODE
        byte[] cipherText = cipher.doFinal(plaintext);
        
        return cipherText;
  }
	
	public static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception { 
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES"); IvParameterSpec ivSpec = new IvParameterSpec(IV); 
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec); //Initialize Cipher for DECRYPT_MODE
        byte[] decryptedText = cipher.doFinal(cipherText);
        
        return new String(decryptedText); // Assert that the ciphertext is decrypted into original plaintext
  }
	
	public static SecretKey getKey() 			      { return key; }
	public static byte[] getIV() 				        { return IV; }
	public static IvParameterSpec getIVSpec() 	{ return ivSpec; }
	public static SecretKeySpec getKeySpec() 		{ return keySpec;}
	
	public static void main(String[] args) throws Exception {
  }
}


