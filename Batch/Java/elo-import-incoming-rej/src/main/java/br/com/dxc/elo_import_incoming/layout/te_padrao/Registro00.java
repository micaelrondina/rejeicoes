package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE Padrao - Registro 00
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
		return linha.substring(4, 23);
	}
	public String getTpLiquidacao() {
		return linha.substring(23, 24);
	}
	public String getIndOrigemAuthCanc() {
		return linha.substring(25, 26);
	}
	public String getNrRefTransacao() {
		return linha.substring(26, 49);
	}
	public String getCdProcesso() {
		return linha.substring(51, 53);
	}
	public String getCdCredenciador() {
		return linha.substring(53, 57);
	}
	public String getDtVendaSaque() {
		return linha.substring(57, 65);
	}
	public String getHrVendaSaque() {
		return linha.substring(65, 71);
	}
	public Double getVlVendaSaqueDisputa() {
		return (Utils.convertStringDouble(linha.substring(76, 88)) / 100);
	}
	public String getCdMoedaTransacao() {
		return linha.substring(88, 91);
	}
	public String getNmPontoVenda() {
		return linha.substring(91, 116);
	}
	public String getCidadePontoVenda() {
		return linha.substring(116, 129);
	}
	public String getCdPaisPontoVenda() {
		return linha.substring(129, 132);
	}
	public String getMccPontoVenda() {
		return linha.substring(132, 136);
	}
	public String getBancoEmissor() {
		return linha.substring(137, 141);
	}
	public String getCdBandeira() {
		return linha.substring(143, 146);
	}
	public String getIdentTpTransacao() {
		return linha.substring(146, 147);
	}
	public String getCdMotivoDisputa() {
		return linha.substring(147, 149);
	}
	public String getCdAuthTransacao() {
		return linha.substring(149, 155);
	}
	public String getIndicadorTecnoTerminal() {
		return linha.substring(155, 156);
	}
	public String getMeioIdentPortador() {
		return linha.substring(156, 157);
	}
	public String getModoEntTransacaoPos() {
		return linha.substring(158, 160);
	}
	public String getDtMovApresentDisputa() {
		return linha.substring(160, 168);
	}
}
