package com.mpxds.apirest.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.mpxds.apirest.model.MpProdutividade;
import com.mpxds.apirest.model.MpTabelaInterna;

public class MpProdutividadeDTO implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message="Preenchimento obrigatório")
	private MpTabelaInterna mpTipoServicoTI;

	private String servico;
	
	private BigDecimal produtividadeValor;
	private String produtividadeUnidade;
	private BigDecimal producaoValor;
	private String producaoUnidade;
	private BigDecimal producaoDiaValor;
	private String producaoDiaUnidade;
	private String equipe;
	private MpTabelaInterna mpCategoriaTI;
	
	public MpProdutividadeDTO() { }
	
	public MpProdutividadeDTO(MpProdutividade obj) {
		//
		id = obj.getId();
		this.mpTipoServicoTI = obj.getMpTipoServicoTI();
		this.servico = obj.getServico();
		this.produtividadeValor = obj.getProdutividadeValor();
		this.produtividadeUnidade = obj.getProdutividadeUnidade();
		this.producaoValor = obj.getProducaoValor();
		this.producaoUnidade = obj.getProducaoUnidade();
		this.producaoDiaValor = obj.getProducaoDiaValor();
		this.producaoDiaUnidade = obj.getProducaoDiaUnidade();
		this.equipe = obj.getEquipe();
		this.mpCategoriaTI = obj.getMpCategoriaTI();
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public MpTabelaInterna getMpTipoServicoTI() { return mpTipoServicoTI; }
	public void setMpTipoServicoTI(MpTabelaInterna mpTipoServicoTI) { this.mpTipoServicoTI = mpTipoServicoTI; }

	public String getServico() { return servico; }
	public void setServico(String servico) { this.servico = servico; }

	public BigDecimal getProdutividadeValor() { return produtividadeValor; }
	public void setProdutividadeValor(BigDecimal produtividadeValor) { this.produtividadeValor = produtividadeValor; }

	public String getProdutividadeUnidade() { return produtividadeUnidade; }
	public void setProdutividadeUnidade(String produtividadeUnidade) { this.produtividadeUnidade = produtividadeUnidade; }

	public BigDecimal getProducaoValor() { return producaoValor; }
	public void setProducaoValor(BigDecimal producaoValor) { this.producaoValor = producaoValor; }

	public String getProducaoUnidade() { return producaoUnidade; }
	public void setProducaoUnidade(String producaoUnidade) { this.producaoUnidade = producaoUnidade; }

	public BigDecimal getProducaoDiaValor() { return producaoDiaValor; }
	public void setProducaoDiaValor(BigDecimal producaoDiaValor) { this.producaoDiaValor = producaoDiaValor; }

	public String getProducaoDiaUnidade() { return producaoDiaUnidade; }
	public void setProducaoDiaUnidade(String producaoDiaUnidade) { this.producaoDiaUnidade = producaoDiaUnidade; }

	public String getEquipe() { return equipe; }
	public void setEquipe(String equipe) { this.equipe = equipe; }

	public MpTabelaInterna getMpCategoriaTI() { return mpCategoriaTI; }
	public void setMpCategoriaTI(MpTabelaInterna mpCategoriaTI) { this.mpCategoriaTI = mpCategoriaTI; }
	
}
