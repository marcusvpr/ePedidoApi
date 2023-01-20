package com.mpxds.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.apirest.model.MpCidade;

@Repository
public interface MpCidadeRepository extends JpaRepository<MpCidade, Integer> {
	//
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM MpCidade obj WHERE obj.mpEstado.id = :estadoId ORDER BY obj.nome")
	public List<MpCidade> findMpCidades(@Param("estadoId") Integer estado_id);
}
