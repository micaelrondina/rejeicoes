package br.com.dxc.elo_import_incoming.enums;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;

public enum SubCdTransacaoEnum {
	REGISTRO_00("00"),
	REGISTRO_01("01"),
	REGISTRO_02("02"),
	REGISTRO_03("03"),
	REGISTRO_05("05"),
	REGISTRO_07("07"),
	REGISTRO_08("08"),
	REGISTRO_09("09");
	
	private String codigo;
	
	SubCdTransacaoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public static SubCdTransacaoEnum getByCodigo(String codigo) throws InvalidValueException {
		for (int i = 0; i < SubCdTransacaoEnum.values().length; i++) {
			if (SubCdTransacaoEnum.values()[i].getCodigo().equals(codigo)) {
				return SubCdTransacaoEnum.values()[i];
			}
		}
		throw new InvalidValueException("SubCdTransacaoPadraEnum.getByCodigo: Nenhum item encontrado com o codigo: \"" + codigo + "\"");
	}
}
