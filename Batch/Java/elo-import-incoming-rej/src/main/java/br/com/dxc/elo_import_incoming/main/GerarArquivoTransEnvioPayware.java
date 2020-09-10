package br.com.dxc.elo_import_incoming.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.dao.DxcIncomingEloItemDAO;
import br.com.dxc.elo_import_incoming.dao.GerarArquivoTransEnvioPaywareDAO;
import br.com.dxc.elo_import_incoming.enums.ModoExecucao;
import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.model.ArquivoTransEnvioPayware;
import br.com.dxc.elo_import_incoming.utils.ArquivoUtils;
import br.com.dxc.elo_import_incoming.utils.Constantes;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class GerarArquivoTransEnvioPayware {
	private static final Logger LOG = LogManager.getLogger(GerarArquivoTransEnvioPayware.class);

	public void gerarArquivo() throws IOException, SQLException, InvalidValueException {
		LOG.info("Iniciando Modo: " + ModoExecucao.GERAR_ARQUIVO_PAYWARE.getModoExecucao());
		
		//criando o diretorio de saida para o arquivo (caso nao exista)
		ArquivoUtils.criarDiretoriosCasoNaoExista(App.getDiretorioSaidaArqPayware());
		
		//obtendo dados para gerar o arquivo
		List<ArquivoTransEnvioPayware> retorno = new GerarArquivoTransEnvioPaywareDAO().getDadosArquivo();

		if (retorno == null || retorno.isEmpty()) {
			LOG.info("NAO ha dados para gerar os arquivos de Transacoes Rejeitadas para envio ao Payware!");
		} else {
			LOG.info("Iniciando Montagem do arquivos...");
			ArquivoTransEnvioPayware arq = new ArquivoTransEnvioPayware();
			List<Long> listaIDsItemNoArquivo = new ArrayList<>();
			StringBuilder sb = new StringBuilder();	
			
			//setando HEADER
			sb.append(arq.montarHeader());
			sb.append(System.lineSeparator());
			
			for (ArquivoTransEnvioPayware item : retorno) {
				listaIDsItemNoArquivo.add(item.getIdItem()); //add na list. Essa list eh usada depois para setar a DH_ENVIO_ARQ_PAYWARE na tabela DXC_INCOMING_ELO_ITEM
				
				//montando a linha do arquivo
				sb.append(System.lineSeparator()); //quebra de linha
				sb.append(item.getRecordType());//Record Type. Value "00"
				sb.append(item.getArn());		//Acquirer Reference Number		
				sb.append(item.getCuotasVan());	//Installment Number
				sb.append(item.getUsageCode());	//Usage Code
			}
			
			//setando TRAILER
			sb.append(System.lineSeparator());
			sb.append(arq.montarTrailer(retorno.size()));

			//montando e salvando o arquivo
			String nomeArqDataHora = Utils.getDataString(App.getDataBatch()) + "_" + Utils.getTimeString(App.getDataBatch());
			Path arquivo = Paths.get(App.getDiretorioSaidaArqPayware(), Constantes.NOME_ARQUIVO_GERADO_ENVIO_PAYWARE + nomeArqDataHora +  ".TXT");//[fortify] Aplicacao roda em intranet - ambiente controlado
			try (
				FileOutputStream fileOutputStream = new FileOutputStream(arquivo.toString());//[fortify] Aplicacao roda em intranet - ambiente controlado
				OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
				) {
				//escrevendo o arquivo e salvando de fato no disco
				LOG.info("Salvando em disco arquivo \"" + arquivo.getFileName() + "\" com " + (2 + retorno.size()) + " linhas (incluindo Header e Trailer)."); //se 2 eh por causa do Header e Trailer
				writer.write(sb.toString().replaceFirst(System.lineSeparator(), ""));
			}

			//gerou o arquivo
			//marcando as transacoes presentes no arquivo como enviadas ao payware
			new DxcIncomingEloItemDAO().setarDhEnvioArqPayware(listaIDsItemNoArquivo.toString());
		}
	}
}
