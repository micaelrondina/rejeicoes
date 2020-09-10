package br.com.dxc.elo_import_incoming.layout;

import br.com.dxc.elo_import_incoming.utils.Utils;

public class RegistroTransacaoTe44 extends LayoutBase {
	
	public RegistroTransacaoTe44(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao() {
		return linha.substring(2, 4);
	}
	public String getBancoEmissor00() {
		return linha.substring(5, 9);
	}
	public String getDtMovimento() {
		return linha.substring(13, 21);
	}
	public String getNrRemessa00() {
		return linha.substring(21, 27);
	}
	public String getDtConfirmRemessa() {
		return linha.substring(27, 35);
	}
	public String getStRemessa() {
		return linha.substring(35, 36);
	}
	public String getMotivoRejeicao() {
		return linha.substring(36, 38);
	}
	public String getCdMoedaTransacao() {
		return linha.substring(38, 41);
	}
	public String getIndicadorTpRetorno() {
		return linha.substring(41, 42);
	}
	public String getQtTotRegArq() {
		return linha.substring(42, 57);
	}
	public String getQtTransAcMoedaReal() {
		return linha.substring(57, 72);
	}
	public Double getVlTransAcMoedaReal() {
		return (Utils.convertStringDouble(linha.substring(72, 87)) / 100);
	}
	public String getQtTransRjMoedaReal() {
		return linha.substring(87, 95);
	}
	public Double getVlTransRjMoedaReal() {
		return (Utils.convertStringDouble(linha.substring(95, 110)) / 100);
	}
	public String getCdBandeira00() {
		return linha.substring(110, 113);
	}
	public String getQtTransAcMoedaDolar() {
		return linha.substring(113, 121);
	}
	public Double getVlTransAcMoedaDolar() {
		return (Utils.convertStringDouble(linha.substring(121, 136)) / 100);
	}
	public String getQtTransRjMoedaDolar() {
		return linha.substring(136, 144);
	}
	public Double getVlTransRjMoedaDolar() {
		return (Utils.convertStringDouble(linha.substring(144, 159)) / 100);
	}
}
