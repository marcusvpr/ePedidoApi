package com.mpxds.apirest.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import com.mpxds.apirest.service.exceptions.MpAuthorizationException;
import com.mpxds.apirest.service.exceptions.MpDataIntegrityException;
import com.mpxds.apirest.service.exceptions.MpFileException;
import com.mpxds.apirest.service.exceptions.MpObjectNotFoundException;

@ControllerAdvice
public class MpResourceExceptionHandler {
	//
	@ExceptionHandler(MpObjectNotFoundException.class)
	public ResponseEntity<MpStandardError> objectNotFound(MpObjectNotFoundException e, HttpServletRequest request) {
		//
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", 
																						e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(MpDataIntegrityException.class)
	public ResponseEntity<MpStandardError> dataIntegrity(MpDataIntegrityException e, HttpServletRequest request) {
		//
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados",
																							e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MpStandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		//
		MpValidationError err = new MpValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), 
																	"Erro de validação", e.getMessage(), request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		//
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(MpAuthorizationException.class)
	public ResponseEntity<MpStandardError> authorization(MpAuthorizationException e, HttpServletRequest request) {
		//
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado",
																						e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(MpFileException.class)
	public ResponseEntity<MpStandardError> file(MpFileException e, HttpServletRequest request) {
		//
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de arquivo",
																						e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<MpStandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		//
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), code.value(), "Erro Amazon Service", e.getMessage(),
																										request.getRequestURI());
		return ResponseEntity.status(code).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<MpStandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		//
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client",
																						e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<MpStandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		
		MpStandardError err = new MpStandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro S3",
																						e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
