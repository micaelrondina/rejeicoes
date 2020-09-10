package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE Padrao - Registro 07
public class Registro07 extends LayoutBase {
	
	public Registro07(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao07() {
		return linha.substring(0, 2);
	}
	public String getCdQualificadorTransacao07() {
		return linha.substring(2, 3);
	}
	public String getNrSeqCompTransacao07() {
		return linha.substring(3, 4);
	}
	public String getTpTransacao() {
		return linha.substring(4, 6);
	}
	public String getNrSeqCartao() {
		return linha.substring(6, 9);
	}
	public String getDtTransacaoTerminal() {
		return linha.substring(9, 15);
	}
	public String getCapacidadeTerminal() {
		return linha.substring(15, 21);
	}
	public String getCdPaisTerminal() {
		return linha.substring(21, 24);
	}
	public String getNrSerieTerminal() {
		return linha.substring(24, 32);
	}
	public String getNrRandomCriptograma() {
		return linha.substring(32, 40);
	}
	public String getContadorTransacaoApp() {
		return linha.substring(40, 44);
	}
	public String getAppInterchangeProfile() {
		return linha.substring(44, 48);
	}
	public String getCriptograma() {
		return linha.substring(48, 64);
	}
	public String getIndiceDerivacaoChave() {
		return linha.substring(64, 66);
	}
	public String getNrVersaoCriptograma() {
		return linha.substring(66, 68);
	}
	public String getVerifResultTerminal() {
		return linha.substring(68, 78);
	}
	public String getVerifResultCartao() {
		return linha.substring(78, 86);
	}
	public Double getVlTransacaoCriptograma() {
		return (Utils.convertStringDouble(linha.substring(86, 98)) / 100);
	}
	public String getFormFactorIndicator() {
		return linha.substring(98, 108);
	}
}
