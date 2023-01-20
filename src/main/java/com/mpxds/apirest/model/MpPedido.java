package com.mpxds.apirest.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class MpPedido extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="mpPedido")
	private MpPagamento mpPagamento;

	@ManyToOne
	@JoinColumn(name="mpCliente_id")
	private MpCliente mpCliente;
	
	@ManyToOne
	@JoinColumn(name="mpEndereco_de_entrega_id")
	private MpEndereco mpEnderecoDeEntrega;
	
	@OneToMany(mappedBy="mpPedido")
	private Set<MpItemPedido> mpItens = new HashSet<>();
	
	public MpPedido() { }

	public MpPedido(Integer id, Date instante, MpCliente mpCliente, MpEndereco mpEnderecoDeEntrega) {
		//
		super();
		
		this.id = id;
		this.instante = instante;
		this.mpCliente = mpCliente;
		this.mpEnderecoDeEntrega = mpEnderecoDeEntrega;
		//
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public double getValorTotal() {
		//
		double soma = 0.0;
		for (MpItemPedido ip : mpItens) {
			soma = soma + ip.getSubTotal();
		}
		//
		return soma;
	}
	
	public Date getInstante() { return instante; }
	public void setInstante(Date instante) { this.instante = instante; }

	public MpPagamento getMpPagamento() { return mpPagamento; }
	public void setMpPagamento(MpPagamento mpPagamento) { this.mpPagamento = mpPagamento; }

	public MpCliente getMpCliente() { return mpCliente; }
	public void setMpCliente(MpCliente mpCliente) { this.mpCliente = mpCliente; }

	public MpEndereco getMpEnderecoDeEntrega() { return mpEnderecoDeEntrega; }
	public void setMpEnderecoDeEntrega(MpEndereco mpEnderecoDeEntrega) { this.mpEnderecoDeEntrega = mpEnderecoDeEntrega; }

	public Set<MpItemPedido> getMpItens() { return mpItens; }
	public void setMpItens(Set<MpItemPedido> mpItens) { this.mpItens = mpItens; }
	
	// ---
	
	@Override
	public String toString() {
		//
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", MpCliente: ");
		builder.append(getMpCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getMpPagamento().getMpEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		
		for (MpItemPedido ip : getMpItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		//
		return builder.toString();
	}
	
}
