package br.com.dxc.cards.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.cards.core.annotation.Description;
import br.com.dxc.cards.core.annotation.DescriptionCodErro;
import br.com.dxc.cards.core.model.Incoming;

public class Utils {
	private static final Logger LOG = LogManager.getLogger(Utils.class);

	private static Context iniCtx;
	private static DataSource ds;
	private static Connection con;

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				iniCtx = new InitialContext();
				ds = (DataSource) iniCtx.lookup("java:/jdbc/OracleDS/DXC_ELO_INCOMING");
				return ds.getConnection();	
			}
		} catch (SQLException | NamingException e) {
			LOG.error("Erro obtendo conexão com o Banco de Dados!", e);
		}
		return con;
	}
	
	public static void fechaConexao(ResultSet result, PreparedStatement ps, Connection con) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
			LOG.error("Erro ao fechar conexao - ResultSet", e);
		}

		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
			LOG.error("Erro ao fechar conexao - PreparedStatement", e);
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao
			LOG.error("Erro ao fechar conexao - Connection", e);
		}
	}

	public static ResultSet executeQuery(PreparedStatement s, String query) {
		try {
			if (query != null) {
				LOG.info( "SQL : " + query);
			}
			return s.executeQuery();
		} catch (SQLException e) {
			LOG.error("Erro ao executar Query", e);//[Fortify] System Information Leak	- alternativa ao printStackTrace()
			throw new RuntimeException(e);
		}
	}
	
	public static void execute(PreparedStatement ps, String command) {
		try {
			if (command != null) {
				LOG.info( "SQL : " + command);
			}
			if ( ps == null ) {
				ps = getConnection().prepareStatement(command);//[fortify] Sql baseado em uma variavel constante - nao oferece risco
			}
			ps.execute();
		} catch (SQLException e) {
			LOG.error("Erro ao executar Query", e);//[Fortify] System Information Leak	- alternativa ao printStackTrace()
			throw new RuntimeException(e);
		}
	}
	
	public static String getyyyyMMddFromIsoDate(String isoDate) {
		String data = isoDate.substring(0, 10);
		data = data.replaceAll("-", "");
		return data;
	}
	
	public static String rightTrim(String texto) {
		return texto.replaceAll("\\s+$", "");
	}
	
	public static String leftTrim(String texto) {
		return texto.replaceAll("^\\s+", "");
	}
	
	public static String leftRightTrim(String texto) {
		if (texto != null) {
			return rightTrim(leftTrim(texto));	
		}
		return texto;
	}
	
	public static String getDataFormata(String data) {
		if (data != null) {
			return data.substring(6,8) + "/" + data.substring(4,6) + "/" + data.substring(0,4);	
		}
		return data;
	}
	
	public static Object preencherObjectByResultSet(ResultSet rs, Class<?> className) 
			throws InvocationTargetException, SQLException, NoSuchMethodException,  IllegalAccessException, InstantiationException {
        
		Method method = null;
		Field[] fields = className.getDeclaredFields();
		Object objRetorno = className.newInstance();
		Method methodAddValueInMapDescriCod = className.getMethod("setValueMapDescricoesCodigos", String.class, String.class);
		
		//verifica se o ResultSet esta nulo			
		if (rs == null) {
			LOG.error("ResultSet esta NULO!");
			return objRetorno;
		}
		
		//percorre os campos da classe
		if (fields != null && fields.length != 0) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				
				//pula essa campo
				if (field.getName().equals("setValueMapDescricoesCodigos") || field.getName().equals("getMapDescricoesCodigos")) {
					continue;
				}
				
	        	field.setAccessible(true); //[fortify] Aplicacao roda em intranet - ambiente controlado
	        	
	        	//monta o nome do metodo set para o campo em questao
	        	method = className.getMethod("set" + StringUtils.capitalize(field.getName()), field.getType());

	        	//pegando o campo do ResultSet para o campo em questa
	        	//converte de dataEntradaSistema para DATA_ENTRADA_SISTEMA
	        	String nomeCampo = field.getName().replaceAll("\\s+", "").replaceAll("(.)([A-Z0-9])", "$1_$2").toUpperCase(); //add "_" no entre caracteres upper case e setando como uppercase
	        	
	        	Object value = null;
	        	//verificando o tipo do campo para obter corretamente do ResultSet
	        	if (field.getType() == Long.class) {
	        		value = rs.getLong(nomeCampo);	
	        	} else if(field.getType() == Integer.class) {
	        		value = rs.getInt(nomeCampo);
	        	} else if(field.getType() == String.class) {
	        		value = rs.getString(nomeCampo);
	        	} else if (field.getType() == BigDecimal.class) {
	        		value = rs.getBigDecimal(nomeCampo);        		
	        	} else if(field.getType() == Date.class) {
					java.util.Date dateSet;
					dateSet = rs.getDate(nomeCampo);
					value = dateSet;
	        	}

				//se valor for diferente de NULO
				if (value != null) {
					//invoca o metodo set do campo em questao, setando o valor do resultSet para a campo em questao
					method.invoke(objRetorno, value); //[fortify] Aplicacao roda em intranet - ambiente controlado
				}
				
				//Realizando a Traducao de Codigos (caso necessario)
				Description annotationDescription = field.getAnnotation(Description.class);
				DescriptionCodErro annotationDescriptionCodErro = field.getAnnotation(DescriptionCodErro.class);
				if (annotationDescription != null) {
					String descricao = ParametroCache.getMapDescricaoCodigos().get(annotationDescription.value() + String.valueOf(value).trim());
					methodAddValueInMapDescriCod.invoke(objRetorno, field.getName(), descricao); //[fortify] Aplicacao roda em intranet - ambiente controlado
					
				} else if (annotationDescriptionCodErro != null){
					String descricao = ParametroCache.getMapCodErros().get(String.valueOf(value));
					methodAddValueInMapDescriCod.invoke(objRetorno, field.getName(), descricao); //[fortify] Aplicacao roda em intranet - ambiente controlado
				}
			}
		}
		return objRetorno;
	}
	
	public static void populaInfoLibRejAtaIncoming(Incoming incoming, ResultSet rs) throws SQLException {
		//
		java.util.Date dateSet;
		//Liberacao
		incoming.setLoginLiberacao(rs.getString("LOGIN_USU_LIBERACAO"));
		dateSet = rs.getDate("DT_LIBERACAO");
		incoming.setDataLiberacao(dateSet);
		
		//Acatado
		incoming.setLoginAcatado(rs.getString("LOGIN_USU_ACATADO"));
		dateSet = rs.getDate("DT_ACATADO");
		incoming.setDataAcatado(dateSet);
		
		//Rejeicao
		incoming.setLoginRejeicao(rs.getString("LOGIN_USU_REJEICAO"));
		dateSet = rs.getDate("DT_REJEICAO");
		incoming.setDataRejeicao(dateSet);
	}

	public static Date getDateFromStringYYYYMMDD(String data) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			throw new RuntimeException("\"" + data + "\" Nao eh uma data valida!", e);
		}
	}
	
	public static Long diferencaMinutos(Date inicio, Date fim) {
		return ( (fim.getTime() - inicio.getTime()) / (60 * 1000) % 60 );
	}
}
