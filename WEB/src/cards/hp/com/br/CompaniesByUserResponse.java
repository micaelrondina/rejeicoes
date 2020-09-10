/**
 * CompaniesByUserResponse.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class CompaniesByUserResponse extends cards.hp.com.br.BaseResponse implements java.io.Serializable {
	private cards.hp.com.br.Company[] companies;

	public CompaniesByUserResponse() {
	}

	public CompaniesByUserResponse(cards.hp.com.br.MetadataResponse metadataResponse,
			cards.hp.com.br.Company[] companies) {
		super(metadataResponse);
		this.companies = companies;
	}

	/**
	 * Gets the companies value for this CompaniesByUserResponse.
	 * 
	 * @return companies
	 */
	public cards.hp.com.br.Company[] getCompanies() {
		return companies;
	}

	/**
	 * Sets the companies value for this CompaniesByUserResponse.
	 * 
	 * @param companies
	 */
	public void setCompanies(cards.hp.com.br.Company[] companies) {
		this.companies = companies;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof CompaniesByUserResponse))
			return false;
		CompaniesByUserResponse other = (CompaniesByUserResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj) && ((this.companies == null && other.getCompanies() == null)
				|| (this.companies != null && java.util.Arrays.equals(this.companies, other.getCompanies())));
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
		if (getCompanies() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getCompanies()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getCompanies(), i);
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
			CompaniesByUserResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "companiesByUserResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("companies");
		elemField.setXmlName(new javax.xml.namespace.QName("", "companies"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "company"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(new javax.xml.namespace.QName("", "company"));
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
