package com.mpxds.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpCliente;
import com.mpxds.apirest.repository.MpClienteRepository;
import com.mpxds.apirest.security.MpUserSS;

@Service
public class MpUserDetailsServiceImpl implements UserDetailsService {
	//
	@Autowired
	private MpClienteRepository mpRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//
		MpCliente mpCli = mpRepo.findByEmail(email);
		if (mpCli == null) {
			throw new UsernameNotFoundException(email);
		}
		//
		return new MpUserSS(mpCli.getId(), mpCli.getEmail(), mpCli.getSenha(), mpCli.getPerfis());
	}
	
}
