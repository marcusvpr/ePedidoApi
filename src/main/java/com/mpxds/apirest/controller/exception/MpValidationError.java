package com.mpxds.apirest.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class MpValidationError extends MpStandardError {
	//
	private static final long serialVersionUID = 1L;

	private List<MpFieldMessage> errors = new ArrayList<>();

	public MpValidationError(Long timestamp, Integer status, String error, String message, String path) {
		//
		super(timestamp, status, error, message, path);
	}

	public List<MpFieldMessage> getErrors() {
		//
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		//
		errors.add(new MpFieldMessage(fieldName, messagem));
	}
}
