package br.com.dxc.elo_import_incoming.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.dao.DxcIncomingEloItemDAO;
import br.com.dxc.elo_import_incoming.enums.ModoExecucao;
import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.model.ArquivoTransRetornoPayware;
import br.com.dxc.elo_import_incoming.utils.ArquivoUtils;
import br.com.dxc.elo_import_incoming.utils.Constantes;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class ImportarArquivoTransRetornoPayware {
	private static final Logger LOG = LogManager.getLogger(ImportarArquivoTransRetornoPayware.class);

	public void importarArquivo() throws Exception {
		LOG.info("Iniciando Modo: " + ModoExecucao.IMPORTAR_ARQUIVO_RET_PAYWARE.getModoExecucao());
		
		//criando o subdiretorio PROCESSADO e IGNORADO
		ArquivoUtils.criarSubDiretorioProcessado(App.getDiretorioRetornoArqPayware());
		ArquivoUtils.criarSubDiretorioIgnorado(App.getDiretorioRetornoArqPayware());
		
		//ver arquivos no diretorio
		LOG.info("Procurando arquivos de Retorno do Payware no diretorio: " + App.getDiretorioRetornoArqPayware());
		File[] files = ArquivoUtils.listarArquivosRetornoPayware();
		
		if (files == null || files.length == 0) {
			LOG.info("Nenhum arquivo encontrado no diretorio para importar!");	
		} else {
			LOG.info("Quantidade Arquivos a serem importados: " + files.length);
			LOG.info("Arquivos a serem importados: " + Arrays.toString(files) + "\n");
			
			//percorrendo arquivos encontrados no diretorio
			for (File file : files) {
				System.out.println(Constantes.STR_LINHA_SEPARADOR);//[fortify] println vazio. Apenas para colocar linha em branco no log
				
				LOG.info("Iniciando Importacao do Arquivo: " + file.getName());
				
				try {
					lerArquivo(file);
					
					//movendo arquivo importado para /processado
					ArquivoUtils.moveFileToProcessado(file.getAbsolutePath());
				} catch (InvalidValueException | IOException | SQLException e1) {
					
					//movendo arquivo importado para /ignorado
					ArquivoUtils.moveFileToIgnorado(file.getAbsolutePath());
					
					LOG.error("Arquivo Movido para Ignorado!");
					throw e1; //[fortify] Aplicacao pequena, tratamento simplificado para excecao
				}
			}
		}
	}

	private void lerArquivo(File file) throws InvalidValueException, IOException, SQLException {
		try (
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1);
				BufferedReader reader = new BufferedReader(inputStreamReader)
			) {
			
			Utils.getConexao().setAutoCommit(false);

			//lendo linhas do arquivo
			String linha;
			while ((linha = reader.readLine()) != null) { //[fortify] Aplicacao roda em intranet - ambiente controlado
				//linha HEADER ou TRAILER, ignora
				if (linha.startsWith(Constantes.HEADER_RECORD_TYPE) || linha.startsWith(Constantes.TRAILER_RECORD_TYPE)) {
					continue;
				}
				ArquivoTransRetornoPayware arqRet = new ArquivoTransRetornoPayware(linha);
				
				DxcIncomingEloItemDAO dao = new DxcIncomingEloItemDAO();
				//procurando na tabela DXC_INCOMING_ELO_ITEM a transacao que esta sendo retornada no arquivo de retorno do Payware
				Long idItem = dao.getByNrRefTransacaoAndNrParcelaAndEnviadoPayware(arqRet.getArn(), arqRet.getCuotasVan());
				
				//se achou alguma transacao, estao marca o codigo de retorno dela
				if (idItem != null) {
					dao.setReturnCodeByArqRetPayware(idItem, arqRet.getReturnCode(), arqRet.getReturnCodeDescription());
				}
			}

			Utils.getConexao().commit();

		} catch (InvalidValueException | IOException | SQLException e) {
			LOG.error("Problema ao ler/processar arquivo de Retorno do Payware: " + file.getName());
			LOG.error("Rollback realizado");
			Utils.getConexao().rollback();
			throw e;
		}		
	}
}
