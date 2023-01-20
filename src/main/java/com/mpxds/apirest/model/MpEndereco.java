package com.mpxds.apirest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MpEndereco extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="mpCliente_id")
	private MpCliente mpCliente;
	
	@ManyToOne
	@JoinColumn(name="mpCidade_id")
	private MpCidade mpCidade;
	
	public MpEndereco() { }

	public MpEndereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
																				MpCliente mpCliente, MpCidade mpCidade) {
		//
		super();
		
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.mpCliente = mpCliente;
		
		this.setMpCidade(mpCidade);
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public String getLogradouro() { return logradouro; }
	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

	public String getNumero() { return numero; }
	public void setNumero(String numero) { this.numero = numero; }

	public String getComplemento() { return complemento; }
	public void setComplemento(String complemento) { this.complemento = complemento; }

	public String getBairro() { return bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }

	public String getCep() { return cep; }
	public void setCep(String cep) { this.cep = cep; }

	public MpCliente getMpCliente() { return mpCliente; }
	public void setMpCliente(MpCliente mpCliente) { this.mpCliente = mpCliente; }

	public MpCidade getMpCidade() { return mpCidade; }
	public void setMpCidade(MpCidade mpCidade) { this.mpCidade = mpCidade; }
	
}
