package com.mpxds.apirest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.mpxds.apirest.model.enums.MpEstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class MpPagamento extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="mpPedido_id")
	@MapsId
	private MpPedido mpPedido;
	
	public MpPagamento() { }

	public MpPagamento(Integer id, MpEstadoPagamento mpEstado, MpPedido mpPedido) {
		//
		super();
		
		this.id = id;
		this.estado = (mpEstado==null) ? null : mpEstado.getCod();
		
		this.mpPedido = mpPedido;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public MpEstadoPagamento getMpEstado() { return MpEstadoPagamento.toEnum(estado); }
	public void setMpEstado(MpEstadoPagamento mpEstado) { this.estado = mpEstado.getCod(); }

	public MpPedido getMpPedido() { return mpPedido; }
	public void setMpPedido(MpPedido mpPedido) { this.mpPedido = mpPedido; }
	
}
