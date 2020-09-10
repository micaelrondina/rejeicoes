package br.com.dxc.elo_import_incoming.model;

public class DxcIncomingEloItemTe40 {
	private Long idIncomingEloItem;
	private Long idIncomingElo;
	
	//Registro "00"
	private String cdTransacao00;
	private String subCdTransacao00;
	private String nrCartao;
	private String complementoNrCartao;
	private String nrRefTransacao;
	private String cdCredenciador;
	private String dtVendaSaque;
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
	private String cdErro;
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */
	private String cidadePontoVenda;
	private String cdPaisPontoVenda;
	private String mccPontoVenda;
	private Double vlFraude;
	private String cdMoedaTransFraudulenta;
	private String dtNotificacaoFraude;
	private String indicadorOrigemAuth;
	private String cdNotificacao;
	private String tpFraude;
	private String dtVencCartao;
	private String tpPlataforma;
	private String cdBandeira;
	private String bancoEmissor;
	
	//Registro "02"
	private String cdTransacao02;
	private String subCdTransacao02;
	private String idTransacao;
	private String pontoVenda;
	private String nrLogicoEquipamento;
	private String indicadorTransTroca;
	private String cdAuthTransacao;
	private String meioIdentPortador;
	private String modoEntTransacaoPos;
	private String identTecnologTerminal;
	private String tecnologiaCartao;
	private Double vlTroco;
	private String indicTransFeitaPor;
	private String cepPortador;
	private String cidadePortador;
	private String ufPortador;
	private String dtConfirmFraude;
	private String indicadorLiq;
	private String nmPortador;
	private String tokenPan;

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
	public String getNrCartao() {
//		return nrCartao;
		return nrCartao.substring(0, 6) + "******" + nrCartao.substring(12, 16);
	}
	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}
	public String getComplementoNrCartao() {
		return complementoNrCartao;
	}
	public void setComplementoNrCartao(String complementoNrCartao) {
		this.complementoNrCartao = complementoNrCartao;
	}
	public String getNrRefTransacao() {
		return nrRefTransacao;
	}
	public void setNrRefTransacao(String nrRefTransacao) {
		this.nrRefTransacao = nrRefTransacao;
	}
	public String getCdCredenciador() {
		return cdCredenciador;
	}
	public void setCdCredenciador(String cdCredenciador) {
		this.cdCredenciador = cdCredenciador;
	}
	public String getDtVendaSaque() {
		return dtVendaSaque;
	}
	public void setDtVendaSaque(String dtVendaSaque) {
		this.dtVendaSaque = dtVendaSaque;
	}
	public String getCdErro() {
		return cdErro;
	}
	public void setCdErro(String cdErro) {
		this.cdErro = cdErro;
	}
	public String getCidadePontoVenda() {
		return cidadePontoVenda;
	}
	public void setCidadePontoVenda(String cidadePontoVenda) {
		this.cidadePontoVenda = cidadePontoVenda;
	}
	public String getCdPaisPontoVenda() {
		return cdPaisPontoVenda;
	}
	public void setCdPaisPontoVenda(String cdPaisPontoVenda) {
		this.cdPaisPontoVenda = cdPaisPontoVenda;
	}
	public String getMccPontoVenda() {
		return mccPontoVenda;
	}
	public void setMccPontoVenda(String mccPontoVenda) {
		this.mccPontoVenda = mccPontoVenda;
	}
	public Double getVlFraude() {
		return vlFraude;
	}
	public void setVlFraude(Double vlFraude) {
		this.vlFraude = vlFraude;
	}
	public String getCdMoedaTransFraudulenta() {
		return cdMoedaTransFraudulenta;
	}
	public void setCdMoedaTransFraudulenta(String cdMoedaTransFraudulenta) {
		this.cdMoedaTransFraudulenta = cdMoedaTransFraudulenta;
	}
	public String getDtNotificacaoFraude() {
		return dtNotificacaoFraude;
	}
	public void setDtNotificacaoFraude(String dtNotificacaoFraude) {
		this.dtNotificacaoFraude = dtNotificacaoFraude;
	}
	public String getIndicadorOrigemAuth() {
		return indicadorOrigemAuth;
	}
	public void setIndicadorOrigemAuth(String indicadorOrigemAuth) {
		this.indicadorOrigemAuth = indicadorOrigemAuth;
	}
	public String getCdNotificacao() {
		return cdNotificacao;
	}
	public void setCdNotificacao(String cdNotificacao) {
		this.cdNotificacao = cdNotificacao;
	}
	public String getTpFraude() {
		return tpFraude;
	}
	public void setTpFraude(String tpFraude) {
		this.tpFraude = tpFraude;
	}
	public String getDtVencCartao() {
		return dtVencCartao;
	}
	public void setDtVencCartao(String dtVencCartao) {
		this.dtVencCartao = dtVencCartao;
	}
	public String getTpPlataforma() {
		return tpPlataforma;
	}
	public void setTpPlataforma(String tpPlataforma) {
		this.tpPlataforma = tpPlataforma;
	}
	public String getCdBandeira() {
		return cdBandeira;
	}
	public void setCdBandeira(String cdBandeira) {
		this.cdBandeira = cdBandeira;
	}
	public String getBancoEmissor() {
		return bancoEmissor;
	}
	public void setBancoEmissor(String bancoEmissor) {
		this.bancoEmissor = bancoEmissor;
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
	public String getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}
	public String getPontoVenda() {
		return pontoVenda;
	}
	public void setPontoVenda(String pontoVenda) {
		this.pontoVenda = pontoVenda;
	}
	public String getNrLogicoEquipamento() {
		return nrLogicoEquipamento;
	}
	public void setNrLogicoEquipamento(String nrLogicoEquipamento) {
		this.nrLogicoEquipamento = nrLogicoEquipamento;
	}
	public String getIndicadorTransTroca() {
		return indicadorTransTroca;
	}
	public void setIndicadorTransTroca(String indicadorTransTroca) {
		this.indicadorTransTroca = indicadorTransTroca;
	}
	public String getCdAuthTransacao() {
		return cdAuthTransacao;
	}
	public void setCdAuthTransacao(String cdAuthTransacao) {
		this.cdAuthTransacao = cdAuthTransacao;
	}
	public String getMeioIdentPortador() {
		return meioIdentPortador;
	}
	public void setMeioIdentPortador(String meioIdentPortador) {
		this.meioIdentPortador = meioIdentPortador;
	}
	public String getModoEntTransacaoPos() {
		return modoEntTransacaoPos;
	}
	public void setModoEntTransacaoPos(String modoEntTransacaoPos) {
		this.modoEntTransacaoPos = modoEntTransacaoPos;
	}
	public String getIdentTecnologTerminal() {
		return identTecnologTerminal;
	}
	public void setIdentTecnologTerminal(String identTecnologTerminal) {
		this.identTecnologTerminal = identTecnologTerminal;
	}
	public String getTecnologiaCartao() {
		return tecnologiaCartao;
	}
	public void setTecnologiaCartao(String tecnologiaCartao) {
		this.tecnologiaCartao = tecnologiaCartao;
	}
	public Double getVlTroco() {
		return vlTroco;
	}
	public void setVlTroco(Double vlTroco) {
		this.vlTroco = vlTroco;
	}
	public String getIndicTransFeitaPor() {
		return indicTransFeitaPor;
	}
	public void setIndicTransFeitaPor(String indicTransFeitaPor) {
		this.indicTransFeitaPor = indicTransFeitaPor;
	}
	public String getCepPortador() {
		return cepPortador;
	}
	public void setCepPortador(String cepPortador) {
		this.cepPortador = cepPortador;
	}
	public String getCidadePortador() {
		return cidadePortador;
	}
	public void setCidadePortador(String cidadePortador) {
		this.cidadePortador = cidadePortador;
	}
	public String getUfPortador() {
		return ufPortador;
	}
	public void setUfPortador(String ufPortador) {
		this.ufPortador = ufPortador;
	}
	public String getDtConfirmFraude() {
		return dtConfirmFraude;
	}
	public void setDtConfirmFraude(String dtConfirmFraude) {
		this.dtConfirmFraude = dtConfirmFraude;
	}
	public String getIndicadorLiq() {
		return indicadorLiq;
	}
	public void setIndicadorLiq(String indicadorLiq) {
		this.indicadorLiq = indicadorLiq;
	}
	public String getNmPortador() {
		return nmPortador;
	}
	public void setNmPortador(String nmPortador) {
		this.nmPortador = nmPortador;
	}
	public String getTokenPan() {
		return tokenPan;
	}
	public void setTokenPan(String tokenPan) {
		this.tokenPan = tokenPan;
	}
}
