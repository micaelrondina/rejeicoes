package br.com.dxc.elo_import_incoming.layout;

import br.com.dxc.elo_import_incoming.utils.Utils;

public class RegistroTrailer extends LayoutBase {
	
	public RegistroTrailer(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdRegistroBz() {
		return linha.substring(0, 2);
	}
	public String getCdServicoBz() {
		return linha.substring(2, 4);
	}
	public String getNrRemessaBz() {
		return linha.substring(4, 8);
	}
	public String getQtTransCreMoedaReal() {
		return linha.substring(8, 16);
	}
	public Double getVlTransCreMoedaReal() {
		return (Utils.convertStringDouble(linha.substring(16, 31)) / 100);
	}
	public String getQtTransDebMoedaReal() {
		return linha.substring(31, 39);
	}
	public Double getVlTransDebMoedaReal() {
		return (Utils.convertStringDouble(linha.substring(39, 54)) / 100);
	}
	public String getQtTransCreMoedaDolar() {
		return linha.substring(54, 62);
	}
	public Double getVlTransCreMoedaDolar() {
		return (Utils.convertStringDouble(linha.substring(62, 77)) / 100);
	}
	public String getQtTransDebMoedaDolar() {
		return linha.substring(77, 85);
	}
	public Double getVlTransDebMoedaDolar() {
		return (Utils.convertStringDouble(linha.substring(85, 100)) / 100);
	}
	public String getQtTotReg() {
		return linha.substring(100, 108);
	}
	public String getQtTransMoviParcelado() {
		return linha.substring(108, 116);
	}
	public Double getVlTransMoviParcelado() {
		return (Utils.convertStringDouble(linha.substring(116, 131)) / 100);
	}
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
	public String getQtTransCreRjMoedaReal() {
		return linha.substring(131, 137);
	}
	public Double getVlTotTransCreRjMoedaReal() {
		return (Utils.convertStringDouble(linha.substring(137, 149)) / 100);
	}
	public String getQtTransDebRjMoedaReal() {
		return linha.substring(149, 155);
	}
	public Double getVlTotTransDebRjMoedaReal() {
		return (Utils.convertStringDouble(linha.substring(155, 167)) / 100);
	}
	/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */
	public String getIndicadorRotaArqBz() {
		return linha.substring(167, 168);
	}
}
