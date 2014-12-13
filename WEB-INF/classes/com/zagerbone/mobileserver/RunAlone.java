package com.zagerbone.mobileserver;

import java.util.Date;

public class RunAlone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread mobileServer = new AjzMobileServer();
		mobileServer.start();
	}
}
