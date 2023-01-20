package com.mpxds.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpCategoria;
import com.mpxds.apirest.model.MpProduto;
import com.mpxds.apirest.repository.MpCategoriaRepository;
import com.mpxds.apirest.repository.MpProdutoRepository;
import com.mpxds.apirest.service.exceptions.MpObjectNotFoundException;

@Service
public class MpProdutoService {
	//
	@Autowired
	private MpProdutoRepository repo;
	
	@Autowired
	private MpCategoriaRepository categoriaRepository;
	
	public MpProduto find(Integer id) {
		//
		Optional<MpProduto> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new MpObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + MpProduto.class.getName()));
	}

	public Page<MpProduto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
																									String direction) {
		//
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<MpCategoria> categorias = categoriaRepository.findAllById(ids);
		
		return repo.findDistinctByNomeContainingAndMpCategoriasIn(nome, categorias, pageRequest);	
	}
}
