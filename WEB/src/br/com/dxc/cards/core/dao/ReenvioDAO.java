package br.com.dxc.cards.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.dxc.cards.core.model.IdIncoming;
import br.com.dxc.cards.core.utils.Utils;

public class ReenvioDAO {

    public void salvarReenvio(String usuario, String data, List<IdIncoming> idsIncomings) throws SQLException {
		PreparedStatement ps = null;
        Connection con = null;

        try {
            con = Utils.getConnection();

            String condicoes = "";
            for (IdIncoming idIncoming : idsIncomings) {
                condicoes += "OR ( ID_INCOMING_ELO = " + idIncoming.getIdMatriz() + " AND ID_INCOMING_ELO_ITEM = " + idIncoming.getIdItem() + " )\r\n";
            }
            condicoes = condicoes.replaceFirst("OR", "");
            
            String update;
            /* TODO: validar se nao tera mais essa funcao, e tirar
            if (data == null && usuario == null) {
            	update = "UPDATE PRODACQR.DXC_INCOMING_ELO_ITEM\r\n" +
					    "    SET LOGIN_USU_LIBERACAO = null,\r\n" +	
					    "        DT_LIBERACAO = null\r\n" +	
					    "WHERE\r\n" +
					    condicoes;
            } else {
                update = "UPDATE PRODACQR.DXC_INCOMING_ELO_ITEM\r\n" +
    						    "    SET LOGIN_USU_LIBERACAO = '" + usuario + "',\r\n" +	
    						    "        DT_LIBERACAO = TO_DATE('" + data + "', 'YYYY-MM-DD')\r\n" +	
    						    "WHERE\r\n" +	
    						    condicoes;	
            }
            */
            update = "UPDATE PRODACQR.DXC_INCOMING_ELO_ITEM\r\n" +
				    "    SET LOGIN_USU_LIBERACAO = '" + usuario + "',\r\n" +	
				    "        DT_LIBERACAO = TO_DATE('" + data + "', 'YYYY-MM-DD')\r\n" +	
				    "WHERE\r\n" +	
				    condicoes;
            
            ps = con.prepareStatement(update); //[fortify] Aplicacao roda em intranet - ambiente controlado
            Utils.execute(ps, update);
            
        } finally {
			Utils.fechaConexao(null, ps, con);
		}
        
    }

}