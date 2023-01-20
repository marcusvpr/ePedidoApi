package com.mpxds.apirest.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mpxds.apirest.service.MpDBService;
import com.mpxds.apirest.service.MpEmailService;
import com.mpxds.apirest.service.MpMockEmailService;

@Configuration
@Profile("test")
public class MpTestConfig {
	//
	@Autowired
	private MpDBService mpDbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		//
		mpDbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public MpEmailService mpEmailService() {
		//
		return new MpMockEmailService();
	}
}
