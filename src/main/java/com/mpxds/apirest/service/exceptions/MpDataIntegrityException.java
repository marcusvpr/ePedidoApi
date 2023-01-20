package com.mpxds.apirest.service.exceptions;

public class MpDataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MpDataIntegrityException(String msg) {
		super(msg);
	}
	
	public MpDataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
