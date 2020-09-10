package br.com.dxc.cards.core.model.response;

public class ApiResponse<T> {
	private T dados;
	private ApiError falha;	
	
	public T getDados() {
		return dados;
	}
	public void setDados(T dados) {
		this.dados = dados;
	}

	public ApiError getFalha() {
		return falha;
	}
	public void setFalha(ApiError falha) {
		this.falha = falha;
	}

}
