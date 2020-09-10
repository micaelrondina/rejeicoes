/**
 * MetadataResponse.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class MetadataResponse implements java.io.Serializable {
	private java.util.Calendar responseDate;

	private long totalRecords;

	private cards.hp.com.br.ResponseMessage responseMessage;

	public MetadataResponse() {
	}

	public MetadataResponse(java.util.Calendar responseDate, long totalRecords,
			cards.hp.com.br.ResponseMessage responseMessage) {
		this.responseDate = responseDate;
		this.totalRecords = totalRecords;
		this.responseMessage = responseMessage;
	}

	/**
	 * Gets the responseDate value for this MetadataResponse.
	 * 
	 * @return responseDate
	 */
	public java.util.Calendar getResponseDate() {
		return responseDate;
	}

	/**
	 * Sets the responseDate value for this MetadataResponse.
	 * 
	 * @param responseDate
	 */
	public void setResponseDate(java.util.Calendar responseDate) {
		this.responseDate = responseDate;
	}

	/**
	 * Gets the totalRecords value for this MetadataResponse.
	 * 
	 * @return totalRecords
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * Sets the totalRecords value for this MetadataResponse.
	 * 
	 * @param totalRecords
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * Gets the responseMessage value for this MetadataResponse.
	 * 
	 * @return responseMessage
	 */
	public cards.hp.com.br.ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	/**
	 * Sets the responseMessage value for this MetadataResponse.
	 * 
	 * @param responseMessage
	 */
	public void setResponseMessage(cards.hp.com.br.ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof MetadataResponse))
			return false;
		MetadataResponse other = (MetadataResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.responseDate == null && other.getResponseDate() == null)
						|| (this.responseDate != null && this.responseDate.equals(other.getResponseDate())))
				&& this.totalRecords == other.getTotalRecords()
				&& ((this.responseMessage == null && other.getResponseMessage() == null)
						|| (this.responseMessage != null && this.responseMessage.equals(other.getResponseMessage())));//[fortify] Acusado como: Dead Code: Expression is Always true, Porem temporariamente sem nenhuma melhoria para o mesmo
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
		if (getResponseDate() != null) {
			_hashCode += getResponseDate().hashCode();
		}
		_hashCode += new Long(getTotalRecords()).hashCode();
		if (getResponseMessage() != null) {
			_hashCode += getResponseMessage().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			MetadataResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "metadataResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseDate");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseDate"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("totalRecords");
		elemField.setXmlName(new javax.xml.namespace.QName("", "totalRecords"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseMessage");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseMessage"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "responseMessage"));
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
