package br.com.dxc.elo_import_incoming.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class Constantes {
	private Constantes() {
		throw new IllegalStateException("Utility class - Can not to be instantiated");
	}
	
	public static final Map<String, String> credenciador = new HashMap<>();
	static {
	    credenciador.put("5140", "GLOBAL PAYMENTS");
	    credenciador.put("5170", "BS2 ADIQ");
	    credenciador.put("5110", "ACQIO");
	}
	
	//usado na classe GenericDAO
	public static final String PARAM_ACQUIRER_PROCESSO_ID_ACQUIRER = "2";
	public static final String PARAM_ACQUIRER_PROCESSO_NM_PROCESSO = "DXC_INCOMIN_ELO";

	//usado em Class Utils
	public static final String SDF_AAAAMMDD = "yyyyMMdd";
	public static final String SDF_HHMMSS = "HHmmss";

	//usados na Class ValidacaoUtils
	public static final String INICIO_NOME_ARQUIVO = "007";
	public static final String INICIO_NOME_ARQUIVO_ERRRO = "ERRO_007";
	
	//usado na Class GerarArquivoTransEnvioPayware e na Class ImportarArquivoTransRetornoPayware
	public static final String NOME_ARQUIVO_GERADO_ENVIO_PAYWARE = "TR_REJ_";
	public static final String NOME_ARQUIVO_RETORNO_PAYWARE = NOME_ARQUIVO_GERADO_ENVIO_PAYWARE;
	public static final String DETAIL_RECORD_RECORD_TYPE_VALUE_DEFAULT = "00";
	public static final String HEADER_RECORD_TYPE = "HE";
	public static final String TRAILER_RECORD_TYPE = "TR";
	//Arquivo DXC --> Payware
	public static final int TAM_LINHA_HEADER_ARQ_DXC_PAYWARE = 25;
	public static final int TAM_LINHA_DETAIL_ARQ_DXC_PAYWARE = 32;
	public static final int TAM_LINHA_TRAILER_ARQ_DXC_PAYWARE = 34;
	//Arquivo Payware --> DXC
	public static final int TAM_LINHA_DETAIL_ARQ_PAYWARE_DXC = 86;
	
	public static final String NOME_USUARIO_BATCH = "BATCH";
	
	//"separador" para o log
	public static final String STR_LINHA_SEPARADOR = "****************************************************************************************************************************";
}