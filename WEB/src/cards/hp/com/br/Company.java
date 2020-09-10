/**
 * Company.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class Company implements java.io.Serializable {
	private java.lang.Integer id;

	private java.lang.String descripcion;

	private cards.hp.com.br.EntityType entityType;

	private cards.hp.com.br.SubCompany[] subCompanies;

	public Company() {
	}

	public Company(java.lang.Integer id, java.lang.String descripcion, cards.hp.com.br.EntityType entityType,
			cards.hp.com.br.SubCompany[] subCompanies) {
		this.id = id;
		this.descripcion = descripcion;
		this.entityType = entityType;
		this.subCompanies = subCompanies;
	}

	/**
	 * Gets the id value for this Company.
	 * 
	 * @return id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * Sets the id value for this Company.
	 * 
	 * @param id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * Gets the descripcion value for this Company.
	 * 
	 * @return descripcion
	 */
	public java.lang.String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion value for this Company.
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the entityType value for this Company.
	 * 
	 * @return entityType
	 */
	public cards.hp.com.br.EntityType getEntityType() {
		return entityType;
	}

	/**
	 * Sets the entityType value for this Company.
	 * 
	 * @param entityType
	 */
	public void setEntityType(cards.hp.com.br.EntityType entityType) {
		this.entityType = entityType;
	}

	/**
	 * Gets the subCompanies value for this Company.
	 * 
	 * @return subCompanies
	 */
	public cards.hp.com.br.SubCompany[] getSubCompanies() {
		return subCompanies;
	}

	/**
	 * Sets the subCompanies value for this Company.
	 * 
	 * @param subCompanies
	 */
	public void setSubCompanies(cards.hp.com.br.SubCompany[] subCompanies) {
		this.subCompanies = subCompanies;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;
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
				&& ((this.id == null && other.getId() == null) || (this.id != null && this.id.equals(other.getId())))
				&& ((this.descripcion == null && other.getDescripcion() == null)
						|| (this.descripcion != null && this.descripcion.equals(other.getDescripcion())))
				&& ((this.entityType == null && other.getEntityType() == null)
						|| (this.entityType != null && this.entityType.equals(other.getEntityType())))
				&& ((this.subCompanies == null && other.getSubCompanies() == null) || (this.subCompanies != null
						&& java.util.Arrays.equals(this.subCompanies, other.getSubCompanies())));//[fortify] Acusado como: Dead Code: Expression is Always true, Porem temporariamente sem nenhuma melhoria para o mesmo
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
		if (getId() != null) {
			_hashCode += getId().hashCode();
		}
		if (getDescripcion() != null) {
			_hashCode += getDescripcion().hashCode();
		}
		if (getEntityType() != null) {
			_hashCode += getEntityType().hashCode();
		}
		if (getSubCompanies() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getSubCompanies()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getSubCompanies(), i);
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
			Company.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "company"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("id");
		elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcion");
		elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("entityType");
		elemField.setXmlName(new javax.xml.namespace.QName("", "entityType"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "entityType"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("subCompanies");
		elemField.setXmlName(new javax.xml.namespace.QName("", "subCompanies"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "subCompany"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(new javax.xml.namespace.QName("", "subCompany"));
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
