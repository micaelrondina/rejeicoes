package br.com.dxc.cards.core.model;

public class IdIncoming {
    private Long idMatriz;
    private Long idItem;
    
    public IdIncoming() {
    	
    }
    
    public IdIncoming(Long idMatriz, Long idItem) {
    	this.idMatriz = idMatriz;
    	this.idItem = idItem;
    }
    

    public Long getIdMatriz() {
        return this.idMatriz;
    }

    public void setIdMatriz(Long idMatriz) {
        this.idMatriz = idMatriz;
    }

    public Long getIdItem() {
        return this.idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }
}