package br.com.dxc.cards.core.model;

public class FiltroConsultar {
	private String idAcquirer;
	private String idProduto;
	private String idTpTransacao;
	private String dataInicio;
	private String dataFim;
	private String codErro;
	
	public String getIdAcquirer() {
		return idAcquirer;
	}
	public void setIdAcquirer(String idAcquirer) {
		this.idAcquirer = idAcquirer;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getIdTpTransacao() {
		return idTpTransacao;
	}
	public void setIdTpTransacao(String idTpTransacao) {
		this.idTpTransacao = idTpTransacao;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getCodErro() {
		return this.codErro;
	}
	public void setCodErro(String codErro) {
		this.codErro = codErro;
	}
}