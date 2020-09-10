package  br.com.dxc.cards.core.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CookieUtil {
	private static final Logger logger = LogManager.getLogger(CookieUtil.class);
	
	public static void removeCookie(String name){
	        Cookie ck = new Cookie(name, "");//[fortify] Aplicacao roda em intranet - ambiente controlado
	        ck.setMaxAge(0); //Cookie expira
	        logger.info("Cookie removido - " + name);
	}

	public static String getCookieValue(HttpServletRequest request, String name){
	    String result = null;
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null){
	        int i = 0;
	        while (i < cookies.length){
	            if (cookies[i].getName().equals(name)){
	                result = cookies[i].getValue();
	                break;
	            }
	            i++;
	        }
	    }
	    return (result);
	}
}