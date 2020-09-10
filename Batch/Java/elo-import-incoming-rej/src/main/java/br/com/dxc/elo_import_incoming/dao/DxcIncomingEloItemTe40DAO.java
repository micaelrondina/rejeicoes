package br.com.dxc.elo_import_incoming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItemTe40;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class DxcIncomingEloItemTe40DAO {

	private static final String SQL_SAVE = "INSERT INTO DXC_INCOMING_ELO_ITEM_TE40 (\r\n" + 
			"ID_INCOMING_ELO, CD_TRANSACAO_00, SUB_CD_TRANSACAO_00, NR_CARTAO, COMPLEMENTO_NR_CARTAO, NR_REF_TRANSACAO, \r\n" + 
			"CD_CREDENCIADOR, DT_VENDA_SAQUE, CIDADE_PONTO_VENDA, CD_PAIS_PONTO_VENDA, MCC_PONTO_VENDA, VL_FRAUDE, \r\n" + 
			"CD_MOEDA_TRANS_FRAUDULENTA, DT_NOTIFICACAO_FRAUDE, INDICADOR_ORIGEM_AUTH, CD_NOTIFICACAO, TP_FRAUDE, \r\n" + 
			"DT_VENC_CARTAO, TP_PLATAFORMA, CD_BANDEIRA, BANCO_EMISSOR, CD_TRANSACAO_02, SUB_CD_TRANSACAO_02, \r\n" + 
			"ID_TRANSACAO, PONTO_VENDA, NR_LOGICO_EQUIPAMENTO, INDICADOR_TRANS_TROCA, CD_AUTH_TRANSACAO, \r\n" + 
			"MEIO_IDENT_PORTADOR, MODO_ENT_TRANSACAO_POS, IDENT_TECNOLOG_TERMINAL, TECNOLOGIA_CARTAO, \r\n" + 
			"VL_TROCO, INDIC_TRANS_FEITA_POR, CEP_PORTADOR, CIDADE_PORTADOR, UF_PORTADOR, DT_CONFIRM_FRAUDE, \r\n" + 
			"INDICADOR_LIQ, NM_PORTADOR, TOKEN_PAN, CD_ERRO)\r\n" + //campo CD_ERRO incluido devido a alteracoes realizadas pela ELO no layout dos arquivos- VERSAO 19.2
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + 
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public void saveDxcIncomingEloItemTe40(DxcIncomingEloItemTe40 dxcIncomingEloItemTe40) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_SAVE);
			
			ps.setLong(1, dxcIncomingEloItemTe40.getIdIncomingElo()); //ID_INCOMING_ELO
			ps.setString(2, dxcIncomingEloItemTe40.getCdTransacao00()); //CD_TRANSACAO_00
			ps.setString(3, dxcIncomingEloItemTe40.getSubCdTransacao00()); //SUB_CD_TRANSACAO_00
			ps.setString(4, dxcIncomingEloItemTe40.getNrCartao()); //NR_CARTAO
			ps.setString(5, dxcIncomingEloItemTe40.getComplementoNrCartao()); //COMPLEMENTO_NR_CARTAO
			ps.setString(6, dxcIncomingEloItemTe40.getNrRefTransacao()); //NR_REF_TRANSACAO
			ps.setString(7, dxcIncomingEloItemTe40.getCdCredenciador()); //CD_CREDENCIADOR
			ps.setString(8, dxcIncomingEloItemTe40.getDtVendaSaque()); //DT_VENDA_SAQUE
			ps.setString(9, dxcIncomingEloItemTe40.getCidadePontoVenda()); //CIDADE_PONTO_VENDA
			ps.setString(10, dxcIncomingEloItemTe40.getCdPaisPontoVenda()); //CD_PAIS_PONTO_VENDA
			ps.setString(11, dxcIncomingEloItemTe40.getMccPontoVenda()); //MCC_PONTO_VENDA
			ps.setObject(12, dxcIncomingEloItemTe40.getVlFraude(), java.sql.Types.DOUBLE); //VL_FRAUDE
			ps.setString(13, dxcIncomingEloItemTe40.getCdMoedaTransFraudulenta()); //CD_MOEDA_TRANS_FRAUDULENTA
			ps.setString(14, dxcIncomingEloItemTe40.getDtNotificacaoFraude()); //DT_NOTIFICACAO_FRAUDE
			ps.setString(15, dxcIncomingEloItemTe40.getIndicadorOrigemAuth()); //INDICADOR_ORIGEM_AUTH
			ps.setString(16, dxcIncomingEloItemTe40.getCdNotificacao()); //CD_NOTIFICACAO
			ps.setString(17, dxcIncomingEloItemTe40.getTpFraude()); //TP_FRAUDE
			ps.setString(18, dxcIncomingEloItemTe40.getDtVencCartao()); //DT_VENC_CARTAO
			ps.setString(19, dxcIncomingEloItemTe40.getTpPlataforma()); //TP_PLATAFORMA
			ps.setString(20, dxcIncomingEloItemTe40.getCdBandeira()); //CD_BANDEIRA
			ps.setString(21, dxcIncomingEloItemTe40.getBancoEmissor()); //BANCO_EMISSOR
			ps.setString(22, dxcIncomingEloItemTe40.getCdTransacao02()); //CD_TRANSACAO_02
			ps.setString(23, dxcIncomingEloItemTe40.getSubCdTransacao02()); //SUB_CD_TRANSACAO_02
			ps.setString(24, dxcIncomingEloItemTe40.getIdTransacao()); //ID_TRANSACAO
			ps.setString(25, dxcIncomingEloItemTe40.getPontoVenda()); //PONTO_VENDA
			ps.setString(26, dxcIncomingEloItemTe40.getNrLogicoEquipamento()); //NR_LOGICO_EQUIPAMENTO
			ps.setString(27, dxcIncomingEloItemTe40.getIndicadorTransTroca()); //INDICADOR_TRANS_TROCA
			ps.setString(28, dxcIncomingEloItemTe40.getCdAuthTransacao()); //CD_AUTH_TRANSACAO
			ps.setString(29, dxcIncomingEloItemTe40.getMeioIdentPortador()); //MEIO_IDENT_PORTADOR
			ps.setString(30, dxcIncomingEloItemTe40.getModoEntTransacaoPos()); //MODO_ENT_TRANSACAO_POS
			ps.setString(31, dxcIncomingEloItemTe40.getIdentTecnologTerminal()); //IDENT_TECNOLOG_TERMINAL
			ps.setString(32, dxcIncomingEloItemTe40.getTecnologiaCartao()); //TECNOLOGIA_CARTAO
			ps.setObject(33, dxcIncomingEloItemTe40.getVlTroco(), java.sql.Types.DOUBLE); //VL_TROCO
			ps.setString(34, dxcIncomingEloItemTe40.getIndicTransFeitaPor()); //INDIC_TRANS_FEITA_POR
			ps.setString(35, dxcIncomingEloItemTe40.getCepPortador()); //CEP_PORTADOR
			ps.setString(36, dxcIncomingEloItemTe40.getCidadePortador()); //CIDADE_PORTADOR
			ps.setString(37, dxcIncomingEloItemTe40.getUfPortador()); //UF_PORTADOR
			ps.setString(38, dxcIncomingEloItemTe40.getDtConfirmFraude()); //DT_CONFIRM_FRAUDE
			ps.setString(39, dxcIncomingEloItemTe40.getIndicadorLiq()); //INDICADOR_LIQ
			ps.setString(40, dxcIncomingEloItemTe40.getNmPortador()); //NM_PORTADOR
			ps.setString(41, dxcIncomingEloItemTe40.getTokenPan()); //TOKEN_PAN
			
			/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
			ps.setString(42, dxcIncomingEloItemTe40.getCdErro()); //CD_ERRO
			/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */

			ps.executeQuery();

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizar Registro DXC_INCOMING_ELO_ITEM_TE40", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
}
