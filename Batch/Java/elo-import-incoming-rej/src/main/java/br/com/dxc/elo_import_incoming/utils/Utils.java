package br.com.dxc.elo_import_incoming.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.elo_import_incoming.exception.InvalidValueException;
import br.com.dxc.elo_import_incoming.main.App;
import br.com.dxc.elo_import_incoming.model.ConfigEnvioEmail;

public class Utils {
	private static final Logger LOG = LogManager.getLogger(Utils.class);
	private static Connection connection;
	
	private Utils() {
		throw new IllegalStateException("Utility class - Can not to be instantiated");
	}

	public static void setConexao(Connection connection) {
		Utils.connection = connection;
	}

	public static Connection getConexao() {
		if (connection == null) {
			throw new NullPointerException("A Conexao com Banco esta NULO!");
		}
		
		try {
			return connection;
		} catch (Exception ex) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao 
			LOG.info("Error getting database connection!");
			LOG.fatal(ex);
		}
		return null;
	}

	/**
	 * Close Connection
	 *
	 * @param		con Connection to close
	 */
	public static void fechaConexao(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao  
			LOG.error(e);
		}
	}
	
	/**
	 * Close ResultSet and PreparedStatement
	 *
	 * @param		rs ResultSet to close
	 * @param		ps PreparedStatement to close
	 */
	public static void fechaConexao(ResultSet rs, PreparedStatement ps) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao 
			LOG.error(e);
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {//[fortify] Aplicacao pequena, tratamento simplificado para excecao 
			LOG.error(e);
		}
	}

	// obtendo a versao do POM
	public static String getInfo() {
		try (InputStream inputStream = App.class.getClass().getResourceAsStream("/META-INF/maven/br.com.dxc.cards/elo-import-incoming-rej/pom.properties")){
			Properties p = new Properties(); // try to load from maven properties first
			if (inputStream != null) {
				p.load(inputStream);
				return p.getProperty("artifactId", "") + "#" + p.getProperty("version", "");
			}
			return "null#null";
		} catch (IOException e) {
			LOG.fatal(e);
			return "null#null";
		}
	}
	
	public static void printBannerDXC() {
		System.out.println(" \t  _______   _______    _____          _____  _____   _____            ____            \r\n" + 
				" \t |  __ \\ \\ / / ____|  / ____|   /\\   |  __ \\|  __ \\ / ____|     /\\   / __ \\     /\\    \r\n" + 
				" \t | |  | \\ V / |      | |       /  \\  | |__) | |  | | (___      /  \\ | |  | |   /  \\   \r\n" + 
				" \t | |  | |> <| |      | |      / /\\ \\ |  _  /| |  | |\\___ \\    / /\\ \\| |  | |  / /\\ \\  \r\n" + 
				" \t | |__| / . \\ |____  | |____ / ____ \\| | \\ \\| |__| |____) |  / ____ \\ |__| | / ____ \\ \r\n" + 
				" \t |_____/_/ \\_\\_____|  \\_____/_/    \\_\\_|  \\_\\_____/|_____/  /_/    \\_\\___\\_\\/_/    \\_\\"); //[fortify] println usado para imprimir o banner no log
		System.out.println(""); //[fortify] println vazio. Apenas para colocar linha em branco no log
	}
	
	public static boolean isEmpty(String dado) {
		return dado == null || dado.equals("");
	}
	
	/**
	 * Returns the date format yyyyMMdd.
	 *
	 * @param		data data a ser formatada	
	 * @return      String
	 */
	public static String getDataString(Date data) {
		return new SimpleDateFormat(Constantes.SDF_AAAAMMDD).format(data);
	}

	/**
	 * Returns the current Time in the format HHmmss
	 * 
	 * @param		hora Hora a ser formatada	
	 * @return      String
	 */
	public static String getTimeString(Date hora) {
		return new SimpleDateFormat(Constantes.SDF_HHMMSS).format(hora);
	}
	
	/**
	 * Returns the current date in the format yyyyMMdd.
	 *
	 * @return      String
	 */
	public static String getDataString() {
		return getDataString(App.getDataBatch());
	}
	
	/**
	 * Returns the current Time in the format HHmmss
	 *
	 * @return      String
	 */
	public static String getTimeString() {
		return getTimeString(App.getDataBatch());
	}
	
	/**
	 * Retorna se a data passada eh valida ou nao
	 * @return Date Object
	 * @throws ParseException 
	 */
	public static boolean validarData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false); //por padrao eh true. Com true, 45/42/2017 eh considerada uma data valida. Por isso, setar como false
		try {
			sdf.parse(data);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static void preencheObjetoByLinha(Object objetoPopular, Object classOrigemDados) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Method[] methods = classOrigemDados.getClass().getDeclaredMethods(); //obtendo os metodos declarados na classe de Origem do Dados
		
		if (methods != null && methods.length > 0) {
			
			//percorrendo os metodos da classe classOrigemDados
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("get")) { //metodo da Classe classOrigemDados comeca com get

					//chamando o metodo get da classe classOrigemDados para pegar o valor de retorno dele
					Object retorno = methods[i].invoke(classOrigemDados); //[fortify] Aplicacao roda em intranet - ambiente controlado
					
					//SETANDO O conteudo no objeto a Popular
					String nomeMetodoProcurarEmObjetoPopular = methods[i].getName().replaceAll("get", "set");
					try {						
						//chamando o metodo set correspondente no objeto a Popular
						objetoPopular.getClass().getDeclaredMethod(nomeMetodoProcurarEmObjetoPopular, methods[i].getReturnType()).invoke(objetoPopular, retorno);
					} catch (NoSuchMethodException e) {
						//metodo nao existe no objeto a Popular
						throw new NoSuchMethodException("Nenhum metodo correspondente encontrado na class " + objetoPopular.getClass().toString() + "! "
								+ "(method: " + nomeMetodoProcurarEmObjetoPopular + ", Param: " + methods[i].getReturnType() + ")");
					}
				}
			}
		}
	}
	
	public static void enviarEmail(File file, String msgAdicional) throws MessagingException {
		if (ConfigEnvioEmail.isEnviar()) {

		    //Get the session object
		    Properties properties = System.getProperties();
		    properties.setProperty("mail.smtp.host", ConfigEnvioEmail.getHost());
		    properties.setProperty("mail.smtp.port", ConfigEnvioEmail.getPort());
		    // SSL Factory
	        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        properties.put("mail.smtp.ssl.checkserveridentity", true);

		    Session session = Session.getDefaultInstance(properties);

		    //compose the message  
		    try{  
		    	MimeMessage message = new MimeMessage(session);
		    	Address from = new InternetAddress(ConfigEnvioEmail.getFrom());
		    	
		    	message.setFrom(from);
		    	message.setRecipients(Message.RecipientType.TO, ConfigEnvioEmail.getTo());
		    	message.setSubject("[DXC-ELO-IMPORT-INCOMING-REJ] - Arquivo Não Importado", "UTF-8");
		    	String msg = "O Batch <strong>elo-import-incoming-rej.jar</strong> (que realiza a Importacao dos Incoming da ELO das transações Rejeitadas)."
		    			+ "<br><br><strong>Não importou</strong> o seguinte arquivo: <strong>\"" + file.getAbsolutePath() + "\"</strong> pois o mesmo está com erro!"
		    			+ "<br><br>Arquivo movido para o diretório <strong>\"" + file.getParent() + "/ignorado\"</strong>"
		    			+ "<br><br>Favor validar o arquivo!";
		    	
		    	if (msgAdicional != null) {
		    		msg += "<br><br><strong>Dados Adicionais:</strong>"
		    			+ "<br>" + msgAdicional;
		    	}
		    	
		    	message.setContent(msg, "text/html; charset=utf-8"); //[fortify] Aplicacao roda em intranet - ambiente controlado

		    	// Send message
		    	Transport.send(message);
		    	LOG.info("[email] O email sobre arquivo rejeitado, foi enviado com sucesso!");
		    } catch (MessagingException e) {
		    	throw new MessagingException("ERRO ao enviar e-mail informando arquivo rejeitado!", e);
		    }	
		} else {
			LOG.info("[email] Envio de email sobre arquivo rejeitado esta desabilitado!");
		}
	}
	
	public static Date getDateFromStringYYYYMMDD(String data) throws InvalidValueException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			throw new InvalidValueException("\"" + data + "\" Nao eh uma data valida!", e);
		}
	}
	
	public static Double convertStringDouble(String value) {
		if (value == null || value.trim().length() == 0) {
			return 0.0;
		}
		return Double.valueOf(value);
	}
}