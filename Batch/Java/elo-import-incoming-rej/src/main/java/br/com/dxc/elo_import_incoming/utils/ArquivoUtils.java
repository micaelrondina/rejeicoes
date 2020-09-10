package br.com.dxc.elo_import_incoming.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import br.com.dxc.elo_import_incoming.main.App;
import br.com.dxc.elo_import_incoming.sort.SortFileByDataNome;

public class ArquivoUtils {
	
	private ArquivoUtils() {
		throw new IllegalStateException("Utility class - Can not to be instantiated");
	}
	//Ex nome arquivo: 0075140C162620190517064549.txt-20190517
	
	/** Retorna o credenciador do arquivo (5140 ou 5170) */
	public static String getNomeArquivoCredenciador(String nomeArquivo) {
		if (nomeArquivo.startsWith(Constantes.INICIO_NOME_ARQUIVO)) {
			return nomeArquivo.substring(3, 7);	
		}
		if (nomeArquivo.startsWith(Constantes.INICIO_NOME_ARQUIVO_ERRRO)) {
			return nomeArquivo.substring(8,12);
		}
		return null;
	}
	
	/** Retorna se o arquivo eh C(Credito) ou D(Debito) */
	public static String getNomeArquivoCD(String nomeArquivo) {
		if (nomeArquivo.startsWith(Constantes.INICIO_NOME_ARQUIVO)) {
			return nomeArquivo.substring(7,8);	
		}
		if (nomeArquivo.startsWith(Constantes.INICIO_NOME_ARQUIVO_ERRRO)) {
			return nomeArquivo.substring(12,13);
		}
		return null;
	}

	/** Retorna o Tipo do Registro da linha */
	public static String getTpRegistroLinha(String linha) {
		return linha.substring(0,2);
	}
	
	/** Retorna o SubCodigo da Transacao (Padrao) */
	public static String getSubCdTransacao(String linha) {
		return linha.substring(2, 4);
	}
	
	public static void moveFileToProcessado(String fileSourcePath) throws IOException {
		moveFileTo(fileSourcePath, "processado");
	}
	
	public static void moveFileToIgnorado(String fileSourcePath) throws IOException {
		moveFileTo(fileSourcePath, "ignorado");
	}
	
	private static void moveFileTo(String fileSourcePath, String diretorioDestino) throws IOException {
		Path pathSource = Paths.get(fileSourcePath);
		Path pathDest = Paths.get(pathSource.getParent().toString(), diretorioDestino, pathSource.getFileName().toString());

		try {
			Files.createDirectories(pathDest.getParent()); //criando diretorio (caso nao exista)
			Files.move(pathSource, pathDest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Erro ao mover arquivo \"" + pathSource.getFileName().toString() + "\" para diretorio " + diretorioDestino, e);
		}
	}
	
	/** Verifica que a Linha passada tem os caracteres "00" na posicao 3 e 4 */
	public static boolean checkIsNewRegistro(String linha) {
		return (linha.substring(2, 4).equals("00"));		
	}
	
	public static void criarDiretoriosCasoNaoExista(String diretorio) throws IOException {
		Path pathDiretorio = Paths.get(diretorio);
		try {
			Files.createDirectories(pathDiretorio);
		} catch (IOException e) {
			throw new IOException("Erro ao criar diretorio \"" + pathDiretorio.toString() + "\"", e);
		}
	}
	
	public static void criarSubDiretorioProcessado(String diretorioPai) throws IOException {
		criarSubDiretorio(diretorioPai, "processado");
	}
	
	public static void criarSubDiretorioIgnorado(String diretorioPai) throws IOException {
		criarSubDiretorio(diretorioPai, "ignorado");
	}
	
	private static void criarSubDiretorio(String diretorioPai, String diretorioFilho) throws IOException {
		Path pathProcessado = Paths.get(diretorioPai, diretorioFilho);
		try {
			Files.createDirectories(pathProcessado);
		} catch (IOException e) {
			throw new IOException("Erro ao criar diretorio \"" + pathProcessado.toAbsolutePath() + "\"", e);
		}
	}
	
	public static File[] listarArquivosIncomingELODiretorioEntrada() {
		File dir = new File(App.getDiretorioEntrada()); //[fortify] Aplicacao roda em intranet - ambiente controlado

		// create fileNameFilter
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.startsWith(Constantes.INICIO_NOME_ARQUIVO) || name.startsWith(Constantes.INICIO_NOME_ARQUIVO_ERRRO)) //comeca com "007" ou "ERRO_007" - 007=ELO
						&& name.contains(".txt")
						&& "CD".contains(ArquivoUtils.getNomeArquivoCD(name)) //tem o tipo C ou D
						&& (Constantes.credenciador.get(ArquivoUtils.getNomeArquivoCredenciador(name)) != null); //eh do credenciador 5140 ou 5170
			}
		};

		// search
		return dir.listFiles(filter);
	}
	
	public static File[] listarArquivosRetornoPayware() {
		File dir = new File(App.getDiretorioRetornoArqPayware()); //[fortify] Aplicacao roda em intranet - ambiente controlado
		
		// create fileNameFilter
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return ( name.startsWith(Constantes.NOME_ARQUIVO_RETORNO_PAYWARE) && (name.contains(".txt") || name.contains(".TXT")) && (name.contains(".OUT")) );
			}
		};
		
		return dir.listFiles(filter);
	}
	
	public static void ordernarArquivosIncomingELOByData(File[] listArquivos) {
		Arrays.sort(listArquivos, new SortFileByDataNome());
	}
}
