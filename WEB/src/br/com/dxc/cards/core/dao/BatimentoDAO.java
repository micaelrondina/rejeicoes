package br.com.dxc.cards.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.cards.core.model.IdIncoming;
import br.com.dxc.cards.core.model.Incoming;
import br.com.dxc.cards.core.model.ReturnProcBatAcatado;
import br.com.dxc.cards.core.utils.Utils;

public class BatimentoDAO {
	private static final Logger LOG = LogManager.getLogger(BatimentoDAO.class);
	
	public List<Incoming> getBatimento(String dataMovimento, String idsIncomingEloItem) throws SQLException {
		List<Incoming> listIncoming = new ArrayList<>();

		String querySelectBatimento = "SELECT I.ID_INCOMING_ELO AS ID_MASTER, I.ID_INCOMING_ELO_ITEM AS ID_ITEM, I.ID_INCOMING_ELO_REJEICAO, I.ID_INCOMING_ELO_ACATADO,\r\n" + 
				"       M.NM_ARQUIVO, M.DT_RETORNO_ARQ, M.NR_REMESSA_B0,\r\n" + 
				"       I.NM_PONTO_VENDA, I.CIDADE_PONTO_VENDA, I.DT_MOV_APRESENT_DISPUTA, I.VL_VENDA_SAQUE_DISPUTA,\r\n" + 
				"       NR_REF_TRANSACAO, NR_PARCELA,\r\n" + 
				"       LOGIN_USU_LIBERACAO, DT_LIBERACAO, LOGIN_USU_ACATADO, DT_ACATADO, LOGIN_USU_REJEICAO, DT_REJEICAO,\r\n" + 
				"       M_REJ.NR_REMESSA_B0 AS NR_REMESSA_REJEICAO, M_ACA.NR_REMESSA_B0 AS NR_REMESSA_ACATADO\r\n" + 
				"FROM DXC_INCOMING_ELO_ITEM I\r\n" + 
				"    INNER JOIN DXC_INCOMING_ELO M ON (I.ID_INCOMING_ELO = M.ID_INCOMING_ELO)\r\n" + 
				"    LEFT JOIN DXC_INCOMING_ELO M_REJ ON (I.ID_INCOMING_ELO_REJEICAO = M_REJ.ID_INCOMING_ELO)\r\n" + 
				"    LEFT JOIN DXC_INCOMING_ELO M_ACA ON (I.ID_INCOMING_ELO_ACATADO = M_ACA.ID_INCOMING_ELO)\r\n" + 
				"WHERE I.DT_LIBERACAO IS NOT NULL\r\n" + 
				"AND (I.DT_REJEICAO IS NOT NULL OR I.DT_ACATADO IS NOT NULL)\r\n"; 
				
				if (dataMovimento != null) {
					String dataMovimentoFormatada = Utils.getyyyyMMddFromIsoDate(dataMovimento);
					querySelectBatimento += " AND (M_REJ.DT_RETORNO_ARQ = '" + dataMovimentoFormatada + "' OR M_ACA.DT_RETORNO_ARQ = '" + dataMovimentoFormatada + "')"; //[fortify] Aplicacao roda em intranet - ambiente controlado e parametro formatado (nao oferece risco)	
				}
				
				if (idsIncomingEloItem != null) {
					querySelectBatimento += " AND I.ID_INCOMING_ELO_ITEM IN (" + idsIncomingEloItem + ")";
				}
				
				querySelectBatimento += " ORDER BY M.CD_CREDENCIADOR, I.TP_LIQUIDACAO ";
					
		
		try (Connection con = Utils.getConnection(); PreparedStatement ps = con.prepareStatement(querySelectBatimento); ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Incoming incoming = new Incoming();
				
				incoming.setId(new IdIncoming(rs.getLong("ID_MASTER"), rs.getLong("ID_ITEM")));
				incoming.setNomeArquivo(rs.getString("NM_ARQUIVO"));
				incoming.setNumLote(rs.getInt("NR_REMESSA_B0"));
				incoming.setNomePV(Utils.leftRightTrim(rs.getString("NM_PONTO_VENDA")));
				incoming.setCidadePV(Utils.leftRightTrim(rs.getString("CIDADE_PONTO_VENDA")));
				incoming.setDataMov(rs.getString("DT_MOV_APRESENT_DISPUTA"));
				incoming.setDataMovFormatada(Utils.getDataFormata(incoming.getDataMov()));
				incoming.setValorVenda(rs.getBigDecimal("VL_VENDA_SAQUE_DISPUTA"));
				incoming.setNumRefTransacao(rs.getString("NR_REF_TRANSACAO"));
				
				//
				Utils.populaInfoLibRejAtaIncoming(incoming, rs);
				incoming.setNrRemessaRejeicao(rs.getString("NR_REMESSA_REJEICAO"));
				incoming.setNrRemessaAcatado(rs.getString("NR_REMESSA_ACATADO"));
				
				listIncoming.add(incoming);
			}
			return listIncoming;
		}
	}
	
	public ReturnProcBatAcatado processarBatimentoAcatadoManualmente(String usuarioAcatado) throws SQLException {
		try (Connection con = Utils.getConnection(); CallableStatement cs = con.prepareCall("{call P_INCOMING_ELO_BATIMENTO_ACAT(?,?,?)}");) {
			cs.setString(1, usuarioAcatado); //p_login_usu_acatado ([fortify] Aplicacao roda em intranet - ambiente controlado)
			cs.registerOutParameter(2, Types.INTEGER); //qtd_itens_acatados
			cs.registerOutParameter(3, Types.VARCHAR); //ids dos itens marcados como rejeitados nessa execucao

			cs.execute();
			
			ReturnProcBatAcatado retorno = new ReturnProcBatAcatado();
			retorno.setQtdItensMarcadosComoAcatados(cs.getLong(2)); //pegando a qtd de linhas/itens marcados como acatados
			
			String idsItemsMarcados = cs.getString(3);
			if (idsItemsMarcados != null) {
				retorno.setIdsDosItemsMarcados(cs.getString(3).replaceFirst(",", ""));
			} else {
				retorno.setIdsDosItemsMarcados(null);
			}	

			return retorno;
			
		} catch (SQLException e) {
			LOG.error("Erro: processarBatimentoAcatadoManualmente");
			throw e;
		}
	}

	public Long processarBatimentoRejeitadoManualmente(String dataMovimento, String usuarioRejeicao) throws SQLException {
		try (Connection con = Utils.getConnection(); CallableStatement cs = con.prepareCall("{call P_INCOMING_ELO_BATIMENTO_REJ(?,?,?)}");) {
			cs.setString(1, Utils.getyyyyMMddFromIsoDate(dataMovimento)); //p_data_ret_arq
			cs.setString(2, usuarioRejeicao); //p_login_usu_rejeicao ([fortify] Aplicacao roda em intranet - ambiente controlado)
			cs.registerOutParameter(3, Types.INTEGER); //qtd_itens_rejeitados

			cs.execute();

			return cs.getLong(3); //pegando a qtd de linhas/itens marcados como rejeitados
			
		} catch (SQLException e) {
			LOG.error("Erro: processarBatimentoRejeitadoManualmente");
			throw e;
		}
	}
}
