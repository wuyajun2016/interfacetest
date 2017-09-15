package com.dfire.testcase.function.util.cash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 收银加密通用类
 * 
 * @author pidan
 *
 */
public class SignUtil {
	private static final int LO_BYTE = 0x0f;
	private static final int MOVE_BIT = 4;
	private static final int HI_BYTE = 0xf0;
	private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String heartSign(Map<String, String> params, String APPSECRET) {
		List<String> keys = new ArrayList<String>();
		keys.addAll(params.keySet());
		Collections.sort(keys, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append(key).append(params.get(key));
		}
		sb.append(APPSECRET);
		String s = sb.toString();
		return encode(s);
	}


	public static String encode(String origin) {
		if (origin == null) {
			throw new NullPointerException("origin == null");
		}
		String resultString = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(origin.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// just ignored
		}
		return resultString;
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuilder buf = new StringBuilder();
		for (byte aB : b) {
			buf.append(byteToHexString(aB));
		}
		return buf.toString();
	}

	private static String byteToHexString(byte b) {
		return HEX_DIGITS[(b & HI_BYTE) >> MOVE_BIT] + HEX_DIGITS[b & LO_BYTE];
	}
}
