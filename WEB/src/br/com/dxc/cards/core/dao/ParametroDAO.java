package br.com.dxc.cards.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.dxc.cards.core.model.Parametro;
import br.com.dxc.cards.core.utils.Utils;

public class ParametroDAO {
	public List<Parametro> getCodTransacoes() throws SQLException {
		return getParametroByNmParametro("CD_TRANSACAO_");
	}
	
	public List<Parametro> getCredenciadores() throws SQLException {
		return getParametroByNmParametro("CD_CREDEN_");
	}
	
	public Map<String, String> getCodErros() throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		Map<String, String> mapCodErros = new LinkedHashMap<>();

		try {
			con = Utils.getConnection();
			String query = "SELECT NM_PARAMETRO, DS_PARAMETRO, VL_PARAMETRO_ALFA\r\n" + 
					"FROM PARAM_ACQUIRER_PROCESSO\r\n" + 
					"WHERE ID_ACQUIRER = 2 AND NM_PROCESSO = 'DXC_INCOMIN_ELO' AND NM_PARAMETRO LIKE 'CD_ERRO_%'\r\n" + 
					"ORDER BY 1";

			ps = con.prepareStatement(query);
			rs = Utils.executeQuery(ps, query); // executa query e print query no log
			while (rs.next()) {
				mapCodErros.put(rs.getString("DS_PARAMETRO"), rs.getString("VL_PARAMETRO_ALFA"));
			}
			return mapCodErros;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	public Map<String, String> getDescricaoCodigos() throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		Map<String, String> mapDescricao = new LinkedHashMap<>();

		try {
			con = Utils.getConnection();
			String query = "SELECT NM_PARAMETRO AS CODIGO, \r\n" + 
					"    CASE WHEN LENGTH(VL_PARAMETRO_ALFA) >= LENGTH(DS_PARAMETRO) THEN VL_PARAMETRO_ALFA\r\n" + //esse case eh necessario, pois ha parametros com a descricao salva em DS_PARAMETRO e outros em VL_PARAMETRO_ALFA (pois o campo era muita grande para slvar em DS_PARAMETRO) 
					"      ELSE DS_PARAMETRO\r\n" + 
					"    END DESCRICAO\r\n" + 
					"FROM PARAM_ACQUIRER_PROCESSO\r\n" + 
					"WHERE ID_ACQUIRER = 2 AND NM_PROCESSO = 'DXC_INCOMIN_ELO'\r\n" + 
					"ORDER BY NM_PARAMETRO, DS_PARAMETRO";

			ps = con.prepareStatement(query);
			rs = Utils.executeQuery(ps, query); // executa query e print query no log
			while (rs.next()) {
				mapDescricao.put(rs.getString("CODIGO"), rs.getString("DESCRICAO"));
			}
			return mapDescricao;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
	}
	
	private List<Parametro> getParametroByNmParametro(String nmParametro) throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		List<Parametro> parametros = new ArrayList<>();

		try {
			con = Utils.getConnection();
			String query = "SELECT NM_PARAMETRO, DS_PARAMETRO, VL_PARAMETRO_ALFA \r\n" + 
					"FROM PARAM_ACQUIRER_PROCESSO \r\n" + 
					"WHERE ID_ACQUIRER = 2 AND NM_PROCESSO = 'DXC_INCOMIN_ELO' AND NM_PARAMETRO LIKE '" + nmParametro + "%'\r\n" + 
					"ORDER BY 1";

			ps = con.prepareStatement(query);
			rs = Utils.executeQuery(ps, query); // executa query e print query no log
			while (rs.next()) {
				Parametro arq = new Parametro();
				arq.setNmParametro(rs.getString("NM_PARAMETRO"));
				arq.setDsParametro(rs.getString("DS_PARAMETRO"));
				arq.setVlParametroAlfa(rs.getString("VL_PARAMETRO_ALFA"));
				parametros.add(arq);
			}
			return parametros;
		} finally {
			Utils.fechaConexao(rs, ps, con);
		}		
	} 
}
