package br.com.dxc.cards.core.controller;

import java.util.List;

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

import br.com.dxc.cards.core.dao.SinteticoDAO;
import br.com.dxc.cards.core.model.FiltroConsultar;
import br.com.dxc.cards.core.model.Sintetico;
import br.com.dxc.cards.core.model.request.ApiRequest;
import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;

@Controller
@RequestMapping("/core/sintetico")
public class SinteticoController extends BaseController {

	private static final Logger logger = LogManager.getLogger(SinteticoController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<List<Sintetico>>> getListSinteticoByFilter(@RequestBody ApiRequest<FiltroConsultar> request) { 
		ApiResponse<List<Sintetico>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;
			ret.setDados(new SinteticoDAO().getListSintetico(
					request.getFiltro().getIdAcquirer(),
					request.getFiltro().getIdProduto(),
					request.getFiltro().getIdTpTransacao(),
					request.getFiltro().getDataInicio(),
					request.getFiltro().getDataFim()
				)
			);

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/sintetico", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<List<Sintetico>>>(ret, getHttpHeaders(), httpStatus);
	}
}