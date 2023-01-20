package com.mpxds.apirest.model.enums;

public enum MpEstadoPagamento {
	//
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private MpEstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() { return cod; }
	
	public String getDescricao () { return descricao; }
	
	public static MpEstadoPagamento toEnum(Integer cod) {
		//
		if (cod == null) return null;
		//
		for (MpEstadoPagamento x : MpEstadoPagamento.values()) {
			//
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		//
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
