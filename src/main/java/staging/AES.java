import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AES {
	
	public static void generateIv() throws FileNotFoundException, IOException {
		SecureRandom rand = new SecureRandom();
		byte[] iv = new byte[256/8];
		rand.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
	
		// Write to file.
		String file = "ivFile";
		try (FileOutputStream out = new FileOutputStream(file)) {
		    out.write(iv);
		}
	}
	public static SecretKey genKey() throws NoSuchAlgorithmException
	{
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecretKey skey = kgen.generateKey();
		return skey;
	}
	public static SecretKey getKey() throws IOException 
	{
		String keyFile = "keyFile";
		byte[] keyb = Files.readAllBytes(Paths.get(keyFile));
		SecretKeySpec skey = new SecretKeySpec(keyb, "AES");
		return skey;
	}
	public static void writeKey(SecretKeySpec skey) throws FileNotFoundException, IOException
	{
		String keyFile = "keyFile";
		try (FileOutputStream out = new FileOutputStream(keyFile)) {
			byte[] keyb = skey.getEncoded();
			out.write(keyb);
		}
	}
	
	public static void encrypt(SecretKeySpec skey, IvParameterSpec ivspec, String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
		
		String outFile = "outFile";
		try (FileOutputStream out = new FileOutputStream(outFile)) {
		    byte[] input = plainText.getBytes("UTF-8");
		    byte[] encoded = ci.doFinal(input);
		    out.write(encoded);
		}
	}
	
	public static void decrypt(SecretKeySpec skey) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		String ivFile = "ivFile";
		byte[] iv = Files.readAllBytes(Paths.get(ivFile));
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.DECRYPT_MODE, skey, ivspec);
		
		String inFile = "inFile";
		byte[] encoded = Files.readAllBytes(Paths.get(inFile));
		String plainText = new String(ci.doFinal(encoded), "UTF-8");
	}
	
	
	
	
}
