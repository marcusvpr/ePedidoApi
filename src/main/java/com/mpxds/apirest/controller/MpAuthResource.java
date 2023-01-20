package com.mpxds.apirest.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mpxds.apirest.model.dto.MpEmailDTO;
import com.mpxds.apirest.security.MpJWTUtil;
import com.mpxds.apirest.security.MpUserSS;
import com.mpxds.apirest.service.MpAuthService;
import com.mpxds.apirest.service.MpUserService;

@RestController
@RequestMapping(value = "/auth")
public class MpAuthResource {
	//
	@Autowired
	private MpJWTUtil jwtUtil;
	
	@Autowired
	private MpAuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		//
		MpUserSS user = MpUserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody MpEmailDTO objDto) {
		//
		service.sendNewPassword(objDto.getEmail());
		
		return ResponseEntity.noContent().build();
	}
}
