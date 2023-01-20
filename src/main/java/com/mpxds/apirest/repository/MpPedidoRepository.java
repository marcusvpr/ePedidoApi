package com.mpxds.apirest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.apirest.model.MpCliente;
import com.mpxds.apirest.model.MpPedido;

@Repository
public interface MpPedidoRepository extends JpaRepository<MpPedido, Integer> {

	@Transactional(readOnly=true)
	Page<MpPedido> findByMpCliente(MpCliente mpCliente, Pageable pageRequest);
}
