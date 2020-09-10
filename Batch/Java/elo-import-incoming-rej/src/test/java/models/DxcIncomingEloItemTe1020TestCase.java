package models;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import br.com.dxc.elo_import_incoming.layout.te10_20.Registro00;
import br.com.dxc.elo_import_incoming.layout.te10_20.Registro02;
import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItemTe1020;
import junit.framework.TestCase;

public class DxcIncomingEloItemTe1020TestCase extends TestCase {

	//Arquivo: 0075140C169420190605065334.txt-20190605
	
	DxcIncomingEloItemTe1020 dxcIncomingEloItemTe10 = new DxcIncomingEloItemTe1020(); 
	
	String linha1000 = "1000514002040010BR 201906035090006811494100   00000000000098600000000080098620051408265228400155279 PRE SEM RESPOSTA PRAZO EXPIRADO               80000000000000009154  ";
	Registro00 registro1000 = new Registro00(linha1000, dxcIncomingEloItemTe10);
	
	String linha1002 = "1002            BR    00220190603                      20190606                                                                                                         ";
	Registro02 registro1002 = new Registro02(linha1002, dxcIncomingEloItemTe10);
	
	//faltou TE20_00 e TE20_02
	
	public DxcIncomingEloItemTe1020TestCase() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		registro1000.preencherDxcIncomingElo();
		registro1002.preencherDxcIncomingElo();
	}
	
	//inicio Registro "00"
	@Test
	public void testGetCdTransacao00() {
		assertEquals("10", dxcIncomingEloItemTe10.getCdTransacao00());
	}

	@Test
	public void testGetSubCdTransacao00() {
		assertEquals("00", dxcIncomingEloItemTe10.getSubCdTransacao00());
	}

	@Test
	public void testGetCdDestino() {
		assertEquals("5140", dxcIncomingEloItemTe10.getCdDestino());
	}

	@Test
	public void testGetCdOrigem() {
		assertEquals("0204", dxcIncomingEloItemTe10.getCdOrigem());
	}

	@Test
	public void testGetCdMotivoTransacao() {
		assertEquals("0010", dxcIncomingEloItemTe10.getCdMotivoTransacao());
	}

	@Test
	public void testGetCdPais00() {
		assertEquals("BR ", dxcIncomingEloItemTe10.getCdPais00());
	}

	@Test
	public void testGetDtEnvio() {
		assertEquals("20190603", dxcIncomingEloItemTe10.getDtEnvio());
	}

	@Test
	public void testGetNrCartao() {
		assertEquals("509000******4100", dxcIncomingEloItemTe10.getNrCartao().trim());
	}
	@Test
	public void testGetVlDestino() {
		assertEquals(0.0, dxcIncomingEloItemTe10.getVlDestino());
	}

	@Test
	public void testGetCdMoedaDestino() {
		assertEquals("986", dxcIncomingEloItemTe10.getCdMoedaDestino());
	}
	
	@Test
	public void testGetVlOrigem() {
		assertEquals(8.00, dxcIncomingEloItemTe10.getVlOrigem());
	}

	@Test
	public void testGetCdMoedaOrigem() {
		assertEquals("986", dxcIncomingEloItemTe10.getCdMoedaOrigem());
	}

	@Test
	public void testGetMsgTexto() {
		assertEquals("20051408265228400155279 PRE SEM RESPOSTA PRAZO EXPIRADO               ", dxcIncomingEloItemTe10.getMsgTexto());
	}

	@Test
	public void testGetIndicadorLiq() {
		assertEquals("8", dxcIncomingEloItemTe10.getIndicadorLiq());
	}

	@Test
	public void testGetIdTransacaoOriginal() {
		assertEquals("000000000000000", dxcIncomingEloItemTe10.getIdTransacaoOriginal());
	}

	@Test
	public void testGetDtProcessamento() {
		assertEquals("9154", dxcIncomingEloItemTe10.getDtProcessamento());
	}
	//fim Registro "00"

	
	//inicio Registro "02"
	@Test
	public void testGetCdTransacao02() {
		assertEquals("10", dxcIncomingEloItemTe10.getCdTransacao02());
	}
	
	@Test
	public void testGetSubCdTransacao02() {
		assertEquals("02", dxcIncomingEloItemTe10.getSubCdTransacao02());
	}

	@Test
	public void testGetCdPais02() {
		assertEquals("BR ", dxcIncomingEloItemTe10.getCdPais02());
	}

	@Test
	public void testGetQtDiasLiqFinanTransacao() {
		assertEquals("002", dxcIncomingEloItemTe10.getQtDiasLiqFinanTransacao());
	}

	@Test
	public void testGetDtProcessamentoRegTp02() {
		assertEquals("20190603", dxcIncomingEloItemTe10.getDtProcessamentoRegTp02());
	}

	@Test
	public void testGetCdErro() {
		assertEquals("   ", dxcIncomingEloItemTe10.getCdErro());
	}

	@Test
	public void testGetTokenPan() {
		assertEquals("                   ", dxcIncomingEloItemTe10.getTokenPan());
	}

	@Test
	public void testGetDtLiqTransacao() {
		assertEquals("20190606", dxcIncomingEloItemTe10.getDtLiqTransacao());
	}
	//fim Registro "02"
}
