package com.mpxds.apirest.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mpxds.apirest.security.MpUserSS;

public class MpUserService {
	//
	public static MpUserSS authenticated() {
		//
		try {
			//
			return (MpUserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
