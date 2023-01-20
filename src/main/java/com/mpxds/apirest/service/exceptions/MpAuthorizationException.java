package com.mpxds.apirest.service.exceptions;

public class MpAuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MpAuthorizationException(String msg) {
		super(msg);
	}
	
	public MpAuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
