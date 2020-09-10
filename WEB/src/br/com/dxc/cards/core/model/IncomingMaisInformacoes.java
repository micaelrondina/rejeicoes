package br.com.dxc.cards.core.model;

public class IncomingMaisInformacoes {

	private DxcIncomingEloMaster incoming;
	private DxcIncomingEloItem incomingItem;
	private DxcIncomingEloItemTE1020 incomingItemTE1020;
	private DxcIncomingEloItemTE40 incomingItemTE40;

	public DxcIncomingEloMaster getIncoming() {
		return incoming;
	}
	public void setIncoming(DxcIncomingEloMaster incoming) {
		this.incoming = incoming;
	}
	public DxcIncomingEloItem getIncomingItem() {
		return incomingItem;
	}
	public void setIncomingItem(DxcIncomingEloItem incomingItem) {
		this.incomingItem = incomingItem;
	}
	public DxcIncomingEloItemTE1020 getIncomingItemTE1020() {
		return incomingItemTE1020;
	}
	public void setIncomingItemTE1020(DxcIncomingEloItemTE1020 incomingItemTE1020) {
		this.incomingItemTE1020 = incomingItemTE1020;
	}
	public DxcIncomingEloItemTE40 getIncomingItemTE40() {
		return incomingItemTE40;
	}
	public void setIncomingItemTE40(DxcIncomingEloItemTE40 incomingItemTE40) {
		this.incomingItemTE40 = incomingItemTE40;
	}
}