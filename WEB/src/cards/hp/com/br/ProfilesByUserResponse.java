/**
 * ProfilesByUserResponse.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

@SuppressWarnings("all")
public class ProfilesByUserResponse extends cards.hp.com.br.BaseResponse implements java.io.Serializable {
	private cards.hp.com.br.UserProfile[] userProfiles;

	public ProfilesByUserResponse() {
	}

	public ProfilesByUserResponse(cards.hp.com.br.MetadataResponse metadataResponse,
			cards.hp.com.br.UserProfile[] userProfiles) {
		super(metadataResponse);
		this.userProfiles = userProfiles;
	}

	/**
	 * Gets the userProfiles value for this ProfilesByUserResponse.
	 * 
	 * @return userProfiles
	 */
	public cards.hp.com.br.UserProfile[] getUserProfiles() {
		return userProfiles;
	}

	/**
	 * Sets the userProfiles value for this ProfilesByUserResponse.
	 * 
	 * @param userProfiles
	 */
	public void setUserProfiles(cards.hp.com.br.UserProfile[] userProfiles) {
		this.userProfiles = userProfiles;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ProfilesByUserResponse))
			return false;
		ProfilesByUserResponse other = (ProfilesByUserResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj) && ((this.userProfiles == null && other.getUserProfiles() == null)
				|| (this.userProfiles != null && java.util.Arrays.equals(this.userProfiles, other.getUserProfiles())));
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
		if (getUserProfiles() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getUserProfiles()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getUserProfiles(), i);
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
			ProfilesByUserResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "profilesByUserResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("userProfiles");
		elemField.setXmlName(new javax.xml.namespace.QName("", "userProfiles"));
		elemField.setXmlType(new javax.xml.namespace.QName("br.com.hp.cards", "userProfile"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(new javax.xml.namespace.QName("", "userProfile"));
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
