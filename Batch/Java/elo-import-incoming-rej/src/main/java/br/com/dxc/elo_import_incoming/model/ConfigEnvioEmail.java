package br.com.dxc.elo_import_incoming.model;

public class ConfigEnvioEmail {
	private static boolean enviar;
	private static String host;
	private static String port;
	private static String to;
	private static String from;
	
	public static boolean isEnviar() {
		return enviar;
	}
	public static void setEnviar(boolean enviar) {
		ConfigEnvioEmail.enviar = enviar;
	}
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		ConfigEnvioEmail.host = host;
	}
	public static String getPort() {
		return port;
	}
	public static void setPort(String port) {
		ConfigEnvioEmail.port = port;
	}
	public static String getTo() {
		return to;
	}
	public static void setTo(String to) {
		ConfigEnvioEmail.to = to;
	}
	public static String getFrom() {
		return from;
	}
	public static void setFrom(String from) {
		ConfigEnvioEmail.from = from;
	}
}
