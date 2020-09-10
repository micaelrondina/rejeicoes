package br.com.dxc.cards.core.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.cards.core.dao.ParametroDAO;
import br.com.dxc.cards.core.model.Parametro;

public class ParametroCache {
	private static final Logger LOG = LogManager.getLogger(ParametroCache.class);
	
	private static List<Parametro> listCodTransacoes = null;
	private static List<Parametro> listCredenciadores = null;
	private static Map<String, String> mapCodErros = null;
	private static Map<String, String> mapDescricaoCodigos = null;
	
	static {
		try {
			cargaValores();			
		} catch (SQLException e) {
			LOG.error("Erro ao carregar ParametrosCache", e);
		}
	}
	
	public static void cargaValores() throws SQLException {
		ParametroDAO dao = new ParametroDAO();
		ParametroCache.listCredenciadores = dao.getCredenciadores();
		ParametroCache.listCodTransacoes = dao.getCodTransacoes();
		ParametroCache.mapCodErros = dao.getCodErros();
		ParametroCache.mapDescricaoCodigos = dao.getDescricaoCodigos();
	}

	public static List<Parametro> getListCodTransacoes() {
		return listCodTransacoes;
	}

	public static List<Parametro> getListCredenciadores() {
		return listCredenciadores;
	}

	public static Map<String, String> getMapCodErros() {
		return mapCodErros;
	}
	
	public static Map<String, String> getMapDescricaoCodigos() {
		return mapDescricaoCodigos;
	}
}
