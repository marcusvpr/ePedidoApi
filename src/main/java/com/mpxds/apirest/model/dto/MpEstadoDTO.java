package com.mpxds.apirest.model.dto;

import java.io.Serializable;

import com.mpxds.apirest.model.MpEstado;

public class MpEstadoDTO implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public MpEstadoDTO() { }

	public MpEstadoDTO(MpEstado obj) {
		//
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) {	this.nome = nome; }

}
