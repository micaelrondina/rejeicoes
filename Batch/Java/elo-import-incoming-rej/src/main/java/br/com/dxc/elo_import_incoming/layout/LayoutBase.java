package br.com.dxc.elo_import_incoming.layout;

import java.lang.reflect.InvocationTargetException;

import br.com.dxc.elo_import_incoming.utils.Utils;

public abstract class LayoutBase {
	protected String linha;
	protected Object objetoPopular;
	
	public LayoutBase(String linha, Object objetoPopular) {
		this.linha = linha;
		this.objetoPopular = objetoPopular;
	}
	
	//para esse metodo funcionar e necessario que o metodo aqui tenha o mesmo nome do metodo declarado na classe do Objeto a Popular
	/** Preenche o Objeto DxcIncomingElo passado como parametro com o conteudo retornado dos metodos get da classe RegistroHeader, Registro01, Registro02... */
	public void preencherDxcIncomingElo() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Utils.preencheObjetoByLinha(objetoPopular, this);
	}
}
