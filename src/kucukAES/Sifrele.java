package kucukAES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Sifrele {

	// AES-256
	private static SecretKeySpec anahtarGizli;
	private static byte[] anahtar;

	public static void setKey(String anahtarim) throws Exception {

		MessageDigest sha = null;

		anahtar = anahtarim.getBytes("UTF-16");
		sha = MessageDigest.getInstance("SHA-1");
		anahtar = sha.digest(anahtar);
		anahtar = Arrays.copyOf(anahtar, 16);
		anahtarGizli = new SecretKeySpec(anahtar, "AES");


	}

	public static void aesSifreleDosya(int cipherMode, String key, String dosya, String dosyaCikti)
			throws Exception {
		try {

			setKey(key);
			File inputFile = new File(dosya);
			Key secretKey = anahtarGizli;
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = null;
			if (cipherMode == Cipher.ENCRYPT_MODE) {
				outputBytes = Base64.getEncoder().encode(cipher.doFinal(inputBytes));
			} else if (cipherMode == Cipher.DECRYPT_MODE) {
				outputBytes = Base64.getDecoder().decode(inputBytes);

				outputBytes = cipher.doFinal(outputBytes);
			}

			File outputFile = new File(dosyaCikti);
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
		}
	}

}
