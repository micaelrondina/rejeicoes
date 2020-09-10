package br.com.dxc.cards.core.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cards.hp.com.br.Operation;
import cards.hp.com.br.OperationsByUserSystemRequest;
import cards.hp.com.br.OperationsByUserSystemResponse;
import cards.hp.com.br.ParameterServiceProxy;

/**
 * @author Wilian Peres
 *
 */
public class AccessControlProxy {
	private static final Logger logger = LogManager.getLogger(AccessControlProxy.class);	

	private static final String JBOSS_PROXY_PROPERTIES_FILE = System.getProperty("jboss.server.config.dir")
			+ File.separator + "accesscontrol.properties";
	private static final String PROXY_PROPERTIES_SERVICE_ACCESSCONTROL = "PARAMETER_ENDPOINT_LOCATION";
	private static final String PROXY_PROPERTIES_LOGIN_ACCESSCONTROL = "ACCESSCONTROL_LOGIN_PAGE";
	private static String accessControlLoginPage = null;
	private static final Properties PROXY_PROPERTIES;
	private static String accessControlEndpoint;

	static {
		PROXY_PROPERTIES = new Properties();
		try {
			// Loading the main endpoint properties
			PROXY_PROPERTIES.load(new FileInputStream(JBOSS_PROXY_PROPERTIES_FILE));//[fortify] sera desalocado da memoria ao fim do bloco(garbage collector)

			accessControlEndpoint = getPropertyValue(PROXY_PROPERTIES_SERVICE_ACCESSCONTROL);
			accessControlLoginPage = getPropertyValue(PROXY_PROPERTIES_LOGIN_ACCESSCONTROL);
			
			logger.info(" AccessControlProxy ");
			logger.info(" accessControlEndpoint: " + accessControlEndpoint);
			logger.info(" accessControlLoginPage: " + accessControlLoginPage);
			

		} catch (NullPointerException | IOException ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error(ex.getMessage(),ex);
		} catch (Exception ex) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao 
			logger.error(ex.getMessage(),ex);
		}
	}

	private AccessControlProxy() {

	}

	/**
	 * Loads the endpoint location using the properties endpoint.
	 * 
	 * @param proxyPropertiesEndpoint
	 * @return
	 * @throws NullPointerException
	 *             when the property is not found.
	 */
	private static String getPropertyValue(String proxyPropertiesEndpoint) {

		String endpointLocation = PROXY_PROPERTIES.getProperty(proxyPropertiesEndpoint);
		
		logger.info(" Endpoint: " + endpointLocation);
		
		if (endpointLocation == null) {
			throw new NullPointerException(
					String.format("Nao localizada a entrada [%s] no arquivo de propriedades localizado em [%s]",
							proxyPropertiesEndpoint, PROXY_PROPERTIES));
		}
		logger.info("Carregado endpoint [%s] usando propriedade [%s] com sucesso " + proxyPropertiesEndpoint);
		return endpointLocation;
	}

	/**
	 * 
	 * @param user
	 * @param token
	 * @param systemKey
	 * @throws RemoteException
	 */
	public static List<String> getUserTasks(String user, String token, String systemKey) throws RemoteException {
		List<String> list = new ArrayList<>();

		ParameterServiceProxy proxy = new ParameterServiceProxy(accessControlEndpoint);

		OperationsByUserSystemResponse response = proxy
				.getOperationsByUserSystem(new OperationsByUserSystemRequest(user, token, systemKey));
		if (response == null) {
			throw new RemoteException("Falha de comunicacao com AccessControl");
		}

		if (response.getMetadataResponse().getResponseMessage().getResponseCode() != 0) {
			throw new RemoteException("Autenticacao negada no AccessControl: "
					+ response.getMetadataResponse().getResponseMessage().getResponseDescription());
		}

		for (Operation o : response.getOperations()) {
			list.add(o.getTask());
		}

		return list;
	}

	/**
	 * @return the accessControlLoginPage
	 */
	public static String getAccessControlLoginPage() {
		return accessControlLoginPage;
	}

}
