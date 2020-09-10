package br.com.dxc.elo_import_incoming.enums;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;

public enum TpRegistroTransacaoEnum {
	HEADER(new String[] {"B0"}),
	TRANSACAO_PADRAO(new String[] {"01", "05", "06", "15", "16", "25", "26", "35", "36", "46", "55"}),
	TRANSACAO_TE40(new String[] {"40"}),
	TRANSACAO_TE10_TE20(new String[] {"10", "20"}),
	TRANSACAO_TE44(new String[] {"44"}),
	TRAILER(new String[] {"BZ"});
	
	private String[] siglas;
	
	private TpRegistroTransacaoEnum(String[] siglas) {
		this.siglas = siglas;
	}

	public String[] getSiglas() {
		return siglas;
	}
	
	public static TpRegistroTransacaoEnum getBySigla(String sigla) throws InvalidValueException {
		for (int i = 0; i < TpRegistroTransacaoEnum.values().length; i++) {
			for (int j = 0; j < TpRegistroTransacaoEnum.values()[i].getSiglas().length; j++) {
				if (TpRegistroTransacaoEnum.values()[i].getSiglas()[j].equals(sigla)) {
					return TpRegistroTransacaoEnum.values()[i];
				}
			}
		}
		throw new InvalidValueException("TpRegistroTransacaoEnum.getBySigla: Nenhum item encontrado com a sigla: \"" + sigla + "\"");
	}
}
