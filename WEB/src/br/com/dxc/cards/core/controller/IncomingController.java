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

import br.com.dxc.cards.core.dao.IncomingDAO;
import br.com.dxc.cards.core.model.Batimento;
import br.com.dxc.cards.core.model.FiltroConsultar;
import br.com.dxc.cards.core.model.FiltroConsultarMaisInfo;
import br.com.dxc.cards.core.model.IncomingCodRetPayware;
import br.com.dxc.cards.core.model.IncomingHistorico;
import br.com.dxc.cards.core.model.IncomingMaisInformacoes;
import br.com.dxc.cards.core.model.IncomingWrapper;
import br.com.dxc.cards.core.model.request.ApiRequest;
import br.com.dxc.cards.core.model.response.ApiError;
import br.com.dxc.cards.core.model.response.ApiResponse;

@Controller
@RequestMapping("/core/incoming")
public class IncomingController extends BaseController {

	private static final Logger logger = LogManager.getLogger(IncomingController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<IncomingWrapper>> getListIncomingByFilter(@RequestBody ApiRequest<FiltroConsultar> request) { 
		ApiResponse<IncomingWrapper> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;
			ret.setDados(new IncomingDAO().getListIncoming(
					request.getFiltro().getIdAcquirer(),
					request.getFiltro().getIdProduto(),
					request.getFiltro().getIdTpTransacao(),
					request.getFiltro().getDataInicio(),
					request.getFiltro().getDataFim(),
					request.getFiltro().getCodErro()
				)
			);

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/incoming", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		
		return new ResponseEntity<ApiResponse<IncomingWrapper>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/historico", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<List<IncomingHistorico>>> getIncomingHistorico(@RequestBody ApiRequest<FiltroConsultarMaisInfo> request) { 
		ApiResponse<List<IncomingHistorico>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;
		
		try {
			httpStatus = HttpStatus.OK;
			ret.setDados(new IncomingDAO().getIncomingHistorico(request.getFiltro().getNumRefTrans(), request.getFiltro().getNrParcela()));

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/incoming/historico", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		return new ResponseEntity<ApiResponse<List<IncomingHistorico>>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/mais-informacoes", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<IncomingMaisInformacoes>> getIncomingMaisInformacoes(@RequestBody ApiRequest<FiltroConsultarMaisInfo> request) { 
		ApiResponse<IncomingMaisInformacoes> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;

		try {
			httpStatus = HttpStatus.OK;
			ret.setDados(new IncomingDAO().getIncomingMaisInformacoes(request.getFiltro().getIdMatriz(), request.getFiltro().getIdItem(), request.getFiltro().getIdTpTrans()));

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/incoming/mais-informacoes", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		return new ResponseEntity<ApiResponse<IncomingMaisInformacoes>>(ret, getHttpHeaders(), httpStatus);
	}
	
	@RequestMapping(value = "/ret-arq-payware", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ApiResponse<List<IncomingCodRetPayware>>> getRetornoArqPayware(@RequestBody ApiRequest<Batimento> request) { 
		ApiResponse<List<IncomingCodRetPayware>> ret = new ApiResponse<>();
		HttpStatus httpStatus=null;

		try {
			httpStatus = HttpStatus.OK;
			ret.setDados(new IncomingDAO().getCodRetornoArqPayware(request.getFiltro().getDataMovimento()));

		} catch(Exception ex) { //[fortify] Aplicacao pequena, tratamento simplificado para excecao
			logger.error("fail /core/incoming/ret-arq-payware", ex);
			if (ex instanceof HttpClientErrorException) {
				httpStatus = ((HttpClientErrorException) ex).getStatusCode();
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ((HttpClientErrorException) ex).getResponseBodyAsString()));
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				ret.setFalha(new ApiError(ex.getLocalizedMessage(), ex.getMessage()));
			}
		}
		return new ResponseEntity<ApiResponse<List<IncomingCodRetPayware>>>(ret, getHttpHeaders(), httpStatus);
	}	
	
}