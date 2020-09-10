package br.com.dxc.elo_import_incoming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.model.ArquivoTransEnvioPayware;
import br.com.dxc.elo_import_incoming.utils.Utils;

public class GerarArquivoTransEnvioPaywareDAO {
	private static final Logger LOG = LogManager.getLogger(GerarArquivoTransEnvioPaywareDAO.class);
	
	public List<ArquivoTransEnvioPayware> getDadosArquivo() throws SQLException, InvalidValueException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		List<ArquivoTransEnvioPayware> listRetorno = null;

		try {
			listRetorno = new ArrayList<>();
			con = Utils.getConexao();
			
			//Você deve enviar as transações com data de envio <> 0 e data de rejeição = 0 e data acatada = 0 e o campo flag = nul ou branco.
			String query = "SELECT I.ID_INCOMING_ELO_ITEM, I.NR_REF_TRANSACAO AS ARN, O.CUOTAS_VAN AS CUOTAS_VAN, O.USAGE_CODE AS USAGE_CODE\r\n" + 
					"FROM DXC_INCOMING_ELO M\r\n" + 
					"     INNER JOIN DXC_INCOMING_ELO_ITEM I ON (M.ID_INCOMING_ELO = I.ID_INCOMING_ELO)\r\n" + 
					"     INNER JOIN PRODACQR.OUTGOING O ON (O.ACQUIRER_REF_NUM = I.NR_REF_TRANSACAO AND O.CUOTAS_VAN = \r\n" + 
					"          ( CASE  \r\n" +
					"              WHEN I.NR_PARCELA = 0 THEN TO_NUMBER(I.NR_PARCELA) + 1\r\n" + 
					"              ELSE TO_NUMBER(I.NR_PARCELA)\r\n" + 
					"            END          \r\n" + 
					"          )\r\n" + 
					"     )\r\n" + 
					"WHERE I.DT_LIBERACAO IS NOT NULL AND I.DT_REJEICAO IS NULL AND I.DT_ACATADO IS NULL\r\n" + 
					"AND I.DH_ENVIO_ARQ_PAYWARE IS NULL\r\n" + 
					"ORDER BY M.CD_CREDENCIADOR, \r\n" + 
					"      CASE \r\n" + 
					"        WHEN M.NM_ARQUIVO LIKE '%C%' THEN 'C'\r\n" + 
					"        ELSE 'D'\r\n" + 
					"      END, I.NR_REF_TRANSACAO";

			ps = con.prepareStatement(query);
			LOG.info( "Executando query getDadosArquivo()");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ArquivoTransEnvioPayware arq = new ArquivoTransEnvioPayware();
				arq.setRecordTypeDefaultValue();
				arq.setIdItem(rs.getLong("ID_INCOMING_ELO_ITEM"));
				arq.setArn(rs.getString("ARN"));
				arq.setCuotasVan(rs.getString("CUOTAS_VAN"));
				arq.setUsageCode(rs.getString("USAGE_CODE"));
				listRetorno.add(arq);
			}
			return listRetorno;

		} finally {
			Utils.fechaConexao(rs, ps);
		}
	}


}
