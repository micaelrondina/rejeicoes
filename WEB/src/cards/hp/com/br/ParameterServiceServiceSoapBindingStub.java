/**
 * ParameterServiceServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class ParameterServiceServiceSoapBindingStub extends org.apache.axis.client.Stub
		implements cards.hp.com.br.ParameterService {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[4];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getIssuersBySystem");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("br.com.hp.cards", "issuerBySystemRequest"),
				cards.hp.com.br.IssuerBySystemRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("br.com.hp.cards", "issuersBySystemResponse"));
		oper.setReturnClass(cards.hp.com.br.IssuersBySystemResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getCompaniesByUser");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("br.com.hp.cards", "companiesByUserRequest"),
				cards.hp.com.br.CompaniesByUserRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("br.com.hp.cards", "companiesByUserResponse"));
		oper.setReturnClass(cards.hp.com.br.CompaniesByUserResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getProfilesByUser");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("br.com.hp.cards", "profilesByUserRequest"),
				cards.hp.com.br.ProfilesByUserRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("br.com.hp.cards", "profilesByUserResponse"));
		oper.setReturnClass(cards.hp.com.br.ProfilesByUserResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getOperationsByUserSystem");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("br.com.hp.cards", "operationsByUserSystemRequest"),
				cards.hp.com.br.OperationsByUserSystemRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("br.com.hp.cards", "operationsByUserSystemResponse"));
		oper.setReturnClass(cards.hp.com.br.OperationsByUserSystemResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

	}

	public ParameterServiceServiceSoapBindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public ParameterServiceServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public ParameterServiceServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		//java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;//[fortify] Poor Style: Value Never Read
		//java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;//[fortify] Poor Style: Value Never Read
		//java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;//[fortify] Poor Style: Value Never Read
		//java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;//[fortify] Poor Style: Value Never Read
		//java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;//[fortify] Poor Style: Value Never Read
		//java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;//[fortify] Poor Style: Value Never Read
		qName = new javax.xml.namespace.QName("br.com.hp.cards", ">companiesByUserResponse>companies");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.Company[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("br.com.hp.cards", "company");
		qName2 = new javax.xml.namespace.QName("", "company");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("br.com.hp.cards", ">company>subCompanies");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.SubCompany[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("br.com.hp.cards", "subCompany");
		qName2 = new javax.xml.namespace.QName("", "subCompany");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("br.com.hp.cards", ">operationsByUserSystemResponse>operations");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.Operation[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("br.com.hp.cards", "operation");
		qName2 = new javax.xml.namespace.QName("", "operation");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("br.com.hp.cards", ">profilesByUserResponse>userProfiles");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.UserProfile[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("br.com.hp.cards", "userProfile");
		qName2 = new javax.xml.namespace.QName("", "userProfile");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "baseAuthenticableRequest");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.BaseAuthenticableRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "baseRequest");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.BaseRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "baseResponse");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.BaseResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "companiesByUserRequest");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.CompaniesByUserRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "companiesByUserResponse");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.CompaniesByUserResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "company");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.Company.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "entityType");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.EntityType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "issuerBO");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.IssuerBO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "issuerBySystemRequest");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.IssuerBySystemRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "issuersBySystemResponse");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.IssuersBySystemResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "metadataResponse");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.MetadataResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "operation");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.Operation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "operationsByUserSystemRequest");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.OperationsByUserSystemRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "operationsByUserSystemResponse");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.OperationsByUserSystemResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "profilesByUserRequest");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.ProfilesByUserRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "profilesByUserResponse");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.ProfilesByUserResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "responseMessage");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.ResponseMessage.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "subCompany");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.SubCompany.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("br.com.hp.cards", "userProfile");
		cachedSerQNames.add(qName);
		cls = cards.hp.com.br.UserProfile.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class) cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class) cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public cards.hp.com.br.IssuersBySystemResponse getIssuersBySystem(cards.hp.com.br.IssuerBySystemRequest request)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("br.com.hp.cards", "getIssuersBySystem"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cards.hp.com.br.IssuersBySystemResponse) _resp;
				} catch (java.lang.Exception _exception) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
					return (cards.hp.com.br.IssuersBySystemResponse) org.apache.axis.utils.JavaUtils.convert(_resp,
							cards.hp.com.br.IssuersBySystemResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cards.hp.com.br.CompaniesByUserResponse getCompaniesByUser(cards.hp.com.br.CompaniesByUserRequest request)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("br.com.hp.cards", "getCompaniesByUser"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cards.hp.com.br.CompaniesByUserResponse) _resp;
				} catch (java.lang.Exception _exception) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
					return (cards.hp.com.br.CompaniesByUserResponse) org.apache.axis.utils.JavaUtils.convert(_resp,
							cards.hp.com.br.CompaniesByUserResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cards.hp.com.br.ProfilesByUserResponse getProfilesByUser(cards.hp.com.br.ProfilesByUserRequest request)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("br.com.hp.cards", "getProfilesByUser"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cards.hp.com.br.ProfilesByUserResponse) _resp;
				} catch (java.lang.Exception _exception) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
					return (cards.hp.com.br.ProfilesByUserResponse) org.apache.axis.utils.JavaUtils.convert(_resp,
							cards.hp.com.br.ProfilesByUserResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cards.hp.com.br.OperationsByUserSystemResponse getOperationsByUserSystem(
			cards.hp.com.br.OperationsByUserSystemRequest request) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("br.com.hp.cards", "getOperationsByUserSystem"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cards.hp.com.br.OperationsByUserSystemResponse) _resp;
				} catch (java.lang.Exception _exception) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
					return (cards.hp.com.br.OperationsByUserSystemResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cards.hp.com.br.OperationsByUserSystemResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
