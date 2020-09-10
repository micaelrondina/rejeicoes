package br.com.dxc.elo_import_incoming.layout;

public class RegistroHeader extends LayoutBase {
	
	public RegistroHeader(String linha, Object objetoPopular) {
		super(linha, objetoPopular);
	}
	
	/** Para o Header do arquivo o conteúdo deve ser "B0". */
	public String getCdRegistroB0() {
		return linha.substring(0, 2);
	}
	
	public String getCdServicoB0() {
		return linha.substring(2, 4);
	}
	
	public String getDtRemessa() {
		return linha.substring(4, 12);
	}
	
	public String getNrRemessaB0() {
		return linha.substring(12, 16);
	}
	
	public String getNrJanela() {
		return linha.substring(16, 17);
	}
	
	public String getDtEnvio() {
		return linha.substring(20, 28);
	}
	
	public String getHrEnvioArq() {
		return linha.substring(28, 34);
	}
	
	public String getDtRetornoArq() {
		return linha.substring(34, 42);
	}
	
	public String getHrRetornoArq() {
		return linha.substring(42, 48);
	}
	
	public String getBancoEmissorB0() {
		return linha.substring(48, 52);
	}
	
	public String getCdProcessadora() {
		return linha.substring(52, 56);
	}
	
	public String getVersaoArq() {
		return linha.substring(157, 160);
	}
	
	public String getCdCredenciador() {
		return linha.substring(160, 164);
	}
	
	public String getCdBandeiraB0() {
		return linha.substring(164, 167);
	}
	
	public String getIndicadorRotaArqB0() {
		return linha.substring(167, 168);
	}
}
