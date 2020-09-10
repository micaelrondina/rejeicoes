package br.com.dxc.elo_import_incoming.model;

import org.apache.commons.lang3.StringUtils;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.utils.Constantes;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class ArquivoTransEnvioPayware {
	private Long idItem;
	private String recordType;
	private String arn;
	private String cuotasVan;
	private String usageCode;
	
	public ArquivoTransEnvioPayware() {	}
	
	public ArquivoTransEnvioPayware(Long idItem, String arn, String cuotasVan, String usageCode) {
		this.idItem = idItem;
		this.recordType = Constantes.DETAIL_RECORD_RECORD_TYPE_VALUE_DEFAULT; //valor default
		this.arn = arn;
		this.cuotasVan = cuotasVan;
		this.usageCode = usageCode;
	}
	
	public String montarHeader() throws InvalidValueException {
		StringBuilder sb = new StringBuilder();
		sb.append(Constantes.HEADER_RECORD_TYPE);
		sb.append(StringUtils.leftPad("0", 9, "0"));
		sb.append(Utils.getDataString());
		sb.append(Utils.getTimeString());
		
		if (sb.toString().length() != Constantes.TAM_LINHA_HEADER_ARQ_DXC_PAYWARE) {
			throw new InvalidValueException("Tamanho a linha HEADER do Arquivo Enviado ao Payware diferente do esperado!");
		}
		return sb.toString();
	}
	
	public String montarTrailer(Integer recordsQuantity) throws InvalidValueException {
		if (recordsQuantity == null) {
			recordsQuantity = 0;
		}
		recordsQuantity += 2; //contar Header e Trailer
		StringBuilder sb = new StringBuilder();
		sb.append(Constantes.TRAILER_RECORD_TYPE);
		sb.append(StringUtils.leftPad(String.valueOf(recordsQuantity-1), 9, "0"));
		sb.append(Utils.getDataString());
		sb.append(Utils.getTimeString());
		sb.append(StringUtils.leftPad(String.valueOf(recordsQuantity), 9, "0"));

		if (sb.toString().length() != Constantes.TAM_LINHA_TRAILER_ARQ_DXC_PAYWARE) {
			throw new InvalidValueException("Tamanho a linha TRAILER do Arquivo Enviado ao Payware diferente do esperado!");
		}
		return sb.toString();
	}
	
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	
	public String getArn() {
		return arn;
	}
	public void setArn(String arn) throws InvalidValueException {
		if (arn.length() > 23) {
			throw new InvalidValueException("Campo ARN maior que o esperado!");
		}
		this.arn = StringUtils.rightPad(arn, 23, " ");
	}
	
	public String getCuotasVan() {
		return cuotasVan;
	}
	public void setCuotasVan(String cuotasVan) throws InvalidValueException {
		if (cuotasVan.length() > 3) {
			throw new InvalidValueException("Campo CUOTAS_VAN maior que o esperado!");
		}
		this.cuotasVan = StringUtils.leftPad(cuotasVan, 3, "0");
	}
	
	public String getUsageCode() {
		return usageCode;
	}
	public void setUsageCode(String usageCode) throws InvalidValueException {
		if (usageCode.length() > 4) {
			throw new InvalidValueException("Campo USAGE_CODE maior que o esperado!");
		}
		this.usageCode = StringUtils.leftPad(usageCode, 4, "0");
	}

	public void setRecordTypeDefaultValue() {
		this.recordType = Constantes.DETAIL_RECORD_RECORD_TYPE_VALUE_DEFAULT;
	}

	public String getRecordType() {
		return recordType;
	}
}
