package com.mpxds.apirest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class MpCategoria extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@ManyToMany(mappedBy="mpCategorias")
	private List<MpProduto> mpProdutos = new ArrayList<>();
	
	public MpCategoria() { }

	public MpCategoria(Integer id, String nome) {
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

	public List<MpProduto> getMpProdutos() { return mpProdutos; }
	public void setMpProdutos(List<MpProduto> mpProdutos) { this.mpProdutos = mpProdutos; }

}
