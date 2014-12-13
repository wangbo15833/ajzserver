// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SquareMode.java

package com.shenghao.arch.util.collapsar;


public interface SquareMode
{

	public abstract void setKey(byte abyte0[]);

	public abstract void setIV(byte abyte0[]);

	public abstract void encrypt(byte abyte0[], int i, int j);

	public abstract void decrypt(byte abyte0[], int i, int j);
}
