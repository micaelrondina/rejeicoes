package br.com.dxc.cards.core.model;

import java.math.BigDecimal;

public class IncomingHistorico {
    private String cupon;
    private String cuotasVan;
    private String tranCode;
    private String producto;
    private String fechaOutgoing;
    private BigDecimal importe;
    private String marca;
    private String acquirerRefNum;
    
	public String getCupon() {
		return cupon;
	}
	public void setCupon(String cupon) {
		this.cupon = cupon;
	}
	public String getCuotasVan() {
		return cuotasVan;
	}
	public void setCuotasVan(String cuotasVan) {
		this.cuotasVan = cuotasVan;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getFechaOutgoing() {
		return fechaOutgoing;
	}
	public void setFechaOutgoing(String fechaOutgoing) {
		this.fechaOutgoing = fechaOutgoing;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getAcquirerRefNum() {
		return acquirerRefNum;
	}
	public void setAcquirerRefNum(String acquirerRefNum) {
		this.acquirerRefNum = acquirerRefNum;
	}
}
