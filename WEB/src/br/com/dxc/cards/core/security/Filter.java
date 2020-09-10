package  br.com.dxc.cards.core.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.dxc.cards.core.model.LoggedUser;
import br.com.dxc.cards.core.proxy.AccessControlProxy;

public class Filter implements javax.servlet.Filter {
	private static final Logger logger = LogManager.getLogger(Filter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		res.setHeader("Access-Control-Allow-Origin", "*");

		if (session.getAttribute("loggedUserBean") != null) {
			chain.doFilter(request, response);
			return;
		}
		if ((req.getRequestURL() != null) && ((req.getRequestURL().toString()
				.startsWith("http://localhost:8080/CMS-ACQUIRER-DXC-INCOMING-ELO/"))
				|| (req.getRequestURL().toString().startsWith("http://localhost:4200/CMS-ACQUIRER-DXC-INCOMING-ELO/")))) {
			logger.info("modo desenv");
			LoggedUser userBean = new LoggedUser();

			userBean.setName("Desenv");
			userBean.setToken("dev_token");
			userBean.setTasks(Arrays.asList("dxc.incoming.elo.cons", "dxc.incoming.elo.envrej", "dxc.incoming.elo.batment"));

			session.setAttribute("loggedUserBean", userBean);
			chain.doFilter(request, response);
			logger.info("usuario: " + userBean.getName());

			return;
		}
		String accessKey = CookieUtil.getCookieValue((HttpServletRequest) request, "authaccess_v2");
		logger.info("accesskey ok " + accessKey);
		if (accessKey == null) {
			res.setStatus(401);
			res.getWriter().print(AccessControlProxy.getAccessControlLoginPage());
			return;
		}
		String[] authData = accessKey.split("\\|");

		String user = authData[0];
		String token = authData[1];
		List<String> tasks = null;
		try {
			tasks = AccessControlProxy.getUserTasks(user, token, "CMSACQUIRERDXCINCOMINGELO");
		} catch (Exception ex) {
			res.setStatus(401);
			res.getWriter().print("HTTP/401 - Falha no acesso aos dados do AccessControl: " + ex.getMessage()); //[fortify] Aplicacao roda em intranet - ambiente controlado
			logger.error(ex.getMessage(), ex);
			return;
		}
		if ((tasks == null) || (tasks.isEmpty())) {
			res.setStatus(401);
			res.getWriter().print("HTTP/401 - Usuario " + user + " nao tem permissao para acessar este sistema"); //[fortify] Aplicacao roda em intranet - ambiente controlado
			return;
		}
		LoggedUser userBean = new LoggedUser();
		userBean.setName(user);
		userBean.setToken(token);
		userBean.setTasks(tasks);

		session.setAttribute("loggedUserBean", userBean); //[fortify] Aplicacao roda em intranet - ambiente controlado

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}
}
