package models;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItemTe40;
import junit.framework.TestCase;

public class DxcIncomingEloItemTe40TestCase extends TestCase {

	//Arquivo: 0075140C162620190517064549.txt-20190517
	
	DxcIncomingEloItemTe40 dxcIncomingEloItemTe40 = new DxcIncomingEloItemTe40();
	
	String linha00 = "40005067410044351658       200514082460380003193550000514020180903                         PORTO VELHOBR 541100000003083798620180903Y10208230000000000CR00700104        ";
	br.com.dxc.elo_import_incoming.layout.te40.Registro00 registro00 = new br.com.dxc.elo_import_incoming.layout.te40.Registro00(linha00, dxcIncomingEloItemTe40);
	
	String linha02 = "4002104053974730    00000005625726001GW0157C4        N1141742055C      000000000 58603636000LONDRINA   PR201905158FRANCIELLE M S CUNHA                                  ";
	br.com.dxc.elo_import_incoming.layout.te40.Registro02 registro02 = new br.com.dxc.elo_import_incoming.layout.te40.Registro02(linha02, dxcIncomingEloItemTe40);
	
	public DxcIncomingEloItemTe40TestCase() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		registro00.preencherDxcIncomingElo();
		registro02.preencherDxcIncomingElo();
	}

	//inicio Registro "00"
	@Test
	public void testGetCdTransacao00() {
		assertEquals("40", dxcIncomingEloItemTe40.getCdTransacao00());
	}

	@Test
	public void testGetSubCdTransacao00() {
		assertEquals("00", dxcIncomingEloItemTe40.getSubCdTransacao00());
	}

	@Test
	public void testGetNrCartao() {
		assertEquals("506741******1658", dxcIncomingEloItemTe40.getNrCartao().trim());
	}

	@Test
	public void testGetComplementoNrCartao() {
		assertEquals("       ", dxcIncomingEloItemTe40.getComplementoNrCartao());
	}

	@Test
	public void testGetNrRefTransacao() {
		assertEquals("20051408246038000319355", dxcIncomingEloItemTe40.getNrRefTransacao());
	}

	@Test
	public void testGetCdCredenciador() {
		assertEquals("5140", dxcIncomingEloItemTe40.getCdCredenciador());
	}

	@Test
	public void testGetDtVendaSaque() {
		assertEquals("20180903", dxcIncomingEloItemTe40.getDtVendaSaque());
	}
	
	@Test
	public void testGetCidadePontoVenda() {
		assertEquals("PORTO VELHO", dxcIncomingEloItemTe40.getCidadePontoVenda());
	}

	@Test
	public void testGetCdPaisPontoVenda() {
		assertEquals("BR ", dxcIncomingEloItemTe40.getCdPaisPontoVenda());
	}

	@Test
	public void testGetMccPontoVenda() {
		assertEquals("5411", dxcIncomingEloItemTe40.getMccPontoVenda());
	}

	@Test
	public void testGetVlFraude() {
		assertEquals(308.37, dxcIncomingEloItemTe40.getVlFraude());
	}

	@Test
	public void testGetCdMoedaTransFraudulenta() {
		assertEquals("986", dxcIncomingEloItemTe40.getCdMoedaTransFraudulenta());
	}

	@Test
	public void testGetDtNotificacaoFraude() {
		assertEquals("20180903", dxcIncomingEloItemTe40.getDtNotificacaoFraude());
	}

	@Test
	public void testGetIndicadorOrigemAuth() {
		assertEquals("Y", dxcIncomingEloItemTe40.getIndicadorOrigemAuth());
	}

	@Test
	public void testGetCdNotificacao() {
		assertEquals("1", dxcIncomingEloItemTe40.getCdNotificacao());
	}

	@Test
	public void testGetTpFraude() {
		assertEquals("02", dxcIncomingEloItemTe40.getTpFraude());
	}

	@Test
	public void testGetDtVencCartao() {
		assertEquals("0823", dxcIncomingEloItemTe40.getDtVencCartao());
	}

	@Test
	public void testGetTpPlataforma() {
		assertEquals("CR", dxcIncomingEloItemTe40.getTpPlataforma());
	}

	@Test
	public void testGetCdBandeira() {
		assertEquals("007", dxcIncomingEloItemTe40.getCdBandeira());
	}

	@Test
	public void testGetBancoEmissor() {
		assertEquals("0104", dxcIncomingEloItemTe40.getBancoEmissor());
	}
	//fim Registro "00"
	
	
	//inicio Registro "02"
	@Test
	public void testGetCdTransacao02() {
		assertEquals("40", dxcIncomingEloItemTe40.getCdTransacao02());
	}

	@Test
	public void testGetSubCdTransacao02() {
		assertEquals("02", dxcIncomingEloItemTe40.getSubCdTransacao02());
	}

	@Test
	public void testGetIdTransacao() {
		assertEquals("104053974730   ", dxcIncomingEloItemTe40.getIdTransacao());
	}

	@Test
	public void testGetPontoVenda() {
		assertEquals("000005625726001", dxcIncomingEloItemTe40.getPontoVenda());
	}

	@Test
	public void testGetNrLogicoEquipamento() {
		assertEquals("GW0157C4", dxcIncomingEloItemTe40.getNrLogicoEquipamento());
	}

	@Test
	public void testGetIndicadorTransTroca() {
		assertEquals("N", dxcIncomingEloItemTe40.getIndicadorTransTroca());
	}

	@Test
	public void testGetCdAuthTransacao() {
		assertEquals("114174", dxcIncomingEloItemTe40.getCdAuthTransacao());
	}

	@Test
	public void testGetMeioIdentPortador() {
		assertEquals("2", dxcIncomingEloItemTe40.getMeioIdentPortador());
	}

	@Test
	public void testGetModoEntTransacaoPos() {
		assertEquals("05", dxcIncomingEloItemTe40.getModoEntTransacaoPos());
	}

	@Test
	public void testGetIdentTecnologTerminal() {
		assertEquals("5", dxcIncomingEloItemTe40.getIdentTecnologTerminal());
	}
	
	@Test
	public void testGetTecnologiaCartao() {
		assertEquals("C", dxcIncomingEloItemTe40.getTecnologiaCartao());
	}

	@Test
	public void testGetVlTroco() {
		assertEquals(0.0, dxcIncomingEloItemTe40.getVlTroco());
	}

	@Test
	public void testGetIndicTransFeitaPor() {
		assertEquals("5", dxcIncomingEloItemTe40.getIndicTransFeitaPor());
	}

	@Test
	public void testGetCepPortador() {
		assertEquals("8603636000", dxcIncomingEloItemTe40.getCepPortador());
	}

	@Test
	public void testGetCidadePortador() {
		assertEquals("LONDRINA   ", dxcIncomingEloItemTe40.getCidadePortador());
	}

	@Test
	public void testGetUfPortador() {
		assertEquals("PR", dxcIncomingEloItemTe40.getUfPortador());
	}

	@Test
	public void testGetDtConfirmFraude() {
		assertEquals("20190515", dxcIncomingEloItemTe40.getDtConfirmFraude());
	}

	@Test
	public void testGetIndicadorLiq() {
		assertEquals("8", dxcIncomingEloItemTe40.getIndicadorLiq());
	}

	@Test
	public void testGetNmPortador() {
		assertEquals("FRANCIELLE M S CUNHA          ", dxcIncomingEloItemTe40.getNmPortador());
	}

	@Test
	public void testGetTokenPan() {
		assertEquals("                   ", dxcIncomingEloItemTe40.getTokenPan());
	}
	//fim Registro "02"
}
