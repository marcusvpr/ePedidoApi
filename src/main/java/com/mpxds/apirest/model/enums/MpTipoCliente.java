package com.mpxds.apirest.model.enums;

public enum MpTipoCliente {
	//
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private MpTipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() { return cod; }
	
	public String getDescricao () { return descricao; }
	
	public static MpTipoCliente toEnum(Integer cod) {
		//
		if (cod == null) return null;
		//		
		for (MpTipoCliente x : MpTipoCliente.values()) {
			//
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		//
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
