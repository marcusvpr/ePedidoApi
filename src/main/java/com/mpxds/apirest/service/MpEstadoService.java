package com.mpxds.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpEstado;
import com.mpxds.apirest.repository.MpEstadoRepository;

@Service
public class MpEstadoService {
	//
	@Autowired
	private MpEstadoRepository repo;
	
	public List<MpEstado> findAll() {
		//
		return repo.findAllByOrderByNome();
	}
}
