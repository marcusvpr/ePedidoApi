package com.mpxds.apirest.service.exceptions;

public class MpFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MpFileException(String msg) {
		super(msg);
	}
	
	public MpFileException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
