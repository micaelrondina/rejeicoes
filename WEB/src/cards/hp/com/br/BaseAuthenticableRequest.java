/**
 * BaseAuthenticableRequest.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public abstract class BaseAuthenticableRequest extends cards.hp.com.br.BaseRequest implements java.io.Serializable {
	private java.lang.String username;

	private java.lang.String token;

	public BaseAuthenticableRequest() {
	}

	public BaseAuthenticableRequest(java.lang.String username, java.lang.String token) {
		this.username = username;
		this.token = token;
	}

	/**
	 * Gets the username value for this BaseAuthenticableRequest.
	 * 
	 * @return username
	 */
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * Sets the username value for this BaseAuthenticableRequest.
	 * 
	 * @param username
	 */
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	 * Gets the token value for this BaseAuthenticableRequest.
	 * 
	 * @return token
	 */
	public java.lang.String getToken() {
		return token;
	}

	/**
	 * Sets the token value for this BaseAuthenticableRequest.
	 * 
	 * @param token
	 */
	public void setToken(java.lang.String token) {
		this.token = token;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof BaseAuthenticableRequest))
			return false;
		BaseAuthenticableRequest other = (BaseAuthenticableRequest) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj)
				&& ((this.username == null && other.getUsername() == null)
						|| (this.username != null && this.username.equals(other.getUsername())))
				&& ((this.token == null && other.getToken() == null)
						|| (this.token != null && this.token.equals(other.getToken())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = super.hashCode();
		if (getUsername() != null) {
			_hashCode += getUsername().hashCode();
		}
		if (getToken() != null) {
			_hashCode += getToken().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			BaseAuthenticableRequest.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "baseAuthenticableRequest"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("username");
		elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("token");
		elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
