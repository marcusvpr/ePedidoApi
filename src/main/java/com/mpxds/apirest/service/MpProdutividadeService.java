package com.mpxds.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpProdutividade;
import com.mpxds.apirest.model.dto.MpProdutividadeDTO;
import com.mpxds.apirest.repository.MpProdutividadeRepository;
import com.mpxds.apirest.service.exceptions.MpDataIntegrityException;
import com.mpxds.apirest.service.exceptions.MpObjectNotFoundException;

@Service
public class MpProdutividadeService {
	//
	@Autowired
	private MpProdutividadeRepository repo;

	public MpProdutividade find(Integer id) {
		//
		Optional<MpProdutividade> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new MpObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + MpProdutividade.class.getName()));
	}
	
	public MpProdutividade insert(MpProdutividade obj) {
		//
		obj.setId(null);
		
		return repo.save(obj);
	}
	
	public MpProdutividade update(MpProdutividade obj) {
		//
		MpProdutividade newObj = find(obj.getId());
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
			throw new MpDataIntegrityException("Não é possível excluir uma PRODUTIVIDADE que possui ORÇAMENTO");
		}
	}
	
	public List<MpProdutividade> findAll() {
		//
		return repo.findAll();
	}
	
	public Page<MpProdutividade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		//
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public MpProdutividade fromDTO(MpProdutividadeDTO obj) {
		//
		return new MpProdutividade(obj.getId(),
									obj.getMpTipoServicoTI(),
									obj.getServico(),
									obj.getProdutividadeValor(),
									obj.getProdutividadeUnidade(),
									obj.getProducaoValor(),
									obj.getProducaoUnidade(),
									obj.getProducaoDiaValor(),
									obj.getProducaoDiaUnidade(),
									obj.getEquipe(),
									obj.getMpCategoriaTI());
	}
	
	private void updateData(MpProdutividade newObj, MpProdutividade obj) {
		//
		newObj.setMpTipoServicoTI(obj.getMpTipoServicoTI());
		newObj.setServico(obj.getServico());
		newObj.setProdutividadeValor(obj.getProdutividadeValor());
		newObj.setProdutividadeUnidade(obj.getProdutividadeUnidade());
		newObj.setProducaoValor(obj.getProducaoValor());
		newObj.setProducaoUnidade(obj.getProducaoUnidade());
		newObj.setProducaoDiaValor(obj.getProducaoDiaValor());
		newObj.setProducaoDiaUnidade(obj.getProducaoDiaUnidade());
		newObj.setEquipe(obj.getEquipe());
		newObj.setMpCategoriaTI(obj.getMpCategoriaTI());
	}
	
}
