// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SquareCfb.java

package com.shenghao.arch.util.collapsar;


// Referenced classes of package com.shenghao.arch.util.collapsar:
//			Square, SquareMode

public final class SquareCfb
	implements SquareMode
{

	private Square sq;
	private final byte mask[] = new byte[16];
	private int avail;

	public SquareCfb()
	{
		avail = 0;
	}

	public final void setKey(byte key[])
	{
		sq = new Square(key);
		for (int i = 0; i < 16; i++)
			mask[i] = 0;

		avail = 0;
	}

	public final void setIV(byte iv[])
	{
		System.arraycopy(iv, 0, mask, 0, 16);
		avail = 0;
	}

	public final void encrypt(byte buf[], int off, int len)
	{
		if (len <= avail)
		{
			int i = 16 - avail;
			for (int m = len; m-- > 0;)
			{
				buf[off] = mask[i] ^= buf[off];
				i++;
				off++;
			}

			avail -= len;
			return;
		}
		for (int i = 16 - avail; i < 16;)
		{
			buf[off] = mask[i] ^= buf[off];
			i++;
			off++;
		}

		for (len -= avail; len > 16; len -= 16)
		{
			sq.blockEncrypt(mask, 0, mask, 0);
			for (int i = 0; i < 16;)
			{
				buf[off] = mask[i] ^= buf[off];
				i++;
				off++;
			}

		}

		sq.blockEncrypt(mask, 0, mask, 0);
		for (int i = 0; i < len;)
		{
			buf[off] = mask[i] ^= buf[off];
			i++;
			off++;
		}

		avail = 16 - len;
	}

	public final void decrypt(byte buf[], int off, int len)
	{
		if (len <= avail)
		{
			int i = 16 - avail;
			for (int m = len; m-- > 0;)
			{
				mask[i] ^= buf[off++] ^= mask[i];
				i++;
			}

			avail -= len;
			return;
		}
		for (int i = 16 - avail; i < 16; i++)
			mask[i] ^= buf[off++] ^= mask[i];

		for (len -= avail; len > 16; len -= 16)
		{
			sq.blockEncrypt(mask, 0, mask, 0);
			for (int i = 0; i < 16; i++)
				mask[i] ^= buf[off++] ^= mask[i];

		}

		sq.blockEncrypt(mask, 0, mask, 0);
		for (int i = 0; i < len; i++)
			mask[i] ^= buf[off++] ^= mask[i];

		avail = 16 - len;
	}

	protected final void finalize()
		throws Throwable
	{
		for (int i = 0; i < 16; i++)
			mask[i] = 0;

		avail = 0;
		super.finalize();
	}
}
