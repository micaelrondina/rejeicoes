package br.com.dxc.elo_import_incoming.layout.te_padrao;

import br.com.dxc.elo_import_incoming.layout.LayoutBase;
import br.com.dxc.elo_import_incoming.utils.Utils;

//TE Padrao - Registro 01
public class Registro01 extends LayoutBase {
	
	public Registro01(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}

	public String getCdTransacao01() {
		return linha.substring(0, 2);
	}
	public String getSubCdTransacao01() {
		return linha.substring(2, 4);
	}
	public String getNrRefDisputa() {
		return linha.substring(16, 22);
	}
	public String getIndicadorEnvDoc() {
		return linha.substring(22, 23);
	}
	public String getTxtLivre() {
		return linha.substring(23, 73);
	}
	public String getCdProduto() {
		return linha.substring(73, 76);
	}
	public String getPontoVenda() {
		return linha.substring(80, 95);
	}
	public String getNrLogicoEquipamento() {
		return linha.substring(95, 103);
	}
	public Double getVlTaxaEmbarque() {
		return (Utils.convertStringDouble(linha.substring(103, 115)) / 100);
	}
	public String getIndicTransFeitaPor() {
		return linha.substring(115, 116);
	}
	public Double getVlTransacao() {
		return (Utils.convertStringDouble(linha.substring(116, 128)) / 100);
	}
	public String getIndicadorMovimentacao() {
		return linha.substring(128, 129);
	}
	public String getQtParcelasTransacao() {
		return linha.substring(129, 132);
	}
	public String getNrParcela() {
		return linha.substring(132, 135);
	}
	public String getTarifaPagtoInsumo() {
		return linha.substring(135, 140);
	}
	public String getTpPessoa() {
		return linha.substring(140, 141);
	}
	public String getDocumento() {
		return linha.substring(141, 155);
	}
	public Double getVlTrocoAgroDeb() {
		return (Utils.convertStringDouble(linha.substring(155, 166)) / 100);
	}
	public String getCdCondTransacaoChip() {
		return linha.substring(166, 167);
	}
}
