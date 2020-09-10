package br.com.dxc.elo_import_incoming.utils;

public class ValidacaoUtils {

	private ValidacaoUtils() {
		throw new IllegalStateException("Utility class - Can not to be instantiated");
	}

	public static boolean validarNomeArquivo(String nomeArquivo) {
		/*Exemplos nomes Arquivos:
		 * 0075140C156720190430032748_ERRO.txt
		 * 0075140C162620190517064549.txt-20190517
		 * 0075140D20180317054501.txt-20180317
		 * ERRO_0075140C15702019043001203220190430211737.txt
		 */
		
		//validando comeco do arquivo
		if (!(nomeArquivo.startsWith(Constantes.INICIO_NOME_ARQUIVO) || nomeArquivo.startsWith(Constantes.INICIO_NOME_ARQUIVO_ERRRO))) {
			return false;
		}
		
		if (!nomeArquivo.contains(".txt")) {
			return false;
		}

		//checando se o credenciador eh 5140 ou 5170
		String credenciador = ArquivoUtils.getNomeArquivoCredenciador(nomeArquivo);
		if (Constantes.credenciador.get(credenciador) == null) {
			return false;
		}
		
		//checando se tipo arquivo eh Credito ou Debito
		if (!"CD".contains(ArquivoUtils.getNomeArquivoCD(nomeArquivo))) {
			return false;
		}
		return true;
	}
}