package models;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import br.com.dxc.elo_import_incoming.layout.RegistroHeader;
import br.com.dxc.elo_import_incoming.layout.RegistroTrailer;
import br.com.dxc.elo_import_incoming.layout.RegistroTransacaoTe44;
import br.com.dxc.elo_import_incoming.model.DxcIncomingElo;
import junit.framework.TestCase;

public class DxcIncomingEloTest extends TestCase {
	
	String nmArquivo = "0075140C162920190515033808.txt-20190515";
	
	DxcIncomingElo dxcIncomingElo = new DxcIncomingElo();
	String linhaHeader = "B0102019051416295   201905150023002019051503380900000000                                                                                                    019151400072";
	RegistroHeader registroHeader = new RegistroHeader(linhaHeader, dxcIncomingElo);

	String linhaTrailer = "BZ101629000000040000000000231410000075100000000548552000000000000000000000000000000000000000000000000000374600000000000000000000000                                    2";
	RegistroTrailer registroTrailer = new RegistroTrailer(linhaTrailer, dxcIncomingElo);
	
	String linha44 = "440000000    2019051400162920190515A00986P000000000003746000000000000745000000005454181000000100000000000544800070000000000000000000000000000000000000000000000         ";
	RegistroTransacaoTe44 registroTransacaoTe44 = new RegistroTransacaoTe44(linha44, dxcIncomingElo);

	public DxcIncomingEloTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		registroHeader.preencherDxcIncomingElo();
		registroTrailer.preencherDxcIncomingElo();
		registroTransacaoTe44.preencherDxcIncomingElo();
		dxcIncomingElo.setNmArquivo(nmArquivo);
	}

	//inicio registro HEADER
	@Test
	public void testGetCdRegistroB0() {
		assertEquals("B0", dxcIncomingElo.getCdRegistroB0());
	}

	@Test
	public void testGetCdServicoB0() {
		assertEquals("10", dxcIncomingElo.getCdServicoB0());
	}

	@Test
	public void testGetDtRemessa() {
		assertEquals("20190514", dxcIncomingElo.getDtRemessa());
	}

	@Test
	public void testGetNrRemessaB0() {
		assertEquals("1629", dxcIncomingElo.getNrRemessaB0());
	}

	@Test
	public void testGetNrJanela() {
		assertEquals("5", dxcIncomingElo.getNrJanela());
	}

	@Test
	public void testGetDtEnvio() {
		assertEquals("20190515", dxcIncomingElo.getDtEnvio());
	}

	@Test
	public void testGetHrEnvioArq() {
		assertEquals("002300", dxcIncomingElo.getHrEnvioArq());
	}

	@Test
	public void testGetDtRetornoArq() {
		assertEquals("20190515", dxcIncomingElo.getDtRetornoArq());
	}

	@Test
	public void testGetHrRetornoArq() {
		assertEquals("033809", dxcIncomingElo.getHrRetornoArq());
	}

	@Test
	public void testGetBancoEmissorB0() {
		assertEquals("0000", dxcIncomingElo.getBancoEmissorB0());
	}

	@Test
	public void testGetCdProcessadora() {
		assertEquals("0000", dxcIncomingElo.getCdProcessadora());
	}

	@Test
	public void testGetVersaoArq() {
		assertEquals("191", dxcIncomingElo.getVersaoArq());
	}

	@Test
	public void testGetCdCredenciador() {
		assertEquals("5140", dxcIncomingElo.getCdCredenciador());
	}

	@Test
	public void testGetCdBandeiraB0() {
		assertEquals("007", dxcIncomingElo.getCdBandeiraB0());
	}

	@Test
	public void testGetIndicadorRotaArqB0() {
		assertEquals("2", dxcIncomingElo.getIndicadorRotaArqB0());
	}
	//fim registro HEADER

	
	//inicio registro TE44
	@Test
	public void testGetCdTransacao() {
		assertEquals("44", dxcIncomingElo.getCdTransacao());
	}

	@Test
	public void testGetSubCdTransacao() {
		assertEquals("00", dxcIncomingElo.getSubCdTransacao());
	}

	@Test
	public void testGetBancoEmissor00() {
		assertEquals("0000", dxcIncomingElo.getBancoEmissor00());
	}

	@Test
	public void testGetDtMovimento() {
		assertEquals("20190514", dxcIncomingElo.getDtMovimento());
	}

	@Test
	public void testGetNrRemessa00() {
		assertEquals("001629", dxcIncomingElo.getNrRemessa00());
	}

	@Test
	public void testGetDtConfirmRemessa() {
		assertEquals("20190515", dxcIncomingElo.getDtConfirmRemessa());
	}

	@Test
	public void testGetStRemessa() {
		assertEquals("A", dxcIncomingElo.getStRemessa());
	}

	@Test
	public void testGetMotivoRejeicao() {
		assertEquals("00", dxcIncomingElo.getMotivoRejeicao());
	}

	@Test
	public void testGetCdMoedaTransacao() {
		assertEquals("986", dxcIncomingElo.getCdMoedaTransacao());
	}

	@Test
	public void testGetIndicadorTpRetorno() {
		assertEquals("P", dxcIncomingElo.getIndicadorTpRetorno());
	}

	@Test
	public void testGetQtTotRegArq() {
		assertEquals("000000000003746", dxcIncomingElo.getQtTotRegArq());
	}
	
	@Test
	public void testGetQtTransAcMoedaReal() {
		assertEquals("000000000000745", dxcIncomingElo.getQtTransAcMoedaReal());
	}

	@Test
	public void testGetVlTransAcMoedaReal() {
		assertEquals(54541.81, dxcIncomingElo.getVlTransAcMoedaReal());
	}

	@Test
	public void testGetQtTransRjMoedaReal() {
		assertEquals("00000010", dxcIncomingElo.getQtTransRjMoedaReal());
	}

	@Test
	public void testGetVlTransRjMoedaReal() {
		assertEquals(544.80, dxcIncomingElo.getVlTransRjMoedaReal());
	}

	@Test
	public void testGetCdBandeira00() {
		assertEquals("007", dxcIncomingElo.getCdBandeira00());
	}

	@Test
	public void testGetQtTransAcMoedaDolar() {
		assertEquals("00000000", dxcIncomingElo.getQtTransAcMoedaDolar());
	}

	@Test
	public void testGetVlTransAcMoedaDolar() {
		assertEquals(0.00, dxcIncomingElo.getVlTransAcMoedaDolar());
	}

	@Test
	public void testGetQtTransRjMoedaDolar() {
		assertEquals("00000000", dxcIncomingElo.getQtTransRjMoedaDolar());
	}

	@Test
	public void testGetVlTransRjMoedaDolar() {
		assertEquals(0.00, dxcIncomingElo.getVlTransRjMoedaDolar());
	}
	//fim registro TE44
	
	
	//inicio registro TRAILER
	@Test
	public void testGetCdRegistroBz() {
		assertEquals("BZ", dxcIncomingElo.getCdRegistroBz());
	}

	@Test
	public void testGetCdServicoBz() {
		assertEquals("10", dxcIncomingElo.getCdServicoBz());
	}

	@Test
	public void testGetNrRemessaBz() {
		assertEquals("1629", dxcIncomingElo.getNrRemessaBz());
	}

	@Test
	public void testGetQtTransCreMoedaReal() {
		assertEquals("00000004", dxcIncomingElo.getQtTransCreMoedaReal());
	}

	@Test
	public void testGetVlTransCreMoedaReal() {
		assertEquals(231.41, dxcIncomingElo.getVlTransCreMoedaReal());
	}

	@Test
	public void testGetQtTransDebMoedaReal() {
		assertEquals("00000751", dxcIncomingElo.getQtTransDebMoedaReal());
	}

	@Test
	public void testGetVlTransDebMoedaReal() {
		assertEquals(54855.20, dxcIncomingElo.getVlTransDebMoedaReal());
	}

	@Test
	public void testGetQtTransCreMoedaDolar() {
		assertEquals("00000000", dxcIncomingElo.getQtTransCreMoedaDolar());
	}

	@Test
	public void testGetVlTransCreMoedaDolar() {
		assertEquals(0.00, dxcIncomingElo.getVlTransCreMoedaDolar());
	}

	@Test
	public void testGetQtTransDebMoedaDolar() {
		assertEquals("00000000", dxcIncomingElo.getQtTransDebMoedaDolar());
	}

	@Test
	public void testGetVlTransDebMoedaDolar() {
		assertEquals(0.0, dxcIncomingElo.getVlTransDebMoedaDolar());
	}

	@Test
	public void testGetQtTotReg() {
		assertEquals("00003746", dxcIncomingElo.getQtTotReg());
	}

	@Test
	public void testGetQtTransMoviParcelado() {
		assertEquals("00000000", dxcIncomingElo.getQtTransMoviParcelado());
	}

	@Test
	public void testGetVlTransMoviParcelado() {
		assertEquals(0.0, dxcIncomingElo.getVlTransMoviParcelado());
	}

	@Test
	public void testGetIndicadorRotaArqBz() {
		assertEquals("2", dxcIncomingElo.getIndicadorRotaArqBz());
	}

	@Test
	public void testGetNmArquivo() {
		assertEquals("0075140C162920190515033808.txt-20190515", dxcIncomingElo.getNmArquivo());
	}
	//fim registro TRAILER
}
