/**
 * ParameterServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class ParameterServiceServiceLocator extends org.apache.axis.client.Service
		implements cards.hp.com.br.ParameterServiceService {

	public ParameterServiceServiceLocator() {
	}

	public ParameterServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public ParameterServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for ParameterServicePort
	private java.lang.String ParameterServicePort_address = "http://hom03.ws.cardsutility.eds.com.br/CMS-STD-WEB-SERVICES-ACQUIRER-ACCESSCONTROL/ParameterService";

	public java.lang.String getParameterServicePortAddress() {
		return ParameterServicePort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String ParameterServicePortWSDDServiceName = "ParameterServicePort";

	public java.lang.String getParameterServicePortWSDDServiceName() {
		return ParameterServicePortWSDDServiceName;
	}

	public void setParameterServicePortWSDDServiceName(java.lang.String name) {
		ParameterServicePortWSDDServiceName = name;
	}

	public cards.hp.com.br.ParameterService getParameterServicePort() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(ParameterServicePort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getParameterServicePort(endpoint);
	}

	public cards.hp.com.br.ParameterService getParameterServicePort(java.net.URL portAddress)
			throws javax.xml.rpc.ServiceException {
		try {
			cards.hp.com.br.ParameterServiceServiceSoapBindingStub _stub = new cards.hp.com.br.ParameterServiceServiceSoapBindingStub(
					portAddress, this);
			_stub.setPortName(getParameterServicePortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setParameterServicePortEndpointAddress(java.lang.String address) {
		ParameterServicePort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has no
	 * port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (cards.hp.com.br.ParameterService.class.isAssignableFrom(serviceEndpointInterface)) {
				cards.hp.com.br.ParameterServiceServiceSoapBindingStub _stub = new cards.hp.com.br.ParameterServiceServiceSoapBindingStub(
						new java.net.URL(ParameterServicePort_address), this);
				_stub.setPortName(getParameterServicePortWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has no
	 * port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("ParameterServicePort".equals(inputPortName)) {
			return getParameterServicePort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("br.com.hp.cards", "ParameterServiceService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("br.com.hp.cards", "ParameterServicePort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {

		if ("ParameterServicePort".equals(portName)) {
			setParameterServicePortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
