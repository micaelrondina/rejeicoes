/**
 * ParameterService.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package cards.hp.com.br;

public interface ParameterService extends java.rmi.Remote {
	public cards.hp.com.br.IssuersBySystemResponse getIssuersBySystem(cards.hp.com.br.IssuerBySystemRequest request)
			throws java.rmi.RemoteException;

	public cards.hp.com.br.CompaniesByUserResponse getCompaniesByUser(cards.hp.com.br.CompaniesByUserRequest request)
			throws java.rmi.RemoteException;

	public cards.hp.com.br.ProfilesByUserResponse getProfilesByUser(cards.hp.com.br.ProfilesByUserRequest request)
			throws java.rmi.RemoteException;

	public cards.hp.com.br.OperationsByUserSystemResponse getOperationsByUserSystem(
			cards.hp.com.br.OperationsByUserSystemRequest request) throws java.rmi.RemoteException;
}
