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

import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;
import br.com.dxc.cards.core.proxy.AccessControlProxy;

@Controller
public class LogoffController extends BaseController {
	private static final Logger logger = LogManager.getLogger(LogoffController.class);

	@RequestMapping(value = "/core/logoff", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ApiResponse<String>> logoff(HttpServletRequest request) throws Exception {
		ApiResponse<String> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		try {			
		    request.getSession().invalidate();

			httpStatus = HttpStatus.OK;
			ret.setDados(AccessControlProxy.getAccessControlLoginPage());
		} catch(Exception ex) {
			logger.error("fail /core/logoff", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<String>>(ret, getHttpHeaders(), httpStatus);
	}
}
