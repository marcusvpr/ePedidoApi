package com.mpxds.apirest.model.dto;

import java.io.Serializable;

public class MpCredenciaisDTO implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	public MpCredenciaisDTO() { }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }
	
}
