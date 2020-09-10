package br.com.dxc.cards.core.model;

import java.util.List;

public class Reenvio {

    private String usuario;
    private String data;
    private List<IdIncoming> idsIncomings;


    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<IdIncoming> getIdsIncomings() {
        return this.idsIncomings;
    }

    public void setIdsIncomings(List<IdIncoming> idsIncomings) {
        this.idsIncomings = idsIncomings;
    }

}