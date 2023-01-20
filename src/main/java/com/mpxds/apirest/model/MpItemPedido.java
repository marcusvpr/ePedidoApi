package com.mpxds.apirest.model;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

//import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MpItemPedido extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
//	@JsonIgnore
//	@EmbeddedId
//	private MpItemPedidoPK idx = new MpItemPedidoPK();

	@ManyToOne
	@JoinColumn(name="mpPedido_id")
	private MpPedido mpPedido;

	@ManyToOne
	@JoinColumn(name="mpProduto_id")
	private MpProduto mpProduto;
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public MpItemPedido() { }

	public MpItemPedido(MpPedido mpPedido, MpProduto mpProduto, Double desconto, Integer quantidade, Double preco) {
		//
		super();
		
//		idx.setMpPedido(mpPedido);
//		idx.setMpProduto(mpProduto);

		this.mpPedido = mpPedido;
		this.mpProduto = mpProduto;
		
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public double getSubTotal() {
		//
		return (preco - desconto) * quantidade;
	}
	
//	@JsonIgnore
//	public MpPedido getMpPedido() { return idx.getMpPedido(); }
//	public void setMpPedido(MpPedido mpPedido) { idx.setMpPedido(mpPedido); }
//	
//	public MpProduto getMpProduto() { return idx.getMpProduto(); }
//	public void setMpProduto(MpProduto mpProduto) { idx.setMpProduto(mpProduto); }
//	
//	public MpItemPedidoPK getIdx() { return idx; }
//	public void setIdx(MpItemPedidoPK idx) { this.idx = idx; }

	public MpPedido getMpPedido() { return mpPedido; }
	public void setMpPedido(MpPedido mpPedido) { this.mpPedido = mpPedido; }

	public MpProduto getMpProduto() { return mpProduto; }
	public void setMpProduto(MpProduto mpProduto) { this.mpProduto = mpProduto; }
	
	public Double getDesconto() { return desconto; }
	public void setDesconto(Double desconto) { this.desconto = desconto; }

	public Integer getQuantidade() { return quantidade; }
	public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

	public Double getPreco() { return preco; }
	public void setPreco(Double preco) { this.preco = preco; }

	// ---
		
	@Override
	public String toString() {
		//
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
//		builder.append(getMpProduto().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		//
		return builder.toString();
	}
	
}
