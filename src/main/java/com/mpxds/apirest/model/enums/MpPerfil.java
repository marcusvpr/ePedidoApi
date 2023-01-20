package com.mpxds.apirest.model.enums;

public enum MpPerfil {
	//
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private MpPerfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() { return cod; }
	
	public String getDescricao () { return descricao; }
	
	public static MpPerfil toEnum(Integer cod) {
		//
		if (cod == null) return null;
		//		
		for (MpPerfil x : MpPerfil.values()) {
			//
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		//
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
