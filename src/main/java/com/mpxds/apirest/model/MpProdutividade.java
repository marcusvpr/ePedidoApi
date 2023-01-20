package com.mpxds.apirest.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class MpProdutividade extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Por favor, informe o TIPO SERVIÇO")	
	@ManyToOne
	@JoinColumn(name = "mpTipoServicoTI_id")
	private MpTabelaInterna mpTipoServicoTI;
	
	@Column(name = "servico")
	private String servico;

	@Column(name = "produtividade_Valor")
	private BigDecimal produtividadeValor;

	@Column(name = "produtividade_Unidade")
	private String produtividadeUnidade;

	@Column(name = "producao_Valor")
	private BigDecimal producaoValor;

	@Column(name = "producao_Unidade")
	private String producaoUnidade;
	
	@Column(name = "producao_Dia_Valor")
	private BigDecimal producaoDiaValor;
	
	@Column(name = "producao_Dia_Unidade")
	private String producaoDiaUnidade;
	
	private String equipe;
	
	@ManyToOne
	@JoinColumn(name = "mpCategoriaTI_id")
	private MpTabelaInterna mpCategoriaTI;
	
	public MpProdutividade() { }
	
	public MpProdutividade(Integer id, MpTabelaInterna mpTipoServicoTI, String servico, 
			BigDecimal produtividadeValor, String produtividadeUnidade,
			BigDecimal producaoValor, String producaoUnidade, 
			BigDecimal producaoDiaValor, String producaoDiaUnidade,
			String equipe, MpTabelaInterna mpCategoriaTI) {
		//
		super();
		
		this.id = id;
		this.mpTipoServicoTI = mpTipoServicoTI;
		this.servico = servico;
		this.produtividadeValor = produtividadeValor;
		this.produtividadeUnidade = produtividadeUnidade;
		this.producaoValor = producaoValor;
		this.producaoUnidade = producaoUnidade;
		this.producaoDiaValor = producaoDiaValor;
		this.producaoDiaUnidade = producaoDiaUnidade;
		this.equipe = equipe;
		this.mpCategoriaTI = mpCategoriaTI;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}
	
	//

	public MpTabelaInterna getMpTipoServicoTI() { return mpTipoServicoTI; }
	public void setMpTipoServicoTI(MpTabelaInterna mpTipoServicoTI) { this.mpTipoServicoTI = mpTipoServicoTI; }

	public String getServico() { return servico; }
	public void setServico(String servico) { this.servico = servico; }

	@Column(name = "produtividade_Valor")
	public BigDecimal getProdutividadeValor() { return produtividadeValor; }
	public void setProdutividadeValor(BigDecimal produtividadeValor) { this.produtividadeValor = produtividadeValor; }

	public String getProdutividadeUnidade() { return produtividadeUnidade; }
	public void setProdutividadeUnidade(String produtividadeUnidade) { this.produtividadeUnidade = produtividadeUnidade; }

	@Column(name = "producao_Valor")
	public BigDecimal getProducaoValor() { return producaoValor; }
	public void setProducaoValor(BigDecimal producaoValor) { this.producaoValor = producaoValor; }

	@Column(name = "producao_Unidade")
	public String getProducaoUnidade() { return producaoUnidade; }
	public void setProducaoUnidade(String producaoUnidade) { this.producaoUnidade = producaoUnidade; }

	@Column(name = "producao_dia_valor")
	public BigDecimal getProducaoDiaValor() { return producaoDiaValor; }
	public void setProducaoDiaValor(BigDecimal producaoDiaValor) { this.producaoDiaValor = producaoDiaValor; }

	@Column(name = "producao_dia_unidade")
	public String getProducaoDiaUnidade() { return producaoDiaUnidade; }
	public void setProducaoDiaUnidade(String producaoDiaUnidade) { this.producaoDiaUnidade = producaoDiaUnidade; }

	public String getEquipe() { return equipe; }
	public void setEquipe(String equipe) { this.equipe = equipe; }

	public MpTabelaInterna getMpCategoriaTI() { return mpCategoriaTI; }
	public void setMpCategoriaTI(MpTabelaInterna mpCategoriaTI) { this.mpCategoriaTI = mpCategoriaTI; }
	
	//

	@Override
	public String toString() {
		return "MpProdutividade [id=" + id 
				+ ", mpTipoServicoTI=" + mpTipoServicoTI.getDescricao()
				+ ", servico=" + servico
				+ ", produtividadeValor=" + produtividadeValor
				+ ", produtividadeUnidade=" + produtividadeUnidade + ", producaoValor=" + producaoValor
				+ ", producaoUnidade=" + producaoUnidade + ", producaoDiaValor=" + producaoDiaValor
				+ ", producaoDiaUnidade=" + producaoDiaUnidade + ", equipe=" + equipe
				+ ", mpCategoriaTI=" + mpCategoriaTI.getDescricao()
				+ "]";
	}
		
}
