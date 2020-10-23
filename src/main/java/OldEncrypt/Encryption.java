import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyStore;

public class Encryption 
{
//	private static byte[] data;
	public static SecretKey key;
	public static SecretKeySpec keySpec;
	public static IvParameterSpec ivSpec;
	public static byte[] IV = new byte[16];
	
	public static SecretKey genKey() throws NoSuchAlgorithmException {	 
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		// Generate Key
		SecretKey genKey = keyGenerator.generateKey();
		key = genKey;
		return key;
	}

	public static byte[] genIV() {
	// Generating IV.
        // byte[] genIV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        // IV = genIV;
        // IV = genIV;
        return IV;
	}
	
	public static byte[] encrypt (byte[] plaintext,SecretKey key, byte[] IV) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //Get Cipher Instance
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES"); //Create SecretKeySpec
            IvParameterSpec ivSpec = new IvParameterSpec(IV); 
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec); //Initialize Cipher for ENCRYPT_MODE
            byte[] cipherText = cipher.doFinal(plaintext); 
            
            return cipherText;
    }
	
	public static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //Get Cipher Instance
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES"); //Create SecretKeySpec
            
            IvParameterSpec ivSpec = new IvParameterSpec(IV); //Create IvParameterSpec
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec); //Initialize Cipher for DECRYPT_MODE
            byte[] decryptedText = cipher.doFinal(cipherText); 
            
            return new String(decryptedText);
    }
	
	// Getters 
	//public static SecretKey getKey() 			{ return key; }
	//public static byte[] getIV() 				{ return IV; }
	//public static IvParameterSpec getIVSpec() 	        { return ivSpec; }
	//public static SecretKeySpec getKeySpec() 		{ return keySpec;}
  
  public static KeyStore createKeyStore() throws Exception { 
    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    //KeyStore keyStore = KeyStore.getInstance("PKCS12");
    return keyStore;
  }
	
	public static void main(String[] args) throws Exception
	{
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
