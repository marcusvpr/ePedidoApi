package com.mpxds.apirest.service.exceptions;

public class MpObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MpObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public MpObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
