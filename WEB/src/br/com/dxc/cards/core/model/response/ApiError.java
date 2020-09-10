package br.com.dxc.cards.core.model.response;

import java.util.Arrays;
import java.util.List;

public class ApiError {
	 
    private String message;
    private List<String> erros;
 
    public ApiError( String message, List<String> erros) {
        super();
        this.message = message;
        this.erros = erros;
    }
 
    public ApiError(String message, String erros) {
        super();
        this.message = message;
        this.erros = Arrays.asList(erros);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}