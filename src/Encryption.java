import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * 
 * A utility class to hash passwords and check passwords vs hashed values. It uses a combination of hashing and unique
 * salt. The algorithm used is PBKDF2WithHmacSHA1 which, although not the best for hashing password (vs. bcrypt) is
 * still considered robust and <a href="https://security.stackexchange.com/a/6415/12614"> recommended by NIST </a>.
 * The hashed value has 256 bits.
 */


public class Encryption 
{
//	private static byte[] data;
	public static SecretKey key;
	public static SecretKeySpec keySpec;
	public static IvParameterSpec ivSpec;
	public static byte[] IV = new byte[16];
	
	public static SecretKey genKey() throws NoSuchAlgorithmException
	{	 KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		// Generate Key
		SecretKey genKey = keyGenerator.generateKey();
		key = genKey;
		return key;
	}

	public static byte[] genIV()
	{
		// Generating IV.
        // byte[] genIV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        // IV = genIV;
        // IV = genIV;
        return IV;
	}
	
	public static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        
        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);
        
        return cipherText;
    }
	
	public static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        //Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);
        
        return new String(decryptedText);
    }
	
	// Getters 
	public static SecretKey getKey() 			{ return key; }
	public static byte[] getIV() 				{ return IV; }
	public static IvParameterSpec getIVSpec() 	{ return ivSpec; }
	public static SecretKeySpec getKeySpec() 		{ return keySpec;}
	
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


