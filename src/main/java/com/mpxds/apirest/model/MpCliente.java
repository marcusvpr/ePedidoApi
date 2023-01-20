package com.mpxds.apirest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.mpxds.apirest.model.enums.MpPerfil;
import com.mpxds.apirest.model.enums.MpTipoCliente;

@Entity
public class MpCliente extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	private String nome;
	
	@Column(unique=true)
	private String email;

	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@JsonIgnore
	private String senha;
	
	@OneToMany(mappedBy="mpCliente", cascade=CascadeType.ALL)
	private List<MpEndereco> mpEnderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="mpCliente")
	private List<MpPedido> mpPedidos = new ArrayList<>();
	
	public MpCliente() {
		//
		addPerfil(MpPerfil.CLIENTE);
	}

	public MpCliente(Integer id, String nome, String email, String cpfOuCnpj, MpTipoCliente tipo, String senha) {
		//
		super();
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null) ? null : tipo.getCod();
		this.senha = senha;
		
		addPerfil(MpPerfil.CLIENTE);
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getCpfOuCnpj() { return cpfOuCnpj; }
	public void setCpfOuCnpj(String cpfOuCnpj) { this.cpfOuCnpj = cpfOuCnpj; }

	public MpTipoCliente getTipo() { return MpTipoCliente.toEnum(tipo); }
	public void setTipo(MpTipoCliente tipo) { this.tipo = tipo.getCod(); }

	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }	

	public Set<MpPerfil> getPerfis() { return perfis.stream().map(x -> MpPerfil.toEnum(x)).collect(Collectors.toSet()); }
	public void addPerfil(MpPerfil perfil) { perfis.add(perfil.getCod()); }
	
	public List<MpEndereco> getMpEnderecos() { return mpEnderecos; }
	public void setMpEnderecos(List<MpEndereco> mpEnderecos) { this.mpEnderecos = mpEnderecos; }

	public Set<String> getTelefones() { return telefones; }
	public void setTelefones(Set<String> telefones) { this.telefones = telefones; }

	public List<MpPedido> getMpPedidos() { return mpPedidos; }
	public void setMpPedidos(List<MpPedido> mpPedidos) { this.mpPedidos = mpPedidos; }

}
