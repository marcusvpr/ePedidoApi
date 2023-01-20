package com.mpxds.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpCategoria;
import com.mpxds.apirest.model.dto.MpCategoriaDTO;
import com.mpxds.apirest.repository.MpCategoriaRepository;
import com.mpxds.apirest.service.exceptions.MpDataIntegrityException;
import com.mpxds.apirest.service.exceptions.MpObjectNotFoundException;

@Service
public class MpCategoriaService {
	//
	@Autowired
	private MpCategoriaRepository repo;

	public MpCategoria find(Integer id) {
		//
		Optional<MpCategoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new MpObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + MpCategoria.class.getName()));
	}
	
	public MpCategoria insert(MpCategoria obj) {
		//
		obj.setId(null);
		return repo.save(obj);
	}
	
	public MpCategoria update(MpCategoria obj) {
		//
		MpCategoria newObj = find(obj.getId());
		updateData(newObj, obj);
		
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		//
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new MpDataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<MpCategoria> findAll() {
		//
		return repo.findAll();
	}
	
	public Page<MpCategoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		//
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public MpCategoria fromDTO(MpCategoriaDTO objDto) {
		//
		return new MpCategoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(MpCategoria newObj, MpCategoria obj) {
		//
		newObj.setNome(obj.getNome());
	}
	
}
