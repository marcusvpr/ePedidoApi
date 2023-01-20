package com.mpxds.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpxds.apirest.model.dto.MpEmpresaDTO;
import com.mpxds.apirest.response.MpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/empresa")
public class MpEmpresaController {

	@ApiOperation(value="Cadastra uma Empresa")
	@PostMapping
	public ResponseEntity<MpResponse<MpEmpresaDTO>> cadastrar(@Valid @RequestBody MpEmpresaDTO mpEmpresaDto,
																										BindingResult result) {
		//
		MpResponse<MpEmpresaDTO> mpResponse = new MpResponse<MpEmpresaDTO>();

		if (result.hasErrors()) {
			//
			result.getAllErrors().forEach(error -> mpResponse.getErrors().add(error.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(mpResponse);
		}
		//
		mpEmpresaDto.setId(1L);
		mpResponse.setData(mpEmpresaDto);
		//
		return ResponseEntity.ok(mpResponse);
	}
	
	@ApiOperation(value="Retorna uma lista de Empresas")
	@GetMapping("/lista")
	public List<MpEmpresaDTO> listaMpEmpresaDtos() {
		//
		List<MpEmpresaDTO> mpEmpresaDtoList = new ArrayList<MpEmpresaDTO>(); 
		
		mpEmpresaDtoList.add(new MpEmpresaDTO(1L, "Empresa A", "1234567890"));
		mpEmpresaDtoList.add(new MpEmpresaDTO(2L, "Empresa B", "1111167890"));
		mpEmpresaDtoList.add(new MpEmpresaDTO(3L, "Empresa C", "2222267890"));
		mpEmpresaDtoList.add(new MpEmpresaDTO(4L, "Empresa D", "3333367890"));
		mpEmpresaDtoList.add(new MpEmpresaDTO(5L, "Empresa E", "4444467890"));
		
//		return produtoRepository.findAll();
		return mpEmpresaDtoList;
	}
	
}
