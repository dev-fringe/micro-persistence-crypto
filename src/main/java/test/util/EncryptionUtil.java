package test.util;

import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class EncryptionUtil {
	private static SecretKeySpec secretKeySpec;
	
	static {	
		try {			
			ClassPathResource res = new ClassPathResource("key.key");
			if(res != null){
				File file = res.getFile();
				FileInputStream input = new FileInputStream(file);
				byte[] in = new byte[(int)file.length()];
				input.read(in);
				secretKeySpec = new SecretKeySpec(in, "AES");
				input.close();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static byte[] encrypt(String input) 
			throws GeneralSecurityException, NoSuchPaddingException{
	       Cipher cipher = Cipher.getInstance("AES");
	       cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	       return cipher.doFinal(input.getBytes());
		
	}
	
	
	public static String decrypt(byte[] input) throws GeneralSecurityException, NoSuchPaddingException{
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		return new String(cipher.doFinal(input));
	}
	
	

}
