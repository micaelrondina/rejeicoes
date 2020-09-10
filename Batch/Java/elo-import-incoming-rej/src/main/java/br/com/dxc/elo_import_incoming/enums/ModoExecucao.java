package br.com.dxc.elo_import_incoming.enums;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;

public enum ModoExecucao {
	IMPORTACAO_INCOMING(1, "Importacao dos Arquivos de Incoming da ELO"),
	GERAR_ARQUIVO_PAYWARE(2, "Geracao dos Arquivos com as Transacoes para Reenvio ao Payware"),
	IMPORTAR_ARQUIVO_RET_PAYWARE(3, "Importar Arquivo de Retorno do Payware com Status das Transacoes");

	private int codigo;
	private String descricao;
	
	ModoExecucao(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	/** Retorna uma String (Cod)-Descricao do Modo de Execução */
	public String getModoExecucao() {
		return "[" + codigo + "]-" + descricao;
	}
	
	/** Retorna uma String com TODOS os Modos de Execução: (Cod) - Descrição */
	public static String getModosExecucao() {
		StringBuilder retorno = new StringBuilder();
		for (ModoExecucao modo : ModoExecucao.values()) {
			retorno.append(" ou " + modo.getModoExecucao());
		}
		return retorno.toString().replaceFirst(" ou ", "");
	}

	/** Retorno o Modo de Execução do codigo passado
	 * @return ModoExecucao */
	public static ModoExecucao getByCodigo(String codigo) throws InvalidValueException {
		for (ModoExecucao modo : ModoExecucao.values()) {
			if (modo.getCodigo() == Integer.valueOf(codigo)) {
				return modo;
			}
		}
		throw new InvalidValueException("Modo de Execucao Invalido! ModoExecucao.getByCodigo: Nenhum Modo de Execucao encontrado com o Codigo: \"" + codigo + "\"");
	}
}
