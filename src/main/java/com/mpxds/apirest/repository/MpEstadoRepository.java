package com.mpxds.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.apirest.model.MpEstado;

@Repository
public interface MpEstadoRepository extends JpaRepository<MpEstado, Integer> {
	//
	@Transactional(readOnly=true)
	public List<MpEstado> findAllByOrderByNome();
}
