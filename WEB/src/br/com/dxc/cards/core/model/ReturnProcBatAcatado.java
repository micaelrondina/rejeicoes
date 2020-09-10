package br.com.dxc.cards.core.model;

public class ReturnProcBatAcatado {

	private Long qtdItensMarcadosComoAcatados;
	private String idsDosItemsMarcados;
	private String mensagem;

	public Long getQtdItensMarcadosComoAcatados() {
		return qtdItensMarcadosComoAcatados;
	}
	public void setQtdItensMarcadosComoAcatados(Long qtdItensMarcadosComoAcatados) {
		this.qtdItensMarcadosComoAcatados = qtdItensMarcadosComoAcatados;
	}
	public String getIdsDosItemsMarcados() {
		return idsDosItemsMarcados;
	}
	public void setIdsDosItemsMarcados(String idsDosItemsMarcados) {
		this.idsDosItemsMarcados = idsDosItemsMarcados;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
