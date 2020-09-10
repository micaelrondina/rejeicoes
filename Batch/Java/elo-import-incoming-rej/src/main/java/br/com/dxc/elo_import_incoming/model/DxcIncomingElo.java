package br.com.dxc.elo_import_incoming.model;

// TABELA MATRIZ/MASTER (deve conter o registros: "B0", "TE44", "BZ"
// Campos de valores ja estao com casas decimais para serem salvos ja convertidos (divididos por 100)
public class DxcIncomingElo {
	//ao se alterar o nome ou o tipo de retorno de algum metodo nessa classe, deve-se alterar tambem na classe RegistroHeader (ou equivalente) para que o metodo preencherDxcIncomingElo funcione

	private Long idIncomingElo; //PK
	
	//Demais Campos
	private String nmArquivo;
	
	//Registro Header (Tipo "B0")
	private String cdRegistroB0;
	private String cdServicoB0;
	private String dtRemessa;
	private String nrRemessaB0;
	private String nrJanela;
	private String dtEnvio;
	private String hrEnvioArq;
	private String dtRetornoArq;
	private String hrRetornoArq;
	private String bancoEmissorB0;
	private String cdProcessadora;
	private String versaoArq;
	private String cdCredenciador;
	private String cdBandeiraB0;
	private String indicadorRotaArqB0;
	
	//Registro TE44 Tipo "00"
	private String cdTransacao;
	private String subCdTransacao;
	private String bancoEmissor00;
	private String dtMovimento;
	private String nrRemessa00;
	private String dtConfirmRemessa;
	private String stRemessa;
	private String motivoRejeicao;
	private String cdMoedaTransacao;
	private String indicadorTpRetorno;
	private String qtTotRegArq;
	private String qtTransAcMoedaReal;
	private Double vlTransAcMoedaReal;
	private String qtTransRjMoedaReal;
	private Double vlTransRjMoedaReal;
	private String cdBandeira00;
	private String qtTransAcMoedaDolar;
	private Double vlTransAcMoedaDolar;
	private String qtTransRjMoedaDolar;
	private Double vlTransRjMoedaDolar;
	
	//Registro Trailer (Tipo "BZ")
	private String cdRegistroBz;
	private String cdServicoBz;
	private String nrRemessaBz;
	private String qtTransCreMoedaReal;
	private Double vlTransCreMoedaReal;
	private String qtTransDebMoedaReal;
	private Double vlTransDebMoedaReal;
	private String qtTransCreMoedaDolar;
	private Double vlTransCreMoedaDolar;
	private String qtTransDebMoedaDolar;
	private Double vlTransDebMoedaDolar;
	private String qtTotReg;
	private String qtTransMoviParcelado;
	private Double vlTransMoviParcelado;
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
	private String qtTransCreRjMoedaReal;
	private Double vlTotTransCreRjMoedaReal;
	private String qtTransDebRjMoedaReal;
	private Double vlTotTransDebRjMoedaReal;
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */
	private String indicadorRotaArqBz;
	
	public DxcIncomingElo() {}
	
	public DxcIncomingElo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public Long getIdIncomingElo() {
		return idIncomingElo;
	}

	public void setIdIncomingElo(Long idIncomingElo) {
		this.idIncomingElo = idIncomingElo;
	}

	public String getCdRegistroB0() {
		return cdRegistroB0;
	}

	public void setCdRegistroB0(String cdRegistroB0) {
		this.cdRegistroB0 = cdRegistroB0;
	}

	public String getCdServicoB0() {
		return cdServicoB0;
	}

	public void setCdServicoB0(String cdServicoB0) {
		this.cdServicoB0 = cdServicoB0;
	}

	public String getDtRemessa() {
		return dtRemessa;
	}

	public void setDtRemessa(String dtRemessa) {
		this.dtRemessa = dtRemessa;
	}

	public String getNrRemessaB0() {
		return nrRemessaB0;
	}

	public void setNrRemessaB0(String nrRemessaB0) {
		this.nrRemessaB0 = nrRemessaB0;
	}

	public String getNrJanela() {
		return nrJanela;
	}

	public void setNrJanela(String nrJanela) {
		this.nrJanela = nrJanela;
	}

	public String getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(String dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public String getHrEnvioArq() {
		return hrEnvioArq;
	}

	public void setHrEnvioArq(String hrEnvioArq) {
		this.hrEnvioArq = hrEnvioArq;
	}

	public String getDtRetornoArq() {
		return dtRetornoArq;
	}

	public void setDtRetornoArq(String dtRetornoArq) {
		this.dtRetornoArq = dtRetornoArq;
	}

	public String getHrRetornoArq() {
		return hrRetornoArq;
	}

	public void setHrRetornoArq(String hrRetornoArq) {
		this.hrRetornoArq = hrRetornoArq;
	}

	public String getBancoEmissorB0() {
		return bancoEmissorB0;
	}

	public void setBancoEmissorB0(String bancoEmissorB0) {
		this.bancoEmissorB0 = bancoEmissorB0;
	}

	public String getCdProcessadora() {
		return cdProcessadora;
	}

	public void setCdProcessadora(String cdProcessadora) {
		this.cdProcessadora = cdProcessadora;
	}

	public String getVersaoArq() {
		return versaoArq;
	}

	public void setVersaoArq(String versaoArq) {
		this.versaoArq = versaoArq;
	}

	public String getCdCredenciador() {
		return cdCredenciador;
	}

	public void setCdCredenciador(String cdCredenciador) {
		this.cdCredenciador = cdCredenciador;
	}

	public String getCdBandeiraB0() {
		return cdBandeiraB0;
	}

	public void setCdBandeiraB0(String cdBandeiraB0) {
		this.cdBandeiraB0 = cdBandeiraB0;
	}

	public String getIndicadorRotaArqB0() {
		return indicadorRotaArqB0;
	}

	public void setIndicadorRotaArqB0(String indicadorRotaArqB0) {
		this.indicadorRotaArqB0 = indicadorRotaArqB0;
	}

	public String getCdTransacao() {
		return cdTransacao;
	}

	public void setCdTransacao(String cdTransacao) {
		this.cdTransacao = cdTransacao;
	}

	public String getSubCdTransacao() {
		return subCdTransacao;
	}

	public void setSubCdTransacao(String subCdTransacao) {
		this.subCdTransacao = subCdTransacao;
	}

	public String getBancoEmissor00() {
		return bancoEmissor00;
	}

	public void setBancoEmissor00(String bancoEmissor00) {
		this.bancoEmissor00 = bancoEmissor00;
	}

	public String getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(String dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	public String getNrRemessa00() {
		return nrRemessa00;
	}

	public void setNrRemessa00(String nrRemessa00) {
		this.nrRemessa00 = nrRemessa00;
	}

	public String getDtConfirmRemessa() {
		return dtConfirmRemessa;
	}

	public void setDtConfirmRemessa(String dtConfirmRemessa) {
		this.dtConfirmRemessa = dtConfirmRemessa;
	}

	public String getStRemessa() {
		return stRemessa;
	}

	public void setStRemessa(String stRemessa) {
		this.stRemessa = stRemessa;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public String getCdMoedaTransacao() {
		return cdMoedaTransacao;
	}

	public void setCdMoedaTransacao(String cdMoedaTransacao) {
		this.cdMoedaTransacao = cdMoedaTransacao;
	}

	public String getIndicadorTpRetorno() {
		return indicadorTpRetorno;
	}

	public void setIndicadorTpRetorno(String indicadorTpRetorno) {
		this.indicadorTpRetorno = indicadorTpRetorno;
	}

	public String getQtTotRegArq() {
		return qtTotRegArq;
	}

	public void setQtTotRegArq(String qtTotRegArq) {
		this.qtTotRegArq = qtTotRegArq;
	}

	public String getQtTransAcMoedaReal() {
		return qtTransAcMoedaReal;
	}

	public void setQtTransAcMoedaReal(String qtTransAcMoedaReal) {
		this.qtTransAcMoedaReal = qtTransAcMoedaReal;
	}

	public Double getVlTransAcMoedaReal() {
		return vlTransAcMoedaReal;
	}

	public void setVlTransAcMoedaReal(Double vlTransAcMoedaReal) {
		this.vlTransAcMoedaReal = vlTransAcMoedaReal;
	}

	public String getQtTransRjMoedaReal() {
		return qtTransRjMoedaReal;
	}

	public void setQtTransRjMoedaReal(String qtTransRjMoedaReal) {
		this.qtTransRjMoedaReal = qtTransRjMoedaReal;
	}

	public Double getVlTransRjMoedaReal() {
		return vlTransRjMoedaReal;
	}

	public void setVlTransRjMoedaReal(Double vlTransRjMoedaReal) {
		this.vlTransRjMoedaReal = vlTransRjMoedaReal;
	}

	public String getCdBandeira00() {
		return cdBandeira00;
	}

	public void setCdBandeira00(String cdBandeira00) {
		this.cdBandeira00 = cdBandeira00;
	}

	public String getQtTransAcMoedaDolar() {
		return qtTransAcMoedaDolar;
	}

	public void setQtTransAcMoedaDolar(String qtTransAcMoedaDolar) {
		this.qtTransAcMoedaDolar = qtTransAcMoedaDolar;
	}

	public Double getVlTransAcMoedaDolar() {
		return vlTransAcMoedaDolar;
	}

	public void setVlTransAcMoedaDolar(Double vlTransAcMoedaDolar) {
		this.vlTransAcMoedaDolar = vlTransAcMoedaDolar;
	}

	public String getQtTransRjMoedaDolar() {
		return qtTransRjMoedaDolar;
	}

	public void setQtTransRjMoedaDolar(String qtTransRjMoedaDolar) {
		this.qtTransRjMoedaDolar = qtTransRjMoedaDolar;
	}

	public Double getVlTransRjMoedaDolar() {
		return vlTransRjMoedaDolar;
	}

	public void setVlTransRjMoedaDolar(Double vlTransRjMoedaDolar) {
		this.vlTransRjMoedaDolar = vlTransRjMoedaDolar;
	}

	public String getCdRegistroBz() {
		return cdRegistroBz;
	}

	public void setCdRegistroBz(String cdRegistroBz) {
		this.cdRegistroBz = cdRegistroBz;
	}

	public String getCdServicoBz() {
		return cdServicoBz;
	}

	public void setCdServicoBz(String cdServicoBz) {
		this.cdServicoBz = cdServicoBz;
	}

	public String getNrRemessaBz() {
		return nrRemessaBz;
	}

	public void setNrRemessaBz(String nrRemessaBz) {
		this.nrRemessaBz = nrRemessaBz;
	}

	public String getQtTransCreMoedaReal() {
		return qtTransCreMoedaReal;
	}

	public void setQtTransCreMoedaReal(String qtTransCreMoedaReal) {
		this.qtTransCreMoedaReal = qtTransCreMoedaReal;
	}

	public Double getVlTransCreMoedaReal() {
		return vlTransCreMoedaReal;
	}

	public void setVlTransCreMoedaReal(Double vlTransCreMoedaReal) {
		this.vlTransCreMoedaReal = vlTransCreMoedaReal;
	}

	public String getQtTransDebMoedaReal() {
		return qtTransDebMoedaReal;
	}

	public void setQtTransDebMoedaReal(String qtTransDebMoedaReal) {
		this.qtTransDebMoedaReal = qtTransDebMoedaReal;
	}

	public Double getVlTransDebMoedaReal() {
		return vlTransDebMoedaReal;
	}

	public void setVlTransDebMoedaReal(Double vlTransDebMoedaReal) {
		this.vlTransDebMoedaReal = vlTransDebMoedaReal;
	}

	public String getQtTransCreMoedaDolar() {
		return qtTransCreMoedaDolar;
	}

	public void setQtTransCreMoedaDolar(String qtTransCreMoedaDolar) {
		this.qtTransCreMoedaDolar = qtTransCreMoedaDolar;
	}

	public Double getVlTransCreMoedaDolar() {
		return vlTransCreMoedaDolar;
	}

	public void setVlTransCreMoedaDolar(Double vlTransCreMoedaDolar) {
		this.vlTransCreMoedaDolar = vlTransCreMoedaDolar;
	}

	public String getQtTransDebMoedaDolar() {
		return qtTransDebMoedaDolar;
	}

	public void setQtTransDebMoedaDolar(String qtTransDebMoedaDolar) {
		this.qtTransDebMoedaDolar = qtTransDebMoedaDolar;
	}

	public Double getVlTransDebMoedaDolar() {
		return vlTransDebMoedaDolar;
	}

	public void setVlTransDebMoedaDolar(Double vlTransDebMoedaDolar) {
		this.vlTransDebMoedaDolar = vlTransDebMoedaDolar;
	}

	public String getQtTotReg() {
		return qtTotReg;
	}

	public void setQtTotReg(String qtTotReg) {
		this.qtTotReg = qtTotReg;
	}

	public String getQtTransMoviParcelado() {
		return qtTransMoviParcelado;
	}

	public void setQtTransMoviParcelado(String qtTransMoviParcelado) {
		this.qtTransMoviParcelado = qtTransMoviParcelado;
	}

	public Double getVlTransMoviParcelado() {
		return vlTransMoviParcelado;
	}

	public void setVlTransMoviParcelado(Double vlTransMoviParcelado) {
		this.vlTransMoviParcelado = vlTransMoviParcelado;
	}
	
	public String getQtTransCreRjMoedaReal() {
		return qtTransCreRjMoedaReal;
	}

	public void setQtTransCreRjMoedaReal(String qtTransCreRjMoedaReal) {
		this.qtTransCreRjMoedaReal = qtTransCreRjMoedaReal;
	}

	public Double getVlTotTransCreRjMoedaReal() {
		return vlTotTransCreRjMoedaReal;
	}

	public void setVlTotTransCreRjMoedaReal(Double vlTotTransCreRjMoedaReal) {
		this.vlTotTransCreRjMoedaReal = vlTotTransCreRjMoedaReal;
	}

	public String getQtTransDebRjMoedaReal() {
		return qtTransDebRjMoedaReal;
	}

	public void setQtTransDebRjMoedaReal(String qtTransDebRjMoedaReal) {
		this.qtTransDebRjMoedaReal = qtTransDebRjMoedaReal;
	}

	public Double getVlTotTransDebRjMoedaReal() {
		return vlTotTransDebRjMoedaReal;
	}

	public void setVlTotTransDebRjMoedaReal(Double vlTotTransDebRjMoedaReal) {
		this.vlTotTransDebRjMoedaReal = vlTotTransDebRjMoedaReal;
	}

	public String getIndicadorRotaArqBz() {
		return indicadorRotaArqBz;
	}

	public void setIndicadorRotaArqBz(String indicadorRotaArqBz) {
		this.indicadorRotaArqBz = indicadorRotaArqBz;
	}

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}
}
