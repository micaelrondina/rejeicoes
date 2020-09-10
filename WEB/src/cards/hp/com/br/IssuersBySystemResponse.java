/**
 * IssuersBySystemResponse.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class IssuersBySystemResponse extends cards.hp.com.br.BaseResponse implements java.io.Serializable {
	private cards.hp.com.br.IssuerBO[] issuerList;

	public IssuersBySystemResponse() {
	}

	public IssuersBySystemResponse(cards.hp.com.br.MetadataResponse metadataResponse,
			cards.hp.com.br.IssuerBO[] issuerList) {
		super(metadataResponse);
		this.issuerList = issuerList;
	}

	/**
	 * Gets the issuerList value for this IssuersBySystemResponse.
	 * 
	 * @return issuerList
	 */
	public cards.hp.com.br.IssuerBO[] getIssuerList() {
		return issuerList;
	}

	/**
	 * Sets the issuerList value for this IssuersBySystemResponse.
	 * 
	 * @param issuerList
	 */
	public void setIssuerList(cards.hp.com.br.IssuerBO[] issuerList) {
		this.issuerList = issuerList;
	}

	public cards.hp.com.br.IssuerBO getIssuerList(int i) {
		return this.issuerList[i];
	}

	public void setIssuerList(int i, cards.hp.com.br.IssuerBO _value) {
		this.issuerList[i] = _value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof IssuersBySystemResponse))
			return false;
		IssuersBySystemResponse other = (IssuersBySystemResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj) && ((this.issuerList == null && other.getIssuerList() == null)
				|| (this.issuerList != null && java.util.Arrays.equals(this.issuerList, other.getIssuerList())));
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
		if (getIssuerList() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getIssuerList()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getIssuerList(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			IssuersBySystemResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "issuersBySystemResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("issuerList");
		elemField.setXmlName(new javax.xml.namespace.QName("", "issuerList"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "issuerBO"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
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
