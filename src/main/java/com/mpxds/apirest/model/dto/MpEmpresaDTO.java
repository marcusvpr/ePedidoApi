package com.mpxds.apirest.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public class MpEmpresaDTO implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	//
	private Long id;
	private String razaoSocial;
	private String cnpj;

	public MpEmpresaDTO() { }	
	
	public MpEmpresaDTO(Long id, String razaoSocial, String cnpj) {
		//
		super();
		
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	// ---

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	@NotEmpty(message = "Razão social não pode ser vazia.")
	@Length(min = 5, max = 200, message = "Razão social deve conter entre 5 e 200 caracteres.")
	public String getRazaoSocial() { return razaoSocial; }
	public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido.")
	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj;} 

	@Override
	public String toString() {
		//
		return "EmpresaDto [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}

}
