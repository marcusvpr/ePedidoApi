package com.mpxds.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpCidade;
import com.mpxds.apirest.repository.MpCidadeRepository;

@Service
public class MpCidadeService {
	//
	@Autowired
	private MpCidadeRepository repo;

	public List<MpCidade> findByMpEstado(Integer estadoId) {
		//
		return repo.findMpCidades(estadoId);
	}
}
