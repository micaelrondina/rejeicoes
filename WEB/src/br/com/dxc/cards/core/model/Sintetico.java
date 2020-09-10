package br.com.dxc.cards.core.model;

import java.math.BigDecimal;

public class Sintetico {

    private String dataOutgoing;
    private String dataOutgoingFormatada;
    private String nomeArqOutgoing;
    private Integer qtdOutgoing;
    private BigDecimal valorTotalOutgoing;
    private String dataRetornoIncomingParcial;
    private String dataRetornoIncomingParcialFormatada;
    private String nomeArqIncomingParcial;
    private Integer qtdIncomingParcial;
    private BigDecimal valorIncomingParcial;
    private String dataRetornoIncomingTotal;
    private String dataRetornoIncomingTotalFormatada;
    private String nomeArqIncomingTotal;
    private Integer qtdIncomingTotal;
    private BigDecimal valorIncomingTotal;
    private Integer qtdLiquido;
    private BigDecimal valorLiquido;
    private int rejTotal;

    public String getDataOutgoing() {
        return this.dataOutgoing;
    }

    public void setDataOutgoing(String dataOutgoing) {
        this.dataOutgoing = dataOutgoing;
    }

    public String getDataOutgoingFormatada() {
        return this.dataOutgoingFormatada;
    }

    public void setDataOutgoingFormatada(String dataOutgoingFormatada) {
        this.dataOutgoingFormatada = dataOutgoingFormatada;
    }

    public String getNomeArqOutgoing() {
        return this.nomeArqOutgoing;
    }

    public void setNomeArqOutgoing(String nomeArqOutgoing) {
        this.nomeArqOutgoing = nomeArqOutgoing;
    }

    public Integer getQtdOutgoing() {
        return this.qtdOutgoing;
    }

    public void setQtdOutgoing(Integer qtdOutgoing) {
        this.qtdOutgoing = qtdOutgoing;
    }

    public BigDecimal getValorTotalOutgoing() {
        return this.valorTotalOutgoing;
    }

    public void setValorTotalOutgoing(BigDecimal valorTotalOutgoing) {
        this.valorTotalOutgoing = valorTotalOutgoing;
    }

    public String getDataRetornoIncomingParcial() {
        return this.dataRetornoIncomingParcial;
    }

    public void setDataRetornoIncomingParcial(String dataRetornoIncomingParcial) {
        this.dataRetornoIncomingParcial = dataRetornoIncomingParcial;
    }

    public String getDataRetornoIncomingParcialFormatada() {
        return this.dataRetornoIncomingParcialFormatada;
    }

    public void setDataRetornoIncomingParcialFormatada(String dataRetornoIncomingParcialFormatada) {
        this.dataRetornoIncomingParcialFormatada = dataRetornoIncomingParcialFormatada;
    }

    public String getNomeArqIncomingParcial() {
        return this.nomeArqIncomingParcial;
    }

    public void setNomeArqIncomingParcial(String nomeArqIncomingParcial) {
        this.nomeArqIncomingParcial = nomeArqIncomingParcial;
    }

    public Integer getQtdIncomingParcial() {
        return this.qtdIncomingParcial;
    }

    public void setQtdIncomingParcial(Integer qtdIncomingParcial) {
        this.qtdIncomingParcial = qtdIncomingParcial;
    }

    public BigDecimal getValorIncomingParcial() {
        return this.valorIncomingParcial;
    }

    public void setValorIncomingParcial(BigDecimal valorIncomingParcial) {
        this.valorIncomingParcial = valorIncomingParcial;
    }

    public String getDataRetornoIncomingTotal() {
        return this.dataRetornoIncomingTotal;
    }

    public void setDataRetornoIncomingTotal(String dataRetornoIncomingTotal) {
        this.dataRetornoIncomingTotal = dataRetornoIncomingTotal;
    }

    public String getDataRetornoIncomingTotalFormatada() {
        return this.dataRetornoIncomingTotalFormatada;
    }

    public void setDataRetornoIncomingTotalFormatada(String dataRetornoIncomingTotalFormatada) {
        this.dataRetornoIncomingTotalFormatada = dataRetornoIncomingTotalFormatada;
    }

    public String getNomeArqIncomingTotal() {
        return this.nomeArqIncomingTotal;
    }

    public void setNomeArqIncomingTotal(String nomeArqIncomingTotal) {
        this.nomeArqIncomingTotal = nomeArqIncomingTotal;
    }

    public Integer getQtdIncomingTotal() {
        return this.qtdIncomingTotal;
    }

    public void setQtdIncomingTotal(Integer qtdIncomingTotal) {
        this.qtdIncomingTotal = qtdIncomingTotal;
    }

    public BigDecimal getValorIncomingTotal() {
        return this.valorIncomingTotal;
    }

    public void setValorIncomingTotal(BigDecimal valorIncomingTotal) {
        this.valorIncomingTotal = valorIncomingTotal;
    }

    public Integer getQtdLiquido() {
        return this.qtdLiquido;
    }

    public void setQtdLiquido(Integer qtdLiquido) {
        this.qtdLiquido = qtdLiquido;
    }

    public BigDecimal getValorLiquido() {
        return this.valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

	public int getRejTotal() {
		return rejTotal;
	}

	public void setRejTotal(int rejTotal) {
		this.rejTotal = rejTotal;
	}

}