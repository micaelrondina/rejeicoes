package br.com.dxc.cards.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import br.com.dxc.cards.core.dao.BatimentoDAO;
import br.com.dxc.cards.core.model.Batimento;
import br.com.dxc.cards.core.model.Incoming;
import br.com.dxc.cards.core.model.LoggedUser;
import br.com.dxc.cards.core.model.ReturnProcBatAcatado;
import br.com.dxc.cards.core.model.request.ApiRequest;
import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;

@Controller
@RequestMapping("/core/batimento")
public class BatimentoController extends BaseController {
	
	private static final Logger logger = LogManager.getLogger(BatimentoController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<List<Incoming>>> rejeitado(@RequestBody ApiRequest<Batimento> request) { 
		ApiResponse<List<Incoming>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
            httpStatus = HttpStatus.OK;
            ret.setDados(new BatimentoDAO().getBatimento(request.getFiltro().getDataMovimento(), null));

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/batimento", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<List<Incoming>>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/processar-rejeitado", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<ApiResponse<String>> processarRejeitado(@RequestBody ApiRequest<Batimento> request, HttpServletRequest httpRequest) {
		ApiResponse<String> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
            httpStatus = HttpStatus.OK;
            logger.info("Iniciando Batimento Rejeitado...");
            
            Long qtdProcessados = new BatimentoDAO().processarBatimentoRejeitadoManualmente(
            		request.getFiltro().getDataMovimento(), ((LoggedUser)httpRequest.getSession().getAttribute("loggedUserBean")).getName());
            ret.setDados(qtdProcessados + " Batimento(s) Rejeitado(s) Processado(s) com Sucesso");
            logger.info(ret.getDados());

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/batimento/processar-rejeitado", ex);
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
	
	@RequestMapping(value = "/processar-acatado", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<ApiResponse<ReturnProcBatAcatado>> processarAcatado(HttpServletRequest httpRequest) { 
		ApiResponse<ReturnProcBatAcatado> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
            httpStatus = HttpStatus.OK;
            logger.info("Iniciando Batimento Acatado...");
            
            ReturnProcBatAcatado returnProcBatAcatado = new BatimentoDAO().processarBatimentoAcatadoManualmente(((LoggedUser)httpRequest.getSession().getAttribute("loggedUserBean")).getName());
            returnProcBatAcatado.setMensagem(returnProcBatAcatado.getQtdItensMarcadosComoAcatados() + " Batimento(s) Acatado(s) Processado(s) com Sucesso");
            
            ret.setDados(returnProcBatAcatado);
            logger.info(ret.getDados());

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/batimento/processar-acatado", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}

		return new ResponseEntity<ApiResponse<ReturnProcBatAcatado>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/get-by-ids", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<List<Incoming>>> exibirBatimentosAcatadosById(@RequestBody ApiRequest<String> request) { 
		ApiResponse<List<Incoming>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
            httpStatus = HttpStatus.OK;
            ret.setDados(new BatimentoDAO().getBatimento(null, request.getFiltro()));

		} catch(Exception ex) {
			logger.error("fail /core/batimento/get-by-ids", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<List<Incoming>>>(ret, getHttpHeaders(), httpStatus);
	}

}
