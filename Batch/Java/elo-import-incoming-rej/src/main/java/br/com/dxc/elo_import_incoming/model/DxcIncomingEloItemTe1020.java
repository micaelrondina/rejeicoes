package br.com.dxc.elo_import_incoming.model;

public class DxcIncomingEloItemTe1020 {
	private Long idIncomingEloItem;
	private Long idIncomingElo;
	
	//Registro "00"
	private String cdTransacao00;
	private String subCdTransacao00;
	private String cdDestino;
	private String cdOrigem;
	private String cdMotivoTransacao;
	private String cdPais00;
	private String dtEnvio;
	private String nrCartao;
	private Double vlDestino;
	private String cdMoedaDestino;
	private Double vlOrigem;
	private String cdMoedaOrigem;
	private String msgTexto;
	private String indicadorLiq;
	private String idTransacaoOriginal;
	private String dtProcessamento;
	
	//Registro "02"
	private String cdTransacao02;
	private String subCdTransacao02;
	private String cdPais02;
	private String qtDiasLiqFinanTransacao;
	private String dtProcessamentoRegTp02;
	private String cdErro;
	private String tokenPan;
	private String dtLiqTransacao;

	public Long getIdIncomingEloItem() {
		return idIncomingEloItem;
	}
	public void setIdIncomingEloItem(Long idIncomingEloItem) {
		this.idIncomingEloItem = idIncomingEloItem;
	}
	public Long getIdIncomingElo() {
		return idIncomingElo;
	}
	public void setIdIncomingElo(Long idIncomingElo) {
		this.idIncomingElo = idIncomingElo;
	}
	public String getCdTransacao00() {
		return cdTransacao00;
	}
	public void setCdTransacao00(String cdTransacao00) {
		this.cdTransacao00 = cdTransacao00;
	}
	public String getSubCdTransacao00() {
		return subCdTransacao00;
	}
	public void setSubCdTransacao00(String subCdTransacao00) {
		this.subCdTransacao00 = subCdTransacao00;
	}
	public String getCdDestino() {
		return cdDestino;
	}
	public void setCdDestino(String cdDestino) {
		this.cdDestino = cdDestino;
	}
	public String getCdOrigem() {
		return cdOrigem;
	}
	public void setCdOrigem(String cdOrigem) {
		this.cdOrigem = cdOrigem;
	}
	public String getCdMotivoTransacao() {
		return cdMotivoTransacao;
	}
	public void setCdMotivoTransacao(String cdMotivoTransacao) {
		this.cdMotivoTransacao = cdMotivoTransacao;
	}
	public String getCdPais00() {
		return cdPais00;
	}
	public void setCdPais00(String cdPais00) {
		this.cdPais00 = cdPais00;
	}
	public String getDtEnvio() {
		return dtEnvio;
	}
	public void setDtEnvio(String dtEnvio) {
		this.dtEnvio = dtEnvio;
	}
	public String getNrCartao() {
//		return nrCartao;
		return nrCartao.substring(0, 6) + "******" + nrCartao.substring(12, 16);
	}
	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}
	public Double getVlDestino() {
		return vlDestino;
	}
	public void setVlDestino(Double vlDestino) {
		this.vlDestino = vlDestino;
	}
	public String getCdMoedaDestino() {
		return cdMoedaDestino;
	}
	public void setCdMoedaDestino(String cdMoedaDestino) {
		this.cdMoedaDestino = cdMoedaDestino;
	}
	public Double getVlOrigem() {
		return vlOrigem;
	}
	public void setVlOrigem(Double vlOrigem) {
		this.vlOrigem = vlOrigem;
	}
	public String getCdMoedaOrigem() {
		return cdMoedaOrigem;
	}
	public void setCdMoedaOrigem(String cdMoedaOrigem) {
		this.cdMoedaOrigem = cdMoedaOrigem;
	}
	public String getMsgTexto() {
		return msgTexto;
	}
	public void setMsgTexto(String msgTexto) {
		this.msgTexto = msgTexto;
	}
	public String getIndicadorLiq() {
		return indicadorLiq;
	}
	public void setIndicadorLiq(String indicadorLiq) {
		this.indicadorLiq = indicadorLiq;
	}
	public String getIdTransacaoOriginal() {
		return idTransacaoOriginal;
	}
	public void setIdTransacaoOriginal(String idTransacaoOriginal) {
		this.idTransacaoOriginal = idTransacaoOriginal;
	}
	public String getDtProcessamento() {
		return dtProcessamento;
	}
	public void setDtProcessamento(String dtProcessamento) {
		this.dtProcessamento = dtProcessamento;
	}
	public String getCdTransacao02() {
		return cdTransacao02;
	}
	public void setCdTransacao02(String cdTransacao02) {
		this.cdTransacao02 = cdTransacao02;
	}
	public String getSubCdTransacao02() {
		return subCdTransacao02;
	}
	public void setSubCdTransacao02(String subCdTransacao02) {
		this.subCdTransacao02 = subCdTransacao02;
	}
	public String getCdPais02() {
		return cdPais02;
	}
	public void setCdPais02(String cdPais02) {
		this.cdPais02 = cdPais02;
	}
	public String getQtDiasLiqFinanTransacao() {
		return qtDiasLiqFinanTransacao;
	}
	public void setQtDiasLiqFinanTransacao(String qtDiasLiqFinanTransacao) {
		this.qtDiasLiqFinanTransacao = qtDiasLiqFinanTransacao;
	}
	public String getDtProcessamentoRegTp02() {
		return dtProcessamentoRegTp02;
	}
	public void setDtProcessamentoRegTp02(String dtProcessamentoRegTp02) {
		this.dtProcessamentoRegTp02 = dtProcessamentoRegTp02;
	}
	public String getCdErro() {
		return cdErro;
	}
	public void setCdErro(String cdErro) {
		this.cdErro = cdErro;
	}
	public String getTokenPan() {
		return tokenPan;
	}
	public void setTokenPan(String tokenPan) {
		this.tokenPan = tokenPan;
	}
	public String getDtLiqTransacao() {
		return dtLiqTransacao;
	}
	public void setDtLiqTransacao(String dtLiqTransacao) {
		this.dtLiqTransacao = dtLiqTransacao;
	}
}
