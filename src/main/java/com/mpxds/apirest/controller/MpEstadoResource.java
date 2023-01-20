package com.mpxds.apirest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mpxds.apirest.model.MpCidade;
import com.mpxds.apirest.model.MpEstado;
import com.mpxds.apirest.model.dto.MpCidadeDTO;
import com.mpxds.apirest.model.dto.MpEstadoDTO;
import com.mpxds.apirest.service.MpCidadeService;
import com.mpxds.apirest.service.MpEstadoService;

@RestController
@RequestMapping(value="/estados")
public class MpEstadoResource {
	
	@Autowired
	private MpEstadoService service;
	
	@Autowired
	private MpCidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MpEstadoDTO>> findAll() {
		//
		List<MpEstado> list = service.findAll();
		List<MpEstadoDTO> listDto = list.stream().map(obj -> new MpEstadoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<MpCidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		//
		List<MpCidade> list = cidadeService.findByMpEstado(estadoId);
		List<MpCidadeDTO> listDto = list.stream().map(obj -> new MpCidadeDTO(obj)).collect(Collectors.toList());  

		return ResponseEntity.ok().body(listDto);
	}
}
