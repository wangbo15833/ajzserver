// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Cipher.java

package com.shenghao.arch.util.collapsar;

import java.io.IOException;

// Referenced classes of package com.shenghao.arch.util.collapsar:
//			SquareCbc, SquareCts, SquareCfb, SquareMode

public final class Cipher
{

	private static final byte key[] = {
		98, 108, 97, 99, 107, 32, 115, 104, 101, 101, 
		112, 32, 119, 97, 108, 108
	};
	private static final byte iv[] = {
		110, 119, 99, 97, 108, 114, 101, 97, 100, 121, 
		103, 111, 116, 111, 110, 101
	};
	private static final String hexDigit[] = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
		"A", "B", "C", "D", "E", "F"
	};

	public Cipher()
	{
	}

	protected static String getCipher(byte buf[], int len, String tag, int flag)
	{
		String s = new String();
		String t = new String();
		for (int i = 0; i < len; i++)
		{
			byte b = buf[i];
			s = s + hexDigit[b >>> 4 & 0xf] + hexDigit[b & 0xf];
			if (flag != 2 || b != 27)
				t = t + (char)buf[i];
		}

		if (flag == 1)
			return s;
		else
			return t;
	}

	protected static byte[] Mode(String tag, SquareMode sq, byte key[], byte iv[], byte plaintext[], int iterations)
	{
		byte data[] = new byte[plaintext.length];
		System.arraycopy(plaintext, 0, data, 0, plaintext.length);
		sq.setKey(key);
		sq.setIV(iv);
		sq.encrypt(data, 0, data.length);
		return data;
	}

	protected static byte[] DeMode(String tag, SquareMode sq, byte key[], byte iv[], byte plaintext[], int iterations)
	{
		byte data[] = new byte[plaintext.length];
		System.arraycopy(plaintext, 0, data, 0, plaintext.length);
		sq.setKey(key);
		sq.setIV(iv);
		sq.decrypt(data, 0, data.length);
		return data;
	}

	public static String Encrypt(String plain)
	{
		int iterations = 0;
		for (; plain.length() < 16; plain = plain + '\033');
		byte data[] = new byte[16];
		byte plain_byte[] = new byte[16];
		plain_byte = plain.getBytes();
		String ciphertext = null;
		data = Mode("CBC", new SquareCbc(), key, iv, plain_byte, iterations);
		ciphertext = getCipher(data, data.length, "cipher", 1);
		return ciphertext;
	}

	public static String superEncrypt(String plain)
		throws IOException
	{
		int iterations = 0;
		for (; plain.length() < 16; plain = plain + '\033');
		byte data[] = new byte[16];
		byte plain_byte[] = new byte[16];
		plain_byte = plain.getBytes();
		String ciphertext = null;
		data = Mode("CBC", new SquareCbc(), plain_byte, iv, plain_byte, iterations);
		ciphertext = getCipher(data, data.length, "cipher", 1);
		return ciphertext;
	}

	public static String Decrypt(String Encrypted)
	{
		int iterations = 0;
		byte plain_byte[] = new byte[16];
		String plain = "";
		int t2 = 0;
		for (int i = 1; i <= 32; i++)
		{
			char c = Encrypted.charAt(i - 1);
			int t1;
			if (c >= '0' && c <= '9')
				t1 = c - 48;
			else
				t1 = (c - 65) + 10;
			if (i % 2 == 0)
			{
				t1 += t2 * 16;
				plain_byte[i / 2 - 1] = (byte)t1;
				t2 = 0;
			} else
			{
				t2 = t1;
			}
		}

		byte data[] = new byte[16];
		String ciphertext = null;
		data = DeMode("CBC", new SquareCbc(), key, iv, plain_byte, iterations);
		ciphertext = getCipher(data, data.length, "Derypted", 2);
		return ciphertext;
	}

	public static boolean pwdCompare(String plain, String Encrypted)
		throws IOException
	{
		int iterations = 0;
		for (; plain.length() < 16; plain = plain + '\033');
		byte data[] = new byte[16];
		byte plain_byte[] = new byte[16];
		plain_byte = plain.getBytes();
		String ciphertext = new String();
		data = Mode("CTS", new SquareCts(), key, iv, plain_byte, iterations);
		data = Mode("CFB", new SquareCfb(), key, iv, plain_byte, iterations);
		data = Mode("CBC", new SquareCbc(), key, iv, plain_byte, iterations);
		ciphertext = getCipher(data, data.length, "cipher", 1);
		return ciphertext.equals(Encrypted);
	}

	public static boolean superpwdCompare(String plain, String Encrypted)
		throws IOException
	{
		int iterations = 0;
		for (; plain.length() < 16; plain = plain + '\033');
		byte data[] = new byte[16];
		byte plain_byte[] = new byte[16];
		plain_byte = plain.getBytes();
		String ciphertext = new String();
		data = Mode("CBC", new SquareCbc(), plain_byte, iv, plain_byte, iterations);
		ciphertext = getCipher(data, data.length, "cipher", 1);
		return ciphertext.equals(Encrypted);
	}

}
