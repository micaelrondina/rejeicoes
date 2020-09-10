package br.com.dxc.elo_import_incoming.model;

import java.util.Date;

//TABELA ITENS
public class DxcIncomingEloItem {
	private Long idIncomingEloItem;
	private Long idIncomingElo;
	
	//Registro "00"
	private String cdTransacao00;
	private String subCdTransacao00;
	private String nrCartao;
	private String tpLiquidacao;
	private String indOrigemAuthCanc;
	private String nrRefTransacao;
	private String cdProcesso;
	private String cdCredenciador;
	private String dtVendaSaque;
	private String hrVendaSaque;
	private Double vlVendaSaqueDisputa;
	private String cdMoedaTransacao;
	private String nmPontoVenda;
	private String cidadePontoVenda;
	private String cdPaisPontoVenda;
	private String mccPontoVenda;
	private String bancoEmissor;
	private String cdBandeira;
	private String identTpTransacao;
	private String cdMotivoDisputa;
	private String cdAuthTransacao;
	private String indicadorTecnoTerminal;
	private String meioIdentPortador;
	private String modoEntTransacaoPos;
	private String dtMovApresentDisputa;
	
	//Registro "01"
	private String cdTransacao01;
	private String subCdTransacao01;
	private String nrRefDisputa;
	private String indicadorEnvDoc;
	private String txtLivre;
	private String cdProduto;
	private String pontoVenda;
	private String nrLogicoEquipamento;
	private Double vlTaxaEmbarque;
	private String indicTransFeitaPor;
	private Double vlTransacao;
	private String indicadorMovimentacao;
	private String qtParcelasTransacao;
	private String nrParcela;
	private String tarifaPagtoInsumo;
	private String tpPessoa;
	private String documento;
	private Double vlTrocoAgroDeb;
	private String cdCondTransacaoChip;
	
	//Registro "02"
	private String cdTransacao02;
	private String subCdTransacao02;
	private String cdPaisLiq;
	private String qtDiasLiqFinanTransacao;
	private Double vlIntercambio;
	private String dtMovTransacaoOriginal;
	private String tpOperacao;
	private String tokenPan;
	private String tokenRequestorId;
	private String tokenAssuranceLevel;
	private String cepEc;
	private String dtLiquiTransacao;
	private String cdPvMarketplace;
	
	//Registro "03"
	private String cdTransacao03;
	private String subCdTransacao03;
	private String tpPagto;
	private String nrContaUnicoRef;
	private String nmRemetente;
	private String enderecoRemetente;
	private String cidadeRemetente;
	private String paisRemetente;
	private String origemFundos;
	
	//Registro "05"
	private String cdTransacao05;
	private String cdQualificadorTransacao05;
	private String nrSeqCompTransacao05;
	private String idTransacao;
	private Double vlAutorizado;
	private String cdMoedaVlAutorizado;
	private String cdRespAutorizacao;
	private String indicadorDireitoDevolucao;
	private String indicadorComerEletronico;
	private String indicadorAuthEspecifica;
	private Double vlTotalAutorizado;
	private String cavvVlVerifAuthPortador;
	private String cdResultVerifCavv;
	
	//Registro "07"
	private String cdTransacao07;
	private String cdQualificadorTransacao07;
	private String nrSeqCompTransacao07;
	private String tpTransacao;
	private String nrSeqCartao;
	private String dtTransacaoTerminal;
	private String capacidadeTerminal;
	private String cdPaisTerminal;
	private String nrSerieTerminal;
	private String nrRandomCriptograma;
	private String contadorTransacaoApp;
	private String appInterchangeProfile;
	private String criptograma;
	private String indiceDerivacaoChave;
	private String nrVersaoCriptograma;
	private String verifResultTerminal;
	private String verifResultCartao;
	private Double vlTransacaoCriptograma;
	private String formFactorIndicator;
	
	//Registro "08"
	private String cdTransacao08;
	private String subCdTransacao08;
	private String cdCompanhiaAerea;
	private String nrDocEmbarque;
	private String cdAgenteIata;
	private String nmPassageiro;
	private String cdCidade1;
	private String transportadora1;
	private String classeServico1;
	private String cdCidade2;
	private String transportadora2;
	private String classeServico2;
	private String cdCidade3;
	private String transportadora3;
	private String classeServico3;
	private String cdCidade4;
	private String transportadora4;
	private String classeServico4;
	private String cdCidade5;
	private String cdAeroportoDestino;
	private String dtPrimeiroVoo;
	private String nmAgenteEmissorDoc;
	
	//Registro "09"
	private String cdTransacao09;
	private String subCdTransacao09;
	private String cdTransacaoOriginal;
	private String dtMovimento;
	private String cdErro;
	private String descricaoErro;
	private String registroComErro;
	private String posicaoComErro;
	
	//Demais campos
	private String loginUsuLiberacao;
	private Date dtLiberacao;
	private String loginUsuAcatado;
	private Date dtAcatado;
	private String loginUsuRejeicao;
	private Date dtRejeicao;
	
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
//		return nrCartao
		return nrCartao.substring(0, 6) + "******" + nrCartao.substring(12, 16);
	}
	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}
	public String getTpLiquidacao() {
		return tpLiquidacao;
	}
	public void setTpLiquidacao(String tpLiquidacao) {
		this.tpLiquidacao = tpLiquidacao;
	}
	public String getIndOrigemAuthCanc() {
		return indOrigemAuthCanc;
	}
	public void setIndOrigemAuthCanc(String indOrigemAuthCanc) {
		this.indOrigemAuthCanc = indOrigemAuthCanc;
	}
	public String getNrRefTransacao() {
		return nrRefTransacao;
	}
	public void setNrRefTransacao(String nrRefTransacao) {
		this.nrRefTransacao = nrRefTransacao;
	}
	public String getCdProcesso() {
		return cdProcesso;
	}
	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
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
	public String getHrVendaSaque() {
		return hrVendaSaque;
	}
	public void setHrVendaSaque(String hrVendaSaque) {
		this.hrVendaSaque = hrVendaSaque;
	}
	public Double getVlVendaSaqueDisputa() {
		return vlVendaSaqueDisputa;
	}
	public void setVlVendaSaqueDisputa(Double vlVendaSaqueDisputa) {
		this.vlVendaSaqueDisputa = vlVendaSaqueDisputa;
	}
	public String getCdMoedaTransacao() {
		return cdMoedaTransacao;
	}
	public void setCdMoedaTransacao(String cdMoedaTransacao) {
		this.cdMoedaTransacao = cdMoedaTransacao;
	}
	public String getNmPontoVenda() {
		return nmPontoVenda;
	}
	public void setNmPontoVenda(String nmPontoVenda) {
		this.nmPontoVenda = nmPontoVenda;
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
	public String getBancoEmissor() {
		return bancoEmissor;
	}
	public void setBancoEmissor(String bancoEmissor) {
		this.bancoEmissor = bancoEmissor;
	}
	public String getCdBandeira() {
		return cdBandeira;
	}
	public void setCdBandeira(String cdBandeira) {
		this.cdBandeira = cdBandeira;
	}
	public String getIdentTpTransacao() {
		return identTpTransacao;
	}
	public void setIdentTpTransacao(String identTpTransacao) {
		this.identTpTransacao = identTpTransacao;
	}
	public String getCdMotivoDisputa() {
		return cdMotivoDisputa;
	}
	public void setCdMotivoDisputa(String cdMotivoDisputa) {
		this.cdMotivoDisputa = cdMotivoDisputa;
	}
	public String getCdAuthTransacao() {
		return cdAuthTransacao;
	}
	public void setCdAuthTransacao(String cdAuthTransacao) {
		this.cdAuthTransacao = cdAuthTransacao;
	}
	public String getIndicadorTecnoTerminal() {
		return indicadorTecnoTerminal;
	}
	public void setIndicadorTecnoTerminal(String indicadorTecnoTerminal) {
		this.indicadorTecnoTerminal = indicadorTecnoTerminal;
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
	public String getDtMovApresentDisputa() {
		return dtMovApresentDisputa;
	}
	public void setDtMovApresentDisputa(String dtMovApresentDisputa) {
		this.dtMovApresentDisputa = dtMovApresentDisputa;
	}
	public String getCdTransacao01() {
		return cdTransacao01;
	}
	public void setCdTransacao01(String cdTransacao01) {
		this.cdTransacao01 = cdTransacao01;
	}
	public String getSubCdTransacao01() {
		return subCdTransacao01;
	}
	public void setSubCdTransacao01(String subCdTransacao01) {
		this.subCdTransacao01 = subCdTransacao01;
	}
	public String getNrRefDisputa() {
		return nrRefDisputa;
	}
	public void setNrRefDisputa(String nrRefDisputa) {
		this.nrRefDisputa = nrRefDisputa;
	}
	public String getIndicadorEnvDoc() {
		return indicadorEnvDoc;
	}
	public void setIndicadorEnvDoc(String indicadorEnvDoc) {
		this.indicadorEnvDoc = indicadorEnvDoc;
	}
	public String getTxtLivre() {
		return txtLivre;
	}
	public void setTxtLivre(String txtLivre) {
		this.txtLivre = txtLivre;
	}
	public String getCdProduto() {
		return cdProduto;
	}
	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
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
	public Double getVlTaxaEmbarque() {
		return vlTaxaEmbarque;
	}
	public void setVlTaxaEmbarque(Double vlTaxaEmbarque) {
		this.vlTaxaEmbarque = vlTaxaEmbarque;
	}
	public String getIndicTransFeitaPor() {
		return indicTransFeitaPor;
	}
	public void setIndicTransFeitaPor(String indicTransFeitaPor) {
		this.indicTransFeitaPor = indicTransFeitaPor;
	}
	public Double getVlTransacao() {
		return vlTransacao;
	}
	public void setVlTransacao(Double vlTransacao) {
		this.vlTransacao = vlTransacao;
	}
	public String getIndicadorMovimentacao() {
		return indicadorMovimentacao;
	}
	public void setIndicadorMovimentacao(String indicadorMovimentacao) {
		this.indicadorMovimentacao = indicadorMovimentacao;
	}
	public String getQtParcelasTransacao() {
		return qtParcelasTransacao;
	}
	public void setQtParcelasTransacao(String qtParcelasTransacao) {
		this.qtParcelasTransacao = qtParcelasTransacao;
	}
	public String getNrParcela() {
		return nrParcela;
	}
	public void setNrParcela(String nrParcela) {
		this.nrParcela = nrParcela;
	}
	public String getTarifaPagtoInsumo() {
		return tarifaPagtoInsumo;
	}
	public void setTarifaPagtoInsumo(String tarifaPagtoInsumo) {
		this.tarifaPagtoInsumo = tarifaPagtoInsumo;
	}
	public String getTpPessoa() {
		return tpPessoa;
	}
	public void setTpPessoa(String tpPessoa) {
		this.tpPessoa = tpPessoa;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Double getVlTrocoAgroDeb() {
		return vlTrocoAgroDeb;
	}
	public void setVlTrocoAgroDeb(Double vlTrocoAgroDeb) {
		this.vlTrocoAgroDeb = vlTrocoAgroDeb;
	}
	public String getCdCondTransacaoChip() {
		return cdCondTransacaoChip;
	}
	public void setCdCondTransacaoChip(String cdCondTransacaoChip) {
		this.cdCondTransacaoChip = cdCondTransacaoChip;
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
	public String getCdPaisLiq() {
		return cdPaisLiq;
	}
	public void setCdPaisLiq(String cdPaisLiq) {
		this.cdPaisLiq = cdPaisLiq;
	}
	public String getQtDiasLiqFinanTransacao() {
		return qtDiasLiqFinanTransacao;
	}
	public void setQtDiasLiqFinanTransacao(String qtDiasLiqFinanTransacao) {
		this.qtDiasLiqFinanTransacao = qtDiasLiqFinanTransacao;
	}
	public Double getVlIntercambio() {
		return vlIntercambio;
	}
	public void setVlIntercambio(Double vlIntercambio) {
		this.vlIntercambio = vlIntercambio;
	}
	public String getDtMovTransacaoOriginal() {
		return dtMovTransacaoOriginal;
	}
	public void setDtMovTransacaoOriginal(String dtMovTransacaoOriginal) {
		this.dtMovTransacaoOriginal = dtMovTransacaoOriginal;
	}
	public String getTpOperacao() {
		return tpOperacao;
	}
	public void setTpOperacao(String tpOperacao) {
		this.tpOperacao = tpOperacao;
	}
	public String getTokenPan() {
		return tokenPan;
	}
	public void setTokenPan(String tokenPan) {
		this.tokenPan = tokenPan;
	}
	public String getTokenRequestorId() {
		return tokenRequestorId;
	}
	public void setTokenRequestorId(String tokenRequestorId) {
		this.tokenRequestorId = tokenRequestorId;
	}
	public String getTokenAssuranceLevel() {
		return tokenAssuranceLevel;
	}
	public void setTokenAssuranceLevel(String tokenAssuranceLevel) {
		this.tokenAssuranceLevel = tokenAssuranceLevel;
	}
	public String getCepEc() {
		return cepEc;
	}
	public void setCepEc(String cepEc) {
		this.cepEc = cepEc;
	}
	public String getDtLiquiTransacao() {
		return dtLiquiTransacao;
	}
	public void setDtLiquiTransacao(String dtLiquiTransacao) {
		this.dtLiquiTransacao = dtLiquiTransacao;
	}
	public String getCdPvMarketplace() {
		return cdPvMarketplace;
	}
	public void setCdPvMarketplace(String cdPvMarketplace) {
		this.cdPvMarketplace = cdPvMarketplace;
	}
	public String getCdTransacao03() {
		return cdTransacao03;
	}
	public void setCdTransacao03(String cdTransacao03) {
		this.cdTransacao03 = cdTransacao03;
	}
	public String getSubCdTransacao03() {
		return subCdTransacao03;
	}
	public void setSubCdTransacao03(String subCdTransacao03) {
		this.subCdTransacao03 = subCdTransacao03;
	}
	public String getTpPagto() {
		return tpPagto;
	}
	public void setTpPagto(String tpPagto) {
		this.tpPagto = tpPagto;
	}
	public String getNrContaUnicoRef() {
		return nrContaUnicoRef;
	}
	public void setNrContaUnicoRef(String nrContaUnicoRef) {
		this.nrContaUnicoRef = nrContaUnicoRef;
	}
	public String getNmRemetente() {
		return nmRemetente;
	}
	public void setNmRemetente(String nmRemetente) {
		this.nmRemetente = nmRemetente;
	}
	public String getEnderecoRemetente() {
		return enderecoRemetente;
	}
	public void setEnderecoRemetente(String enderecoRemetente) {
		this.enderecoRemetente = enderecoRemetente;
	}
	public String getCidadeRemetente() {
		return cidadeRemetente;
	}
	public void setCidadeRemetente(String cidadeRemetente) {
		this.cidadeRemetente = cidadeRemetente;
	}
	public String getPaisRemetente() {
		return paisRemetente;
	}
	public void setPaisRemetente(String paisRemetente) {
		this.paisRemetente = paisRemetente;
	}
	public String getOrigemFundos() {
		return origemFundos;
	}
	public void setOrigemFundos(String origemFundos) {
		this.origemFundos = origemFundos;
	}
	public String getCdTransacao05() {
		return cdTransacao05;
	}
	public void setCdTransacao05(String cdTransacao05) {
		this.cdTransacao05 = cdTransacao05;
	}
	public String getCdQualificadorTransacao05() {
		return cdQualificadorTransacao05;
	}
	public void setCdQualificadorTransacao05(String cdQualificadorTransacao05) {
		this.cdQualificadorTransacao05 = cdQualificadorTransacao05;
	}
	public String getNrSeqCompTransacao05() {
		return nrSeqCompTransacao05;
	}
	public void setNrSeqCompTransacao05(String nrSeqCompTransacao05) {
		this.nrSeqCompTransacao05 = nrSeqCompTransacao05;
	}
	public String getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}
	public Double getVlAutorizado() {
		return vlAutorizado;
	}
	public void setVlAutorizado(Double vlAutorizado) {
		this.vlAutorizado = vlAutorizado;
	}
	public String getCdMoedaVlAutorizado() {
		return cdMoedaVlAutorizado;
	}
	public void setCdMoedaVlAutorizado(String cdMoedaVlAutorizado) {
		this.cdMoedaVlAutorizado = cdMoedaVlAutorizado;
	}
	public String getCdRespAutorizacao() {
		return cdRespAutorizacao;
	}
	public void setCdRespAutorizacao(String cdRespAutorizacao) {
		this.cdRespAutorizacao = cdRespAutorizacao;
	}
	public String getIndicadorDireitoDevolucao() {
		return indicadorDireitoDevolucao;
	}
	public void setIndicadorDireitoDevolucao(String indicadorDireitoDevolucao) {
		this.indicadorDireitoDevolucao = indicadorDireitoDevolucao;
	}
	public String getIndicadorComerEletronico() {
		return indicadorComerEletronico;
	}
	public void setIndicadorComerEletronico(String indicadorComerEletronico) {
		this.indicadorComerEletronico = indicadorComerEletronico;
	}
	public String getIndicadorAuthEspecifica() {
		return indicadorAuthEspecifica;
	}
	public void setIndicadorAuthEspecifica(String indicadorAuthEspecifica) {
		this.indicadorAuthEspecifica = indicadorAuthEspecifica;
	}
	public Double getVlTotalAutorizado() {
		return vlTotalAutorizado;
	}
	public void setVlTotalAutorizado(Double vlTotalAutorizado) {
		this.vlTotalAutorizado = vlTotalAutorizado;
	}
	public String getCavvVlVerifAuthPortador() {
		return cavvVlVerifAuthPortador;
	}
	public void setCavvVlVerifAuthPortador(String cavvVlVerifAuthPortador) {
		this.cavvVlVerifAuthPortador = cavvVlVerifAuthPortador;
	}
	public String getCdResultVerifCavv() {
		return cdResultVerifCavv;
	}
	public void setCdResultVerifCavv(String cdResultVerifCavv) {
		this.cdResultVerifCavv = cdResultVerifCavv;
	}
	public String getCdTransacao07() {
		return cdTransacao07;
	}
	public void setCdTransacao07(String cdTransacao07) {
		this.cdTransacao07 = cdTransacao07;
	}
	public String getCdQualificadorTransacao07() {
		return cdQualificadorTransacao07;
	}
	public void setCdQualificadorTransacao07(String cdQualificadorTransacao07) {
		this.cdQualificadorTransacao07 = cdQualificadorTransacao07;
	}
	public String getNrSeqCompTransacao07() {
		return nrSeqCompTransacao07;
	}
	public void setNrSeqCompTransacao07(String nrSeqCompTransacao07) {
		this.nrSeqCompTransacao07 = nrSeqCompTransacao07;
	}
	public String getTpTransacao() {
		return tpTransacao;
	}
	public void setTpTransacao(String tpTransacao) {
		this.tpTransacao = tpTransacao;
	}
	public String getNrSeqCartao() {
		return nrSeqCartao;
	}
	public void setNrSeqCartao(String nrSeqCartao) {
		this.nrSeqCartao = nrSeqCartao;
	}
	public String getDtTransacaoTerminal() {
		return dtTransacaoTerminal;
	}
	public void setDtTransacaoTerminal(String dtTransacaoTerminal) {
		this.dtTransacaoTerminal = dtTransacaoTerminal;
	}
	public String getCapacidadeTerminal() {
		return capacidadeTerminal;
	}
	public void setCapacidadeTerminal(String capacidadeTerminal) {
		this.capacidadeTerminal = capacidadeTerminal;
	}
	public String getCdPaisTerminal() {
		return cdPaisTerminal;
	}
	public void setCdPaisTerminal(String cdPaisTerminal) {
		this.cdPaisTerminal = cdPaisTerminal;
	}
	public String getNrSerieTerminal() {
		return nrSerieTerminal;
	}
	public void setNrSerieTerminal(String nrSerieTerminal) {
		this.nrSerieTerminal = nrSerieTerminal;
	}
	public String getNrRandomCriptograma() {
		return nrRandomCriptograma;
	}
	public void setNrRandomCriptograma(String nrRandomCriptograma) {
		this.nrRandomCriptograma = nrRandomCriptograma;
	}
	public String getContadorTransacaoApp() {
		return contadorTransacaoApp;
	}
	public void setContadorTransacaoApp(String contadorTransacaoApp) {
		this.contadorTransacaoApp = contadorTransacaoApp;
	}
	public String getAppInterchangeProfile() {
		return appInterchangeProfile;
	}
	public void setAppInterchangeProfile(String appInterchangeProfile) {
		this.appInterchangeProfile = appInterchangeProfile;
	}
	public String getCriptograma() {
		return criptograma;
	}
	public void setCriptograma(String criptograma) {
		this.criptograma = criptograma;
	}
	public String getIndiceDerivacaoChave() {
		return indiceDerivacaoChave;
	}
	public void setIndiceDerivacaoChave(String indiceDerivacaoChave) {
		this.indiceDerivacaoChave = indiceDerivacaoChave;
	}
	public String getNrVersaoCriptograma() {
		return nrVersaoCriptograma;
	}
	public void setNrVersaoCriptograma(String nrVersaoCriptograma) {
		this.nrVersaoCriptograma = nrVersaoCriptograma;
	}
	public String getVerifResultTerminal() {
		return verifResultTerminal;
	}
	public void setVerifResultTerminal(String verifResultTerminal) {
		this.verifResultTerminal = verifResultTerminal;
	}
	public String getVerifResultCartao() {
		return verifResultCartao;
	}
	public void setVerifResultCartao(String verifResultCartao) {
		this.verifResultCartao = verifResultCartao;
	}
	public Double getVlTransacaoCriptograma() {
		return vlTransacaoCriptograma;
	}
	public void setVlTransacaoCriptograma(Double vlTransacaoCriptograma) {
		this.vlTransacaoCriptograma = vlTransacaoCriptograma;
	}
	public String getFormFactorIndicator() {
		return formFactorIndicator;
	}
	public void setFormFactorIndicator(String formFactorIndicator) {
		this.formFactorIndicator = formFactorIndicator;
	}
	public String getCdTransacao08() {
		return cdTransacao08;
	}
	public void setCdTransacao08(String cdTransacao08) {
		this.cdTransacao08 = cdTransacao08;
	}
	public String getSubCdTransacao08() {
		return subCdTransacao08;
	}
	public void setSubCdTransacao08(String subCdTransacao08) {
		this.subCdTransacao08 = subCdTransacao08;
	}
	public String getCdCompanhiaAerea() {
		return cdCompanhiaAerea;
	}
	public void setCdCompanhiaAerea(String cdCompanhiaAerea) {
		this.cdCompanhiaAerea = cdCompanhiaAerea;
	}
	public String getNrDocEmbarque() {
		return nrDocEmbarque;
	}
	public void setNrDocEmbarque(String nrDocEmbarque) {
		this.nrDocEmbarque = nrDocEmbarque;
	}
	public String getCdAgenteIata() {
		return cdAgenteIata;
	}
	public void setCdAgenteIata(String cdAgenteIata) {
		this.cdAgenteIata = cdAgenteIata;
	}
	public String getNmPassageiro() {
		return nmPassageiro;
	}
	public void setNmPassageiro(String nmPassageiro) {
		this.nmPassageiro = nmPassageiro;
	}
	public String getCdCidade1() {
		return cdCidade1;
	}
	public void setCdCidade1(String cdCidade1) {
		this.cdCidade1 = cdCidade1;
	}
	public String getTransportadora1() {
		return transportadora1;
	}
	public void setTransportadora1(String transportadora1) {
		this.transportadora1 = transportadora1;
	}
	public String getClasseServico1() {
		return classeServico1;
	}
	public void setClasseServico1(String classeServico1) {
		this.classeServico1 = classeServico1;
	}
	public String getCdCidade2() {
		return cdCidade2;
	}
	public void setCdCidade2(String cdCidade2) {
		this.cdCidade2 = cdCidade2;
	}
	public String getTransportadora2() {
		return transportadora2;
	}
	public void setTransportadora2(String transportadora2) {
		this.transportadora2 = transportadora2;
	}
	public String getClasseServico2() {
		return classeServico2;
	}
	public void setClasseServico2(String classeServico2) {
		this.classeServico2 = classeServico2;
	}
	public String getCdCidade3() {
		return cdCidade3;
	}
	public void setCdCidade3(String cdCidade3) {
		this.cdCidade3 = cdCidade3;
	}
	public String getTransportadora3() {
		return transportadora3;
	}
	public void setTransportadora3(String transportadora3) {
		this.transportadora3 = transportadora3;
	}
	public String getClasseServico3() {
		return classeServico3;
	}
	public void setClasseServico3(String classeServico3) {
		this.classeServico3 = classeServico3;
	}
	public String getCdCidade4() {
		return cdCidade4;
	}
	public void setCdCidade4(String cdCidade4) {
		this.cdCidade4 = cdCidade4;
	}
	public String getTransportadora4() {
		return transportadora4;
	}
	public void setTransportadora4(String transportadora4) {
		this.transportadora4 = transportadora4;
	}
	public String getClasseServico4() {
		return classeServico4;
	}
	public void setClasseServico4(String classeServico4) {
		this.classeServico4 = classeServico4;
	}
	public String getCdCidade5() {
		return cdCidade5;
	}
	public void setCdCidade5(String cdCidade5) {
		this.cdCidade5 = cdCidade5;
	}
	public String getCdAeroportoDestino() {
		return cdAeroportoDestino;
	}
	public void setCdAeroportoDestino(String cdAeroportoDestino) {
		this.cdAeroportoDestino = cdAeroportoDestino;
	}
	public String getDtPrimeiroVoo() {
		return dtPrimeiroVoo;
	}
	public void setDtPrimeiroVoo(String dtPrimeiroVoo) {
		this.dtPrimeiroVoo = dtPrimeiroVoo;
	}
	public String getNmAgenteEmissorDoc() {
		return nmAgenteEmissorDoc;
	}
	public void setNmAgenteEmissorDoc(String nmAgenteEmissorDoc) {
		this.nmAgenteEmissorDoc = nmAgenteEmissorDoc;
	}
	public String getCdTransacao09() {
		return cdTransacao09;
	}
	public void setCdTransacao09(String cdTransacao09) {
		this.cdTransacao09 = cdTransacao09;
	}
	public String getSubCdTransacao09() {
		return subCdTransacao09;
	}
	public void setSubCdTransacao09(String subCdTransacao09) {
		this.subCdTransacao09 = subCdTransacao09;
	}
	public String getCdTransacaoOriginal() {
		return cdTransacaoOriginal;
	}
	public void setCdTransacaoOriginal(String cdTransacaoOriginal) {
		this.cdTransacaoOriginal = cdTransacaoOriginal;
	}
	public String getDtMovimento() {
		return dtMovimento;
	}
	public void setDtMovimento(String dtMovimento) {
		this.dtMovimento = dtMovimento;
	}
	public String getCdErro() {
		return cdErro;
	}
	public void setCdErro(String cdErro) {
		this.cdErro = cdErro;
	}
	public String getDescricaoErro() {
		return descricaoErro;
	}
	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}
	public String getRegistroComErro() {
		return registroComErro;
	}
	public void setRegistroComErro(String registroComErro) {
		this.registroComErro = registroComErro;
	}
	public String getPosicaoComErro() {
		return posicaoComErro;
	}
	public void setPosicaoComErro(String posicaoComErro) {
		this.posicaoComErro = posicaoComErro;
	}
	public String getLoginUsuLiberacao() {
		return loginUsuLiberacao;
	}
	public void setLoginUsuLiberacao(String loginUsuLiberacao) {
		this.loginUsuLiberacao = loginUsuLiberacao;
	}
	public Date getDtLiberacao() {
		return dtLiberacao;
	}
	public void setDtLiberacao(Date dtLiberacao) {
		this.dtLiberacao = dtLiberacao;
	}
	public String getLoginUsuAcatado() {
		return loginUsuAcatado;
	}
	public void setLoginUsuAcatado(String loginUsuAcatado) {
		this.loginUsuAcatado = loginUsuAcatado;
	}
	public Date getDtAcatado() {
		return dtAcatado;
	}
	public void setDtAcatado(Date dtAcatado) {
		this.dtAcatado = dtAcatado;
	}
	public String getLoginUsuRejeicao() {
		return loginUsuRejeicao;
	}
	public void setLoginUsuRejeicao(String loginUsuRejeicao) {
		this.loginUsuRejeicao = loginUsuRejeicao;
	}
	public Date getDtRejeicao() {
		return dtRejeicao;
	}
	public void setDtRejeicao(Date dtRejeicao) {
		this.dtRejeicao = dtRejeicao;
	}
}
