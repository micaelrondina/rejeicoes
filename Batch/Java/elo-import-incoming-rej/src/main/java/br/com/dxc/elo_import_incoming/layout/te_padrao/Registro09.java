package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;

//TE Padrao - Registro 09
public class Registro09 extends LayoutBase {
	
	public Registro09(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao09() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao09() {
		return linha.substring(2, 4);
	}
	public String getCdTransacaoOriginal() {
		return linha.substring(16, 18);
	}
	public String getDtMovimento() {
		return linha.substring(20, 28);
	}
	public String getCdErro() {
		return linha.substring(35, 38);
	}
	public String getDescricaoErro() {
		return linha.substring(38, 118);
	}
	public String getRegistroComErro() {
		return linha.substring(118, 120);
	}
	public String getPosicaoComErro() {
		return linha.substring(120, 123);
	}
}
