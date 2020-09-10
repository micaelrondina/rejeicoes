package br.com.dxc.elo_import_incoming.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.dao.DxcIncomingEloDAO;
import br.com.dxc.elo_import_incoming.dao.DxcIncomingEloItemDAO;
import br.com.dxc.elo_import_incoming.dao.DxcIncomingEloItemTe1020DAO;
import br.com.dxc.elo_import_incoming.dao.DxcIncomingEloItemTe40DAO;
import br.com.dxc.elo_import_incoming.enums.ModoExecucao;
import br.com.dxc.elo_import_incoming.enums.SubCdTransacaoEnum;
import br.com.dxc.elo_import_incoming.enums.TpRegistroTransacaoEnum;
import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.layout.RegistroHeader;
import br.com.dxc.elo_import_incoming.layout.RegistroTrailer;
import br.com.dxc.elo_import_incoming.layout.RegistroTransacaoTe44;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro00;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro01;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro02;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro03;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro05;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro07;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro08;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro09;
import br.com.dxc.elo_import_incoming.model.DxcIncomingElo;
import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItem;
import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItemTe1020;
import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItemTe40;
import br.com.dxc.elo_import_incoming.utils.ArquivoUtils;
import br.com.dxc.elo_import_incoming.utils.Constantes;
import br.com.dxc.elo_import_incoming.utils.Utils;
import br.com.dxc.elo_import_incoming.utils.ValidacaoUtils;

public class ImportarArquivosIncomingElo {
	private static final Logger LOG = LogManager.getLogger(ImportarArquivosIncomingElo.class);
	
	//DAOs
	DxcIncomingEloDAO dxcIncomingEloDAO = new DxcIncomingEloDAO();
	
	//models
	DxcIncomingElo dxcIncomingEloMaster; //master (1 registro por arquivo)
	
	//listas dos registros tipo ITEM (sao preenchidas no metodo lerArquivo() e usadas depois no metodo persistirDadosBanco())
	List<DxcIncomingEloItem> listDxcIncomingEloItem;
	List<DxcIncomingEloItemTe1020> listDxcIncomingEloItemTe1020;
	List<DxcIncomingEloItemTe40> listDxcIncomingEloItemTe40;
	
	//Contadores
	int countArqImportadosSucesso = 0;
	int countArqIgnorados = 0;
	
	//Set (Lista) das Transacoes Rejeitadas novamente
	//na funcao "validarSeTransacaoEhRejeitadoNovamente" populo esse Map 
	//na funcao "persistirDadosBanco" percorre esse Map e salvo no banco de fato
	//Isso pq, preciso salvar o ID_INCOMING_ELO_REJEICAO (ou seja, preciso no ID_INCOMING Master que sou vou ter na funcao "persistirDadosBanco"
	Set<Long> setTransacoesRejeitadasNovamente = null; 

	public void importarArquivos() throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InvalidValueException, SQLException, MessagingException {
		LOG.info("Iniciando Modo: " + ModoExecucao.IMPORTACAO_INCOMING.getModoExecucao());
		//zerando contadores
		countArqImportadosSucesso = 0;
		countArqIgnorados = 0;
		
		//ver arquivos no diretorio
		LOG.info("Procurando arquivos no diretorio: " + App.getDiretorioEntrada());
		File[] files = ArquivoUtils.listarArquivosIncomingELODiretorioEntrada();
		
		if (files == null || files.length == 0) {
			LOG.info("Nenhum arquivo encontrado no diretorio para importar!");	
		} else {
			LOG.info("Quantidade Arquivos a serem importados: " + files.length);
			LOG.info("Arquivos a serem importados: " + Arrays.toString(files) + "\n\n");
			
			//ordenando
			LOG.info("Ordenando Arquivos: " + files.length);
			ArquivoUtils.ordernarArquivosIncomingELOByData(files);
			
			//percorrendo arquivos encontrados no diretorio
			for (File file : files) {
				System.out.println(Constantes.STR_LINHA_SEPARADOR); //[fortify] println vazio. Apenas para colocar linha em branco no log
				
				LOG.info("Iniciando Importacao do Arquivo: " + file.getName());
				
				//validando o nome do arquivo
				if (!ValidacaoUtils.validarNomeArquivo(file.getName())) {
					LOG.warn("Nome do arquivo invalido! Arquivo NAO IMPORTADO");
				} else {
					
					//verificando se o arquivo eh arquivo de erro
					if (file.getName().contains("ERRO")) {
						countArqIgnorados++;
						LOG.info("Arquivo \"" + file.getName() + "\" NAO IMPORTADO. Arquivo com ERRO! (movido para diretorio ignorado)!\n");
												
						//movendo arquivo importado para /ignorado
						ArquivoUtils.moveFileToIgnorado(file.getAbsolutePath());
						
						Utils.enviarEmail(file, null);
					} else {
						
						//verificando se o arquivo ja foi importado
						if (dxcIncomingEloDAO.arquivoJaImportado(file.getName())) {
							countArqIgnorados++;
							LOG.info("Arquivo \"" + file.getName() + "\" NAO IMPORTADO. Arquivo JA FOI IMPORTADO (movido para diretorio de processado)!\n");
						} else {
							//arquivo nao contem erro; e nao foi importado. Entao: IMPORTAR
							
							//lendo o arquivo
							if (lerArquivo(file)) {
								//se conseguiu ler com sucesso o arquivo
								
								//salva os dados do arquivo de fato no banco
								persistirDadosBanco();
								
								//importando de fato o arquivo
								countArqImportadosSucesso++;
								
								//movendo arquivo importado para /processado
								ArquivoUtils.moveFileToProcessado(file.getAbsolutePath());
								
								logFimSucesso(file.getName());
							} else {
								//erro ao ler o arquivo ou arquivo ignorado
								countArqIgnorados++;
								
								//movendo arquivo importado para /ignorado
								ArquivoUtils.moveFileToIgnorado(file.getAbsolutePath());
								
								logFimIgnorado(file.getName());
							}
						}
					}
				}
			}
			LOG.info("Resumo Arquivos:");
			LOG.info(countArqIgnorados + " Arquivo(s) Ignorado(s) (nao importados)...");
			LOG.info(countArqImportadosSucesso + " Arquivo(s) Importado(s) com Sucesso...\n");
		}
	}
	
	private boolean lerArquivo(File file) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InvalidValueException, SQLException, MessagingException {
		boolean sucesso = false;
		try (
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1);
				BufferedReader reader = new BufferedReader(inputStreamReader)
			) {
			
			//models
			dxcIncomingEloMaster = new DxcIncomingElo(file.getName()); //master (1 registro por arquivo)
			DxcIncomingEloItem dxcIncomingEloItem = null;
			DxcIncomingEloItemTe1020 dxcIncomingEloItemTe1020 = null;
			DxcIncomingEloItemTe40 dxcIncomingEloItemTe40 = null;
			
			//criando listar
			listDxcIncomingEloItem = new ArrayList<>();
			listDxcIncomingEloItemTe1020 = new ArrayList<>();
			listDxcIncomingEloItemTe40 = new ArrayList<>();
			setTransacoesRejeitadasNovamente = new LinkedHashSet<>();
			
			//contadores
			int countHeader = 0;
			int countTrailer = 0;
			int countTE44 = 0;
			
			String linha;
			TpRegistroTransacaoEnum tpRegistro;
			
			//lendo linhas do arquivo
			while ((linha = reader.readLine()) != null) { //[fortify] Aplicacao roda em intranet - ambiente controlado
				 //obtendo o Tipo do Registro da Transacao
				 try {
					 tpRegistro = TpRegistroTransacaoEnum.getBySigla(ArquivoUtils.getTpRegistroLinha(linha));	 
				 } catch (InvalidValueException e) {
					 LOG.error("Erro no TpRegistroTransacaoEnum.getBySigla: " + e);
					 sucesso = false;
					 Utils.enviarEmail(file, "Arquivo <strong>\"" + file.getName() + "\"</strong> NAO IMPORTADO! Estrutura Invalida (movido para diretorio ignorado)!"
								+ "<br>" + e);
					 return sucesso;
				 }
				 
				 //verifica se estou iniciando um registro novo, ou se estou add mais informacoes a um registro ja existente
				 boolean isNewRegistro = ArquivoUtils.checkIsNewRegistro(linha); 
				 if (isNewRegistro) {
					 //criando objetos ITEM
					 dxcIncomingEloItem = new DxcIncomingEloItem();
					 dxcIncomingEloItemTe1020 = new DxcIncomingEloItemTe1020();
					 dxcIncomingEloItemTe40 = new DxcIncomingEloItemTe40(); 
				 }

				 switch (tpRegistro) {
					 case HEADER:
						 new RegistroHeader(linha, dxcIncomingEloMaster).preencherDxcIncomingElo();
						 countHeader++;
						 break;

					 case TRANSACAO_PADRAO:
						 importarLinhaTEPadrao(dxcIncomingEloItem, linha); //preenchendo o objeto
						 validarSeTransacaoEhRejeitadoNovamente(dxcIncomingEloMaster, dxcIncomingEloItem);
						 if (isNewRegistro) {							 
							 listDxcIncomingEloItem.add(dxcIncomingEloItem); //add objeto na lista
						 }
						 break;

					 case TRANSACAO_TE40:
						 importarLinhaTE40(dxcIncomingEloItemTe40, linha); //preenchendo o objeto
						 if (isNewRegistro) {
							 listDxcIncomingEloItemTe40.add(dxcIncomingEloItemTe40); //add objeto na lista
						 }
						 break;

					 case TRANSACAO_TE10_TE20:
						 importarLinhaTE10TE20(dxcIncomingEloItemTe1020, linha); //preenchendo o objeto
						 if (isNewRegistro) {
							 listDxcIncomingEloItemTe1020.add(dxcIncomingEloItemTe1020); //add objeto na lista
						 }
						 break;

					 case TRANSACAO_TE44:
						 new RegistroTransacaoTe44(linha, dxcIncomingEloMaster).preencherDxcIncomingElo();
						 countTE44++;
						 break;

					 case TRAILER:
						 new RegistroTrailer(linha, dxcIncomingEloMaster).preencherDxcIncomingElo();
						 countTrailer++;
						 break;

					 default:
						 throw new InvalidValueException("Tipo do Registro Invalido!");
				 }
			}
			
			//validando estrutura do arquivo (1 Header, 1 TE44 e 1 Trailer)
			if (countHeader != 1 || countTE44 != 1 || countTrailer != 1) {
				sucesso = false;
				Utils.enviarEmail(file, "Arquivo <strong>\"" + file.getName() + "\"</strong> NAO IMPORTADO! Estrutura Invalida (movido para diretorio ignorado)!"
						+ "<br>Arquivo deve conter 1 HEADER, 1 TE44 e 1 TRAILER!"
						+ "<br>Arquivo contem: " + countHeader + " HEADER, " + countTE44 + " TE44, " + countTrailer + " TRAILER");
			} else {
				sucesso = true;	
			}
			return sucesso;
			
		} catch (IOException e) {
			LOG.error("Erro ao ler arquivo: " + file.getName(), e);
		}
		return false;
	}
	
	private void persistirDadosBanco() throws SQLException, InvalidValueException {
		try {
			Utils.getConexao().setAutoCommit(false);
			
			DxcIncomingEloItemDAO dxcIncomingEloItemDAO = new DxcIncomingEloItemDAO();
			DxcIncomingEloItemTe1020DAO dxcIncomingEloItemTe1020DAO = new DxcIncomingEloItemTe1020DAO();
			DxcIncomingEloItemTe40DAO dxcIncomingEloItemTe40DAO = new DxcIncomingEloItemTe40DAO();
			
			//salvando dados - MASTER
			Long idIncomingElo = dxcIncomingEloDAO.saveRegistroMaster(dxcIncomingEloMaster);
			dxcIncomingEloMaster.setIdIncomingElo(idIncomingElo);
			
			//salvando dados DXC_INCOMING_ELO_ITEM
			if (!listDxcIncomingEloItem.isEmpty()) {
				for (DxcIncomingEloItem dxcIncomingEloItem : listDxcIncomingEloItem) {
					dxcIncomingEloItem.setIdIncomingElo(idIncomingElo);
					dxcIncomingEloItemDAO.saveDxcIncomingEloItem(dxcIncomingEloItem);
				}
			}
			listDxcIncomingEloItem = null;
			
			//salvando dados DXC_INCOMING_ELO_ITEM_TE40
			if (!listDxcIncomingEloItemTe40.isEmpty()) {
				for (DxcIncomingEloItemTe40 dxcIncomingEloItemTe40 : listDxcIncomingEloItemTe40) {
					dxcIncomingEloItemTe40.setIdIncomingElo(idIncomingElo);
					dxcIncomingEloItemTe40DAO.saveDxcIncomingEloItemTe40(dxcIncomingEloItemTe40);
				}
			}
			listDxcIncomingEloItemTe40 = null;
			
			//salvando dados DXC_INCOMING_ELO_ITEM_TE10_20
			if (!listDxcIncomingEloItemTe1020.isEmpty()) {
				for (DxcIncomingEloItemTe1020 dxcIncomingEloItemTe1020 : listDxcIncomingEloItemTe1020) {
					dxcIncomingEloItemTe1020.setIdIncomingElo(idIncomingElo);
					dxcIncomingEloItemTe1020DAO.saveDxcIncomingEloItemTe1020(dxcIncomingEloItemTe1020);
				}
			}
			listDxcIncomingEloItemTe1020 = null;
			
			//Salvando Dados de Transacoes Rejeitadas Novamente
			if (!setTransacoesRejeitadasNovamente.isEmpty()) {
				DxcIncomingEloItemDAO dao = new DxcIncomingEloItemDAO();
				
				for (Long idItemRejeitadoNovamente : setTransacoesRejeitadasNovamente) {
					LOG.info("PERSISTINDO Transacao Rejeitada Novamente (Marcada como Rejeitada)! ID_DXC_INCOMING_ELO_ITEM: " + idItemRejeitadoNovamente);
					dao.atualizarTransacaoRejeitadaNovamente(dxcIncomingEloMaster.getDtRetornoArq(), dxcIncomingEloMaster.getIdIncomingElo(), idItemRejeitadoNovamente);
				}
			}
			setTransacoesRejeitadasNovamente = null;
			
			Utils.getConexao().commit();
		} catch (SQLException | InvalidValueException e) {
			Utils.getConexao().rollback();
			throw e;
		}
	}
	
	private void importarLinhaTE10TE20(DxcIncomingEloItemTe1020 dxcIncomingEloItemTe1020, String linha) throws InvalidValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//verifica o Sub Codigo da Transacao TE40
		SubCdTransacaoEnum subCdTransacaoPadraEnum = SubCdTransacaoEnum.getByCodigo(ArquivoUtils.getSubCdTransacao(linha));
		
		//transacao TE10 e TE20 tem apenas Registro00 e Registro02
		switch (subCdTransacaoPadraEnum) {
		case REGISTRO_00: new br.com.dxc.elo_import_incoming.layout.te10_20.Registro00(linha, dxcIncomingEloItemTe1020).preencherDxcIncomingElo();
			break;
		case REGISTRO_02: new br.com.dxc.elo_import_incoming.layout.te10_20.Registro00(linha, dxcIncomingEloItemTe1020).preencherDxcIncomingElo();
			break;
		default:
			throw new InvalidValueException("SubCodigo da Transacao TE10 e TE20 Invalido!");
		}
	}
	
	private void importarLinhaTE40(DxcIncomingEloItemTe40 dxcIncomingEloItemTe40, String linha) throws InvalidValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//verifica o Sub Codigo da Transacao TE40
		SubCdTransacaoEnum subCdTransacaoPadraEnum = SubCdTransacaoEnum.getByCodigo(ArquivoUtils.getSubCdTransacao(linha));
		
		//transacao TE40 tem apenas Registro00 e Registro02
		switch (subCdTransacaoPadraEnum) {
		case REGISTRO_00: new br.com.dxc.elo_import_incoming.layout.te40.Registro00(linha, dxcIncomingEloItemTe40).preencherDxcIncomingElo();
			break;
		case REGISTRO_02: new br.com.dxc.elo_import_incoming.layout.te40.Registro02(linha, dxcIncomingEloItemTe40).preencherDxcIncomingElo();
			break;
		default:
			throw new InvalidValueException("SubCodigo da Transacao TE40 Invalido!");
		}
	}
	
	private void importarLinhaTEPadrao(DxcIncomingEloItem dxcIncomingEloItem, String linha) throws InvalidValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//verifica o Sub Codigo da Transacao Padrao
		SubCdTransacaoEnum subCdTransacaoPadraEnum = SubCdTransacaoEnum.getByCodigo(ArquivoUtils.getSubCdTransacao(linha));

		switch (subCdTransacaoPadraEnum) {
			case REGISTRO_00: new Registro00(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_01: new Registro01(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_02: new Registro02(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_03: new Registro03(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_05: new Registro05(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_07: new Registro07(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_08: new Registro08(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			case REGISTRO_09: new Registro09(linha, dxcIncomingEloItem).preencherDxcIncomingElo();
				break;
			default:
				throw new InvalidValueException("SubCodigo da Transacao Padrao Invalido!");
		}
	}
	
	private void logFimSucesso(String nomeArquivo) {
		LOG.info("Arquivo " + nomeArquivo.toUpperCase() + " - Importado com SUCESSO! \n" + Constantes.STR_LINHA_SEPARADOR + "\n"); //[fortify] println vazio. Apenas para colocar linha em branco no log
	}
	
	private void logFimIgnorado(String nomeArquivo) {
		LOG.info("Arquivo " + nomeArquivo.toUpperCase() + " - Ignorado! \n" + Constantes.STR_LINHA_SEPARADOR + "\n"); //[fortify] println vazio. Apenas para colocar linha em branco no log
	}
	
	private void validarSeTransacaoEhRejeitadoNovamente(DxcIncomingElo dxcIncomingEloMaster, DxcIncomingEloItem dxcIncomingEloItem) throws SQLException {
		Long idItemRejeitadoNovamente = new DxcIncomingEloItemDAO().validarSeTransacaoEhRejeitadoNovamente(dxcIncomingEloMaster.getDtRetornoArq(), dxcIncomingEloItem.getNrRefTransacao(), dxcIncomingEloItem.getNrParcela());

		//como o commit eh feito apenas no final, verifica que esse idItemRejeitadoNovamente ja foi inserido na lista para nao inserir novamente
		if (idItemRejeitadoNovamente != null && !setTransacoesRejeitadasNovamente.contains(idItemRejeitadoNovamente)) { 
			//Rejeitado novamento
			LOG.info("Transacao Rejeitada Novamente (Marcada como Rejeitada)! ID_DXC_INCOMING_ELO_ITEM: " + idItemRejeitadoNovamente);
				
			//populando a lista de transacoes rejeitados novamente
			setTransacoesRejeitadasNovamente.add(idItemRejeitadoNovamente);

			//salvar no banco de fato essa transacao como reijetado, ocorre na funcao "persistirDadosBanco"	
		}
	}
}
