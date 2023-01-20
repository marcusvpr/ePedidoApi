package com.mpxds.apirest.model;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mpxds.apirest.model.enums.MpEstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class MpPagamentoComBoleto extends MpPagamento {
	//
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;

	public MpPagamentoComBoleto() { }

	public MpPagamentoComBoleto(Integer id, MpEstadoPagamento mpEstado, MpPedido mpPedido, Date dataVencimento, 
																										Date dataPagamento) {
		//
		super(id, mpEstado, mpPedido);
		
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;		
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Date getDataVencimento() { return dataVencimento; }
	public void setDataVencimento(Date dataVencimento) { this.dataVencimento = dataVencimento; }

	public Date getDataPagamento() { return dataPagamento; }
	public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }	
	
}
