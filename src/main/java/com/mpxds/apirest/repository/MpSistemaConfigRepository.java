package com.mpxds.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.apirest.model.MpSistemaConfig;

public interface MpSistemaConfigRepository extends JpaRepository<MpSistemaConfig, Long> {
	// 
	Optional<MpSistemaConfig> findByParametro(String parametro);
	
	@Transactional(readOnly=true)
	public List<MpSistemaConfig> findAllByOrderByParametro();
	
	MpSistemaConfig findFirstByParametroIsNotNullOrderByParametroAsc();
	MpSistemaConfig findFirstByParametroIsNotNullOrderByParametroDesc();
	
    public List<MpSistemaConfig> findTop1ByParametroGreaterThanOrderByParametro(String Parametro);
    public List<MpSistemaConfig> findTop1ByParametroLessThanOrderByParametroDesc(String Parametro);
}
