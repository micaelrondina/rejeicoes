package br.com.dxc.cards.core.model;

public class FiltroConsultarMaisInfo {
	private long idMatriz;
	private long idItem;
	private String numRefTrans;
	private String nrParcela;
	private String idTpTrans;
	
	public FiltroConsultarMaisInfo() {}
	
	public FiltroConsultarMaisInfo(long idMatriz, long idItem, String numRefTrans, String nrParcela) {
		this.idMatriz = idMatriz;
		this.idItem = idItem;
		this.numRefTrans = numRefTrans;
		this.nrParcela = nrParcela;
	}
	
	public long getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(long idMatriz) {
		this.idMatriz = idMatriz;
	}
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}
	public String getNumRefTrans() {
		return numRefTrans;
	}
	public void setNumRefTrans(String numRefTrans) {
		this.numRefTrans = numRefTrans;
	}
	public String getNrParcela() {
		return nrParcela;
	}
	public void setNrParcela(String nrParcela) {
		this.nrParcela = nrParcela;
	}
	public String getIdTpTrans() {
		return idTpTrans;
	}
	public void setIdTpTrans(String idTpTrans) {
		this.idTpTrans = idTpTrans;
	}
}
