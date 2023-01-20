package com.mpxds.apirest.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mpxds.apirest.model.MpProdutividade;
import com.mpxds.apirest.model.dto.MpProdutividadeDTO;
import com.mpxds.apirest.service.MpProdutividadeService;

@RestController
@RequestMapping(value="/produtividades")
public class MpProdutividadeResource {
	//
	@Autowired
	private MpProdutividadeService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MpProdutividade> find(@PathVariable Integer id) {
		//
		MpProdutividade obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MpProdutividadeDTO objDto) {
		//
		MpProdutividade obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MpProdutividadeDTO objDto, @PathVariable Integer id) {
		//
		MpProdutividade obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MpProdutividadeDTO>> findAll() {
		//
		List<MpProdutividade> list = service.findAll();
		List<MpProdutividadeDTO> listDto = list.stream().map(obj -> new MpProdutividadeDTO(obj)).collect(Collectors.toList());  

		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<MpProdutividadeDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		//
		Page<MpProdutividade> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<MpProdutividadeDTO> listDto = list.map(obj -> new MpProdutividadeDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
	
}
