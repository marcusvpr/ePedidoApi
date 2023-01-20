package com.mpxds.apirest.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mpxds.apirest.model.enums.MpPerfil;

public class MpUserSS implements UserDetails {
	//
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public MpUserSS() { }
	
	public MpUserSS(Integer id, String email, String senha, Set<MpPerfil> perfis) {
		//
		super();
		
		this.id = id;
		this.email = email;
		this.senha = senha;
		
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() { return id; }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//
		return authorities;
	}

	@Override
	public String getPassword() { return senha; }

	@Override
	public String getUsername() { return email; }

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }
	
	public boolean hasRole(MpPerfil perfil) { 
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao())); }
	
}
