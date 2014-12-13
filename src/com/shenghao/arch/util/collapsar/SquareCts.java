// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SquareCts.java

package com.shenghao.arch.util.collapsar;


// Referenced classes of package com.shenghao.arch.util.collapsar:
//			Square, SquareMode

public final class SquareCts
	implements SquareMode
{

	private Square sq;
	private final byte mask[] = new byte[16];

	public SquareCts()
	{
	}

	public final void setKey(byte key[])
	{
		sq = new Square(key);
		for (int i = 0; i < 16; i++)
			mask[i] = 0;

	}

	public final void setIV(byte iv[])
	{
		System.arraycopy(iv, 0, mask, 0, 16);
		sq.blockEncrypt(mask, 0, mask, 0);
	}

	public final void encrypt(byte buf[], int off, int len)
	{
		if (len < 16)
			throw new IndexOutOfBoundsException();
		for (; len >= 16; len -= 16)
		{
			for (int i = 0; i < 16; i++)
				buf[off + i] ^= mask[i];

			sq.blockEncrypt(buf, off, buf, off);
			for (int i = 0; i < 16; i++)
				mask[i] = buf[off++];

		}

		if (len != 0)
		{
			for (int i = 0; i < len; i++)
				mask[i] ^= buf[off + i];

			sq.blockEncrypt(mask, 0, mask, 0);
			System.arraycopy(buf, off - 16, buf, off, len);
			System.arraycopy(mask, 0, buf, off - 16, 16);
		}
	}

	public final void decrypt(byte buf[], int off, int len)
	{
		if (len < 16)
			throw new IndexOutOfBoundsException();
		byte temp[] = new byte[16];
		for (; len >= 32; len -= 16)
		{
			for (int i = 0; i < 16; i++)
				temp[i] = buf[off + i];

			sq.blockDecrypt(buf, off, buf, off);
			for (int i = 0; i < 16; i++)
			{
				buf[off++] ^= mask[i];
				mask[i] = temp[i];
			}

		}

		System.arraycopy(buf, off, temp, 0, 16);
		if (len > 16)
		{
			sq.blockDecrypt(buf, off, buf, off);
			for (int i = 0; i < len - 16; i++)
				buf[off + i] ^= buf[off + 16 + i] ^= buf[off + i];

		}
		sq.blockDecrypt(buf, off, buf, off);
		for (int i = 0; i < 16; i++)
		{
			buf[off++] ^= mask[i];
			mask[i] = temp[i];
		}

	}

	protected final void finalize()
		throws Throwable
	{
		for (int i = 0; i < 16; i++)
			mask[i] = 0;

		super.finalize();
	}
}
