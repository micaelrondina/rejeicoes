package br.com.dxc.cards.core.model;

import java.math.BigDecimal;
import java.util.List;

public class IncomingWrapper {
	private int qtd;
	private BigDecimal valorTotal;
	private List<Incoming> listIncoming;
	
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<Incoming> getListIncoming() {
		return listIncoming;
	}
	public void setListIncoming(List<Incoming> listIncoming) {
		this.listIncoming = listIncoming;
	}
}
