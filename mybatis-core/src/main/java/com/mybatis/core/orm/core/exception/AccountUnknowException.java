package com.mybatis.core.orm.core.exception;

@SuppressWarnings("serial")
public class AccountUnknowException extends Exception{

	public AccountUnknowException() {
		super();
	}

	public AccountUnknowException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AccountUnknowException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AccountUnknowException(String arg0) {
		super(arg0);
	}

	public AccountUnknowException(Throwable arg0) {
		super(arg0);
	}
}
