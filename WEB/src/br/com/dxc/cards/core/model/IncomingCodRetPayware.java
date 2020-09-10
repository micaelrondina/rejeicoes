package br.com.dxc.cards.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class IncomingCodRetPayware {
	private IdIncoming id;
	private String dataMovFormatada;
	private BigDecimal valorVenda;
	private int numParcela;
	private int qtdTotParcelas;
	private String numRefTransacao;
	private Date dhEnvioArqPayware;
	private int returnCodePayware;
	private String returnDescriptionPayware;
	
	public IdIncoming getId() {
		return id;
	}
	public void setId(IdIncoming id) {
		this.id = id;
	}
	public String getDataMovFormatada() {
		return dataMovFormatada;
	}
	public void setDataMovFormatada(String dataMovFormatada) {
		this.dataMovFormatada = dataMovFormatada;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public int getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(int numParcela) {
		this.numParcela = numParcela;
	}
	public int getQtdTotParcelas() {
		return qtdTotParcelas;
	}
	public void setQtdTotParcelas(int qtdTotParcelas) {
		this.qtdTotParcelas = qtdTotParcelas;
	}
	public String getNumRefTransacao() {
		return numRefTransacao;
	}
	public void setNumRefTransacao(String numRefTransacao) {
		this.numRefTransacao = numRefTransacao;
	}
	public Date getDhEnvioArqPayware() {
		return dhEnvioArqPayware;
	}
	public void setDhEnvioArqPayware(Date dhEnvioArqPayware) {
		this.dhEnvioArqPayware = dhEnvioArqPayware;
	}
	public int getReturnCodePayware() {
		return returnCodePayware;
	}
	public void setReturnCodePayware(int returnCodePayware) {
		this.returnCodePayware = returnCodePayware;
	}
	public String getReturnDescriptionPayware() {
		return returnDescriptionPayware;
	}
	public void setReturnDescriptionPayware(String returnDescriptionPayware) {
		this.returnDescriptionPayware = returnDescriptionPayware;
	}
}
