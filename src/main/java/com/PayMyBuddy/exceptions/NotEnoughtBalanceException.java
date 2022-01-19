package com.PayMyBuddy.exceptions;

public class NotEnoughtBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughtBalanceException() {
		super("Not enought founds on account");

	}
}
