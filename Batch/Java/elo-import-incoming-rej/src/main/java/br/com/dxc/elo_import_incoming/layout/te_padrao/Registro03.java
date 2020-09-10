package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;

//TE Padrao - Registro 03
public class Registro03 extends LayoutBase {
	
	public Registro03(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao03() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao03() {
		return linha.substring(2, 4);
	}
	public String getTpPagto() {
		return linha.substring(4, 7);
	}
	public String getNrContaUnicoRef() {
		return linha.substring(7, 48);
	}
	public String getNmRemetente() {
		return linha.substring(48, 88);
	}
	public String getEnderecoRemetente() {
		return linha.substring(88, 138);
	}
	public String getCidadeRemetente() {
		return linha.substring(138, 163);
	}
	public String getPaisRemetente() {
		return linha.substring(163, 166);
	}
	public String getOrigemFundos() {
		return linha.substring(166, 168);
	}
}
