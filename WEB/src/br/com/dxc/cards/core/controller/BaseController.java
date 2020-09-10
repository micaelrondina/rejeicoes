package br.com.dxc.cards.core.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import br.com.dxc.cards.core.db.CreateOracleDataSource;

public class BaseController {
	private static final Logger LOG_BASE = LogManager.getLogger(BaseController.class); 
	private static final HttpHeaders headers = new HttpHeaders();

	private Map<String, Connection> connection = new HashMap<>();
	
    private Logger logger = null;
    
    protected Logger getLogger(){
        if(this.logger == null){
            this.logger = LogManager.getLogger(getClass());
        }
        return this.logger;
    }

	static {
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
	}

	protected HttpHeaders getHttpHeaders() {
		return headers;
	}

	protected Connection getConnection(String dataSourceJboss) {
		try {
			if (!connection.containsKey(dataSourceJboss) || (connection.get(dataSourceJboss) != null && connection.get(dataSourceJboss).isClosed())) {
				connection.put(dataSourceJboss, CreateOracleDataSource.createJndiDataSource(dataSourceJboss).getConnection());
			}
			return connection.get(dataSourceJboss);
		} catch (SQLException ex) {
			LOG_BASE.error("falha ao buscar conexao com dataSourceJboss " + dataSourceJboss, ex);
			return null;
		}
	}

	protected void disconectByDataSourceJboss(String dataSourceJboss) {
		try {
			if (connection.get(dataSourceJboss) != null && !connection.get(dataSourceJboss).isClosed()) {
				Connection conn = connection.get(dataSourceJboss);
				conn.close();
			}
		} catch (SQLException ex) {
			LOG_BASE.error("falha ao disconectar conexao com dataSourceJboss " + dataSourceJboss, ex);
		}
	}

	protected void disconectAllDataSourceJboss() {
		connection.forEach((k, v) -> disconectByDataSourceJboss(k));
	}

}
