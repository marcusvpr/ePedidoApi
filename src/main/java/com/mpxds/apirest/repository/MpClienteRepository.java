package com.mpxds.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.apirest.model.MpCliente;

@Repository
public interface MpClienteRepository extends JpaRepository<MpCliente, Integer> {
	//
	@Transactional(readOnly=true)
	MpCliente findByEmail(String email);
}
