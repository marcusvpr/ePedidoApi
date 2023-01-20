package com.mpxds.apirest.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 
@Entity
public class MpCidade extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="mpEstado_id")
	private MpEstado mpEstado;
	
	public MpCidade() { }

	public MpCidade(Integer id, String nome, MpEstado mpEstado) {
		//
		super();
		
		this.id = id;
		this.nome = nome;
		this.mpEstado = mpEstado;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public MpEstado getMpEstado() { return mpEstado; }
	public void setMpEstado(MpEstado mpEstado) { this.mpEstado = mpEstado; }
	
}
