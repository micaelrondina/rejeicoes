package br.com.dxc.cards.core.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateOracleDataSource {
	private static final Logger LOG = LogManager.getLogger(CreateOracleDataSource.class);

	private CreateOracleDataSource() {}

	/** 
	 * 
	 * @return
	 * @throws CustomServiceFaultException 
	 */
	public static DataSource createJndiDataSource(String dts) {
		InitialContext jndiCntx;
		DataSource ds;
		try {
			jndiCntx = new InitialContext();
			ds = (DataSource) jndiCntx.lookup("java:/"+dts);
			return ds;
		} catch (NamingException e) {
			LOG.fatal("Erro ao obter conexao by jndi", e);
			return null;
		}
	}
}