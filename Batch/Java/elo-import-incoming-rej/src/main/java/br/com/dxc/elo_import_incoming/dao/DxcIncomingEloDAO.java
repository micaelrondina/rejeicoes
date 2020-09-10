package br.com.dxc.elo_import_incoming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.model.DxcIncomingElo;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class DxcIncomingEloDAO {
	
	private static final String SQL_SAVE = "INSERT INTO DXC_INCOMING_ELO (\r\n" + 
			"NM_ARQUIVO, CD_REGISTRO_B0, CD_SERVICO_B0, DT_REMESSA, NR_REMESSA_B0, NR_JANELA, DT_ENVIO, HR_ENVIO_ARQ, \r\n" + 
			"DT_RETORNO_ARQ, HR_RETORNO_ARQ, BANCO_EMISSOR_B0, CD_PROCESSADORA, VERSAO_ARQ, CD_CREDENCIADOR, \r\n" + 
			"CD_BANDEIRA_B0, INDICADOR_ROTA_ARQ_B0, CD_TRANSACAO, SUB_CD_TRANSACAO, BANCO_EMISSOR_00, DT_MOVIMENTO, \r\n" + 
			"NR_REMESSA_00, DT_CONFIRM_REMESSA, ST_REMESSA, MOTIVO_REJEICAO, CD_MOEDA_TRANSACAO, INDICADOR_TP_RETORNO, \r\n" + 
			"QT_TOT_REG_ARQ, QT_TRANS_AC_MOEDA_REAL, VL_TRANS_AC_MOEDA_REAL, QT_TRANS_RJ_MOEDA_REAL, \r\n" + 
			"VL_TRANS_RJ_MOEDA_REAL, CD_BANDEIRA_00, QT_TRANS_AC_MOEDA_DOLAR, VL_TRANS_AC_MOEDA_DOLAR, \r\n" + 
			"QT_TRANS_RJ_MOEDA_DOLAR, VL_TRANS_RJ_MOEDA_DOLAR, CD_REGISTRO_BZ, CD_SERVICO_BZ, NR_REMESSA_BZ, \r\n" + 
			"QT_TRANS_CRE_MOEDA_REAL, VL_TRANS_CRE_MOEDA_REAL, QT_TRANS_DEB_MOEDA_REAL, VL_TRANS_DEB_MOEDA_REAL, \r\n" + 
			"QT_TRANS_CRE_MOEDA_DOLAR, VL_TRANS_CRE_MOEDA_DOLAR, QT_TRANS_DEB_MOEDA_DOLAR, VL_TRANS_DEB_MOEDA_DOLAR, \r\n" + 
			"QT_TOT_REG, QT_TRANS_MOVI_PARCELADO, VL_TRANS_MOVI_PARCELADO, INDICADOR_ROTA_ARQ_BZ, \r\n" +
			/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
			"QT_TRANS_CRE_RJ_MOEDA_REAL, VL_TOT_TRANS_CRE_RJ_MOEDA_REAL, QT_TRANS_DEB_RJ_MOEDA_REAL, VL_TOT_TRANS_DEB_RJ_MOEDA_REAL\r\n" +
			/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */
			") VALUES (\r\n" + 
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \r\n" + 
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private static final String SQL_SAVE_HEADER = "INSERT INTO DXC_INCOMING_ELO ( \r\n" + 
			"NM_ARQUIVO, CD_REGISTRO_B0, CD_SERVICO_B0, DT_REMESSA, NR_REMESSA_B0, NR_JANELA, DT_ENVIO, HR_ENVIO_ARQ, DT_RETORNO_ARQ, \r\n" + 
			"HR_RETORNO_ARQ, BANCO_EMISSOR_B0, CD_PROCESSADORA, VERSAO_ARQ, CD_CREDENCIADOR, CD_BANDEIRA_B0, INDICADOR_ROTA_ARQ_B0) \r\n" + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static final String SQL_UPDATE_TE44 = "UPDATE DXC_INCOMING_ELO\r\n" + 
			"SET CD_TRANSACAO = ?, SUB_CD_TRANSACAO = ?, BANCO_EMISSOR_00 = ?, DT_MOVIMENTO = ?, NR_REMESSA_00 = ?,\r\n" + 
			"DT_CONFIRM_REMESSA = ?, ST_REMESSA = ?, MOTIVO_REJEICAO = ?, CD_MOEDA_TRANSACAO = ?, INDICADOR_TP_RETORNO = ?, QT_TOT_REG_ARQ = ?,\r\n" + 
			"QT_TRANS_AC_MOEDA_REAL = ?, VL_TRANS_AC_MOEDA_REAL = ?, QT_TRANS_RJ_MOEDA_REAL = ?, VL_TRANS_RJ_MOEDA_REAL = ?,\r\n" + 
			"CD_BANDEIRA_00 = ?, QT_TRANS_AC_MOEDA_DOLAR = ?, VL_TRANS_AC_MOEDA_DOLAR = ?,\r\n" + 
			"QT_TRANS_RJ_MOEDA_DOLAR = ?, VL_TRANS_RJ_MOEDA_DOLAR = ?\r\n" + 
			"WHERE ID_INCOMING_ELO = ?";

	private static final String SQL_UPDATE_TRAILER = "UPDATE DXC_INCOMING_ELO\r\n" + 
			"SET CD_REGISTRO_BZ = ?, CD_SERVICO_BZ = ?, NR_REMESSA_BZ = ?, \r\n" + 
			"QT_TRANS_CRE_MOEDA_REAL = ?, VL_TRANS_CRE_MOEDA_REAL = ?, QT_TRANS_DEB_MOEDA_REAL = ?, VL_TRANS_DEB_MOEDA_REAL = ?,\r\n" + 
			"QT_TRANS_CRE_MOEDA_DOLAR = ?, VL_TRANS_CRE_MOEDA_DOLAR = ?, QT_TRANS_DEB_MOEDA_DOLAR = ?, VL_TRANS_DEB_MOEDA_DOLAR = ?,\r\n" + 
			"QT_TOT_REG = ?, QT_TRANS_MOVI_PARCELADO = ?, VL_TRANS_MOVI_PARCELADO = ?, INDICADOR_ROTA_ARQ_BZ = ?\r\n" + 
			"WHERE ID_INCOMING_ELO = ?";
	
	public boolean arquivoJaImportado(String arquivoNome) throws SQLException, InvalidValueException {
		Long result = getIdDxcIncomingElo(arquivoNome);
		if (result == null) {
			return false;
		}
		return result != 0;
	}

	private Long getIdDxcIncomingElo(String arquivoNome) throws SQLException, InvalidValueException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			String query = "SELECT ID_INCOMING_ELO FROM DXC_INCOMING_ELO WHERE NM_ARQUIVO = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, arquivoNome);

			rs = ps.executeQuery();
			int count = 0;
			Long id = null;
			while (rs.next()) {
				count++;
				id = rs.getLong("ID_INCOMING_ELO");
			}
			
			if (count > 1) {
				throw new InvalidValueException("Erro ao obter o ID do registro DXC_INCOMING_ELO inserido! Mais de um registro encontrado!");
			}
			return id;
			
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao obter o ID do registro DXC_INCOMING_ELO inserido!", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public Long saveRegistroHeader(DxcIncomingElo dxcIncomingElo) throws InvalidValueException, SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_SAVE_HEADER);
			 
			ps.setString(1 , dxcIncomingElo.getNmArquivo());
			ps.setString(2 , dxcIncomingElo.getCdRegistroB0());
			ps.setString(3 , dxcIncomingElo.getCdServicoB0());
			ps.setString(4 , dxcIncomingElo.getDtRemessa());
			ps.setString(5 , dxcIncomingElo.getNrRemessaB0());
			ps.setString(6 , dxcIncomingElo.getNrJanela());
			ps.setString(7 , dxcIncomingElo.getDtEnvio());
			ps.setString(8 , dxcIncomingElo.getHrEnvioArq());
			ps.setString(9 , dxcIncomingElo.getDtRetornoArq());
			ps.setString(10, dxcIncomingElo.getHrRetornoArq());
			ps.setString(11, dxcIncomingElo.getBancoEmissorB0());
			ps.setString(12, dxcIncomingElo.getCdProcessadora());
			ps.setString(13, dxcIncomingElo.getVersaoArq());
			ps.setString(14, dxcIncomingElo.getCdCredenciador());
			ps.setString(15, dxcIncomingElo.getCdBandeiraB0());
			ps.setString(16, dxcIncomingElo.getIndicadorRotaArqB0());
			 
			ps.executeQuery();
			
			Long idMaster = getIdDxcIncomingElo(dxcIncomingElo.getNmArquivo());
			if (idMaster == null || idMaster == 0) {
				throw new InvalidValueException("ID do registro DXC_INCOMING_ELO esta NULO ou ZERADO");
			}
			return idMaster;

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao salvar Registro Header", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public void updateRegistroHeaderTE44(DxcIncomingElo dxcIncomingElo) throws SQLException, InvalidValueException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_UPDATE_TE44);
			
			ps.setString(1 , dxcIncomingElo.getCdTransacao()); //CD_TRANSACAO
			ps.setString(2 , dxcIncomingElo.getSubCdTransacao()); //SUB_CD_TRANSACAO
			ps.setString(3 , dxcIncomingElo.getBancoEmissor00()); //BANCO_EMISSOR_00
			ps.setString(4 , dxcIncomingElo.getDtMovimento()); //DT_MOVIMENTO
			ps.setString(5 , dxcIncomingElo.getNrRemessa00()); //NR_REMESSA_00
			ps.setString(6 , dxcIncomingElo.getDtConfirmRemessa()); //DT_CONFIRM_REMESSA
			ps.setString(7 , dxcIncomingElo.getStRemessa()); //ST_REMESSA
			ps.setString(8 , dxcIncomingElo.getMotivoRejeicao()); //MOTIVO_REJEICAO
			ps.setString(9 , dxcIncomingElo.getCdMoedaTransacao()); //CD_MOEDA_TRANSACAO
			ps.setString(10, dxcIncomingElo.getIndicadorTpRetorno()); //INDICADOR_TP_RETORNO
			ps.setString(11, dxcIncomingElo.getQtTotRegArq()); //QT_TOT_REG_ARQ
			ps.setString(12, dxcIncomingElo.getQtTransAcMoedaReal()); //QT_TRANS_AC_MOEDA_REAL
			ps.setObject(13, dxcIncomingElo.getVlTransAcMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_AC_MOEDA_REAL
			ps.setString(14, dxcIncomingElo.getQtTransRjMoedaReal()); //QT_TRANS_RJ_MOEDA_REAL
			ps.setObject(15, dxcIncomingElo.getVlTransRjMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_RJ_MOEDA_REAL
			ps.setString(16, dxcIncomingElo.getCdBandeiraB0()); //CD_BANDEIRA_00
			ps.setString(17, dxcIncomingElo.getQtTransAcMoedaDolar()); //QT_TRANS_AC_MOEDA_DOLAR
			ps.setObject(18, dxcIncomingElo.getVlTransAcMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_AC_MOEDA_DOLAR
			ps.setString(19, dxcIncomingElo.getQtTransRjMoedaDolar()); //QT_TRANS_RJ_MOEDA_DOLAR
			ps.setObject(20, dxcIncomingElo.getVlTransRjMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_RJ_MOEDA_DOLAR
			ps.setLong(21, dxcIncomingElo.getIdIncomingElo()); //ID_INCOMING_ELO
			 
			int countLinesUpdate = ps.executeUpdate();
			if (countLinesUpdate == 0) {
				throw new InvalidValueException("Erro ao atualizar registro DXC_INCOMING_ELO - Transacao TE44");
			}
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizar Registro TE44", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
	
	public void updateRegistroTrailer(DxcIncomingElo dxcIncomingElo) throws SQLException, InvalidValueException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_UPDATE_TRAILER);
			
			ps.setString(1 , dxcIncomingElo.getCdRegistroBz()); //CD_REGISTRO_BZ
			ps.setString(2 , dxcIncomingElo.getCdServicoBz()); //CD_SERVICO_BZ
			ps.setString(3 , dxcIncomingElo.getNrRemessaBz()); //NR_REMESSA_BZ
			ps.setString(4 , dxcIncomingElo.getQtTransCreMoedaReal()); //QT_TRANS_CRE_MOEDA_REAL
			ps.setObject(5 , dxcIncomingElo.getVlTransCreMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_CRE_MOEDA_REAL
			ps.setString(6 , dxcIncomingElo.getQtTransDebMoedaReal()); //QT_TRANS_DEB_MOEDA_REAL
			ps.setObject(7 , dxcIncomingElo.getVlTransDebMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_DEB_MOEDA_REAL
			ps.setString(8 , dxcIncomingElo.getQtTransCreMoedaDolar()); //QT_TRANS_CRE_MOEDA_DOLAR
			ps.setObject(9 , dxcIncomingElo.getVlTransCreMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_CRE_MOEDA_DOLAR
			ps.setString(10, dxcIncomingElo.getQtTransDebMoedaDolar()); //QT_TRANS_DEB_MOEDA_DOLAR
			ps.setObject(11, dxcIncomingElo.getVlTransDebMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_DEB_MOEDA_DOLAR
			ps.setString(12, dxcIncomingElo.getQtTotReg()); //QT_TOT_REG
			ps.setString(13, dxcIncomingElo.getQtTransMoviParcelado()); //QT_TRANS_MOVI_PARCELADO
			ps.setObject(14, dxcIncomingElo.getVlTransMoviParcelado(), java.sql.Types.DOUBLE); //VL_TRANS_MOVI_PARCELADO
			ps.setString(15, dxcIncomingElo.getIndicadorRotaArqBz()); //INDICADOR_ROTA_ARQ_BZ
			ps.setLong(16, dxcIncomingElo.getIdIncomingElo()); //ID_INCOMING_ELO

			int countLinesUpdate = ps.executeUpdate();
			if (countLinesUpdate == 0) {
				throw new InvalidValueException("Erro ao atualizar registro DXC_INCOMING_ELO - Transacao Trailer");
			}
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao atualizar Registro Trailer", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}

	public Long saveRegistroMaster(DxcIncomingElo dxcIncomingEloMaster) throws InvalidValueException, SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Utils.getConexao();
			ps = con.prepareStatement(SQL_SAVE);
			
			ps.setString(1,  dxcIncomingEloMaster.getNmArquivo()); //NM_ARQUIVO
			ps.setString(2,  dxcIncomingEloMaster.getCdRegistroB0()); //CD_REGISTRO_B0
			ps.setString(3,  dxcIncomingEloMaster.getCdServicoB0()); //CD_SERVICO_B0
			ps.setString(4,  dxcIncomingEloMaster.getDtRemessa()); //DT_REMESSA
			ps.setString(5,  dxcIncomingEloMaster.getNrRemessaB0()); //NR_REMESSA_B0
			ps.setString(6,  dxcIncomingEloMaster.getNrJanela()); //NR_JANELA
			ps.setString(7,  dxcIncomingEloMaster.getDtEnvio()); //DT_ENVIO
			ps.setString(8,  dxcIncomingEloMaster.getHrEnvioArq()); //HR_ENVIO_ARQ
			ps.setString(9,  dxcIncomingEloMaster.getDtRetornoArq()); //DT_RETORNO_ARQ
			ps.setString(10, dxcIncomingEloMaster.getHrRetornoArq()); //HR_RETORNO_ARQ
			ps.setString(11, dxcIncomingEloMaster.getBancoEmissorB0()); //BANCO_EMISSOR_B0
			ps.setString(12, dxcIncomingEloMaster.getCdProcessadora()); //CD_PROCESSADORA
			ps.setString(13, dxcIncomingEloMaster.getVersaoArq()); //VERSAO_ARQ
			ps.setString(14, dxcIncomingEloMaster.getCdCredenciador()); //CD_CREDENCIADOR
			ps.setString(15, dxcIncomingEloMaster.getCdBandeiraB0()); //CD_BANDEIRA_B0
			ps.setString(16, dxcIncomingEloMaster.getIndicadorRotaArqB0()); //INDICADOR_ROTA_ARQ_B0
			ps.setString(17, dxcIncomingEloMaster.getCdTransacao()); //CD_TRANSACAO
			ps.setString(18, dxcIncomingEloMaster.getSubCdTransacao()); //SUB_CD_TRANSACAO
			ps.setString(19, dxcIncomingEloMaster.getBancoEmissor00()); //BANCO_EMISSOR_00
			ps.setString(20, dxcIncomingEloMaster.getDtMovimento()); //DT_MOVIMENTO
			ps.setString(21, dxcIncomingEloMaster.getNrRemessa00()); //NR_REMESSA_00
			ps.setString(22, dxcIncomingEloMaster.getDtConfirmRemessa()); //DT_CONFIRM_REMESSA
			ps.setString(23, dxcIncomingEloMaster.getStRemessa()); //ST_REMESSA
			ps.setString(24, dxcIncomingEloMaster.getMotivoRejeicao()); //MOTIVO_REJEICAO
			ps.setString(25, dxcIncomingEloMaster.getCdMoedaTransacao()); //CD_MOEDA_TRANSACAO
			ps.setString(26, dxcIncomingEloMaster.getIndicadorTpRetorno()); //INDICADOR_TP_RETORNO
			ps.setString(27, dxcIncomingEloMaster.getQtTotRegArq()); //QT_TOT_REG_ARQ
			ps.setString(28, dxcIncomingEloMaster.getQtTransAcMoedaReal()); //QT_TRANS_AC_MOEDA_REAL
			ps.setObject(29, dxcIncomingEloMaster.getVlTransAcMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_AC_MOEDA_REAL (dessa forma, se o valor vier NULL nao da erro)
			ps.setString(30, dxcIncomingEloMaster.getQtTransRjMoedaReal()); //QT_TRANS_RJ_MOEDA_REAL
			ps.setObject(31, dxcIncomingEloMaster.getVlTransRjMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_RJ_MOEDA_REAL
			ps.setString(32, dxcIncomingEloMaster.getCdBandeira00()); //CD_BANDEIRA_00
			ps.setString(33, dxcIncomingEloMaster.getQtTransAcMoedaDolar()); //QT_TRANS_AC_MOEDA_DOLAR
			ps.setObject(34, dxcIncomingEloMaster.getVlTransAcMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_AC_MOEDA_DOLAR
			ps.setString(35, dxcIncomingEloMaster.getQtTransRjMoedaDolar()); //QT_TRANS_RJ_MOEDA_DOLAR
			ps.setObject(36, dxcIncomingEloMaster.getVlTransRjMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_RJ_MOEDA_DOLAR
			ps.setString(37, dxcIncomingEloMaster.getCdRegistroBz()); //CD_REGISTRO_BZ
			ps.setString(38, dxcIncomingEloMaster.getCdServicoBz()); //CD_SERVICO_BZ
			ps.setString(39, dxcIncomingEloMaster.getNrRemessaBz()); //NR_REMESSA_BZ
			ps.setString(40, dxcIncomingEloMaster.getQtTransCreMoedaReal()); //QT_TRANS_CRE_MOEDA_REAL
			ps.setObject(41, dxcIncomingEloMaster.getVlTransCreMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_CRE_MOEDA_REAL
			ps.setString(42, dxcIncomingEloMaster.getQtTransDebMoedaReal()); //QT_TRANS_DEB_MOEDA_REAL
			ps.setObject(43, dxcIncomingEloMaster.getVlTransDebMoedaReal(), java.sql.Types.DOUBLE); //VL_TRANS_DEB_MOEDA_REAL
			ps.setString(44, dxcIncomingEloMaster.getQtTransCreMoedaDolar()); //QT_TRANS_CRE_MOEDA_DOLAR
			ps.setObject(45, dxcIncomingEloMaster.getVlTransCreMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_CRE_MOEDA_DOLAR
			ps.setString(46, dxcIncomingEloMaster.getQtTransDebMoedaDolar()); //QT_TRANS_DEB_MOEDA_DOLAR
			ps.setObject(47, dxcIncomingEloMaster.getVlTransDebMoedaDolar(), java.sql.Types.DOUBLE); //VL_TRANS_DEB_MOEDA_DOLAR
			ps.setString(48, dxcIncomingEloMaster.getQtTotReg()); //QT_TOT_REG
			ps.setString(49, dxcIncomingEloMaster.getQtTransMoviParcelado()); //QT_TRANS_MOVI_PARCELADO
			ps.setObject(50, dxcIncomingEloMaster.getVlTransMoviParcelado(), java.sql.Types.DOUBLE); //VL_TRANS_MOVI_PARCELADO
			ps.setString(51, dxcIncomingEloMaster.getIndicadorRotaArqBz()); //INDICADOR_ROTA_ARQ_BZ
			
			/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - INICIO */
			ps.setString(52, dxcIncomingEloMaster.getQtTransCreRjMoedaReal()); //QT_TRANS_CRE_RJ_MOEDA_REAL
			ps.setObject(53, dxcIncomingEloMaster.getVlTotTransCreRjMoedaReal(), java.sql.Types.DOUBLE); //VL_TOT_TRANS_CRE_RJ_MOEDA_REAL
			ps.setString(54, dxcIncomingEloMaster.getQtTransDebRjMoedaReal()); //QT_TRANS_DEB_RJ_MOEDA_REAL
			ps.setObject(55, dxcIncomingEloMaster.getVlTotTransDebRjMoedaReal(), java.sql.Types.DOUBLE); //VL_TOT_TRANS_DEB_RJ_MOEDA_REAL
			/* CAMPOS INCLUIDOS DEVIDO A ALTERACOES REALIZADAS PELA ELO NO LAYOUT DOS ARQUIVOS - VERSAO 19.2 - FIM */

			ps.executeQuery();

			Long idMaster = getIdDxcIncomingElo(dxcIncomingEloMaster.getNmArquivo());
			if (idMaster == null || idMaster == 0) {
				throw new InvalidValueException("ID do registro DXC_INCOMING_ELO esta NULO ou ZERADO");
			}
			return idMaster;

		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		    throw new SQLException("Erro ao salvar Registro DXC_INCOMING_ELO", e);
		} finally {
		    Utils.fechaConexao(rs, ps);
		}
	}
}
