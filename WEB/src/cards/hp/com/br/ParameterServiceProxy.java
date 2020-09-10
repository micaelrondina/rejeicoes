package cards.hp.com.br;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParameterServiceProxy implements cards.hp.com.br.ParameterService {
	private static final Logger logger = LogManager.getLogger(ParameterServiceProxy.class);
	private String _endpoint = null;
	private cards.hp.com.br.ParameterService parameterService = null;

	public ParameterServiceProxy() {
		_initParameterServiceProxy();
	}

	public ParameterServiceProxy(String endpoint) {
		_endpoint = endpoint;
		_initParameterServiceProxy();
	}

	private void _initParameterServiceProxy() {
		try {
			parameterService = (new cards.hp.com.br.ParameterServiceServiceLocator()).getParameterServicePort();
			if (parameterService != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) parameterService)._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) parameterService)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
			logger.error(serviceException.getMessage() ,serviceException);
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (parameterService != null)
			((javax.xml.rpc.Stub) parameterService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public cards.hp.com.br.ParameterService getParameterService() {
		if (parameterService == null)
			_initParameterServiceProxy();
		return parameterService;
	}

	public cards.hp.com.br.IssuersBySystemResponse getIssuersBySystem(cards.hp.com.br.IssuerBySystemRequest request)
			throws java.rmi.RemoteException {
		if (parameterService == null)
			_initParameterServiceProxy();
		return parameterService.getIssuersBySystem(request);
	}

	public cards.hp.com.br.CompaniesByUserResponse getCompaniesByUser(cards.hp.com.br.CompaniesByUserRequest request)
			throws java.rmi.RemoteException {
		if (parameterService == null)
			_initParameterServiceProxy();
		return parameterService.getCompaniesByUser(request);
	}

	public cards.hp.com.br.ProfilesByUserResponse getProfilesByUser(cards.hp.com.br.ProfilesByUserRequest request)
			throws java.rmi.RemoteException {
		if (parameterService == null)
			_initParameterServiceProxy();
		return parameterService.getProfilesByUser(request);
	}

	public cards.hp.com.br.OperationsByUserSystemResponse getOperationsByUserSystem(
			cards.hp.com.br.OperationsByUserSystemRequest request) throws java.rmi.RemoteException {
		if (parameterService == null)
			_initParameterServiceProxy();
		return parameterService.getOperationsByUserSystem(request);
	}

}