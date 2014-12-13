// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Square.java

package com.shenghao.arch.util.collapsar;


public final class Square
{

	public static final int BLOCK_LENGTH = 16;
	public static final int KEY_LENGTH = 16;
	private static final int R = 8;
	private static final int offset[];
	private static final int phi[];
	private static final int Se[];
	private static final int Sd[];
	private static final int Te[];
	private static final int Td[];
	private final int roundKeys_e[][] = new int[9][4];
	private final int roundKeys_d[][] = new int[9][4];
	private static final int ROOT = 501;
	private static final int exp[];
	private static final int log[];

	private static final int mul(int a, int b)
	{
		return a == 0 || b == 0 ? 0 : exp[(log[a] + log[b]) % 255];
	}

	private static final int rotr(int x, int s)
	{
		return x >>> s | x << 32 - s;
	}

	private static final int rotl(int x, int s)
	{
		return x << s | x >>> 32 - s;
	}

	private final void transform(int roundKey[])
	{
		roundKey[0] = phi[roundKey[0] & 0xff] ^ rotl(phi[roundKey[0] >>> 8 & 0xff], 8) ^ rotl(phi[roundKey[0] >>> 16 & 0xff], 16) ^ rotl(phi[roundKey[0] >>> 24 & 0xff], 24);
		roundKey[1] = phi[roundKey[1] & 0xff] ^ rotl(phi[roundKey[1] >>> 8 & 0xff], 8) ^ rotl(phi[roundKey[1] >>> 16 & 0xff], 16) ^ rotl(phi[roundKey[1] >>> 24 & 0xff], 24);
		roundKey[2] = phi[roundKey[2] & 0xff] ^ rotl(phi[roundKey[2] >>> 8 & 0xff], 8) ^ rotl(phi[roundKey[2] >>> 16 & 0xff], 16) ^ rotl(phi[roundKey[2] >>> 24 & 0xff], 24);
		roundKey[3] = phi[roundKey[3] & 0xff] ^ rotl(phi[roundKey[3] >>> 8 & 0xff], 8) ^ rotl(phi[roundKey[3] >>> 16 & 0xff], 16) ^ rotl(phi[roundKey[3] >>> 24 & 0xff], 24);
	}

	public Square(byte key[])
	{
		for (int i = 0; i < 16; i += 4)
			roundKeys_e[0][i >> 2] = key[i] & 0xff | (key[i + 1] & 0xff) << 8 | (key[i + 2] & 0xff) << 16 | (key[i + 3] & 0xff) << 24;

		for (int t = 1; t <= 8; t++)
		{
			roundKeys_d[8 - t][0] = roundKeys_e[t][0] = roundKeys_e[t - 1][0] ^ rotr(roundKeys_e[t - 1][3], 8) ^ offset[t - 1];
			roundKeys_d[8 - t][1] = roundKeys_e[t][1] = roundKeys_e[t - 1][1] ^ roundKeys_e[t][0];
			roundKeys_d[8 - t][2] = roundKeys_e[t][2] = roundKeys_e[t - 1][2] ^ roundKeys_e[t][1];
			roundKeys_d[8 - t][3] = roundKeys_e[t][3] = roundKeys_e[t - 1][3] ^ roundKeys_e[t][2];
			transform(roundKeys_e[t - 1]);
		}

		for (int i = 0; i < 4; i++)
			roundKeys_d[8][i] = roundKeys_e[0][i];

	}

	private final void round(int block[], int T[], int roundKey[])
	{
		int t0 = block[0];
		int t1 = block[1];
		int t2 = block[2];
		int t3 = block[3];
		block[0] = T[t0 & 0xff] ^ rotl(T[t1 & 0xff], 8) ^ rotl(T[t2 & 0xff], 16) ^ rotl(T[t3 & 0xff], 24) ^ roundKey[0];
		block[1] = T[t0 >>> 8 & 0xff] ^ rotl(T[t1 >>> 8 & 0xff], 8) ^ rotl(T[t2 >>> 8 & 0xff], 16) ^ rotl(T[t3 >>> 8 & 0xff], 24) ^ roundKey[1];
		block[2] = T[t0 >>> 16 & 0xff] ^ rotl(T[t1 >>> 16 & 0xff], 8) ^ rotl(T[t2 >>> 16 & 0xff], 16) ^ rotl(T[t3 >>> 16 & 0xff], 24) ^ roundKey[2];
		block[3] = T[t0 >>> 24 & 0xff] ^ rotl(T[t1 >>> 24 & 0xff], 8) ^ rotl(T[t2 >>> 24 & 0xff], 16) ^ rotl(T[t3 >>> 24 & 0xff], 24) ^ roundKey[3];
		t0 = t1 = t2 = t3 = 0;
	}

	public final void blockEncrypt(byte in[], int in_offset, byte out[], int out_offset)
	{
		int block[] = new int[4];
		for (int i = 0; i < 4; i++)
			block[i] = in[in_offset++] & 0xff ^ (in[in_offset++] & 0xff) << 8 ^ (in[in_offset++] & 0xff) << 16 ^ (in[in_offset++] & 0xff) << 24 ^ roundKeys_e[0][i];

		for (int r = 1; r < 8; r++)
			round(block, Te, roundKeys_e[r]);

		round(block, Se, roundKeys_e[8]);
		for (int i = 0; i < 4; i++)
		{
			int w = block[i];
			out[out_offset++] = (byte)w;
			out[out_offset++] = (byte)(w >>> 8);
			out[out_offset++] = (byte)(w >>> 16);
			out[out_offset++] = (byte)(w >>> 24);
		}

	}

	public final void blockDecrypt(byte in[], int in_offset, byte out[], int out_offset)
	{
		int block[] = new int[4];
		for (int i = 0; i < 4; i++)
			block[i] = in[in_offset++] & 0xff ^ (in[in_offset++] & 0xff) << 8 ^ (in[in_offset++] & 0xff) << 16 ^ (in[in_offset++] & 0xff) << 24 ^ roundKeys_d[0][i];

		for (int r = 1; r < 8; r++)
			round(block, Td, roundKeys_d[r]);

		round(block, Sd, roundKeys_d[8]);
		int w;
		for (int i = 0; i < 4; i++)
		{
			w = block[i];
			out[out_offset++] = (byte)w;
			out[out_offset++] = (byte)(w >>> 8);
			out[out_offset++] = (byte)(w >>> 16);
			out[out_offset++] = (byte)(w >>> 24);
		}

		w = 0;
		for (int i = 0; i < 4; i++)
			block[i] = 0;

	}

	protected final void finalize()
		throws Throwable
	{
		for (int r = 0; r <= 8; r++)
		{
			for (int i = 0; i < 4; i++)
				roundKeys_e[r][i] = roundKeys_d[r][i] = 0;

		}

		super.finalize();
	}

	static 
	{
		offset = new int[8];
		phi = new int[256];
		Se = new int[256];
		Sd = new int[256];
		Te = new int[256];
		Td = new int[256];
		exp = new int[256];
		log = new int[256];
		exp[0] = exp[255] = 1;
		log[1] = 0;
		for (int i = 1; i < 255; i++)
		{
			int j = exp[i - 1] << 1;
			if (j >= 256)
				j ^= 0x1f5;
			exp[i] = j;
			log[j] = i;
		}

		Se[0] = 0;
		for (int i = 1; i < 256; i++)
			Se[i] = exp[255 - log[i]];

		int trans[] = {
			1, 3, 5, 15, 31, 61, 123, 214
		};
		for (int i = 0; i < 256; i++)
		{
			int v = 177;
			for (int t = 0; t < 8; t++)
			{
				int u = Se[i] & trans[t];
				u ^= u >> 4;
				u ^= u >> 2;
				u ^= u >> 1;
				u &= 1;
				v ^= u << t;
			}

			Se[i] = v;
			Sd[v] = i;
		}

		int c[] = {
			2, 1, 1, 3
		};
		int d[] = {
			14, 9, 13, 11
		};
		for (int t = 0; t < 256; t++)
		{
			phi[t] = mul(t, c[3]) << 24 ^ mul(t, c[2]) << 16 ^ mul(t, c[1]) << 8 ^ mul(t, c[0]);
			int v = Se[t];
			Te[t] = Se[t & 3] == 0 ? 0 : mul(v, c[3]) << 24 ^ mul(v, c[2]) << 16 ^ mul(v, c[1]) << 8 ^ mul(v, c[0]);
			v = Sd[t];
			Td[t] = Sd[t & 3] == 0 ? 0 : mul(v, d[3]) << 24 ^ mul(v, d[2]) << 16 ^ mul(v, d[1]) << 8 ^ mul(v, d[0]);
		}

		offset[0] = 1;
		for (int i = 1; i < 8; i++)
			offset[i] = mul(offset[i - 1], 2);

	}
}
