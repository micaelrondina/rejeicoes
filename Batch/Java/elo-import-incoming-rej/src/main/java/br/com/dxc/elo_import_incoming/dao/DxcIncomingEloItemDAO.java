package br.com.dxc.elo_import_incoming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItem;
import br.com.dxc.elo_import_incoming.utils.Constantes;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class DxcIncomingEloItemDAO {
	private static final Logger LOG = LogManager.getLogger(DxcIncomingEloItemDAO.class);
	
	private static final String SQL_SAVE = "INSERT INTO DXC_INCOMING_ELO_ITEM (\r\n" + 
			"ID_INCOMING_ELO, CD_TRANSACAO_00, SUB_CD_TRANSACAO_00, NR_CARTAO, TP_LIQUIDACAO, IND_ORIGEM_AUTH_CANC, \r\n" + 
			"NR_REF_TRANSACAO, CD_PROCESSO, CD_CREDENCIADOR, DT_VENDA_SAQUE, HR_VENDA_SAQUE, VL_VENDA_SAQUE_DISPUTA, \r\n" + 
			"CD_MOEDA_TRANSACAO, NM_PONTO_VENDA, CIDADE_PONTO_VENDA, CD_PAIS_PONTO_VENDA, MCC_PONTO_VENDA, BANCO_EMISSOR, \r\n" + 
			"CD_BANDEIRA, IDENT_TP_TRANSACAO, CD_MOTIVO_DISPUTA, CD_AUTH_TRANSACAO, INDICADOR_TECNO_TERMINAL, \r\n" + 
			"MEIO_IDENT_PORTADOR, MODO_ENT_TRANSACAO_POS, DT_MOV_APRESENT_DISPUTA, CD_TRANSACAO_01, SUB_CD_TRANSACAO_01, \r\n" + 
			"NR_REF_DISPUTA, INDICADOR_ENV_DOC, TXT_LIVRE, CD_PRODUTO, PONTO_VENDA, NR_LOGICO_EQUIPAMENTO, \r\n" + 
			"VL_TAXA_EMBARQUE, INDIC_TRANS_FEITA_POR, VL_TRANSACAO, INDICADOR_MOVIMENTACAO, QT_PARCELAS_TRANSACAO, \r\n" + 
			"NR_PARCELA, TARIFA_PAGTO_INSUMO, TP_PESSOA, DOCUMENTO, VL_TROCO_AGRO_DEB, CD_COND_TRANSACAO_CHIP, \r\n" + 
			"CD_TRANSACAO_02, SUB_CD_TRANSACAO_02, CD_PAIS_LIQ, QT_DIAS_LIQ_FINAN_TRANSACAO, \r\n" + 
			"VL_INTERCAMBIO, DT_MOV_TRANSACAO_ORIGINAL, TP_OPERACAO, TOKEN_PAN, TOKEN_REQUESTOR_ID, TOKEN_ASSURANCE_LEVEL, \r\n" + 
			"CEP_EC, DT_LIQUI_TRANSACAO, CD_PV_MARKETPLACE, CD_TRANSACAO_03, SUB_CD_TRANSACAO_03, TP_PAGTO, \r\n" + 
			"NR_CONTA_UNICO_REF, NM_REMETENTE, ENDERECO_REMETENTE, CIDADE_REMETENTE, PAIS_REMETENTE, ORIGEM_FUNDOS, \r\n" + 
			"CD_TRANSACAO_05, CD_QUALIFICADOR_TRANSACAO_05, NR_SEQ_COMP_TRANSACAO_05, ID_TRANSACAO, VL_AUTORIZADO, \r\n" + 
			"CD_MOEDA_VL_AUTORIZADO, CD_RESP_AUTORIZACAO, INDICADOR_DIREITO_DEVOLUCAO, INDICADOR_COMER_ELETRONICO, \r\n" + 
			"INDICADOR_AUTH_ESPECIFICA, VL_TOTAL_AUTORIZADO, CAVV_VL_VERIF_AUTH_PORTADOR, CD_RESULT_VERIF_CAVV, \r\n" + 
			"CD_TRANSACAO_07, CD_QUALIFICADOR_TRANSACAO_07, NR_SEQ_COMP_TRANSACAO_07, TP_TRANSACAO, NR_SEQ_CARTAO, \r\n" + 
			"DT_TRANSACAO_TERMINAL, CAPACIDADE_TERMINAL, CD_PAIS_TERMINAL, NR_SERIE_TERMINAL, NR_RANDOM_CRIPTOGRAMA, \r\n" + 
			"CONTADOR_TRANSACAO_APP, APP_INTERCHANGE_PROFILE, CRIPTOGRAMA, INDICE_DERIVACAO_CHAVE, NR_VERSAO_CRIPTOGRAMA, \r\n" + 
			"VERIF_RESULT_TERMINAL, VERIF_RESULT_CARTAO, VL_TRANSACAO_CRIPTOGRAMA, FORM_FACTOR_INDICATOR, CD_TRANSACAO_08, \r\n" + 
			"SUB_CD_TRANSACAO_08, CD_COMPANHIA_AEREA, NR_DOC_EMBARQUE, CD_AGENTE_IATA, NM_PASSAGEIRO, CD_CIDADE_1, \r\n" + 
			"TRANSPORTADORA_1, CLASSE_SERVICO_1, CD_CIDADE_2, TRANSPORTADORA_2, CLASSE_SERVICO_2, CD_CIDADE_3, \r\n" + 
			"TRANSPORTADORA_3, CLASSE_SERVICO_3, CD_CIDADE_4, TRANSPORTADORA_4, CLASSE_SERVICO_4, CD_CIDADE_5, \r\n" + 
			"CD_AEROPORTO_DESTINO, DT_PRIMEIRO_VOO, NM_AGENTE_EMISSOR_DOC, CD_TRANSACAO_09, SUB_CD_TRANSACAO_09, \r\n" + 
			"CD_TRANSACAO_ORIGINAL, DT_MOVIMENTO, CD_ERRO, DESCRICAO_ERRO, REGISTRO_COM_ERRO, POSICAO_COM_ERRO)\r\n" +  
			"VALUES (" +
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + //25
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + //50
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + //75
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + //100
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + //125
			"?, ?, ?, ?)"; //129
	
	private static final String SQL_UPDATE_DH_ENVIO_ARQ_PAYWARE = "UPDATE DXC_INCOMING_ELO_ITEM SET DH_ENVIO_ARQ_PAYWARE = SYSDATE WHERE ID_INCOMING_ELO_ITEM IN (@ID_INCOMING_ELO_ITEM)";
	
	public void saveDxcIncomingEloItem(DxcIncomingEloItem dxcIncomingEloItem) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_SAVE);
			
			setPrepareStatement(ps, dxcIncomingEloItem);
			 
			ps.executeQuery();

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao salvar Registro DXC_INCOMING_ELO_ITEM", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public int setarDhEnvioArqPayware(String ids) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		int retorno = -1;
		
		try {
			con = Utils.getConexao();
			ids = ids.replaceAll("\\[", "");
			ids = ids.replaceAll("\\]", "");
			String query = SQL_UPDATE_DH_ENVIO_ARQ_PAYWARE.replaceAll("@ID_INCOMING_ELO_ITEM", ids); //[fortify] Aplicacao roda em intranet - ambiente controlado
			
			LOG.info( "SQL : " + query);
			ps = con.prepareStatement(query);
			retorno = ps.executeUpdate();
			return retorno; 

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizar DH_ENVIO_ARQ_PAYWARE na tabela DXC_INCOMING_ELO_ITEM", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public Long validarSeTransacaoEhRejeitadoNovamente(String dtRetornoArq, String nrRefTransacao, String nrParcela) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			String query = "SELECT I.ID_INCOMING_ELO AS ID_MASTER, I.ID_INCOMING_ELO_ITEM AS ID_ITEM \r\n" + 
				"FROM DXC_INCOMING_ELO_ITEM I\r\n" + 
				"    INNER JOIN DXC_INCOMING_ELO M ON (I.ID_INCOMING_ELO = M.ID_INCOMING_ELO)\r\n" + 
				"WHERE M.DT_RETORNO_ARQ < '" + dtRetornoArq + "'\r\n" + 
				"AND I.DT_LIBERACAO IS NOT NULL AND I.DT_REJEICAO IS NULL AND I.DT_ACATADO IS NULL\r\n" + 
				"AND I.NR_REF_TRANSACAO = '" + nrRefTransacao + "'\r\n" + 
				"AND I.CD_TRANSACAO_00 = '01' ";

			if (nrParcela == null) {
				query += " AND NR_PARCELA = '000'";
			} else {
				query += " AND NR_PARCELA = '" + nrParcela + "'";	
			}

			con = Utils.getConexao();
			ps = con.prepareStatement(query); //[fortify] Aplicacao roda em intranet - ambiente controlado
			rs = ps.executeQuery();
			
			Long idRetorno = null;
			while (rs.next()) {
				idRetorno = rs.getLong("ID_ITEM");
			}

			return idRetorno;

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao validarSeTransacaoEhRejeitadoNovamento", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public void atualizarTransacaoRejeitadaNovamente(String dtRejeicao, Long idEloIncomingMasterRejeitado, Long idIncomingEloItem) throws SQLException, InvalidValueException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			String query = "UPDATE DXC_INCOMING_ELO_ITEM\r\n" + 
					"SET DT_REJEICAO = ?, LOGIN_USU_REJEICAO = ?, ID_INCOMING_ELO_REJEICAO = ?\r\n" + 
					"WHERE ID_INCOMING_ELO_ITEM = ?";

			con = Utils.getConexao();
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date((Utils.getDateFromStringYYYYMMDD(dtRejeicao)).getTime())); //DT_REJEICAO (date)
			ps.setString(2, Constantes.NOME_USUARIO_BATCH); //LOGIN_REJEICAO
			ps.setLong(3, idEloIncomingMasterRejeitado); //ID_INCOMING_ELO_REJEICAO
			ps.setLong(4, idIncomingEloItem); //ID_INCOMING_ELO_ITEM
			rs = ps.executeQuery();

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizarTransacaoRejeitadaNovamente", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public Long getByNrRefTransacaoAndNrParcelaAndEnviadoPayware(String nrRefTransacao, String nrParcela) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		Long idRet = null;
		
		try {
			String query = "SELECT ID_INCOMING_ELO_ITEM\r\n" + 
					"FROM DXC_INCOMING_ELO_ITEM \r\n" + 
					"WHERE NR_REF_TRANSACAO = '" + nrRefTransacao + "'\r\n" + 
					"AND DT_LIBERACAO IS NOT NULL AND DH_ENVIO_ARQ_PAYWARE IS NOT NULL \r\n" + 
					"AND DT_ACATADO IS NULL AND DT_REJEICAO IS NULL AND RETURN_CODE_PAYWARE IS NULL\r\n";
			
			if (Integer.valueOf(nrParcela) == 1) {
				query += "AND (NR_PARCELA = '" + nrParcela + "' OR NR_PARCELA = '000')";
			} else {
				query += "AND (NR_PARCELA = '" + nrParcela + "')";
			}
			
			con = Utils.getConexao();
			ps = con.prepareStatement(query); //[fortify] Aplicacao roda em intranet - ambiente controlado
			rs = ps.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				idRet = rs.getLong("ID_INCOMING_ELO_ITEM");
				count++;
			}
			
			if (count == 0) {
				idRet = null;
				LOG.info("Nenhuma Transacao Encontrada no getByNrRefTransacaoAndNrParcelaAndEnviadoPayware com ARN: " + nrRefTransacao + ", e CUOTAS_VAN: " +  nrParcela);
			} else if (count > 1) {
				idRet = null;
				LOG.info("Mais de uma Transacao Encontrada no getByNrRefTransacaoAndNrParcelaAndEnviadoPayware com ARN: " + nrRefTransacao + ", e CUOTAS_VAN: " +  nrParcela);				
			}
			return idRet;
			
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizarTransacaoRejeitadaNovamente", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	

	public void setReturnCodeByArqRetPayware(Long idItem, String returnCode, String descriptionReturn) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			String query = "UPDATE DXC_INCOMING_ELO_ITEM SET RETURN_CODE_PAYWARE = ?, RETURN_DESCRIPTION_PAYWARE = ? WHERE ID_INCOMING_ELO_ITEM = ?";

			con = Utils.getConexao();
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.valueOf(returnCode)); //RETURN_CODE_PAYWARE //[fortify] Aplicacao roda em intranet - ambiente controlado
			ps.setString(2, descriptionReturn); //RETURN_DESCRIPTION_PAYWARE
			ps.setLong(3, idItem); //ID_INCOMING_ELO_ITEM
			rs = ps.executeQuery();

		} catch (NumberFormatException e) {
			throw new SQLException("Return Code: \"" + returnCode + "\" nao eh um numero valido!", e);
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao setReturnCodeByArqRetPayware", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}

	private void setPrepareStatement(PreparedStatement ps, DxcIncomingEloItem dxcIncomingEloItem) throws SQLException {
		
		ps.setLong(1, dxcIncomingEloItem.getIdIncomingElo()); //ID_INCOMING_ELO
		ps.setString(2, dxcIncomingEloItem.getCdTransacao00()); //CD_TRANSACAO_00
		ps.setString(3, dxcIncomingEloItem.getSubCdTransacao00()); //SUB_CD_TRANSACAO_00
		ps.setString(4, dxcIncomingEloItem.getNrCartao()); //NR_CARTAO
		ps.setString(5, dxcIncomingEloItem.getTpLiquidacao()); //TP_LIQUIDACAO
		ps.setString(6, dxcIncomingEloItem.getIndOrigemAuthCanc()); //IND_ORIGEM_AUTH_CANC
		ps.setString(7, dxcIncomingEloItem.getNrRefTransacao()); //NR_REF_TRANSACAO
		ps.setString(8, dxcIncomingEloItem.getCdProcesso()); //CD_PROCESSO
		ps.setString(9, dxcIncomingEloItem.getCdCredenciador()); //CD_CREDENCIADOR
		ps.setString(10, dxcIncomingEloItem.getDtVendaSaque()); //DT_VENDA_SAQUE
		ps.setString(11, dxcIncomingEloItem.getHrVendaSaque()); //HR_VENDA_SAQUE
		ps.setObject(12, dxcIncomingEloItem.getVlVendaSaqueDisputa(), java.sql.Types.DOUBLE); //VL_VENDA_SAQUE_DISPUTA
		ps.setString(13, dxcIncomingEloItem.getCdMoedaTransacao()); //CD_MOEDA_TRANSACAO
		ps.setString(14, dxcIncomingEloItem.getNmPontoVenda()); //NM_PONTO_VENDA
		ps.setString(15, dxcIncomingEloItem.getCidadePontoVenda()); //CIDADE_PONTO_VENDA
		ps.setString(16, dxcIncomingEloItem.getCdPaisPontoVenda()); //CD_PAIS_PONTO_VENDA
		ps.setString(17, dxcIncomingEloItem.getMccPontoVenda()); //MCC_PONTO_VENDA
		ps.setString(18, dxcIncomingEloItem.getBancoEmissor()); //BANCO_EMISSOR
		ps.setString(19, dxcIncomingEloItem.getCdBandeira()); //CD_BANDEIRA
		ps.setString(20, dxcIncomingEloItem.getIdentTpTransacao()); //IDENT_TP_TRANSACAO
		ps.setString(21, dxcIncomingEloItem.getCdMotivoDisputa()); //CD_MOTIVO_DISPUTA
		ps.setString(22, dxcIncomingEloItem.getCdAuthTransacao()); //CD_AUTH_TRANSACAO
		ps.setString(23, dxcIncomingEloItem.getIndicadorTecnoTerminal()); //INDICADOR_TECNO_TERMINAL
		ps.setString(24, dxcIncomingEloItem.getMeioIdentPortador()); //MEIO_IDENT_PORTADOR
		ps.setString(25, dxcIncomingEloItem.getModoEntTransacaoPos()); //MODO_ENT_TRANSACAO_POS
		ps.setString(26, dxcIncomingEloItem.getDtMovApresentDisputa()); //DT_MOV_APRESENT_DISPUTA
		ps.setString(27, dxcIncomingEloItem.getCdTransacao01()); //CD_TRANSACAO_01
		ps.setString(28, dxcIncomingEloItem.getSubCdTransacao01()); //SUB_CD_TRANSACAO_01
		ps.setString(29, dxcIncomingEloItem.getNrRefDisputa()); //NR_REF_DISPUTA
		ps.setString(30, dxcIncomingEloItem.getIndicadorEnvDoc()); //INDICADOR_ENV_DOC
		ps.setString(31, dxcIncomingEloItem.getTxtLivre()); //TXT_LIVRE
		ps.setString(32, dxcIncomingEloItem.getCdProduto()); //CD_PRODUTO
		ps.setString(33, dxcIncomingEloItem.getPontoVenda()); //PONTO_VENDA
		ps.setString(34, dxcIncomingEloItem.getNrLogicoEquipamento()); //NR_LOGICO_EQUIPAMENTO
		ps.setObject(35, dxcIncomingEloItem.getVlTaxaEmbarque(), java.sql.Types.DOUBLE); //VL_TAXA_EMBARQUE
		ps.setString(36, dxcIncomingEloItem.getIndicTransFeitaPor()); //INDIC_TRANS_FEITA_POR
		ps.setObject(37, dxcIncomingEloItem.getVlTransacao(), java.sql.Types.DOUBLE); //VL_TRANSACAO
		ps.setString(38, dxcIncomingEloItem.getIndicadorMovimentacao()); //INDICADOR_MOVIMENTACAO
		ps.setString(39, dxcIncomingEloItem.getQtParcelasTransacao()); //QT_PARCELAS_TRANSACAO
		ps.setString(40, dxcIncomingEloItem.getNrParcela()); //NR_PARCELA
		ps.setString(41, dxcIncomingEloItem.getTarifaPagtoInsumo()); //TARIFA_PAGTO_INSUMO
		ps.setString(42, dxcIncomingEloItem.getTpPessoa()); //TP_PESSOA
		ps.setString(43, dxcIncomingEloItem.getDocumento()); //DOCUMENTO
		ps.setObject(44, dxcIncomingEloItem.getVlTrocoAgroDeb(), java.sql.Types.DOUBLE); //VL_TROCO_AGRO_DEB
		ps.setString(45, dxcIncomingEloItem.getCdCondTransacaoChip()); //CD_COND_TRANSACAO_CHIP
		ps.setString(46, dxcIncomingEloItem.getCdTransacao02()); //CD_TRANSACAO_02
		ps.setString(47, dxcIncomingEloItem.getSubCdTransacao02()); //SUB_CD_TRANSACAO_02
		ps.setString(48, dxcIncomingEloItem.getCdPaisLiq()); //CD_PAIS_LIQ
		ps.setString(49, dxcIncomingEloItem.getQtDiasLiqFinanTransacao()); //QT_DIAS_LIQ_FINAN_TRANSACAO
		ps.setObject(50, dxcIncomingEloItem.getVlIntercambio(), java.sql.Types.DOUBLE); //VL_INTERCAMBIO
		ps.setString(51, dxcIncomingEloItem.getDtMovTransacaoOriginal()); //DT_MOV_TRANSACAO_ORIGINAL
		ps.setString(52, dxcIncomingEloItem.getTpOperacao()); //TP_OPERACAO
		ps.setString(53, dxcIncomingEloItem.getTokenPan()); //TOKEN_PAN
		ps.setString(54, dxcIncomingEloItem.getTokenRequestorId()); //TOKEN_REQUESTOR_ID
		ps.setString(55, dxcIncomingEloItem.getTokenAssuranceLevel()); //TOKEN_ASSURANCE_LEVEL
		ps.setString(56, dxcIncomingEloItem.getCepEc()); //CEP_EC
		ps.setString(57, dxcIncomingEloItem.getDtLiquiTransacao()); //DT_LIQUI_TRANSACAO
		ps.setString(58, dxcIncomingEloItem.getCdPvMarketplace()); //CD_PV_MARKETPLACE
		ps.setString(59, dxcIncomingEloItem.getCdTransacao03()); //CD_TRANSACAO_03
		ps.setString(60, dxcIncomingEloItem.getSubCdTransacao03()); //SUB_CD_TRANSACAO_03
		ps.setString(61, dxcIncomingEloItem.getTpPagto()); //TP_PAGTO
		ps.setString(62, dxcIncomingEloItem.getNrContaUnicoRef()); //NR_CONTA_UNICO_REF
		ps.setString(63, dxcIncomingEloItem.getNmRemetente()); //NM_REMETENTE
		ps.setString(64, dxcIncomingEloItem.getEnderecoRemetente()); //ENDERECO_REMETENTE
		ps.setString(65, dxcIncomingEloItem.getCidadeRemetente()); //CIDADE_REMETENTE
		ps.setString(66, dxcIncomingEloItem.getPaisRemetente()); //PAIS_REMETENTE
		ps.setString(67, dxcIncomingEloItem.getOrigemFundos()); //ORIGEM_FUNDOS
		ps.setString(68, dxcIncomingEloItem.getCdTransacao05()); //CD_TRANSACAO_05
		ps.setString(69, dxcIncomingEloItem.getCdQualificadorTransacao05()); //CD_QUALIFICADOR_TRANSACAO_05
		ps.setString(70, dxcIncomingEloItem.getNrSeqCompTransacao05()); //NR_SEQ_COMP_TRANSACAO_05
		ps.setString(71, dxcIncomingEloItem.getIdTransacao()); //ID_TRANSACAO
		ps.setObject(72, dxcIncomingEloItem.getVlAutorizado(), java.sql.Types.DOUBLE); //VL_AUTORIZADO
		ps.setString(73, dxcIncomingEloItem.getCdMoedaVlAutorizado()); //CD_MOEDA_VL_AUTORIZADO
		ps.setString(74, dxcIncomingEloItem.getCdRespAutorizacao()); //CD_RESP_AUTORIZACAO
		ps.setString(75, dxcIncomingEloItem.getIndicadorDireitoDevolucao()); //INDICADOR_DIREITO_DEVOLUCAO
		ps.setString(76, dxcIncomingEloItem.getIndicadorComerEletronico()); //INDICADOR_COMER_ELETRONICO
		ps.setString(77, dxcIncomingEloItem.getIndicadorAuthEspecifica()); //INDICADOR_AUTH_ESPECIFICA
		ps.setObject(78, dxcIncomingEloItem.getVlTotalAutorizado(), java.sql.Types.DOUBLE); //VL_TOTAL_AUTORIZADO
		ps.setString(79, dxcIncomingEloItem.getCavvVlVerifAuthPortador()); //CAVV_VL_VERIF_AUTH_PORTADOR
		ps.setString(80, dxcIncomingEloItem.getCdResultVerifCavv()); //CD_RESULT_VERIF_CAVV
		ps.setString(81, dxcIncomingEloItem.getCdTransacao07()); //CD_TRANSACAO_07
		ps.setString(82, dxcIncomingEloItem.getCdQualificadorTransacao07()); //CD_QUALIFICADOR_TRANSACAO_07
		ps.setString(83, dxcIncomingEloItem.getNrSeqCompTransacao07()); //NR_SEQ_COMP_TRANSACAO_07
		ps.setString(84, dxcIncomingEloItem.getTpTransacao()); //TP_TRANSACAO
		ps.setString(85, dxcIncomingEloItem.getNrSeqCartao()); //NR_SEQ_CARTAO
		ps.setString(86, dxcIncomingEloItem.getDtTransacaoTerminal()); //DT_TRANSACAO_TERMINAL
		ps.setString(87, dxcIncomingEloItem.getCapacidadeTerminal()); //CAPACIDADE_TERMINAL
		ps.setString(88, dxcIncomingEloItem.getCdPaisTerminal()); //CD_PAIS_TERMINAL
		ps.setString(89, dxcIncomingEloItem.getNrSerieTerminal()); //NR_SERIE_TERMINAL
		ps.setString(90, dxcIncomingEloItem.getNrRandomCriptograma()); //NR_RANDOM_CRIPTOGRAMA
		ps.setString(91, dxcIncomingEloItem.getContadorTransacaoApp()); //CONTADOR_TRANSACAO_APP
		ps.setString(92, dxcIncomingEloItem.getAppInterchangeProfile()); //APP_INTERCHANGE_PROFILE
		ps.setString(93, dxcIncomingEloItem.getCriptograma()); //CRIPTOGRAMA
		ps.setString(94, dxcIncomingEloItem.getIndiceDerivacaoChave()); //INDICE_DERIVACAO_CHAVE
		ps.setString(95, dxcIncomingEloItem.getNrVersaoCriptograma()); //NR_VERSAO_CRIPTOGRAMA
		ps.setString(96, dxcIncomingEloItem.getVerifResultTerminal()); //VERIF_RESULT_TERMINAL
		ps.setString(97, dxcIncomingEloItem.getVerifResultCartao()); //VERIF_RESULT_CARTAO
		ps.setObject(98, dxcIncomingEloItem.getVlTransacaoCriptograma(), java.sql.Types.DOUBLE); //VL_TRANSACAO_CRIPTOGRAMA
		ps.setString(99, dxcIncomingEloItem.getFormFactorIndicator()); //FORM_FACTOR_INDICATOR
		ps.setString(100, dxcIncomingEloItem.getCdTransacao08()); //CD_TRANSACAO_08
		ps.setString(101, dxcIncomingEloItem.getSubCdTransacao08()); //SUB_CD_TRANSACAO_08
		ps.setString(102, dxcIncomingEloItem.getCdCompanhiaAerea()); //CD_COMPANHIA_AEREA
		ps.setString(103, dxcIncomingEloItem.getNrDocEmbarque()); //NR_DOC_EMBARQUE
		ps.setString(104, dxcIncomingEloItem.getCdAgenteIata()); //CD_AGENTE_IATA
		ps.setString(105, dxcIncomingEloItem.getNmPassageiro()); //NM_PASSAGEIRO
		ps.setString(106, dxcIncomingEloItem.getCdCidade1()); //CD_CIDADE_1
		ps.setString(107, dxcIncomingEloItem.getTransportadora1()); //TRANSPORTADORA_1
		ps.setString(108, dxcIncomingEloItem.getClasseServico1()); //CLASSE_SERVICO_1
		ps.setString(109, dxcIncomingEloItem.getCdCidade2()); //CD_CIDADE_2
		ps.setString(110, dxcIncomingEloItem.getTransportadora2()); //TRANSPORTADORA_2
		ps.setString(111, dxcIncomingEloItem.getClasseServico2()); //CLASSE_SERVICO_2
		ps.setString(112, dxcIncomingEloItem.getCdCidade3()); //CD_CIDADE_3
		ps.setString(113, dxcIncomingEloItem.getTransportadora3()); //TRANSPORTADORA_3
		ps.setString(114, dxcIncomingEloItem.getClasseServico3()); //CLASSE_SERVICO_3
		ps.setString(115, dxcIncomingEloItem.getCdCidade4()); //CD_CIDADE_4
		ps.setString(116, dxcIncomingEloItem.getTransportadora4()); //TRANSPORTADORA_4
		ps.setString(117, dxcIncomingEloItem.getClasseServico4()); //CLASSE_SERVICO_4
		ps.setString(118, dxcIncomingEloItem.getCdCidade5()); //CD_CIDADE_5
		ps.setString(119, dxcIncomingEloItem.getCdAeroportoDestino()); //CD_AEROPORTO_DESTINO
		ps.setString(120, dxcIncomingEloItem.getDtPrimeiroVoo()); //DT_PRIMEIRO_VOO
		ps.setString(121, dxcIncomingEloItem.getNmAgenteEmissorDoc()); //NM_AGENTE_EMISSOR_DOC
		ps.setString(122, dxcIncomingEloItem.getCdTransacao09()); //CD_TRANSACAO_09
		ps.setString(123, dxcIncomingEloItem.getSubCdTransacao09()); //SUB_CD_TRANSACAO_09
		ps.setString(124, dxcIncomingEloItem.getCdTransacaoOriginal()); //CD_TRANSACAO_ORIGINAL
		ps.setString(125, dxcIncomingEloItem.getDtMovimento()); //DT_MOVIMENTO
		ps.setString(126, dxcIncomingEloItem.getCdErro()); //CD_ERRO
		ps.setString(127, dxcIncomingEloItem.getDescricaoErro()); //DESCRICAO_ERRO
		ps.setString(128, dxcIncomingEloItem.getRegistroComErro()); //REGISTRO_COM_ERRO
		ps.setString(129, dxcIncomingEloItem.getPosicaoComErro()); //POSICAO_COM_ERRO
	}
}
