// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MailData.java

package com.shenghao.arch.util.mail;

import java.io.Serializable;

public class MailData
	implements Serializable
{

	public String mailSubject;
	public String mailFrom;
	public String mailTo;
	public String mailCc;
	public String mailBcc;
	public String mailBody;
	public String mailPwd;
	public String smtpServer;

	public MailData()
	{
		mailSubject = "";
		mailFrom = "";
		mailTo = "";
		mailCc = "";
		mailBcc = "";
		mailBody = "";
		mailPwd = "";
		smtpServer = "smtp.1768.org";
	}

	public MailData(String mailSubject, String mailFrom, String mailPwd, String mailTo, String mailCc, String mailBcc, String mailBody)
	{
		this.mailSubject = "";
		this.mailFrom = "";
		this.mailTo = "";
		this.mailCc = "";
		this.mailBcc = "";
		this.mailBody = "";
		this.mailPwd = "";
		smtpServer = "smtp.1768.org";
		this.mailSubject = mailSubject;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.mailCc = mailSubject;
		this.mailBcc = mailBcc;
		this.mailBody = mailBody;
		this.mailPwd = mailPwd;
	}

	public MailData(String mailSubject, String mailFrom, String mailPwd, String mailTo, String mailBody)
	{
		this.mailSubject = "";
		this.mailFrom = "";
		this.mailTo = "";
		mailCc = "";
		mailBcc = "";
		this.mailBody = "";
		this.mailPwd = "";
		smtpServer = "smtp.1768.org";
		this.mailSubject = mailSubject;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.mailBody = mailBody;
		this.mailPwd = mailPwd;
	}

	public String getMailBcc()
	{
		return mailBcc;
	}

	public String getMailCc()
	{
		return mailCc;
	}

	public String getMailBody()
	{
		return mailBody;
	}

	public String getMailFrom()
	{
		return mailFrom;
	}

	public String getMailPwd()
	{
		return mailPwd;
	}

	public String getMailSubject()
	{
		return mailSubject;
	}

	public String getMailTo()
	{
		return mailTo;
	}

	public String getSmtpServer()
	{
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer)
	{
		this.smtpServer = smtpServer;
	}

	public void setMailTo(String mailTo)
	{
		this.mailTo = mailTo;
	}

	public void setMailSubject(String mailSubject)
	{
		this.mailSubject = mailSubject;
	}

	public void setMailPwd(String mailPwd)
	{
		this.mailPwd = mailPwd;
	}

	public void setMailFrom(String mailFrom)
	{
		this.mailFrom = mailFrom;
	}

	public void setMailCc(String mailCc)
	{
		this.mailCc = mailCc;
	}

	public void setMailBody(String mailBody)
	{
		this.mailBody = mailBody;
	}

	public void setMailBcc(String mailBcc)
	{
		this.mailBcc = mailBcc;
	}
}
