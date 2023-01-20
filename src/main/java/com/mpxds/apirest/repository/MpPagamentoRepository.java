package com.mpxds.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpxds.apirest.model.MpPagamento;

@Repository
public interface MpPagamentoRepository extends JpaRepository<MpPagamento, Integer> {

}
