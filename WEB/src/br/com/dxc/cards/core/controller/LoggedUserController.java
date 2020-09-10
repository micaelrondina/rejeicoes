package br.com.dxc.cards.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import br.com.dxc.cards.core.model.LoggedUser;
import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;

@Controller
public class LoggedUserController extends BaseController {
	private static final Logger logger = LogManager.getLogger(LoggedUserController.class);

	@RequestMapping(value = "/core/usuario", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ApiResponse<LoggedUser>> logoff(HttpServletRequest request) {
		ApiResponse<LoggedUser> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;
			ret.setDados((LoggedUser)request.getSession().getAttribute("loggedUserBean"));
		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/usuario", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<LoggedUser>>(ret, getHttpHeaders(), httpStatus);
	}

}
