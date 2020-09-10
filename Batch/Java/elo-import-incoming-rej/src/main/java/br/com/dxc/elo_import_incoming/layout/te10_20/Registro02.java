package br.com.dxc.elo_import_incoming.layout.te10_20;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;

//TE10 e TE20 - Registro 02
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
	public String getCdPais02() {
		return linha.substring(16, 19);
	}
	public String getQtDiasLiqFinanTransacao() {
		return linha.substring(22, 25);
	}
	public String getDtProcessamentoRegTp02() {
		return linha.substring(25, 33);
	}
	public String getCdErro() {
		return linha.substring(33, 36);
	}
	public String getTokenPan() {
		return linha.substring(36, 55);
	}
	public String getDtLiqTransacao() {
		return linha.substring(55, 63);
	}
	
}
