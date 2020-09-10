package br.com.dxc.elo_import_incoming.utils;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TripleDesCriptor {
	private static final Logger LOG = LogManager.getLogger(TripleDesCriptor.class);
	
	private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	static byte[] arrayBytes;
	static SecretKey key;
	private static final String LOAD_HC_KEY = "13FFFA94312342BCDEFCDEBBDD123EF1";
	
	public static String cifrar(String valor) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		return new TripleDesCriptor().des3Encrypt(valor, LOAD_HC_KEY);
	}

	public static String decifrar(String valorCifrado) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		return new TripleDesCriptor().des3Decrypt(valorCifrado, LOAD_HC_KEY);
	}

	private String des3Encrypt(String clearMessage, String pwd) 
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		String text = paddInputString(clearMessage);
		String texthex = asHex(text.getBytes());

		String k1 = null;
		String k2 = null;
		String k3 = null;
		if (pwd.length() == 32) {
			k1 = pwd.substring(0, 16);
			k2 = pwd.substring(16, 32);
			k3 = k1;
		} else if (pwd.length() == 48) {
			k1 = pwd.substring(0, 16);
			k2 = pwd.substring(16, 32);
			k3 = pwd.substring(32, 48);
		} else {
			LOG.info("INVALID: Key length !!");
			return "ERROR";
		}
		String eCodedText = new TripleDesCriptor().desEncrypt(texthex, k1);
		String eDecodedText = new TripleDesCriptor().desDecrypt(eCodedText, k2);

		return new TripleDesCriptor().desEncrypt(eDecodedText, k3);
	}

	private String des3Decrypt(String cipherMessage, String pwd)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		String cipheredText = paddInputString(cipherMessage);

		String k1 = null;
		String k2 = null;
		String k3 = null;
		if (pwd.length() == 32) {
			k1 = pwd.substring(0, 16);
			k2 = pwd.substring(16, 32);
			k3 = k1;
		} else if (pwd.length() == 48) {
			k1 = pwd.substring(0, 16);
			k2 = pwd.substring(16, 32);
			k3 = pwd.substring(32, 48);
		} else {
			LOG.info("INVALID: Key length !!");
			return "ERROR";
		}
		String dDecodedText = new TripleDesCriptor().desDecrypt(cipheredText, k1);
		String dCodedText = new TripleDesCriptor().desEncrypt(dDecodedText, k2);
		String dDecodedText2 = new TripleDesCriptor().desDecrypt(dCodedText, k3);

		return hexToAscii(dDecodedText2).trim();
	}

	private String desEncrypt(String message, String pwd) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		String algorithm = "DES";
		byte[] encBytes = hexStringToByteArray(pwd);
		setDESKeyParity(encBytes);
		SecretKey myKey = new SecretKeySpec(encBytes, algorithm); //[fortify] Aplicacao roda em intranet - ambiente controlado

		Cipher cipher = Cipher.getInstance("DES/ECB/Nopadding"); //[fortify] Aplicacao roda em intranet - ambiente controlado
		cipher.init(1, myKey);

		byte[] plainTextBytes = hexStringToByteArray(message);
		byte[] cipherText = cipher.doFinal(plainTextBytes);

		return toHex(cipherText);
	}

	private String desDecrypt(String message, String pwd) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		String algorithm = "DES";
		byte[] decBytes = hexStringToByteArray(pwd);
		setDESKeyParity(decBytes);
		SecretKey myKey = new SecretKeySpec(decBytes, algorithm); //[fortify] Aplicacao roda em intranet - ambiente controlado

		Cipher cipher = Cipher.getInstance("DES/ECB/Nopadding"); //[fortify] Aplicacao roda em intranet - ambiente controlado
		cipher.init(2, myKey);

		byte[] cipheredTextBytes = hexStringToByteArray(message);
		byte[] plainText = cipher.doFinal(cipheredTextBytes);

		return toHex(plainText);
	}

	private byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[(i / 2)] = ((byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16)));
		}
		return data;
	}

	private static String toHex(byte[] stringBytes) {
		StringBuilder result = new StringBuilder(2 * stringBytes.length);
		for (int i = 0; i < stringBytes.length; i++) {
			result.append("0123456789ABCDEF".charAt(stringBytes[i] >> 4 & 0xF))
					.append("0123456789ABCDEF".charAt(stringBytes[i] & 0xF));
		}
		return result.toString();
	}

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	private static String asHex(byte[] buf) {
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; i++) {
			chars[(2 * i)] = HEX_CHARS[((buf[i] & 0xF0) >>> 4)];
			chars[(2 * i + 1)] = HEX_CHARS[(buf[i] & 0xF)];
		}
		return new String(chars);
	}

	private static void setDESKeyParity(byte[] key) {
		byte[] partab = { 1, 1, 2, 2, 4, 4, 7, 7, 8, 8, 11, 11, 13, 13, 14, 14, 16, 16, 19, 19, 21, 21, 22, 22, 25, 25,
				26, 26, 28, 28, 31, 31, 32, 32, 35, 35, 37, 37, 38, 38, 41, 41, 42, 42, 44, 44, 47, 47, 49, 49, 50, 50,
				52, 52, 55, 55, 56, 56, 59, 59, 61, 61, 62, 62, 64, 64, 67, 67, 69, 69, 70, 70, 73, 73, 74, 74, 76, 76,
				79, 79, 81, 81, 82, 82, 84, 84, 87, 87, 88, 88, 91, 91, 93, 93, 94, 94, 97, 97, 98, 98, 100, 100, 103,
				103, 104, 104, 107, 107, 109, 109, 110, 110, 112, 112, 115, 115, 117, 117, 118, 118, 121, 121, 122, 122,
				124, 124, Byte.MAX_VALUE, Byte.MAX_VALUE };
		for (int i = 0; i < key.length; i++) {
			key[i] = partab[(key[i] & 0x7F)];
		}
	}

	private static String paddInputString(String str) {
		StringBuilder temp = new StringBuilder();
		int fillLen = 0;
		if (str.length() % 8 != 0) {
			fillLen = 8 - str.length() % 8;
		}
		for (int i = 0; i < fillLen; i++) {
			temp.append(' ');
		}
		return str + temp.toString();
	}

	private static String hexToAscii(String s) {
		int n = s.length();
		StringBuilder sb = new StringBuilder(n / 2);
		for (int i = 0; i < n; i += 2) {
			char a = s.charAt(i);
			char b = s.charAt(i + 1);
			sb.append((char) (hexToInt(a) << 4 | hexToInt(b)));
		}
		return sb.toString();
	}

	private static int hexToInt(char ch) {
		if (('a' <= ch) && (ch <= 'f')) {
			return ch - 'a' + 10;
		}
		if (('A' <= ch) && (ch <= 'F')) {
			return ch - 'A' + 10;
		}
		if (('0' <= ch) && (ch <= '9')) {
			return ch - '0';
		}
		throw new IllegalArgumentException(String.valueOf(ch));
	}
	
	public static String decryptBase64(String encryptedString) throws GeneralSecurityException {		
		String decryptedText = null;
		Cipher cipher;
		KeySpec ks;
		SecretKeyFactory skf;
		String myEncryptionKey;
		String myEncryptionScheme;
		try {
			myEncryptionKey = "ThisIsSpartaThisIsSparta";  //[fortify] Aplicacao roda em intranet - ambiente controlado
			myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
			arrayBytes = myEncryptionKey.getBytes(StandardCharsets.UTF_8);
			ks = new DESedeKeySpec(arrayBytes);
			skf = SecretKeyFactory.getInstance(myEncryptionScheme);
			cipher = Cipher.getInstance(myEncryptionScheme); //[fortify] Aplicacao roda em intranet - ambiente controlado
			key = skf.generateSecret(ks);
			
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);  //[fortify] Aplicacao roda em intranet - ambiente controlado
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e) {
			LOG.fatal("Erro - decryptBase64");
			throw e;
		}
		return decryptedText;
	}
	
}