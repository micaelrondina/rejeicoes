/**
 * ResponseMessage.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class ResponseMessage implements java.io.Serializable {
	private int responseCode;

	private java.lang.String responseDescription;

	public ResponseMessage() {
	}

	public ResponseMessage(int responseCode, java.lang.String responseDescription) {
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	/**
	 * Gets the responseCode value for this ResponseMessage.
	 * 
	 * @return responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the responseCode value for this ResponseMessage.
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the responseDescription value for this ResponseMessage.
	 * 
	 * @return responseDescription
	 */
	public java.lang.String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * Sets the responseDescription value for this ResponseMessage.
	 * 
	 * @param responseDescription
	 */
	public void setResponseDescription(java.lang.String responseDescription) {
		this.responseDescription = responseDescription;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ResponseMessage))
			return false;
		ResponseMessage other = (ResponseMessage) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && this.responseCode == other.getResponseCode()
				&& ((this.responseDescription == null && other.getResponseDescription() == null)
						|| (this.responseDescription != null
								&& this.responseDescription.equals(other.getResponseDescription())));//[fortify] Acusado como: Dead Code: Expression is Always true, Porem temporariamente sem nenhuma melhoria para o mesmo
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		_hashCode += getResponseCode();
		if (getResponseDescription() != null) {
			_hashCode += getResponseDescription().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			ResponseMessage.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "responseMessage"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseDescription");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseDescription"));
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
