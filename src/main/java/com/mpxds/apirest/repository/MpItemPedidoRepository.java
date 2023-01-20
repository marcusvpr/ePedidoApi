package com.mpxds.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpxds.apirest.model.MpItemPedido;

@Repository
public interface MpItemPedidoRepository extends JpaRepository<MpItemPedido, Integer> {

}
