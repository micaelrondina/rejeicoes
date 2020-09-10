package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE Padrao - Registro 02
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
	public String getCdPaisLiq() {
		return linha.substring(16, 19);
	}
	public String getQtDiasLiqFinanTransacao() {
		return linha.substring(22, 25);
	}
	public Double getVlIntercambio() {
		return (Utils.convertStringDouble(linha.substring(25, 35)) / 100);
	}
	public String getDtMovTransacaoOriginal() {
		return linha.substring(35, 43);
	}
	public String getTpOperacao() {
		return linha.substring(43, 48);
	}
	public String getTokenPan() {
		return linha.substring(48, 67);
	}
	public String getTokenRequestorId() {
		return linha.substring(67, 78); 
	}
	public String getTokenAssuranceLevel() {
		return linha.substring(78, 80);
	}
	public String getCepEc() {
		return linha.substring(80, 88);
	}
	public String getDtLiquiTransacao() {
		return linha.substring(88, 96);
	}
	public String getCdPvMarketplace() {
		return linha.substring(96, 111);
	}
}
