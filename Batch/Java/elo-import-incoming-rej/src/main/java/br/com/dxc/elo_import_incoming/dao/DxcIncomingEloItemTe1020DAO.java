package br.com.dxc.elo_import_incoming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItemTe1020;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class DxcIncomingEloItemTe1020DAO {

	private static final String SQL_SAVE = "INSERT INTO DXC_INCOMING_ELO_ITEM_TE10_20 (\r\n" + 
			"ID_INCOMING_ELO, CD_TRANSACAO_00, SUB_CD_TRANSACAO_00, CD_DESTINO, CD_ORIGEM, CD_MOTIVO_TRANSACAO, \r\n" + 
			"CD_PAIS_00, DT_ENVIO, NR_CARTAO, VL_DESTINO, CD_MOEDA_DESTINO, VL_ORIGEM, CD_MOEDA_ORIGEM, MSG_TEXTO, \r\n" + 
			"INDICADOR_LIQ, ID_TRANSACAO_ORIGINAL, DT_PROCESSAMENTO, CD_TRANSACAO_02, SUB_CD_TRANSACAO_02, CD_PAIS_02, \r\n" + 
			"QT_DIAS_LIQ_FINAN_TRANSACAO, DT_PROCESSAMENTO_REG_TP_02, CD_ERRO, TOKEN_PAN, DT_LIQ_TRANSACAO) \r\n" + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public void saveDxcIncomingEloItemTe1020(DxcIncomingEloItemTe1020 dxcIncomingEloItemTe1020) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_SAVE);
			
			ps.setLong(1, dxcIncomingEloItemTe1020.getIdIncomingElo()); //ID_INCOMING_ELO
			ps.setString(2, dxcIncomingEloItemTe1020.getCdTransacao00()); //CD_TRANSACAO_00
			ps.setString(3, dxcIncomingEloItemTe1020.getSubCdTransacao00()); //SUB_CD_TRANSACAO_00
			ps.setString(4, dxcIncomingEloItemTe1020.getCdDestino()); //CD_DESTINO
			ps.setString(5, dxcIncomingEloItemTe1020.getCdOrigem()); //CD_ORIGEM
			ps.setString(6, dxcIncomingEloItemTe1020.getCdMotivoTransacao()); //CD_MOTIVO_TRANSACAO
			ps.setString(7, dxcIncomingEloItemTe1020.getCdPais00()); //CD_PAIS_00
			ps.setString(8, dxcIncomingEloItemTe1020.getDtEnvio()); //DT_ENVIO
			ps.setString(9, dxcIncomingEloItemTe1020.getNrCartao()); //NR_CARTAO
			ps.setObject(10, dxcIncomingEloItemTe1020.getVlDestino(), java.sql.Types.DOUBLE); //VL_DESTINO
			ps.setString(11, dxcIncomingEloItemTe1020.getCdMoedaDestino()); //CD_MOEDA_DESTINO
			ps.setObject(12, dxcIncomingEloItemTe1020.getVlOrigem(), java.sql.Types.DOUBLE); //VL_ORIGEM
			ps.setString(13, dxcIncomingEloItemTe1020.getCdMoedaOrigem()); //CD_MOEDA_ORIGEM
			ps.setString(14, dxcIncomingEloItemTe1020.getMsgTexto()); //MSG_TEXTO
			ps.setString(15, dxcIncomingEloItemTe1020.getIndicadorLiq()); //INDICADOR_LIQ
			ps.setString(16, dxcIncomingEloItemTe1020.getIdTransacaoOriginal()); //ID_TRANSACAO_ORIGINAL
			ps.setString(17, dxcIncomingEloItemTe1020.getDtProcessamento()); //DT_PROCESSAMENTO
			ps.setString(18, dxcIncomingEloItemTe1020.getCdTransacao02()); //CD_TRANSACAO_02
			ps.setString(19, dxcIncomingEloItemTe1020.getSubCdTransacao02()); //SUB_CD_TRANSACAO_02
			ps.setString(20, dxcIncomingEloItemTe1020.getCdPais02()); //CD_PAIS_02
			ps.setString(21, dxcIncomingEloItemTe1020.getQtDiasLiqFinanTransacao()); //QT_DIAS_LIQ_FINAN_TRANSACAO
			ps.setString(22, dxcIncomingEloItemTe1020.getDtProcessamentoRegTp02()); //DT_PROCESSAMENTO_REG_TP_02
			ps.setString(23, dxcIncomingEloItemTe1020.getCdErro()); //CD_ERRO
			ps.setString(24, dxcIncomingEloItemTe1020.getTokenPan()); //TOKEN_PAN
			ps.setString(25, dxcIncomingEloItemTe1020.getDtLiqTransacao()); //DT_LIQ_TRANSACAO
			 
			ps.executeQuery();

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizar Registro DXC_INCOMING_ELO_ITEM_TE10_20", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
}
