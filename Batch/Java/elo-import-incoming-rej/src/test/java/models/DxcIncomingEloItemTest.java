package models;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro00;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro01;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro02;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro05;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro07;
import br.com.dxc.elo_import_incoming.layout.te_padrao.Registro09;
import br.com.dxc.elo_import_incoming.model.DxcIncomingEloItem;
import junit.framework.TestCase;

public class DxcIncomingEloItemTest extends TestCase {
	
//	arquivo: "0075140C162920190515033808.txt-20190515";
	
	DxcIncomingEloItem dxcIncomingEloItem = new DxcIncomingEloItem();
	
	String linha00 = "01006504876002317387   C 1200514091341437910069640000514020190514181923     000000012366986DROGARIA BRASIL          RODEIO BONITOBR 591200001  00710002649950 0520190514";
	Registro00 registro00 = new Registro00(linha00, dxcIncomingEloItem);
	
	String linha01 = "010100000       000000                                                   070    012005578557001GT012365000000000000 000000000000000000000000J92022656000123000000000001 ";
	Registro01 registro01 = new Registro01(linha01, dxcIncomingEloItem);
	
	String linha02 = "0102            BR    030000000000000000000                                     9836000020190613000000000000000                                                         ";
	Registro02 registro02 = new Registro02(linha02, dxcIncomingEloItem);
	
	//Registro03
	
	String linha05 = "0105181923140043   00000001236698600        0000 000000012366                                                                                                           ";
	Registro05 registro05 = new Registro05(linha05, dxcIncomingEloItem);
	
	String linha07 = "010700000190514E0E8C0076        8E2A0B4C0318580096FEDE6D60C6D85F01A5200000E000A238F800000000012366                                                                      ";
	Registro07 registro07 = new Registro07(linha07, dxcIncomingEloItem);
	
	//Registro08
	
	String linha09 = "0109            050020190514       B24                                                                                                                                  ";
	Registro09 registro09 = new Registro09(linha09, dxcIncomingEloItem);
	
	public DxcIncomingEloItemTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		registro00.preencherDxcIncomingElo();
		registro01.preencherDxcIncomingElo();
		registro02.preencherDxcIncomingElo();
		//registro03
		registro05.preencherDxcIncomingElo();
		registro07.preencherDxcIncomingElo();
		//registro08
		registro09.preencherDxcIncomingElo();
	}

	//inicio Registro "00"
	@Test
	public void testGetCdTransacao00() {
		assertEquals("01", dxcIncomingEloItem.getCdTransacao00());
	}

	@Test
	public void testGetSubCdTransacao00() {
		assertEquals("00", dxcIncomingEloItem.getSubCdTransacao00());
	}

	@Test
	public void testGetNrCartao() {
		assertEquals("650487******7387", dxcIncomingEloItem.getNrCartao().trim());
	}

	@Test
	public void testGetTpLiquidacao() {
		assertEquals("C", dxcIncomingEloItem.getTpLiquidacao());
	}

	@Test
	public void testGetIndOrigemAuthCanc() {
		assertEquals("1", dxcIncomingEloItem.getIndOrigemAuthCanc());
	}

	@Test
	public void testGetNrRefTransacao() {
		assertEquals("20051409134143791006964", dxcIncomingEloItem.getNrRefTransacao());
	}

	@Test
	public void testGetCdProcesso() {
		assertEquals("00", dxcIncomingEloItem.getCdProcesso());
	}

	@Test
	public void testGetCdCredenciador() {
		assertEquals("5140", dxcIncomingEloItem.getCdCredenciador());
	}

	@Test
	public void testGetDtVendaSaque() {
		assertEquals("20190514", dxcIncomingEloItem.getDtVendaSaque());
	}

	@Test
	public void testGetHrVendaSaque() {
		assertEquals("181923", dxcIncomingEloItem.getHrVendaSaque());
	}

	@Test
	public void testGetVlVendaSaqueDisputa() {
		assertEquals(123.66, dxcIncomingEloItem.getVlVendaSaqueDisputa());
	}

	@Test
	public void testGetCdMoedaTransacao() {
		assertEquals("986", dxcIncomingEloItem.getCdMoedaTransacao());
	}

	@Test
	public void testGetNmPontoVenda() {
		assertEquals("DROGARIA BRASIL          ", dxcIncomingEloItem.getNmPontoVenda());
	}

	@Test
	public void testGetCidadePontoVenda() {
		assertEquals("RODEIO BONITO", dxcIncomingEloItem.getCidadePontoVenda());
	}

	@Test
	public void testGetCdPaisPontoVenda() {
		assertEquals("BR ", dxcIncomingEloItem.getCdPaisPontoVenda());
	}

	@Test
	public void testGetMccPontoVenda() {
		assertEquals("5912", dxcIncomingEloItem.getMccPontoVenda());
	}

	@Test
	public void testGetBancoEmissor() {
		assertEquals("0001", dxcIncomingEloItem.getBancoEmissor());
	}

	@Test
	public void testGetCdBandeira() {
		assertEquals("007", dxcIncomingEloItem.getCdBandeira());
	}

	@Test
	public void testGetIdentTpTransacao() {
		assertEquals("1", dxcIncomingEloItem.getIdentTpTransacao());
	}

	@Test
	public void testGetCdMotivoDisputa() {
		assertEquals("00", dxcIncomingEloItem.getCdMotivoDisputa());
	}

	@Test
	public void testGetCdAuthTransacao() {
		assertEquals("026499", dxcIncomingEloItem.getCdAuthTransacao());
	}

	@Test
	public void testGetIndicadorTecnoTerminal() {
		assertEquals("5", dxcIncomingEloItem.getIndicadorTecnoTerminal());
	}

	@Test
	public void testGetMeioIdentPortador() {
		assertEquals("0", dxcIncomingEloItem.getMeioIdentPortador());
	}

	@Test
	public void testGetModoEntTransacaoPos() {
		assertEquals("05", dxcIncomingEloItem.getModoEntTransacaoPos());
	}

	@Test
	public void testGetDtMovApresentDisputa() {
		assertEquals("20190514", dxcIncomingEloItem.getDtMovApresentDisputa());
	}
	//fim Registro "00"
	
	
	//inicio Registro "01"
	@Test
	public void testGetCdTransacao01() {
		assertEquals("01", dxcIncomingEloItem.getCdTransacao01());
	}

	@Test
	public void testGetSubCdTransacao01() {
		assertEquals("01", dxcIncomingEloItem.getSubCdTransacao01());
	}

	@Test
	public void testGetNrRefDisputa() {
		assertEquals("000000", dxcIncomingEloItem.getNrRefDisputa());
	}

	@Test
	public void testGetIndicadorEnvDoc() {
		assertEquals(" ", dxcIncomingEloItem.getIndicadorEnvDoc());
	}

	@Test
	public void testGetTxtLivre() {
		assertEquals("                                                  ", dxcIncomingEloItem.getTxtLivre());
	}

	@Test
	public void testGetCdProduto() {
		assertEquals("070", dxcIncomingEloItem.getCdProduto());
	}

	@Test
	public void testGetPontoVenda() {
		assertEquals("012005578557001", dxcIncomingEloItem.getPontoVenda());
	}

	@Test
	public void testGetNrLogicoEquipamento() {
		assertEquals("GT012365", dxcIncomingEloItem.getNrLogicoEquipamento());
	}

	@Test
	public void testGetVlTaxaEmbarque() {
		assertEquals(0.0, dxcIncomingEloItem.getVlTaxaEmbarque());
	}

	@Test
	public void testGetIndicTransFeitaPor() {
		assertEquals(" ", dxcIncomingEloItem.getIndicTransFeitaPor());
	}

	@Test
	public void testGetVlTransacao() {
		assertEquals(0.0, dxcIncomingEloItem.getVlTransacao());
	}

	@Test
	public void testGetIndicadorMovimentacao() {
		assertEquals("0", dxcIncomingEloItem.getIndicadorMovimentacao());
	}

	@Test
	public void testGetQtParcelasTransacao() {
		assertEquals("000", dxcIncomingEloItem.getQtParcelasTransacao());
	}

	@Test
	public void testGetNrParcela() {
		assertEquals("000", dxcIncomingEloItem.getNrParcela());
	}

	@Test
	public void testGetTarifaPagtoInsumo() {
		assertEquals("00000", dxcIncomingEloItem.getTarifaPagtoInsumo());
	}

	@Test
	public void testGetTpPessoa() {
		assertEquals("J", dxcIncomingEloItem.getTpPessoa());
	}

	@Test
	public void testGetDocumento() {
		assertEquals("92022656000123", dxcIncomingEloItem.getDocumento());
	}

	@Test
	public void testGetVlTrocoAgroDeb() {
		assertEquals(0.0, dxcIncomingEloItem.getVlTrocoAgroDeb());
	}

	@Test
	public void testGetCdCondTransacaoChip() {
		assertEquals("1", dxcIncomingEloItem.getCdCondTransacaoChip());
	}
	//fim Registro "01"
	
	
	//inicio Registro "02"
	@Test
	public void testGetCdTransacao02() {
		assertEquals("01", dxcIncomingEloItem.getCdTransacao02());
	}

	@Test
	public void testGetSubCdTransacao02() {
		assertEquals("02", dxcIncomingEloItem.getSubCdTransacao02());
	}

	@Test
	public void testGetCdPaisLiq() {
		assertEquals("BR ", dxcIncomingEloItem.getCdPaisLiq());
	}

	@Test
	public void testGetQtDiasLiqFinanTransacao() {
		assertEquals("030", dxcIncomingEloItem.getQtDiasLiqFinanTransacao());
	}

	@Test
	public void testGetVlIntercambio() {
		assertEquals(0.0, dxcIncomingEloItem.getVlIntercambio());
	}

	@Test
	public void testGetDtMovTransacaoOriginal() {
		assertEquals("00000000", dxcIncomingEloItem.getDtMovTransacaoOriginal());
	}

	@Test
	public void testGetTpOperacao() {
		assertEquals("     ", dxcIncomingEloItem.getTpOperacao());
	}

	@Test
	public void testGetTokenPan() {
		assertEquals("                   ", dxcIncomingEloItem.getTokenPan());
	}

	@Test
	public void testGetTokenRequestorId() {
		assertEquals("           ", dxcIncomingEloItem.getTokenRequestorId());
	}

	@Test
	public void testGetTokenAssuranceLevel() {
		assertEquals("  ", dxcIncomingEloItem.getTokenAssuranceLevel());
	}

	@Test
	public void testGetCepEc() {
		assertEquals("98360000", dxcIncomingEloItem.getCepEc());
	}

	@Test
	public void testGetDtLiquiTransacao() {
		assertEquals("20190613", dxcIncomingEloItem.getDtLiquiTransacao());
	}

	@Test
	public void testGetCdPvMarketplace() {
		assertEquals("000000000000000", dxcIncomingEloItem.getCdPvMarketplace());
	}
	//fim Registro "02"
	

	/*
	//inicio Registro "03"
	@Test
	public void testGetCdTransacao03() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetSubCdTransacao03() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetTpPagto() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetNrContaUnicoRef() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetNmRemetente() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetEnderecoRemetente() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCidadeRemetente() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetPaisRemetente() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetOrigemFundos() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}
	//fim Registro "03"
	*/
	
	//inicio Registro "05"
	@Test
	public void testGetCdTransacao05() {
		assertEquals("01", dxcIncomingEloItem.getCdTransacao05());
	}

	@Test
	public void testGetCdQualificadorTransacao05() {
		assertEquals("0", dxcIncomingEloItem.getCdQualificadorTransacao05());
	}

	@Test
	public void testGetNrSeqCompTransacao05() {
		assertEquals("5", dxcIncomingEloItem.getNrSeqCompTransacao05());
	}

	@Test
	public void testGetIdTransacao() {
		assertEquals("181923140043   ", dxcIncomingEloItem.getIdTransacao());
	}

	@Test
	public void testGetVlAutorizado() {
		assertEquals(123.66, dxcIncomingEloItem.getVlAutorizado());
	}

	@Test
	public void testGetCdMoedaVlAutorizado() {
		assertEquals("986", dxcIncomingEloItem.getCdMoedaVlAutorizado());
	}

	@Test
	public void testGetCdRespAutorizacao() {
		assertEquals("00", dxcIncomingEloItem.getCdRespAutorizacao());
	}

	@Test
	public void testGetIndicadorDireitoDevolucao() {
		assertEquals("  ", dxcIncomingEloItem.getIndicadorDireitoDevolucao());
	}

	@Test
	public void testGetIndicadorComerEletronico() {
		assertEquals("00", dxcIncomingEloItem.getIndicadorComerEletronico());
	}

	@Test
	public void testGetIndicadorAuthEspecifica() {
		assertEquals(" ", dxcIncomingEloItem.getIndicadorAuthEspecifica());
	}

	@Test
	public void testGetVlTotalAutorizado() {
		assertEquals(123.66, dxcIncomingEloItem.getVlTotalAutorizado());
	}

	@Test
	public void testGetCavvVlVerifAuthPortador() {
		assertEquals("                                          ", dxcIncomingEloItem.getCavvVlVerifAuthPortador());
	}

	@Test
	public void testGetCdResultVerifCavv() {
		assertEquals(" ", dxcIncomingEloItem.getCdResultVerifCavv());
	}
	//fim Registro "05"

	
	//inicio Registro "07"
	@Test
	public void testGetCdTransacao07() {
		assertEquals("01", dxcIncomingEloItem.getCdTransacao07());
	}

	@Test
	public void testGetCdQualificadorTransacao07() {
		assertEquals("0", dxcIncomingEloItem.getCdQualificadorTransacao07());
	}

	@Test
	public void testGetNrSeqCompTransacao07() {
		assertEquals("7", dxcIncomingEloItem.getNrSeqCompTransacao07());
	}

	@Test
	public void testGetTpTransacao() {
		assertEquals("00", dxcIncomingEloItem.getTpTransacao());
	}

	@Test
	public void testGetNrSeqCartao() {
		assertEquals("000", dxcIncomingEloItem.getNrSeqCartao());
	}

	@Test
	public void testGetDtTransacaoTerminal() {
		assertEquals("190514", dxcIncomingEloItem.getDtTransacaoTerminal());
	}

	@Test
	public void testGetCapacidadeTerminal() {
		assertEquals("E0E8C0", dxcIncomingEloItem.getCapacidadeTerminal());
	}

	@Test
	public void testGetCdPaisTerminal() {
		assertEquals("076", dxcIncomingEloItem.getCdPaisTerminal());
	}

	@Test
	public void testGetNrSerieTerminal() {
		assertEquals("        ", dxcIncomingEloItem.getNrSerieTerminal());
	}

	@Test
	public void testGetNrRandomCriptograma() {
		assertEquals("8E2A0B4C", dxcIncomingEloItem.getNrRandomCriptograma());
	}

	@Test
	public void testGetContadorTransacaoApp() {
		assertEquals("0318", dxcIncomingEloItem.getContadorTransacaoApp());
	}

	@Test
	public void testGetAppInterchangeProfile() {
		assertEquals("5800", dxcIncomingEloItem.getAppInterchangeProfile());
	}

	@Test
	public void testGetCriptograma() {
		assertEquals("96FEDE6D60C6D85F", dxcIncomingEloItem.getCriptograma());
	}

	@Test
	public void testGetIndiceDerivacaoChave() {
		assertEquals("01", dxcIncomingEloItem.getIndiceDerivacaoChave());
	}

	@Test
	public void testGetNrVersaoCriptograma() {
		assertEquals("A5", dxcIncomingEloItem.getNrVersaoCriptograma());
	}

	@Test
	public void testGetVerifResultTerminal() {
		assertEquals("200000E000", dxcIncomingEloItem.getVerifResultTerminal());
	}

	@Test
	public void testGetVerifResultCartao() {
		assertEquals("A238F800", dxcIncomingEloItem.getVerifResultCartao());
	}

	@Test
	public void testGetVlTransacaoCriptograma() {
		assertEquals(123.66, dxcIncomingEloItem.getVlTransacaoCriptograma());
	}

	@Test
	public void testGetFormFactorIndicator() {
		assertEquals("          ", dxcIncomingEloItem.getFormFactorIndicator());
	}
	//fim Registro "07"
	
	/*
	//inicio Registro "08"
	@Test
	public void testGetCdTransacao08() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetSubCdTransacao08() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdCompanhiaAerea() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetNrDocEmbarque() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdAgenteIata() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetNmPassageiro() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdCidade1() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetTransportadora1() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetClasseServico1() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdCidade2() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetTransportadora2() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetClasseServico2() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdCidade3() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}
	
	@Test
	public void testGetTransportadora3() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetClasseServico3() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdCidade4() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetTransportadora4() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetClasseServico4() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdCidade5() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetCdAeroportoDestino() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetDtPrimeiroVoo() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}

	@Test
	public void testGetNmAgenteEmissorDoc() {
		assertEquals("00", dxcIncomingEloItem.testGetCdTransacao00());
	}
	//fim Registro "08"
	*/
	
	//inicio Registro "09"
	@Test
	public void testGetCdTransacao09() {
		assertEquals("01", dxcIncomingEloItem.getCdTransacao09());
	}

	@Test
	public void testGetSubCdTransacao09() {
		assertEquals("09", dxcIncomingEloItem.getSubCdTransacao09());
	}

	@Test
	public void testGetCdTransacaoOriginal() {
		assertEquals("05", dxcIncomingEloItem.getCdTransacaoOriginal());
	}

	@Test
	public void testGetDtMovimento() {
		assertEquals("20190514", dxcIncomingEloItem.getDtMovimento());
	}

	@Test
	public void testGetCdErro() {
		assertEquals("B24", dxcIncomingEloItem.getCdErro());
	}

	@Test
	public void testGetDescricaoErro() {
		assertEquals("                                                                                ", dxcIncomingEloItem.getDescricaoErro());
	}

	@Test
	public void testGetRegistroComErro() {
		assertEquals("  ", dxcIncomingEloItem.getRegistroComErro());
	}

	@Test
	public void testGetPosicaoComErro() {
		assertEquals("   ", dxcIncomingEloItem.getPosicaoComErro());
	}
	//fim Registro "09"
}
