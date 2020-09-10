package br.com.dxc.elo_import_incoming.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.jdbc.pool.OracleDataSource;

public class CreateOracleDataSource {
	
	private static final Logger LOG = LogManager.getLogger(CreateOracleDataSource.class);

	public DataSource createPropertiesDataSource(String path) throws SQLException, IOException, GeneralSecurityException {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
		OracleDataSource dataSource = null;

		try (FileInputStream fileInputStream = new FileInputStream(path)){ //[fortify] Aplicacao roda em intranet - ambiente controlado
			Properties prop = new Properties();
			prop.load(fileInputStream);

			//getting properties
			String url = prop.getProperty("jdbc.url");
			String user = prop.getProperty("jdbc.user");
			String passwd = prop.getProperty("jdbc.passwd");
			String hashedPassword = prop.getProperty("jdbc.hashedpasswd");

			LOG.info("DataSource url: " + url);
			dataSource = new OracleDataSource();
			dataSource.setURL(url);
			dataSource.setUser(user);
			
			if (hashedPassword != null) {
				dataSource.setPassword(TripleDesCriptor.decryptBase64(hashedPassword));
			} else {
				dataSource.setPassword(passwd);
			}

		} catch (SQLException ex) {
			LOG.error("Failed to connect database");
			throw ex;
		} catch (IOException ex) {
			LOG.error("Failed to read properties");
			throw ex;
		} catch (GeneralSecurityException ex) {
			LOG.error("Failed to decrypt password");
			throw ex;
		}

		return dataSource;
	}
}