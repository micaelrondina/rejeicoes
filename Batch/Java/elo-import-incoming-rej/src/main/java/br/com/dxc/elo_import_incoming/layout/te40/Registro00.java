package br.com.dxc.elo_import_incoming.layout.te40;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE40 - Registro 00
public class Registro00 extends LayoutBase {
	
	public Registro00(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao00() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao00() {
		return linha.substring(2, 4);
	}
	public String getNrCartao() {
		return linha.substring(4, 20);
	}
	public String getComplementoNrCartao() {
		return linha.substring(20, 27);
	}
	public String getNrRefTransacao() {
		return linha.substring(27, 50);
	}
	public String getCdCredenciador() {
		return linha.substring(54, 58);
	}
	public String getDtVendaSaque() {
		return linha.substring(58, 66);
	}
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
	public String getCdErro() {
		return linha.substring(66,69);
	}
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */
	public String getCidadePontoVenda() {
		return linha.substring(91, 102);
	}
	public String getCdPaisPontoVenda() {
		return linha.substring(102, 105);
	}
	public String getMccPontoVenda() {
		return linha.substring(105, 109);
	}
	public Double getVlFraude() {
		return (Utils.convertStringDouble(linha.substring(109, 121)) / 100);
	}
	public String getCdMoedaTransFraudulenta() {
		return linha.substring(121, 124);
	}
	public String getDtNotificacaoFraude() {
		return linha.substring(124, 132);
	}
	public String getIndicadorOrigemAuth() {
		return linha.substring(132, 133);
	}
	public String getCdNotificacao() {
		return linha.substring(133, 134);
	}
	public String getTpFraude() {
		return linha.substring(134, 136);
	}
	public String getDtVencCartao() {
		return linha.substring(136, 140);
	}
	public String getTpPlataforma() {
		return linha.substring(150, 152);
	}
	public String getCdBandeira() {
		return linha.substring(152, 155);
	}
	public String getBancoEmissor() {
		return linha.substring(156, 160);
	}
}