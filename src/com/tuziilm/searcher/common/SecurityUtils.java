package com.tuziilm.searcher.common;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ��ȫ��ع�����
 */
public class SecurityUtils {
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
	private static final String key = "keyisabcd";
	protected static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5FileUtil messagedigest��ʼ��ʧ��", e);
		}
	}

	/**
	 * ʹ��md5�㷨�����ִ�
	 *
	 * @param text
	 * @param salt
	 * @return
	 */
	public static String md5Encode(String text, String salt) {
		return md5Encode(text, salt.hashCode());
	}

	public SecurityUtils() {
		super();
	}

	/**
	 * ʹ��md5�㷨�����ִ�
	 *
	 * @param text
	 * @param salt
	 * @return
	 */
	public static String md5Encode(String text, int salt) {
		String hexString = new String(DigestUtils.md5DigestAsHex(text.getBytes()));
		String first = hexString.substring(0, 15);
		String second = hexString.substring(17);
		Long firstLong = Long.valueOf(first, 16) + salt;
		Long secondLong = Long.valueOf(second, 16) + salt;
		first = Long.toString(firstLong, 16);
		second = Long.toString(secondLong, 16);
		hexString = first + hexString.substring(15, 17) + second;
		return new String(DigestUtils.md5DigestAsHex(hexString.getBytes()));
	}

	/**
	 * md5ժҪ
	 *
	 * @param text
	 * @return
	 */
	public static String md5Encode(String text) {
		return new String(DigestUtils.md5DigestAsHex(text.getBytes()));
	}

	/**
	 * md5���ļ�����ժҪ
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
	public static String md5Encode(File file)throws IOException{
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());

	}
	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	public static String Get32CodeModel(String text) {
		//����md5����
		String message = md5Encode(text);
		char[] result = new char[message.length()];
		int len = message.length();
		for (int i = 0; ; i += key.length()) {
			int j;
			for (j=0; j < key.length() && i + j < len; j++) {
				result[i + j] = hexDigits[((message.charAt(i + j) + key.charAt(j)) % 16)&0xF];
			}
			if (i + j >= len) {
				break;
			}
		}
		return String.copyValueOf(result);

	}

    /**
     * ����㷨��m=md5(md5(imei+":"+n))
     * @param imei
     * @param m
     * @param n
     * @return
     */
    public static boolean checkImeiMn(String imei, String m, String n){
        if(Strings.isNullOrEmpty(imei) || Strings.isNullOrEmpty(m) || Strings.isNullOrEmpty(n)){
            return false;
        }
        String key=imei+":"+n;
        return m.equals(md5Encode(md5Encode(key)));
    }
	public static void main(String[] args) throws NoSuchAlgorithmException {
//		String result = "Mw-T7dxx7fg6nEoONOoUyTBr2bGloOlO7_KT76gxoTqOBauwBbomovPlndti8RoSMw-TsdITs625ndpONOoOyb-Ws4QONOoZofimovPUoTqO36xr2womofg5s6IroTqOBaQUBp2DN0oZQT2d2aHbBD-02eQlNaH0g0LigToZ2TLOyb-Ws4QONOoZofiF";
//		System.out.println(result);
//		String paramC = Get32CodeModel(result);
//		System.out.println(paramC);

	}
}
