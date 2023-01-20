package com.mpxds.apirest.model;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mpxds.apirest.model.enums.MpEstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class MpPagamentoComCartao extends MpPagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;
	
	public MpPagamentoComCartao() {	}

	public MpPagamentoComCartao(Integer id, MpEstadoPagamento mpEstado, MpPedido mpPedido, Integer numeroDeParcelas) {
		//
		super(id, mpEstado, mpPedido);
		this.numeroDeParcelas = numeroDeParcelas;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Integer getNumeroDeParcelas() { return numeroDeParcelas; }
	public void setNumeroDeParcelas(Integer numeroDeParcelas) { this.numeroDeParcelas = numeroDeParcelas; }
	
	
		
}
