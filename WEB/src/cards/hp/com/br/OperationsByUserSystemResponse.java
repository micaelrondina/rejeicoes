/**
 * OperationsByUserSystemResponse.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class OperationsByUserSystemResponse extends cards.hp.com.br.BaseResponse implements java.io.Serializable {
	private cards.hp.com.br.Operation[] operations;

	public OperationsByUserSystemResponse() {
	}

	public OperationsByUserSystemResponse(cards.hp.com.br.MetadataResponse metadataResponse,
			cards.hp.com.br.Operation[] operations) {
		super(metadataResponse);
		this.operations = operations;
	}

	/**
	 * Gets the operations value for this OperationsByUserSystemResponse.
	 * 
	 * @return operations
	 */
	public cards.hp.com.br.Operation[] getOperations() {
		return operations;
	}

	/**
	 * Sets the operations value for this OperationsByUserSystemResponse.
	 * 
	 * @param operations
	 */
	public void setOperations(cards.hp.com.br.Operation[] operations) {
		this.operations = operations;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof OperationsByUserSystemResponse))
			return false;
		OperationsByUserSystemResponse other = (OperationsByUserSystemResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj) && ((this.operations == null && other.getOperations() == null)
				|| (this.operations != null && java.util.Arrays.equals(this.operations, other.getOperations())));
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
		if (getOperations() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getOperations()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getOperations(), i);
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
			OperationsByUserSystemResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "operationsByUserSystemResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("operations");
		elemField.setXmlName(new javax.xml.namespace.QName("", "operations"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "operation"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(new javax.xml.namespace.QName("", "operation"));
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
