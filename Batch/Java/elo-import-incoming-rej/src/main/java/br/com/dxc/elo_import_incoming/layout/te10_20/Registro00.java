package br.com.dxc.elo_import_incoming.layout.te10_20;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE10 e TE20 - Registro 00
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
	public String getCdDestino() {
		return linha.substring(4, 8);
	}
	public String getCdOrigem() {
		return linha.substring(8, 12);
	}
	public String getCdMotivoTransacao() {
		return linha.substring(12, 16);
	}
	public String getCdPais00() {
		return linha.substring(16, 19);
	}
	public String getDtEnvio() {
		return linha.substring(19, 27);
	}
	public String getNrCartao() {
		return linha.substring(27, 46);
	}
	public Double getVlDestino() {
		return (Utils.convertStringDouble(linha.substring(46, 58)) / 100);
	}
	public String getCdMoedaDestino() {
		return linha.substring(58, 61);
	}
	public Double getVlOrigem() {
		return (Utils.convertStringDouble(linha.substring(61, 73)) / 100);
	}
	public String getCdMoedaOrigem() {
		return linha.substring(73, 76);
	}
	public String getMsgTexto() {
		return linha.substring(76, 146);
	}
	public String getIndicadorLiq() {
		return linha.substring(146, 147);
	}
	public String getIdTransacaoOriginal() {
		return linha.substring(147, 162);
	}
	public String getDtProcessamento() {
		return linha.substring(162, 166);
	}
}
