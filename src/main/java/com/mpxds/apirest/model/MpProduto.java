package com.mpxds.apirest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MpProduto extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Double preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "mpProduto_id"),
		inverseJoinColumns = @JoinColumn(name = "mpCategoria_id")
	)
	private List<MpCategoria> mpCategorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="mpProduto")
	private Set<MpItemPedido> mpItens = new HashSet<>();
	
	public MpProduto() { }

	public MpProduto(Integer id, String nome, Double preco) {
		//
		super();
		
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	@JsonIgnore
	public List<MpPedido> getMpPedidos() {
		//
		List<MpPedido> lista = new ArrayList<>();
//		for (MpItemPedido x : mpItens) {
//			lista.add(x.getMpPedido());
//		}
		//
		return lista;
	}
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public Double getPreco() { return preco; }
	public void setPreco(Double preco) { this.preco = preco; }

	public List<MpCategoria> getMpCategorias() { return mpCategorias; }
	public void setMpCategorias(List<MpCategoria> mpCategorias) { this.mpCategorias = mpCategorias; }

	public Set<MpItemPedido> getMpItens() { return mpItens; }
	public void setMpItens(Set<MpItemPedido> mpItens) { this.mpItens = mpItens; }

}
