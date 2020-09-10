package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;

//TE Padrao - Registro 08
public class Registro08 extends LayoutBase {
	
	public Registro08(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao08() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao08() {
		return linha.substring(2, 4);
	}
	public String getCdCompanhiaAerea() {
		return linha.substring(4, 7);
	}
	public String getNrDocEmbarque() {
		return linha.substring(7, 17);
	}
	public String getCdAgenteIata() {
		return linha.substring(17, 24);
	}
	public String getNmPassageiro() {
		return linha.substring(24, 49);
	}
	public String getCdCidade1() {
		return linha.substring(49, 52);
	}
	public String getTransportadora1() {
		return linha.substring(52, 56);
	}
	public String getClasseServico1() {
		return linha.substring(56, 58);
	}
	public String getCdCidade2() {
		return linha.substring(58, 61);
	}
	public String getTransportadora2() {
		return linha.substring(61, 65);
	}
	public String getClasseServico2() {
		return linha.substring(65, 67);
	}
	public String getCdCidade3() {
		return linha.substring(67, 70);
	}
	public String getTransportadora3() {
		return linha.substring(70, 74);
	}
	public String getClasseServico3() {
		return linha.substring(74, 76);
	}
	public String getCdCidade4() {
		return linha.substring(76, 79);
	}
	public String getTransportadora4() {
		return linha.substring(79, 83);
	}
	public String getClasseServico4() {
		return linha.substring(83, 85);
	}
	public String getCdCidade5() {
		return linha.substring(85, 88);
	}
	public String getCdAeroportoDestino() {
		return linha.substring(88, 91);
	}
	public String getDtPrimeiroVoo() {
		return linha.substring(91, 97);
	}
	public String getNmAgenteEmissorDoc() {
		return linha.substring(97, 127);
	}
}
