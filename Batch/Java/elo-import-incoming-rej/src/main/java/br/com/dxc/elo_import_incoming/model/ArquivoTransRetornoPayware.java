package br.com.dxc.elo_import_incoming.model;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.utils.Constantes;

public class ArquivoTransRetornoPayware extends ArquivoTransEnvioPayware {
	private String returnCode;
	private String returnCodeDescription;
	
	public ArquivoTransRetornoPayware(){}
	
	public ArquivoTransRetornoPayware(String linha) throws InvalidValueException {
		if (linha.length() != Constantes.TAM_LINHA_DETAIL_ARQ_PAYWARE_DXC) {
			throw new InvalidValueException("Arquivo de Retorno do Payware - Tamanho da Linha do Arquivo maior de " + Constantes.TAM_LINHA_DETAIL_ARQ_PAYWARE_DXC + ". "
					+ "\nLinha: " + linha);
		}
		
		try {
			super.setArn(linha.substring(2,25));
			super.setCuotasVan(linha.substring(25,28));
			super.setUsageCode(linha.substring(28,32));
			this.setReturnCode(linha.substring(32,36)); //[fortify] Aplicacao roda em intranet - ambiente controlado
			this.setReturnCodeDescription(linha.substring(36, 86)); //[fortify] Aplicacao roda em intranet - ambiente controlado

		} catch (InvalidValueException e) {
			throw new InvalidValueException("Arquivo de Retorno do Payware - Erro Preencher Objeto com os Dados do Arquivo de Retorno do Payware!", e);
		}

	}

	public String getReturnCodeDescription() {
		return returnCodeDescription;
	}

	public void setReturnCodeDescription(String returnCodeDescription) throws InvalidValueException {
		if (returnCodeDescription.length() > 50) {
			throw new InvalidValueException("Arquivo de Retorno do Payware - Campo RETURN CODE DESCRIPTION maior que o esperado!");
		}
		this.returnCodeDescription = returnCodeDescription.trim();
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) throws InvalidValueException {
		if (returnCode.length() > 4) {
			throw new InvalidValueException("Arquivo de Retorno do Payware - Campo RETURN CODE maior que o esperado!");
		}
		this.returnCode = returnCode;
	}
	
	@Override
	public String toString() {
		return "[ARN: " + getArn() + ", CuotasVan: " + getCuotasVan() + ", UsageCode: " + getUsageCode() + ", ReturnCode:" + returnCode + ", ReturnCodeDescription:" + returnCodeDescription + "]";
	}
}
