package br.com.dxc.elo_import_incoming.layout.te40;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE40 - Registro 02
public class Registro02 extends LayoutBase {
	
	public Registro02(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao02() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao02() {
		return linha.substring(2, 4);
	}
	public String getIdTransacao() {
		return linha.substring(4, 19);
	}
	public String getPontoVenda() {
		return linha.substring(22, 37);
	}
	public String getNrLogicoEquipamento() {
		return linha.substring(37, 45);
	}
	public String getIndicadorTransTroca() {
		return linha.substring(53, 54);
	}
	public String getCdAuthTransacao() {
		return linha.substring(54, 60);
	}
	public String getMeioIdentPortador() {
		return linha.substring(60, 61);
	}
	public String getModoEntTransacaoPos() {
		return linha.substring(61, 63);
	}
	public String getIdentTecnologTerminal() {
		return linha.substring(63, 64);
	}
	public String getTecnologiaCartao() {
		return linha.substring(64, 65);
	}
	public Double getVlTroco() {
		return (Utils.convertStringDouble(linha.substring(71, 80)) / 100);
	}
	public String getIndicTransFeitaPor() {
		return linha.substring(81, 82);
	}
	public String getCepPortador() {
		return linha.substring(82, 92);
	}
	public String getCidadePortador() {
		return linha.substring(92, 103);
	}
	public String getUfPortador() {
		return linha.substring(103, 105);
	}
	public String getDtConfirmFraude() {
		return linha.substring(105, 113);
	}
	public String getIndicadorLiq() {
		return linha.substring(113, 114);
	}
	public String getNmPortador() {
		return linha.substring(114, 144);
	}
	public String getTokenPan() {
		return linha.substring(144, 163);
	}
}
