package com.mpxds.apirest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MpEstado extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="mpEstado")
	private List<MpCidade> mpCidades = new ArrayList<>();
	
	public MpEstado() { }

	public MpEstado(Integer id, String nome) {
		//
		super();
		
		this.id = id;
		this.nome = nome;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public List<MpCidade> getMpCidades() { return mpCidades; }
	public void setMpCidades(List<MpCidade> mpCidades) { this.mpCidades = mpCidades; }
	
}
