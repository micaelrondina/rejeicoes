package br.com.dxc.cards.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.dxc.cards.core.model.Sintetico;
import br.com.dxc.cards.core.utils.Utils;

public class SinteticoDAO {
    public List<Sintetico> getListSintetico(String cdCredenciador, String produto, String cdTransacao, String dtPeriodoInicio, String dtPeriodoFim) throws SQLException, ParseException {
        ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
        List<Sintetico> listSintetico = new ArrayList<>();

        try {
			con = Utils.getConnection();
			
			String dataInicio = Utils.getyyyyMMddFromIsoDate(dtPeriodoInicio);
			String dataFim = Utils.getyyyyMMddFromIsoDate(dtPeriodoFim);

			String query = "SELECT\r\n" +
							//Outgoing
						   "    O.DT_OUTGOING,\r\n" +
						   "    O.NM_ARQ_OUTGOING,\r\n" +
						   "    O.QTD_OUTGOING,\r\n" +
						   "    O.VL_TOT_OUTGOING,\r\n" +
						   
						   //Incoming ELO
						   "    IP.DT_RETORNO_ARQ DT_RET_INCOMING_PARC,\r\n" +
						   "    IP.NM_ARQUIVO NM_ARQUIVO_INCOMING_PARC,\r\n" +
						   "    TO_NUMBER(IP.QT_TRANS_RJ_MOEDA_REAL) QTD_INCOMING_PARC,\r\n" +
						   "    IP.VL_TRANS_RJ_MOEDA_REAL VL_INCOMING_PARC,\r\n" +
						   
						   //Incoming Emissor
						   "    IT.DT_RETORNO_ARQ DT_RET_INCOMING_TOT,\r\n" +
						   "    IT.NM_ARQUIVO NM_ARQUIVO_INCOMING_TOT,\r\n" +
						   "    TO_NUMBER(IT.QT_TRANS_RJ_MOEDA_REAL) QTD_INCOMING_TOT,\r\n" +
						   "    IT.VL_TRANS_RJ_MOEDA_REAL VL_INCOMING_TOT,\r\n" +
						   
						   //Liquido
						   "    TO_NUMBER(COALESCE(IT.QT_TRANS_AC_MOEDA_REAL,IP.QT_TRANS_AC_MOEDA_REAL,TO_CHAR(O.QTD_OUTGOING))) QTD_LIQUIDO,\r\n" +
						   "    COALESCE(IT.VL_TRANS_AC_MOEDA_REAL,IP.VL_TRANS_AC_MOEDA_REAL,O.VL_TOT_OUTGOING) VL_LIQUIDO,\r\n" + 
						   "	CASE\r\n" + 
						   "    	WHEN IP.ST_REMESSA IS NULL AND IT.ST_REMESSA IS NULL THEN 1\r\n" + 
						   "    	ELSE 0\r\n" + 
						   "    END REJ_TOTAL\r\n" +
						   "FROM\r\n" +
						   "    (\r\n" +
						   "        SELECT\r\n" +
						   "            TRIM(SUBSTR(TRIM(P.NM_PARAMETRO),19) ) CD_CREDENCIADOR,\r\n" +
						   "            O.FECHA_OUTGOING DT_OUTGOING,\r\n" +
						   "            O.FILE_S02 NM_ARQ_OUTGOING,\r\n" +
						   "            SUBSTR(TRIM(O.FILE_S02),1,LENGTH(TRIM(O.FILE_S02) ) - 1) NR_REMESSA_B0,\r\n" +
						   "            SUBSTR(TRIM(O.FILE_S02),LENGTH(TRIM(O.FILE_S02) ) ) PRODUTO,\r\n" +
						   "            COUNT(*) QTD_OUTGOING,\r\n" +
						   "            SUM(O.IMPORTE) VL_TOT_OUTGOING\r\n" +
						   "        FROM\r\n" +
						   "            PRODACQR.OUTGOING O\r\n" +
						   "            INNER JOIN PRODACQR.PARAM_ACQUIRER_PROCESSO P ON O.ACQUIRER_ID = TO_NUMBER(P.VL_PARAMETRO_ALFA)\r\n" +
						   "        WHERE\r\n" +
						   "            P.NM_PARAMETRO LIKE 'CD_PAYWARE_CREDEN_%'\r\n" +
						   "            AND   TRAN_CODE IN (05, 06)\r\n" +
						   "        GROUP BY\r\n" +
						   "            NM_PARAMETRO, FECHA_OUTGOING, FILE_S02\r\n" +
						   "    ) O\r\n" +
						   "    LEFT JOIN (\r\n" +
						   "        SELECT\r\n" +
						   "    		CD_CREDENCIADOR,\r\n" +
						   "    		NR_REMESSA_B0,\r\n" +
						   "    		DT_RETORNO_ARQ,\r\n" +
						   "    		NM_ARQUIVO,\r\n" +
						   "    		QT_TRANS_RJ_MOEDA_REAL,\r\n" +
						   "    		VL_TRANS_RJ_MOEDA_REAL,\r\n" +
						   "    		QT_TRANS_AC_MOEDA_REAL,\r\n" +
						   "    		VL_TRANS_AC_MOEDA_REAL,\r\n" +
						   "			ST_REMESSA\r\n" +
						   "        FROM\r\n" +
						   "            PRODACQR.DXC_INCOMING_ELO\r\n" +
						   "        WHERE\r\n" +
						   "            INDICADOR_TP_RETORNO = 'P'\r\n" +
						   "    ) IP ON IP.CD_CREDENCIADOR = O.CD_CREDENCIADOR\r\n" +
						   "            AND IP.NR_REMESSA_B0 = O.NR_REMESSA_B0\r\n" +
						   "            AND IP.NM_ARQUIVO LIKE ('%' || O.PRODUTO || '%')\r\n" +
						   "    LEFT JOIN (\r\n" +
						   "        SELECT\r\n" +
						   "    		CD_CREDENCIADOR,\r\n" +
						   "    		NR_REMESSA_B0,\r\n" +
						   "    		DT_RETORNO_ARQ,\r\n" +
						   "    		NM_ARQUIVO,\r\n" +
						   "    		QT_TRANS_RJ_MOEDA_REAL,\r\n" +
						   "    		VL_TRANS_RJ_MOEDA_REAL,\r\n" +
						   "    		QT_TRANS_AC_MOEDA_REAL,\r\n" +
						   "    		VL_TRANS_AC_MOEDA_REAL,\r\n" +
						   "			ST_REMESSA\r\n" +
						   "        FROM\r\n" +
						   "            PRODACQR.DXC_INCOMING_ELO\r\n" +
						   "        WHERE\r\n" +
						   "            INDICADOR_TP_RETORNO = 'T'\r\n" +
						   "    ) IT ON IT.CD_CREDENCIADOR = O.CD_CREDENCIADOR\r\n" +
						   "            AND IT.NR_REMESSA_B0 = O.NR_REMESSA_B0\r\n" +
						   "            AND IT.NM_ARQUIVO LIKE ('%' || O.PRODUTO || '%')\r\n" +
						   "WHERE\r\n" +
						   "    O.CD_CREDENCIADOR = " + cdCredenciador + "\r\n" +
						   "    AND   O.PRODUTO = '" + produto + "'\r\n" +
						   "    AND   O.DT_OUTGOING BETWEEN '" + dataInicio + "' AND '" + dataFim + "'\r\n" +
						   "ORDER BY\r\n" +
						   "    DT_OUTGOING, NM_ARQ_OUTGOING";						   

			ps = con.prepareStatement(query); //[fortify] Aplicacao roda em intranet - ambiente controlado
			rs = Utils.executeQuery(ps, query); // executa query e printa query no log
			
			while (rs.next()) {
				
				Sintetico sintetico = new Sintetico();
				//
				sintetico.setDataOutgoing(rs.getString("DT_OUTGOING"));
				sintetico.setDataOutgoingFormatada(Utils.getDataFormata(sintetico.getDataOutgoing()));
				sintetico.setNomeArqOutgoing(Utils.leftRightTrim(rs.getString("NM_ARQ_OUTGOING")));
				sintetico.setQtdOutgoing(rs.getObject("QTD_OUTGOING", Integer.class));
				sintetico.setValorTotalOutgoing(rs.getBigDecimal("VL_TOT_OUTGOING"));
				//
				sintetico.setDataRetornoIncomingParcial(rs.getString("DT_RET_INCOMING_PARC"));
				sintetico.setDataRetornoIncomingParcialFormatada(Utils.getDataFormata(sintetico.getDataRetornoIncomingParcial()));
				sintetico.setNomeArqIncomingParcial(Utils.leftRightTrim(rs.getString("NM_ARQUIVO_INCOMING_PARC")));
				sintetico.setQtdIncomingParcial(rs.getObject("QTD_INCOMING_PARC", Integer.class));
				sintetico.setValorIncomingParcial(rs.getBigDecimal("VL_INCOMING_PARC"));
				// 
				sintetico.setDataRetornoIncomingTotal(rs.getString("DT_RET_INCOMING_TOT"));
				sintetico.setDataRetornoIncomingTotalFormatada(Utils.getDataFormata(sintetico.getDataRetornoIncomingTotal()));
				sintetico.setNomeArqIncomingTotal(Utils.leftRightTrim(rs.getString("NM_ARQUIVO_INCOMING_TOT")));
				sintetico.setQtdIncomingTotal(rs.getObject("QTD_INCOMING_TOT", Integer.class));
				sintetico.setValorIncomingTotal(rs.getBigDecimal("VL_INCOMING_TOT"));
				//
				sintetico.setQtdLiquido(rs.getObject("QTD_LIQUIDO", Integer.class));
				sintetico.setValorLiquido(rs.getBigDecimal("VL_LIQUIDO"));
				//
				sintetico.setRejTotal(rs.getObject("REJ_TOTAL", Integer.class)); //1=Rejeitado Total; 0=Acatado Total ou Parcial
				//
				listSintetico.add(sintetico);
			}

		} finally {
			Utils.fechaConexao(rs, ps, con);
		}
        
        return listSintetico;
	}
	
	static enum Produto {
		CREDITO("C", 22),
		DEBITO("D", 23);

		private String letra; // código do produto na aplicação
		private int numero;	  // código do produto no Payware

		private Produto(String letra, int numero) {
			this.letra = letra;
			this.numero = numero;
		}

		public String getLetra() {
			return letra;
		}

		public int getNumero() {
			return numero;
		}

		public static Produto findByLetra(final String letra){
			return Arrays.stream(Produto.values()).filter(value -> value.getLetra().equals(letra)).findFirst().orElse(null);
		}
	}
}