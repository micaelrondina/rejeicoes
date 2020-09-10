package br.com.dxc.cards.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class Incoming {
	private IdIncoming id;
	private String nomeArquivo;
	private String nomePV;
	private String cidadePV;
	private String dataMov;
	private String dataMovFormatada;
	private BigDecimal valorVenda;
	private int numParcela;
	private int qtdTotParcelas;
	private int qtdDiasLiqFinanTransacao;
	private String numRefTransacao;
	private String codAuthTransacao;
	private int qtdVezesTransRej;
	private String codErro;
	private String descMsgErro;
	private int numLote;
	private String loginLiberacao;
	private Date dataLiberacao;
	private String loginAcatado;
	private Date dataAcatado;
	private String loginRejeicao;
	private Date dataRejeicao;
	private String nrRemessaRejeicao;
	private String nrRemessaAcatado;
	//
	private String codEC;
	private String nomeEC;
	private String codPV;
	private String nomePVBanco;
	//
	private boolean podeMarcarParaReenviar = false;
	private String idTpTrans;

	public IdIncoming getId() {
		return this.id;
	}
	public void setId(IdIncoming id) {
		this.id = id;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getNomePV() {
		return nomePV;
	}
	public void setNomePV(String nomePV) {
		this.nomePV = nomePV;
	}
	public String getCidadePV() {
		return cidadePV;
	}
	public void setCidadePV(String cidadePV) {
		this.cidadePV = cidadePV;
	}
	public String getDataMov() {
		return dataMov;
	}
	public void setDataMov(String dataMov) {
		this.dataMov = dataMov;
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
	public int getQtdDiasLiqFinanTransacao() {
		return qtdDiasLiqFinanTransacao;
	}
	public void setQtdDiasLiqFinanTransacao(int qtdDiasLiqFinanTransacao) {
		this.qtdDiasLiqFinanTransacao = qtdDiasLiqFinanTransacao;
	}
	public String getNumRefTransacao() {
		return numRefTransacao;
	}
	public void setNumRefTransacao(String numRefTransacao) {
		this.numRefTransacao = numRefTransacao;
	}
	public String getCodAuthTransacao() {
		return codAuthTransacao;
	}
	public void setCodAuthTransacao(String codAuthTransacao) {
		this.codAuthTransacao = codAuthTransacao;
	}
	public String getCodErro() {
		return codErro;
	}
	public void setCodErro(String codErro) {
		this.codErro = codErro;
	}
	public String getDescMsgErro() {
		return descMsgErro;
	}
	public void setDescMsgErro(String descMsgErro) {
		this.descMsgErro = descMsgErro;
	}
	public int getNumLote() {
		return this.numLote;
	}
	public void setNumLote(int numLote) {
		this.numLote = numLote;
	}
	public String getLoginLiberacao() {
		return loginLiberacao;
	}
	public void setLoginLiberacao(String loginLiberacao) {
		this.loginLiberacao = loginLiberacao;
	}
	public Date getDataLiberacao() {
		return dataLiberacao;
	}
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}
	public String getLoginAcatado() {
		return loginAcatado;
	}
	public void setLoginAcatado(String loginAcatado) {
		this.loginAcatado = loginAcatado;
	}
	public Date getDataAcatado() {
		return dataAcatado;
	}
	public void setDataAcatado(Date dataAcatado) {
		this.dataAcatado = dataAcatado;
	}
	public String getLoginRejeicao() {
		return loginRejeicao;
	}
	public void setLoginRejeicao(String loginRejeicao) {
		this.loginRejeicao = loginRejeicao;
	}
	public Date getDataRejeicao() {
		return dataRejeicao;
	}
	public void setDataRejeicao(Date dataRejeicao) {
		this.dataRejeicao = dataRejeicao;
	}
	public int getQtdVezesTransRej() {
		return qtdVezesTransRej;
	}
	public void setQtdVezesTransRej(int qtdVezesTransRej) {
		this.qtdVezesTransRej = qtdVezesTransRej;
	}
	public String getNrRemessaRejeicao() {
		return nrRemessaRejeicao;
	}
	public void setNrRemessaRejeicao(String nrRemessaRejeicao) {
		this.nrRemessaRejeicao = nrRemessaRejeicao;
	}
	public String getNrRemessaAcatado() {
		return nrRemessaAcatado;
	}
	public void setNrRemessaAcatado(String nrRemessaAcatado) {
		this.nrRemessaAcatado = nrRemessaAcatado;
	}
	public String getCodEC() {
		return codEC;
	}
	public void setCodEC(String codEC) {
		this.codEC = codEC;
	}
	public String getNomeEC() {
		return nomeEC;
	}
	public void setNomeEC(String nomeEC) {
		this.nomeEC = nomeEC;
	}
	public String getCodPV() {
		return codPV;
	}
	public void setCodPV(String codPV) {
		this.codPV = codPV;
	}
	public String getNomePVBanco() {
		return nomePVBanco;
	}
	public void setNomePVBanco(String nomePVBanco) {
		this.nomePVBanco = nomePVBanco;
	}
	public boolean isPodeMarcarParaReenviar() {
		return podeMarcarParaReenviar;
	}
	public void setPodeMarcarParaReenviar(boolean podeMarcarParaReenviar) {
		this.podeMarcarParaReenviar = podeMarcarParaReenviar;
	}
	public String getIdTpTrans() {
		return idTpTrans;
	}
	public void setIdTpTrans(String idTpTrans) {
		this.idTpTrans = idTpTrans;
	}
}
