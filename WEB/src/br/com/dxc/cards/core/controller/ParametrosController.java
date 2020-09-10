package br.com.dxc.cards.core.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import br.com.dxc.cards.core.model.Parametro;
import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;
import br.com.dxc.cards.core.utils.ParametroCache;

@Controller
@RequestMapping("/core/parametros")
public class ParametrosController extends BaseController {
	private static final Logger logger = LogManager.getLogger(ParametrosController.class);

	@RequestMapping(value = "/codtransacoes", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ApiResponse<List<Parametro>>> getListCodtransacoes() {
		ApiResponse<List<Parametro>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;
			
			//retorna a lista salva
			ret.setDados( ParametroCache.getListCodTransacoes() );

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/parametros/codtransacoes", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<List<Parametro>>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/credenciadores", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ApiResponse<List<Parametro>>> getListCredenciadores() {
		ApiResponse<List<Parametro>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;

			//retorna a lista salva
			ret.setDados( ParametroCache.getListCredenciadores() );

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/parametros/credenciadores", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<List<Parametro>>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/reset-cache", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ApiResponse<String>> restCache() {
		ApiResponse<String> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;
			
			//resetando cache (na proxima consulta, vai buscar no banco)
			ParametroCache.cargaValores();

			//retorna a lista salva
			ret.setDados( "Cache Parametros Resetados" );

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/parametros/reset-cache", ex);
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
