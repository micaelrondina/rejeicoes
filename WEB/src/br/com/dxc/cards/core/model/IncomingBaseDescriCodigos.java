package br.com.dxc.cards.core.model;

import java.util.HashMap;

public abstract class IncomingBaseDescriCodigos {
	
	private HashMap<String, String> mapDescricoesCodigos = new HashMap<>();
	
	public void setValueMapDescricoesCodigos(String key, String value) {
		this.mapDescricoesCodigos.put(key, value);
	}
	public HashMap<String, String> getMapDescricoesCodigos() {
		return mapDescricoesCodigos;
	}
}
