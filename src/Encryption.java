import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

	private static SecretKeySpec secretKey;
	private static SecretKeySpec masterKey;
	private static byte[] key;
	private static byte[] iv;
	private static byte[] ciphertext;
	public static void setKey(String myKey) 
	{
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-256");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			secretKey = new SecretKeySpec(key, "AES");
		} 
		catch (NoSuchAlgorithmException e) { e.printStackTrace(); } 
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
	}
	
	public static void setMasterKey(String myKey) 
	{
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-256");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			masterKey = new SecretKeySpec(key, "AES");
		} 
		catch (NoSuchAlgorithmException e) { e.printStackTrace(); } 
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
	}
	
	public static String encrypt(String strToEncrypt, SecretKeySpec myKey) 
	{
		try
		{
			// setKey(secret);
			// secretKey = myKey;
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			AlgorithmParameters params = cipher.getParameters();
			iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			// System.out.println("Initialization Vector: " + iv);
			// byte[] ciphertext = cipher.doFinal(secretKey.getBytes("UTF-8"));
			ciphertext = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
			// System.out.println("The Encrypted CipherText: " + ciphertext);
			//System.out.println(secretKey);
			return Base64.getEncoder().encodeToString(ciphertext);
			/*
            FileOutputStream out = new FileOutputStream("Master-key");
            out.write(data);
            out.close();
            Path file = Paths.get("Master-key");
            Files.write(file, data, StandardOpenOption.APPEND);
            // File masDir= new File("Master-key");
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			 */ 
		} 
		catch (Exception e) { System.out.println("Error while encrypting: " + e.toString()); }
		return null;
	}

	public static String encrypt(String strToEncrypt) 
	{
		try
		{

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			AlgorithmParameters params = cipher.getParameters();
			iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			ciphertext = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(ciphertext);
		} 
		catch (Exception e) { System.out.println("Error while encrypting: " + e.toString()); }
		return null;
	}
	public static String decrypt(String strToDecrypt, String secret) 
	{
		try
		{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
			String plaintext = new String(cipher.doFinal(ciphertext), "UTF-8");
			// System.out.println(plaintext);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

			/*
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
			 */
		} 
		catch (Exception e) 
		{
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}


	public static void saveToFile(String fileName,
			BigInteger mod, BigInteger exp) throws IOException {
		ObjectOutputStream oout = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)));
		try {
			oout.writeObject(mod);
			oout.writeObject(exp);
		} catch (Exception e) { throw new IOException("Unexpected error", e); } finally { oout.close(); }
	}
	public static KeyPair createMasterKeys()
	{
		KeyPair masterKeys = RSA.generateKeyPair();
		return masterKeys;
	}

	public static byte[] transport(String data) throws ClassNotFoundException, IOException
	{
		/*	-=--=--=--=- Encryption -=--=--=--=-
		 *  We're going to encrypt our already encrypted data, which contains the key to decrypt the package, inside of another encrypted package.
		 *  Change of plans, instead of encrypted our package with RSA, we will use a second AES master key to encrypt and decrypt it.
		 *  Grab the AES MK in the Db, and decrypt out package.
		 *  Then we will interpret the plaintext, and do some tasks, or fetch the encrypted data on our database.
		 *  We will send this encrypted data back to the application, encrypted with the same AES-256 Encryption.
		 *  We will now decrypt the final layer of the data, and utilize this in our application.
		 *  
		 */

		// getSK will not return an actual value.
		// The key will be generatated later on from SecureRandom
		String transportData = Encryption.encrypt(data, secretKey);
		PublicKey pubKey = Encryption.getPubKey();
		byte[] encryptedData = RSA.encrypt(transportData, pubKey);
		//encryptCipher(transportData, "");
		return encryptedData;
	}
	
	public static byte[] send(String data)
	{
		String transportData = Encryption.encrypt(data, secretKey);
		
		
		/*
		 * FileInputStream pubFile = new FileInputStream("public.key");
		ObjectInput oi = new ObjectInputStream(pubFile);
		PublicKey pubKey = (PublicKey) oi.readObject();
		pubFile.close();
		return pubKey;
		 */
	}
	
	
	
	public static String receive(byte[] encryptedData, String secret) throws ClassNotFoundException, IOException
	{
		PrivateKey privKey = Encryption.getPrivKey();
		System.out.println(privKey.getEncoded());
		String unencryptedData = RSA.decrypt(encryptedData, privKey);
		String finalLayer = Encryption.decrypt(unencryptedData, secret);
		return finalLayer;
	}
	
	public static SecretKeySpec getSK() { return secretKey; }
	
	public static boolean write(KeyPair masterKey) throws IOException
	{
		FileOutputStream privOut = new FileOutputStream("public.key");
		ObjectOutput oo1 = new ObjectOutputStream(privOut);
		oo1.writeObject(masterKey.getPublic());        
		privOut.close();        

		// Save private key in File2
		FileOutputStream pubOut = new FileOutputStream("private.key");
		ObjectOutput oo2 = new ObjectOutputStream(pubOut);
		oo2.writeObject(masterKey.getPrivate());   
		pubOut.close();
		return true;
	}

	public static PublicKey getPubKey() throws IOException, ClassNotFoundException
	{
		FileInputStream pubFile = new FileInputStream("public.key");
		ObjectInput oi = new ObjectInputStream(pubFile);
		PublicKey pubKey = (PublicKey) oi.readObject();
		pubFile.close();
		return pubKey;
	}

	public static PrivateKey getPrivKey() throws IOException, ClassNotFoundException
	{
		FileInputStream privFile = new FileInputStream("private.key");
		ObjectInput oi = new ObjectInputStream(privFile);
		PrivateKey privKey = (PrivateKey) oi.readObject();
		privFile.close();
		return privKey;
	}
	
	public static byte[] getIV() { return iv; }
	
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException
	{
		/*
    	final String secretKey = "ssshhhhhhhhhhh!!!!";


        String originalString = "howtodoinjava.com";
        String encryptedString = Encryption.encrypt(originalString, secretKey) ;
        String decryptedString = Encryption.decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
		 */
		/*
    	String password = "aaacc";
    	securePassword(password);
    	if (Passwords.isExpectedPassword(password.toCharArray(), salt, "[B@1ff8b8f".getBytes()))
    		{
    			System.out.println(securePassword(password));	
    		}
    	else
    		{
    			System.out.println("failed"); 
    		}
		 */

		/*	The password will be hashed on the PHP / Server side. The password will be sent encrypted, then decrypted on the server.
		 * 
		 * 
		 * 
		 * 
		 */

		
		/* -=--=--=--=--=- Encryption -=--=--=--=--=-
		 * 
		 * Steps:
		 * 1) Take our precious data, and encrypt it in AES-256, inside of the Java application. Make sure to use unique keys, and also use unique IVs.
		 * 2) Encrypt the data, alongside the AES-256 secretKey, with an RSA 2048 Public key. Make sure to store this key, and also the private key.
		 * 3) Send our data, with the AES-256 key, to the PHP. 
		 * 4) The PHP grabs the private key from it's files, and unlocks the package.
		 * 5) The PHP then decrypts the AES-256 encrypted data, using the newly unlocked AES-256 key.
		 * 6) Interpret the results of the data. Fetch user data, or 
		 */
		final String secretKey = "ssshhhhhhhhhhh!!!!";
		Encryption.setKey(secretKey);
		String originalString = "howtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.comhowtodoinjava.coms";
		// String encryptedString = Encryption.encrypt(originalString, secretKey);
		setKey(secretKey);
		byte[] transporting = Encryption.transport(originalString);
		System.out.println("My encrypted Data: " + transporting);
		System.out.println("Simulating transport ... ");
		
		String unencryptedData = Encryption.receive(transporting, secretKey);
		System.out.println("My unencrypted Data: " + unencryptedData);
		
		
		/*
		final String secretKey = "ssshhhhhhhhhhh!!!!";


        String originalString = "howtodoinjava.com";
        String encryptedString = Encryption.encrypt(originalString, secretKey) ;
        
        
        
        KeyPair masterKey = Encryption.createMasterKeys();
		Encryption.write(masterKey);
		// PrivateKey privKey = masterKey.getPrivate();
		// PublicKey pubKey = masterKey.getPublic();
		
		PrivateKey privKey = Encryption.getPrivKey();
		PublicKey pubKey = Encryption.getPubKey();
		
		byte[] cipherText = RSA.encrypt(encryptedString, pubKey);
		
		System.out.println("Transporting ... ");
		
		String decryptedText = RSA.decrypt(cipherText, privKey);
        String decryptedString = Encryption.decrypt(decryptedText, secretKey) ;
        
        System.out.println("Decrypted at destination: " + decryptedText);
        System.out.println("Decrypted final layer: " + decryptedString);
        */
       /*
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        
		
		
		
		
		System.out.println("Before Encryption " + secretKey);
		
		System.out.println("After Encryption " + cipherText);
		
		System.out.println("After Decryption " + decryptedText);
		System.out.println(decryptedText);
		*/
		/*
		 * Make method to write key to file, then next day finish up everything, and finally do something with your life. 
		 */
		
		/*
		String hello = "Hello, world";
		System.out.println("Before Encryption " + hello);
		byte[] cipherText = RSA.encrypt(hello, pubKey);
		System.out.println("After Encryption " +cipherText);
		String decryptedText = RSA.decrypt(cipherText, privKey);
		System.out.println("After Decryption " + decryptedText);
		System.out.println(decryptedText);
		*/

	}

}



/*
 * TODO:
 * How the whole encryption will work.
 * Encrypt the data in java.
 * Send the encrypted data to the PHP
 * Have two different keys, stored in config files.
 * PHP has the Java decryption key
 * Java has the PHP encryption key, and decryption key.
 * PHP sends the encrypted data back to the Java application.
 * 
 * Interpret the encrypted data, and send the encrypted data back to the Java application.
 * Decrypt the result and use the data.
 * 
 */