package com.zyh.spring.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 以上都是基于byte数组转16进制字符串
 * @author HU
 *
 */
public class DesUtil {

	public static String getKeyDES() throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		generator.init(56);
		SecretKey key = generator.generateKey();
		return NumberUtils.bytesToHexString(key.getEncoded());
	}

	
	public static SecretKey loadKeyDES(byte[] secretKeyBytes) {
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "DES");
		return secretKey;
	}
	
	public static SecretKey loadKeyDES(String hexKey) {
		byte[] bytes = NumberUtils.hexStringToBytes(hexKey);
		SecretKey secretKey = new SecretKeySpec(bytes, "DES");
		return secretKey;
	}
	
	
	public static byte[] encryptDES(byte[] source, byte[] secretKeyBytes) throws Exception{
		return encryptDES(source,loadKeyDES(secretKeyBytes));
	}
	
	public static byte[] encryptDES(byte[] source, String hexSecretKey) throws Exception{
		return encryptDES(source,loadKeyDES(hexSecretKey));
	}

	public static byte[] encryptDES(byte[] source, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}
	
	
	public static byte[] decryptDES(byte[] source, byte[] secretKeyBytes) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, loadKeyDES(secretKeyBytes));
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}
	
	public static byte[] decryptDES(byte[] source, String hexSecretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, loadKeyDES(hexSecretKey));
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}
	
	public static byte[] decryptDESEx(byte[] source, String secretKey) throws Exception {
		return decryptDES(source, loadKeyDES(secretKey));
	}
	
	public static byte[] decryptDES(byte[] source, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}

}
