package com.mpxds.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpxds.apirest.model.MpProdutividade;

@Repository
public interface MpProdutividadeRepository extends JpaRepository<MpProdutividade, Integer> {

}
