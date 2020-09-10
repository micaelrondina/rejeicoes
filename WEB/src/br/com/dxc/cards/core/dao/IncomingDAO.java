package br.com.dxc.cards.core.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.cards.core.model.DxcIncomingEloItem;
import br.com.dxc.cards.core.model.DxcIncomingEloItemTE1020;
import br.com.dxc.cards.core.model.DxcIncomingEloItemTE40;
import br.com.dxc.cards.core.model.DxcIncomingEloMaster;
import br.com.dxc.cards.core.model.IdIncoming;
import br.com.dxc.cards.core.model.Incoming;
import br.com.dxc.cards.core.model.IncomingCodRetPayware;
import br.com.dxc.cards.core.model.IncomingHistorico;
import br.com.dxc.cards.core.model.IncomingMaisInformacoes;
import br.com.dxc.cards.core.model.IncomingWrapper;
import br.com.dxc.cards.core.utils.ParametroCache;
import br.com.dxc.cards.core.utils.Utils;

public class IncomingDAO {
	private static final Logger LOG = LogManager.getLogger(IncomingDAO.class);

	public IncomingWrapper getListIncoming(String cdCredenciador, String produto, String cdTransacao, String dtPeriodoInicio, String dtPeriodoFim, String codErro) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		List<Incoming> listIncoming = new ArrayList<>();
		IncomingWrapper wrapper = new IncomingWrapper();

		try {
			con = Utils.getConnection();

			String dataInicio = Utils.getyyyyMMddFromIsoDate(dtPeriodoInicio);
			String dataFim = Utils.getyyyyMMddFromIsoDate(dtPeriodoFim);

			String query = "SELECT 'P' AS ID_TP_TRANS, I.ID_INCOMING_ELO, I.ID_INCOMING_ELO_ITEM,\r\n" + 
					"       I.NM_PONTO_VENDA, I.CIDADE_PONTO_VENDA, I.DT_MOV_APRESENT_DISPUTA, I.VL_VENDA_SAQUE_DISPUTA,\r\n" + 
					"       I.NR_PARCELA, I.QT_PARCELAS_TRANSACAO, I.QT_DIAS_LIQ_FINAN_TRANSACAO, \r\n" + 
					"       I.CD_AUTH_TRANSACAO, I.NR_REF_TRANSACAO,\r\n" + 
					"       (" + 
					"			SELECT COUNT(1) FROM PRODACQR.OUTGOING WHERE ACQUIRER_REF_NUM = I.NR_REF_TRANSACAO \r\n" + 
					"           	AND ( CUOTAS_VAN = TO_NUMBER(DECODE(I.NR_PARCELA, '000','001', NR_PARCELA))\r\n" + 
					"               	  OR ( CASE WHEN CUOTAS_VAN < 51 THEN CUOTAS_VAN\r\n" + 
					"                            ELSE MOD(CUOTAS_VAN,50)\r\n" + 
					"                          END\r\n" + 
					"                        ) = TO_NUMBER(DECODE(I.NR_PARCELA, '000','001', I.NR_PARCELA)) \r\n" + 
					"                	)" +
					"		) AS QTD_VEZES_TRANS_REJ,\r\n" + 
					"       I.CD_ERRO, I.DESCRICAO_ERRO, M.NR_REMESSA_B0,\r\n" + 
					"       I.LOGIN_USU_LIBERACAO, I.DT_LIBERACAO,\r\n" + 
					"       I.LOGIN_USU_ACATADO, I.DT_ACATADO,\r\n" + 
					"       I.LOGIN_USU_REJEICAO, I.DT_REJEICAO\r\n" + 
					"FROM DXC_INCOMING_ELO M\r\n" + 
					"    LEFT JOIN DXC_INCOMING_ELO_ITEM I ON (M.ID_INCOMING_ELO = I.ID_INCOMING_ELO)\r\n" + //Item Padrao
					"WHERE M.CD_CREDENCIADOR = '@CD_CREDENCIADOR'\r\n" + 
					"AND M.NM_ARQUIVO LIKE '%@CD_PRODUTO%'\r\n" + 
					"AND M.DT_RETORNO_ARQ BETWEEN '@DT_RETORNO_ARQ_INICIO' AND '@DT_RETORNO_ARQ_FIM' AND\r\n" + 
					"(\r\n" + 
					"    I.CD_TRANSACAO_00 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_01 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_02 = '@CD_TRANSACAO' OR \r\n" + 
					"    I.CD_TRANSACAO_03 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_05 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_07 = '@CD_TRANSACAO' OR \r\n" + 
					"    I.CD_TRANSACAO_08 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_09 = '@CD_TRANSACAO'\r\n" + 
					") \r\n" +
					"@FILTRO_COD_ERRO \r\n" +
					"UNION ALL\r\n" + 
					"\r\n" +
					
					//TE10 e TE20
					"SELECT '1020' AS ID_TP_TRANS, I.ID_INCOMING_ELO, I.ID_INCOMING_ELO_ITEM,\r\n" + 
					"       '' AS NM_PONTO_VENDA, '' AS CIDADE_PONTO_VENDA, '' AS DT_MOV_APRESENT_DISPUTA, 0.0 AS VL_VENDA_SAQUE_DISPUTA,\r\n" + 
					"       '' AS NR_PARCELA, '' AS QT_PARCELAS_TRANSACAO, I.QT_DIAS_LIQ_FINAN_TRANSACAO, \r\n" + 
					"       '' AS CD_AUTH_TRANSACAO, '' AS NR_REF_TRANSACAO,\r\n" + 
					"       0 AS QTD_VEZES_TRANS_REJ,\r\n" + 
					"       I.CD_ERRO, '' AS DESCRICAO_ERRO, M.NR_REMESSA_B0,\r\n" + 
					"       '' AS LOGIN_USU_LIBERACAO, NULL AS DT_LIBERACAO,\r\n" + 
					"       '' AS LOGIN_USU_ACATADO, NULL AS DT_ACATADO,\r\n" + 
					"       '' AS LOGIN_USU_REJEICAO, NULL AS DT_REJEICAO\r\n" + 
					"FROM DXC_INCOMING_ELO M\r\n" + 
					"    LEFT JOIN DXC_INCOMING_ELO_ITEM_TE10_20 I ON (M.ID_INCOMING_ELO = I.ID_INCOMING_ELO)\r\n" + 
					"WHERE M.CD_CREDENCIADOR = '@CD_CREDENCIADOR'\r\n" + 
					"AND M.NM_ARQUIVO LIKE '%@CD_PRODUTO%'\r\n" + 
					"AND M.DT_RETORNO_ARQ BETWEEN '@DT_RETORNO_ARQ_INICIO' AND '@DT_RETORNO_ARQ_FIM' AND\r\n" + 
					"( I.CD_TRANSACAO_00 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_02 = '@CD_TRANSACAO' )\r\n" +
					"@FILTRO_COD_ERRO \r\n" +
					"UNION ALL\r\n" + 
					"\r\n" + 
					
					//TE40
					"SELECT '40' AS ID_TP_TRANS, I.ID_INCOMING_ELO, I.ID_INCOMING_ELO_ITEM,\r\n" + 
					"       '' AS NM_PONTO_VENDA, '' AS CIDADE_PONTO_VENDA, '' AS DT_MOV_APRESENT_DISPUTA, 0.0 AS VL_VENDA_SAQUE_DISPUTA,\r\n" + 
					"       '' AS NR_PARCELA, '' AS QT_PARCELAS_TRANSACAO, '' AS QT_DIAS_LIQ_FINAN_TRANSACAO, \r\n" + 
					"       '' AS CD_AUTH_TRANSACAO, '' AS NR_REF_TRANSACAO,\r\n" + 
					"       0 AS QTD_VEZES_TRANS_REJ,\r\n" + 
					"       '' AS CD_ERRO, '' AS DESCRICAO_ERRO, M.NR_REMESSA_B0,\r\n" + 
					"       '' AS LOGIN_USU_LIBERACAO, NULL AS DT_LIBERACAO,\r\n" + 
					"       '' AS LOGIN_USU_ACATADO, NULL AS DT_ACATADO,\r\n" + 
					"       '' AS LOGIN_USU_REJEICAO, NULL AS DT_REJEICAO\r\n" + 
					"FROM DXC_INCOMING_ELO M\r\n" + 
					"    LEFT JOIN DXC_INCOMING_ELO_ITEM_TE40 I ON (M.ID_INCOMING_ELO = I.ID_INCOMING_ELO)    \r\n" + 
					"WHERE M.CD_CREDENCIADOR = '@CD_CREDENCIADOR'\r\n" + 
					"AND M.NM_ARQUIVO LIKE '%@CD_PRODUTO%'\r\n" + 
					"AND M.DT_RETORNO_ARQ BETWEEN '@DT_RETORNO_ARQ_INICIO' AND '@DT_RETORNO_ARQ_FIM' AND\r\n" + 
					"( I.CD_TRANSACAO_00 = '@CD_TRANSACAO' OR I.CD_TRANSACAO_02 = '@CD_TRANSACAO' )";

			//Colocando o Filtro por COD_ERRO
			query = query.replace("@FILTRO_COD_ERRO", (codErro == null || codErro.trim().equalsIgnoreCase("")) ? "" : ("AND I.CD_ERRO = '" + codErro + "' ")); //[fortify] Aplicacao roda em intranet - ambiente controlado

			//Add Filtros...
			query = query.replace("@CD_CREDENCIADOR", cdCredenciador); //[fortify] Aplicacao roda em intranet - ambiente controlado
			query = query.replace("@CD_PRODUTO", produto); //[fortify] Aplicacao roda em intranet - ambiente controlado
			query = query.replace("@DT_RETORNO_ARQ_INICIO", dataInicio); //[fortify] Aplicacao roda em intranet - ambiente controlado
			query = query.replace("@DT_RETORNO_ARQ_FIM", dataFim); //[fortify] Aplicacao roda em intranet - ambiente controlado
			query = query.replace("@CD_TRANSACAO", cdTransacao); //[fortify] Aplicacao roda em intranet - ambiente controlado

			ps = con.prepareStatement(query);
//			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			
			LOG.info("IncomingDAO.getListIncoming Parametros: ");
			LOG.info("cdCredenciador: " + cdCredenciador);
			LOG.info("produto: " + produto);
			LOG.info("cdTransacao: " + cdTransacao);
			LOG.info("dtPeriodoInicio: " + dtPeriodoInicio);
			LOG.info("dtPeriodoFim: " + dtPeriodoFim);
			LOG.info("codErro: " + codErro); 
			rs = ps.executeQuery();
			
			int countQtd = 0;
			BigDecimal valor = BigDecimal.ZERO;
			
			while (rs.next()) {
				countQtd++;
				
				Incoming incoming = new Incoming();
				
				IdIncoming id = new IdIncoming();
				id.setIdMatriz(rs.getLong("ID_INCOMING_ELO"));
				id.setIdItem(rs.getLong("ID_INCOMING_ELO_ITEM"));

				incoming.setId(id);
				incoming.setNomePV(Utils.leftRightTrim(rs.getString("NM_PONTO_VENDA")));
				incoming.setCidadePV(Utils.leftRightTrim(rs.getString("CIDADE_PONTO_VENDA")));
				incoming.setDataMov(rs.getString("DT_MOV_APRESENT_DISPUTA"));
				incoming.setDataMovFormatada(Utils.getDataFormata(incoming.getDataMov()));
				incoming.setValorVenda(rs.getBigDecimal("VL_VENDA_SAQUE_DISPUTA"));
				//somando valores total
				valor = valor.add(rs.getBigDecimal("VL_VENDA_SAQUE_DISPUTA"));
				//
				incoming.setNumParcela(rs.getInt("NR_PARCELA"));
				incoming.setQtdTotParcelas(rs.getInt("QT_PARCELAS_TRANSACAO"));
				incoming.setQtdDiasLiqFinanTransacao(rs.getInt("QT_DIAS_LIQ_FINAN_TRANSACAO"));
				incoming.setNumRefTransacao(rs.getString("NR_REF_TRANSACAO"));
				incoming.setCodAuthTransacao(rs.getString("CD_AUTH_TRANSACAO"));
				incoming.setQtdVezesTransRej(rs.getInt("QTD_VEZES_TRANS_REJ"));
				incoming.setCodErro(rs.getString("CD_ERRO"));
				incoming.setDescMsgErro(rs.getString("DESCRICAO_ERRO"));
				incoming.setNumLote(rs.getInt("NR_REMESSA_B0"));
				//
				//checando se tem a descricao do codigo de erro 
				if (incoming.getDescMsgErro() == null || incoming.getDescMsgErro().trim().length() == 0) {
					//se nao tiver a descricao do codigo de erro, procura na tabela de erros
					incoming.setDescMsgErro(ParametroCache.getMapCodErros().get(incoming.getCodErro()));
				}
				//
				Utils.populaInfoLibRejAtaIncoming(incoming, rs);
				
				//
				preencherIncomingComInformacoesECFilial(incoming);
				
				//Verificando se o registro pode ser marcado para Reenviar ou nao
				boolean podeMarcar = true;
				if (incoming.getDataLiberacao() != null || incoming.getDataRejeicao() != null || incoming.getDataAcatado() != null 
						|| (incoming.getCodErro() != null && incoming.getCodErro().trim().equals("B24"))) {
					podeMarcar = false;
				}
				incoming.setPodeMarcarParaReenviar(podeMarcar);
				//
				incoming.setIdTpTrans(rs.getString("ID_TP_TRANS")); //identifica se esta na Tablea TE_Padrao, TE10_20 ou TE40 (isso eh usado na modla obter mais informacoes) 

				//
				listIncoming.add(incoming);
			}
			//
			wrapper.setQtd(countQtd);
			wrapper.setValorTotal(valor);
			wrapper.setListIncoming(listIncoming);
			
			return wrapper;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}		
	}
	
	public List<IncomingHistorico> getIncomingHistorico(String numRefTrans, String nrParcela) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		List<IncomingHistorico> listIncomingHistorico = new ArrayList<>();

		try {
			con = Utils.getConnection();
			Integer numeroParcela = Integer.valueOf(nrParcela);
			if (numeroParcela == 0) {
				numeroParcela++;
			}

			String query = "SELECT CUPON, CUOTAS_VAN, TRAN_CODE, PRODUCTO, FECHA_OUTGOING, IMPORTE, MARCA, ACQUIRER_REF_NUM\r\n" + 
					"FROM PRODACQR.OUTGOING WHERE ACQUIRER_REF_NUM  = '" + numRefTrans + "'\r\n" + //[fortify] Aplicacao roda em intranet - ambiente controlado
					"AND (CUOTAS_VAN = " + numeroParcela +  " OR \r\n" + //[fortify] Aplicacao roda em intranet - ambiente controlado
					"    ( CASE WHEN CUOTAS_VAN < 51 THEN CUOTAS_VAN\r\n" + 
					"           ELSE MOD(CUOTAS_VAN,50)\r\n" + 
					"      END\r\n" + 
					"    ) = " + numeroParcela + " )\r\n" + //[fortify] Aplicacao roda em intranet - ambiente controlado
					"ORDER BY CUOTAS_VAN";
//			query += " AND MARCA = 2 \r\n" + //MARCA = 2, o código 2 indica transação rejeitada 
			
			ps = con.prepareStatement(query);
			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			
			while (rs.next()) {
				IncomingHistorico obj = new IncomingHistorico();
				obj.setCupon(rs.getString("CUPON"));
				obj.setCuotasVan(rs.getString("CUOTAS_VAN"));
				obj.setTranCode(rs.getString("TRAN_CODE"));
				obj.setProducto(rs.getString("PRODUCTO"));
				obj.setFechaOutgoing(rs.getString("FECHA_OUTGOING"));
				obj.setImporte(rs.getBigDecimal("IMPORTE"));
				obj.setMarca(rs.getString("MARCA"));
				obj.setAcquirerRefNum(rs.getString("ACQUIRER_REF_NUM"));
				
				listIncomingHistorico.add(obj);
			}
			
			return listIncomingHistorico;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	public IncomingMaisInformacoes getIncomingMaisInformacoes(Long idIncomingMaster, Long idIncomingEloItem, String idTpTrans) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
		IncomingMaisInformacoes retorno = new IncomingMaisInformacoes();
		retorno.setIncoming(this.getDxcIncomingEloMaster(idIncomingMaster));				//MASTER
		
		//verificando qual tipo de transacao eh, para preencher os detalhes corretamente
		if (idTpTrans.equalsIgnoreCase("P")) {
			retorno.setIncomingItem(this.getDxcIncomingEloItem(idIncomingEloItem));				//ITEM	
		} else if (idTpTrans.equalsIgnoreCase("1020")) {
			retorno.setIncomingItemTE1020(this.getDxcIncomingEloItemTE1020(idIncomingEloItem));	//ITEM TE1020	
		} else if (idTpTrans.equalsIgnoreCase("40")) {
			retorno.setIncomingItemTE40(this.getDxcIncomingEloItemTE40(idIncomingEloItem));		//ITEM TE40	
		}

		return retorno;
	}
	
	private DxcIncomingEloMaster getDxcIncomingEloMaster(Long idIncomingMaster) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		DxcIncomingEloMaster incoming = null;
		String query = "SELECT * FROM PRODACQR.DXC_INCOMING_ELO WHERE ID_INCOMING_ELO = ? ";

		try {
			con = Utils.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, idIncomingMaster);

			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			while (rs.next()) {
				incoming = (DxcIncomingEloMaster) Utils.preencherObjectByResultSet(rs, DxcIncomingEloMaster.class);
			}
			return incoming;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	private DxcIncomingEloItem getDxcIncomingEloItem(Long idIncomingEloItem) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		DxcIncomingEloItem item = null;
		String query = "SELECT * FROM PRODACQR.DXC_INCOMING_ELO_ITEM WHERE ID_INCOMING_ELO_ITEM = ? ";

		try {
			con = Utils.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, idIncomingEloItem);

			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			while (rs.next()) {
				item = (DxcIncomingEloItem) Utils.preencherObjectByResultSet(rs, DxcIncomingEloItem.class);
			}
			return item;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	private DxcIncomingEloItemTE1020 getDxcIncomingEloItemTE1020(Long idIncomingEloItem) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		DxcIncomingEloItemTE1020 item = null;
		String query = "SELECT * FROM PRODACQR.DXC_INCOMING_ELO_ITEM_TE10_20 WHERE ID_INCOMING_ELO_ITEM = ? ";

		try {
			con = Utils.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, idIncomingEloItem);

			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			while (rs.next()) {
				item = (DxcIncomingEloItemTE1020) Utils.preencherObjectByResultSet(rs, DxcIncomingEloItemTE1020.class);
			}
			return item;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	private DxcIncomingEloItemTE40 getDxcIncomingEloItemTE40(Long idIncomingEloItem) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		DxcIncomingEloItemTE40 item = null;
		String query = "SELECT * FROM PRODACQR.DXC_INCOMING_ELO_ITEM_TE40 WHERE ID_INCOMING_ELO_ITEM = ? ";

		try {
			con = Utils.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, idIncomingEloItem);
			
			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			while (rs.next()) {
				item = (DxcIncomingEloItemTE40) Utils.preencherObjectByResultSet(rs, DxcIncomingEloItemTE40.class);
			}
			return item;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	public List<IncomingCodRetPayware> getCodRetornoArqPayware(String dataMovimento) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<IncomingCodRetPayware> listaRet = new ArrayList<>();
		
		String dataMovimentoFormatada = Utils.getyyyyMMddFromIsoDate(dataMovimento);
		String query = "SELECT ID_INCOMING_ELO, ID_INCOMING_ELO_ITEM, DT_MOV_APRESENT_DISPUTA, VL_VENDA_SAQUE_DISPUTA, \r\n" + 
				"NR_REF_TRANSACAO, NR_PARCELA, QT_PARCELAS_TRANSACAO, DH_ENVIO_ARQ_PAYWARE, RETURN_CODE_PAYWARE, RETURN_DESCRIPTION_PAYWARE \r\n" + 
				"FROM DXC_INCOMING_ELO_ITEM\r\n" + 
				"WHERE DH_ENVIO_ARQ_PAYWARE IS NOT NULL AND RETURN_CODE_PAYWARE IS NOT NULL \r\n" + 
				"AND TRUNC(DH_ENVIO_ARQ_PAYWARE) = TO_DATE( ? , 'YYYYMMDD') ";

		try {
			con = Utils.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, dataMovimentoFormatada);
			
			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			while (rs.next()) {
				IncomingCodRetPayware item = new IncomingCodRetPayware();
				
				item.setId(new IdIncoming(rs.getLong("ID_INCOMING_ELO"), rs.getLong("ID_INCOMING_ELO_ITEM")));
				item.setDataMovFormatada(Utils.getDataFormata(rs.getString("DT_MOV_APRESENT_DISPUTA")));
				item.setNumRefTransacao(rs.getString("NR_REF_TRANSACAO"));
				item.setNumParcela(rs.getInt("NR_PARCELA"));
				item.setQtdTotParcelas(rs.getInt("QT_PARCELAS_TRANSACAO"));
				java.util.Date data = null;
				Timestamp timestamp = rs.getTimestamp("DH_ENVIO_ARQ_PAYWARE");
				if (timestamp != null) {
					data = new java.util.Date(timestamp.getTime());
				}				    
				item.setDhEnvioArqPayware(data);
				item.setReturnCodePayware(rs.getInt("RETURN_CODE_PAYWARE"));
				item.setReturnDescriptionPayware(rs.getString("RETURN_DESCRIPTION_PAYWARE"));
				item.setValorVenda(rs.getBigDecimal("VL_VENDA_SAQUE_DISPUTA"));
				
				listaRet.add(item);
			}
			return listaRet;
			
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	private void preencherIncomingComInformacoesECFilial(Incoming incoming) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "SELECT C.COMERCIO, SC.SUCURSAL, C.DESCRIPCION AS NOME_EC, SC.DESCRIPCION AS NOME_PV \r\n" + 
				"       FROM OUTGOING O\r\n" + 
				"       INNER JOIN TRANS_ISO_ACQR T ON (T.ID_TRANS_ISO_ACQR = O.CUPON)\r\n" + 
				"       INNER JOIN COMERCIOS C ON (C.COMERCIO = T.COMERCIO AND C.ACQUIRER_ID = T.ACQUIRER_ID)\r\n" + 
				"       INNER JOIN SUCURSALES_COMERC SC ON (SC.COMERCIO = C.COMERCIO AND SC.ACQUIRER_ID = C.ACQUIRER_ID AND SC.SUCURSAL = T.SUCURSAL)\r\n" + 
				"WHERE O.ACQUIRER_REF_NUM = ? AND O.CUOTAS_VAN = ? ";

		try {
			con = Utils.getConnection();
			ps = con.prepareStatement(query);
			
			//ajustando o numero da parcela
			Integer numeroParcela = incoming.getNumParcela();
			if (numeroParcela == 0) {
				numeroParcela++;
			}
			
			ps.setString(1, incoming.getNumRefTransacao().trim()); //ACQUIRER_REF_NUM
			ps.setInt(2, numeroParcela); //CUOTAS_VAN
			
			rs = ps.executeQuery();
			String codComercio = "";
			String nomeComercio = "";
			String codPV = "";
			String nomePV = "";
			//
			while (rs.next()) {
				codComercio = rs.getString("COMERCIO");
				nomeComercio = rs.getString("NOME_EC");
				codPV = rs.getString("SUCURSAL");
				nomePV = rs.getString("NOME_PV");
			}

			incoming.setCodEC(codComercio.trim());
			incoming.setNomeEC(nomeComercio.replaceAll("\\s+$", "")); //RTRIM
			incoming.setCodPV(codPV.trim());
			incoming.setNomePVBanco(nomePV.replaceAll("\\s+$", "")); //RTRIM
			
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
}
