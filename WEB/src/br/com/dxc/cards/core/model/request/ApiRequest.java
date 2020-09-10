package br.com.dxc.cards.core.model.request;

public class ApiRequest<T> {
	private T filtro;
	private T dados;

	/**
	 * @return the filtro
	 */
	public T getFiltro() {
		return filtro;
	}
	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(T filtro) {
		this.filtro = filtro;
	}


	public T getDados() {
		return this.dados;
	}

	public void setDados(T dados) {
		this.dados = dados;
	}


}
