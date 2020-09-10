/**
 * BaseResponse.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public abstract class BaseResponse implements java.io.Serializable {
	private cards.hp.com.br.MetadataResponse metadataResponse;

	public BaseResponse() {
	}

	public BaseResponse(cards.hp.com.br.MetadataResponse metadataResponse) {
		this.metadataResponse = metadataResponse;
	}

	/**
	 * Gets the metadataResponse value for this BaseResponse.
	 * 
	 * @return metadataResponse
	 */
	public cards.hp.com.br.MetadataResponse getMetadataResponse() {
		return metadataResponse;
	}

	/**
	 * Sets the metadataResponse value for this BaseResponse.
	 * 
	 * @param metadataResponse
	 */
	public void setMetadataResponse(cards.hp.com.br.MetadataResponse metadataResponse) {
		this.metadataResponse = metadataResponse;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof BaseResponse))
			return false;
		BaseResponse other = (BaseResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.metadataResponse == null && other.getMetadataResponse() == null)
				|| (this.metadataResponse != null && this.metadataResponse.equals(other.getMetadataResponse())));//[fortify] Acusado como: Dead Code: Expression is Always true, Porem temporariamente sem nenhuma melhoria para o mesmo
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
		if (getMetadataResponse() != null) {
			_hashCode += getMetadataResponse().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			BaseResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "baseResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("metadataResponse");
		elemField.setXmlName(new javax.xml.namespace.QName("", "MetadataResponse"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "metadataResponse"));
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
