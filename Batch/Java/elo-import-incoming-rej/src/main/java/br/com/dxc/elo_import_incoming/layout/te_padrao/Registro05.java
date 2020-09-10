package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE Padrao - Registro 05
public class Registro05 extends LayoutBase {
	
	public Registro05(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao05() {
		return linha.substring(0, 2);
	}
	public String getCdQualificadorTransacao05() {
		return linha.substring(2, 3);
	}
	public String getNrSeqCompTransacao05() {
		return linha.substring(3, 4);
	}
	public String getIdTransacao() {
		return linha.substring(4, 19);
	}
	public Double getVlAutorizado() {
		return (Utils.convertStringDouble(linha.substring(19, 31)) / 100);
	}
	public String getCdMoedaVlAutorizado() {
		return linha.substring(31, 34);
	}
	public String getCdRespAutorizacao() {
		return linha.substring(34, 36);
	}
	public String getIndicadorDireitoDevolucao() {
		return linha.substring(42, 44);
	}
	public String getIndicadorComerEletronico() {
		return linha.substring(44, 46);
	}
	public String getIndicadorAuthEspecifica() {
		return linha.substring(48, 49);
	}
	public Double getVlTotalAutorizado() {
		return (Utils.convertStringDouble(linha.substring(49, 61)) / 100);
	}
	public String getCavvVlVerifAuthPortador() {
		return linha.substring(61, 103);
	}
	public String getCdResultVerifCavv() {
		return linha.substring(103, 104);
	}
}
