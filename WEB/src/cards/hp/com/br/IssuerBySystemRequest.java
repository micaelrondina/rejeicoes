/**
 * IssuerBySystemRequest.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class IssuerBySystemRequest extends cards.hp.com.br.BaseAuthenticableRequest implements java.io.Serializable {
	private java.lang.String systemKey;

	public IssuerBySystemRequest() {
	}

	public IssuerBySystemRequest(java.lang.String username, java.lang.String token, java.lang.String systemKey) {
		super(username, token);
		this.systemKey = systemKey;
	}

	/**
	 * Gets the systemKey value for this IssuerBySystemRequest.
	 * 
	 * @return systemKey
	 */
	public java.lang.String getSystemKey() {
		return systemKey;
	}

	/**
	 * Sets the systemKey value for this IssuerBySystemRequest.
	 * 
	 * @param systemKey
	 */
	public void setSystemKey(java.lang.String systemKey) {
		this.systemKey = systemKey;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof IssuerBySystemRequest))
			return false;
		IssuerBySystemRequest other = (IssuerBySystemRequest) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj) && ((this.systemKey == null && other.getSystemKey() == null)
				|| (this.systemKey != null && this.systemKey.equals(other.getSystemKey())));
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
		if (getSystemKey() != null) {
			_hashCode += getSystemKey().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			IssuerBySystemRequest.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "issuerBySystemRequest"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("systemKey");
		elemField.setXmlName(new javax.xml.namespace.QName("", "systemKey"));
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
