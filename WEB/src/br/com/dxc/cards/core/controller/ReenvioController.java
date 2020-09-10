package br.com.dxc.cards.core.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import br.com.dxc.cards.core.dao.ReenvioDAO;
import br.com.dxc.cards.core.model.Reenvio;
import br.com.dxc.cards.core.model.request.ApiRequest;
import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;

@Controller
@RequestMapping(value = "/core/reenvio", produces = "application/json;charset=UTF-8")
public class ReenvioController extends BaseController {

    private static final Logger logger = LogManager.getLogger(ReenvioController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<String>> reenviar(@RequestBody ApiRequest<Reenvio> request) { 
		ApiResponse<String> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
            httpStatus = HttpStatus.OK;
            new ReenvioDAO().salvarReenvio(
                request.getDados().getUsuario(),
                request.getDados().getData(),
                request.getDados().getIdsIncomings()
            );
            ret.setDados("Informações de reenvio salvas!");

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/reenvio", ex);
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