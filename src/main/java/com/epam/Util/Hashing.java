package com.epam.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Hashing {

	private static final Logger LOG = Logger.getLogger(Hashing.class);
	public static final String SALT = "SimpleWeb04";
	public static final String ALGORITHM = "SHA-1";

	private Hashing() {

	}

	public static String generateHash(String password) {
		String saltedPassword = password + SALT;
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance(ALGORITHM);
			byte[] hashedBytes = sha.digest(saltedPassword.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			LOG.error("can't find the algorithm", e);
		}

		return hash.toString();
	}

}
