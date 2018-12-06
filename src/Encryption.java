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
import javax.crypto.KeyGenerator;
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
	//private static SecretKeySpec masterKey;
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
	/*
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
	*/
	public static String encrypt(String strToEncrypt, SecretKeySpec myKey) 
	{
		try
		{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			AlgorithmParameters params = cipher.getParameters();
			iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			ciphertext = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
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
	public static String decrypt(String strToDecrypt) 
	{
		try
		{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
			// String plaintext = new String(cipher.doFinal(ciphertext), "UTF-8");
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} 
		catch (Exception e) { System.out.println("Error while decrypting: " + e.toString()); }
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
		// KeyPair masterKeys = Rsa.generateKeyPair();
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
		
		// byte[] encryptedData = Rsa.encrypt(transportData, pubKey);
		byte[] encryptedData = RSA.encrypt(transportData, pubKey);
		//encryptCipher(transportData, "");
		return encryptedData;
	}

	public static String receive(byte[] encryptedData) throws ClassNotFoundException, IOException
	{
		PrivateKey privKey = Encryption.getPrivKey();
		System.out.println(privKey.getEncoded());
		
		// String unencryptedData = Rsa.decrypt(encryptedData, privKey);
		String unencryptedData = RSA.decrypt(encryptedData, privKey);
		String finalLayer = Encryption.decrypt(unencryptedData);
		return finalLayer;
	}
	
	public static byte[] getSK() { return secretKey.getEncoded(); }
	
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
	
	// public static int getIV() { return iv.hashCode(); }
	public static byte[] getIV() { return iv; }
	public static byte[] encryptMSG(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(256); // The AES key size in number of bits
		// SecretKey secKey = generator.generateKey();
		Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] byteCipherText = aesCipher.doFinal(data.getBytes());
		return byteCipherText;
		
	}
	// private static PrivateKey prKey;
	
	public static byte[] encryptRSAKey(PublicKey puKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.PUBLIC_KEY, puKey);
		byte[] encryptedKey = cipher.doFinal(secretKey.getEncoded()/*Seceret Key From Step 1*/);
		return encryptedKey;
	}
	
	public static byte[] decryptKey(byte[] encryptedKey, PrivateKey prKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.PRIVATE_KEY, prKey);
		
		// byte[] decryptedKey = cipher.doFinal(encryptedKey);
		byte[] decryptedKey = cipher.doFinal(prKey.getEncoded());
		return decryptedKey;
	}
	
	public static String decryptMSG(byte[] encryptedData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		//Convert bytes to AES SecretKey
		// SecretKey originalKey = new SecretKeySpec(decryptedKey , 0, decryptedKey .length, "AES");
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] bytePlainText = aesCipher.doFinal(encryptedData);
		String plainText = new String(bytePlainText);
		return plainText;
	}
	
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
		
		
		/*	 How this will work.
		 *   I will set the key at the start of the program.
		 *   I will send AES-256 Bit encrypted data. And will receive AES-256 Bit Encryption.
		 * 
		 */
		
		
		// The finally working set
		setKey("This is my secretKey");
		final String data = "SELECT * FROM USERS WHERE acct_number=12345";
		
		// byte[] encryptedData = Encryption.encryptMSG(data);
		String encryptedData = Encryption.encrypt(data);
		/*
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair keyPair = kpg.generateKeyPair();

		PublicKey puKey = keyPair.getPublic();
		prKey = keyPair.getPrivate();
		byte[] encryptedKey = Encryption.encryptRSAKey(puKey);
		*/
		System.out.println("Transporting ... ");
		System.out.println(secretKey.getEncoded());
		
		// byte[] decryptedKey = Encryption.decryptKey(encryptedKey, prKey);
		// String decryptedMSG = Encryption.decryptMSG(encryptedData.getBytes());
		String decryptedMSG = new String(Encryption.decrypt(encryptedData));
		System.out.println("Decrypted Message: " + decryptedMSG);
		
		/*
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
		*/
		
		
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


