package br.com.dxc.elo_import_incoming.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.enums.ModoExecucao;
import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.model.ConfigEnvioEmail;
import br.com.dxc.elo_import_incoming.utils.CreateOracleDataSource;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class App {
	private static final Logger LOG = LogManager.getLogger(App.class);
	private static final String BATCH_INFO = Utils.getInfo();// return name#version
	private static final String BATCH_NAME = BATCH_INFO.split("#")[0];
	private static final String BATCH_VERSION = BATCH_INFO.split("#")[1];
	private static String path; //caminho do arquivo properties
	private static ModoExecucao modoExecucao; //modo de execução do batch
	private static Date dataBatch = new Date();
	private static String diretorioEntrada;
	private static String diretorioSaidaArqPayware;
	private static String diretorioRetornoArqPayware;
	
	public static void main(String[] args) {
		Utils.printBannerDXC();
		LOG.info("Project: " + BATCH_NAME);
		LOG.info("Version: " + BATCH_VERSION);
		
		//Validando Parametros
		//1. OBRIGATORIO - Path Arquivo Properties
		//2. OBRIGATORIO - Modo Execução
		if (args.length != 2) {
			LOG.fatal("Check your parameters! "
					+ "\n\t 1 Parametro (obrigatorio): Arquivo Properties."
					+ "\n\t 2 Parametro (obrigatorio): Modo Execução. (" + ModoExecucao.getModosExecucao() + ")");
			LOG.info("Exiting batch!");
			System.exit(-1);
		}

		//Return Code
		int rc = 0;

		try {
			//Lendo Parametros
			LOG.info("Parameters:");
			path = args[0];
			modoExecucao = ModoExecucao.getByCodigo(args[1]);
			LOG.info("Properties Path = " + getPath());
			LOG.info("Modo de Execucao = " + getModoExecucao().getModoExecucao());
			LOG.info("DataBatch: " + getDataBatch().toString() + " (" + Utils.getDataString() + ")");
			
			//lendo o arquivo properties
			lerArqProperties();

			DataSource dataSource = null;
			LOG.info("Creating the datasource from path: " + path);
		    dataSource = new CreateOracleDataSource().createPropertiesDataSource(path);
			if (dataSource == null) {
				LOG.fatal("Could not acquire the dataSource");
				LOG.fatal("Batch finalizado!");
				System.exit(-100);
			}
			Utils.setConexao(dataSource.getConnection());
			LOG.info("Datasource created");

			if (getModoExecucao() == ModoExecucao.IMPORTACAO_INCOMING) {
				new ImportarArquivosIncomingElo().importarArquivos();
			}
			else if (getModoExecucao() == ModoExecucao.GERAR_ARQUIVO_PAYWARE) {
				new GerarArquivoTransEnvioPayware().gerarArquivo();
				
			} else if (getModoExecucao() == ModoExecucao.IMPORTAR_ARQUIVO_RET_PAYWARE) {
				new ImportarArquivoTransRetornoPayware().importarArquivo();
				
			} else {
				throw new InvalidValueException("Modo de Execucao Invalido! Favor verificar" );
			}
			
			LOG.info(BATCH_NAME + " Concluido com Sucesso");
			
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			rc = -1;
			Utils.fechaConexao(Utils.getConexao());
		}

		LOG.info("Exiting. Return Code: " + rc);
		Utils.fechaConexao(Utils.getConexao());
		System.exit(rc);
	}
	
	private static void lerArqProperties() throws InvalidValueException {
		try (FileInputStream fileInputStream = new FileInputStream(App.getPath())) {  //[fortify] Aplicacao roda em intranet - ambiente controlado

			Properties prop = new Properties();
			prop.load(fileInputStream);
			diretorioEntrada = prop.getProperty("diretorio.entrada");
			if (Utils.isEmpty(diretorioEntrada)) {
				throw new InvalidValueException("Arquivo Properties: \"Diretorio de Entrada dos Arquivos\" nao informado! Favor informar!" );
			}
			
			diretorioSaidaArqPayware = prop.getProperty("diretorio.saida.arq.payware");
			if (Utils.isEmpty(diretorioSaidaArqPayware)) {
				throw new InvalidValueException("Arquivo Properties: \"Diretorio Saida do Arquivo com as Transacoes de Reenvio para o Payware\" nao informado! Favor informar!" );
			}
			
			diretorioRetornoArqPayware = prop.getProperty("diretorio.retorno.arq.payware");
			if (Utils.isEmpty(diretorioRetornoArqPayware)) {
				throw new InvalidValueException("Arquivo Properties: \"Diretorio de Retorno do Arquivo Enviado pelo Payware com o Status das Transacoes \" nao informado! Favor informar!" );
			}
			
			//Configuracoes do envio de Email
			String enviarEmail = prop.getProperty("mail.sender"); 
			if (!Utils.isEmpty(enviarEmail) && enviarEmail.equalsIgnoreCase("true")) {
				ConfigEnvioEmail.setEnviar(true);
				lerConfigEnvioEmail(prop);
			} else {
				ConfigEnvioEmail.setEnviar(false);
				LOG.info("[email] Parameters Envio E-mail:");
				LOG.info("Enviar = " + ConfigEnvioEmail.isEnviar());
			}
			
		} catch (IOException e) {
			LOG.fatal("Erro ao obter as propriedades! (Erro ao ler arquivo .properties)");
			LOG.fatal("Batch finalizado!");
			System.exit(-100);
		}
	}
	
	private static void lerConfigEnvioEmail(Properties prop) throws InvalidValueException {
		//host
		String configAux = prop.getProperty("mail.host");
		if (Utils.isEmpty(configAux)) {
			throw new InvalidValueException("Arquivo Properties: \"mail.host\" nao informado! Favor informar!" );
		}
		ConfigEnvioEmail.setHost(configAux);
		
		//port
		configAux = prop.getProperty("mail.port");
		if (Utils.isEmpty(configAux)) {
			throw new InvalidValueException("Arquivo Properties: \"mail.port\" nao informado! Favor informar!" );
		}
		ConfigEnvioEmail.setPort(configAux);
		
		//from
		configAux = prop.getProperty("mail.from");
		if (Utils.isEmpty(configAux)) {
			throw new InvalidValueException("Arquivo Properties: \"mail.from\" nao informado! Favor informar!" );
		}
		ConfigEnvioEmail.setFrom(configAux);
		
		//to
		configAux = prop.getProperty("mail.to");
		if (Utils.isEmpty(configAux)) {
			throw new InvalidValueException("Arquivo Properties: \"mail.to\" nao informado! Favor informar!" );
		}
		ConfigEnvioEmail.setTo(configAux);

		LOG.info("[email] Parameters Envio E-mail:");
		LOG.info("Enviar  = " + ConfigEnvioEmail.isEnviar());
		LOG.info("Host    = " + ConfigEnvioEmail.getHost());
		LOG.info("Port    = " + ConfigEnvioEmail.getPort());
		LOG.info("From/De = " + ConfigEnvioEmail.getFrom());
		LOG.info("To/Para = " + ConfigEnvioEmail.getTo());
	}

	public static String getPath() {
		return path;
	}

	public static Date getDataBatch() {
		return dataBatch;
	}

	public static String getDiretorioEntrada() {
		return diretorioEntrada;
	}

	public static ModoExecucao getModoExecucao() {
		return modoExecucao;
	}

	public static String getDiretorioSaidaArqPayware() {
		return diretorioSaidaArqPayware;
	}

	public static String getDiretorioRetornoArqPayware() {
		return diretorioRetornoArqPayware;
	}
}
