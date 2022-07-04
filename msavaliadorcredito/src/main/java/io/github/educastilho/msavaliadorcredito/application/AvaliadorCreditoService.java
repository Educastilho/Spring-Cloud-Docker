package io.github.educastilho.msavaliadorcredito.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import feign.FeignException.FeignClientException;
import io.github.educastilho.msavaliadorcredito.domain.model.Cartao;
import io.github.educastilho.msavaliadorcredito.domain.model.CartaoAprovado;
import io.github.educastilho.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.educastilho.msavaliadorcredito.domain.model.DadosCliente;
import io.github.educastilho.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.github.educastilho.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.educastilho.msavaliadorcredito.infra.clients.CartaoResourceClient;
import io.github.educastilho.msavaliadorcredito.infra.clients.ClienteResourceClient;

@Service
public class AvaliadorCreditoService {
	
	@Autowired
	private ClienteResourceClient clientesClient;
	
	@Autowired
	private CartaoResourceClient cartaoClient;

	public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroServicesException {
		
		try {
			
			ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
			
			DadosCliente clienteResponse = dadosClienteResponse.getBody();
			
			ResponseEntity<List<CartaoCliente>> dadosCartaoResponse = cartaoClient.getClientesCartao(cpf);
			
			List<CartaoCliente> cartaoResponse = dadosCartaoResponse.getBody();
			
			return new SituacaoCliente(clienteResponse, cartaoResponse);
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			
			if(HttpStatus.NOT_FOUND.equals(status)) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicroServicesException(e.getMessage(), status);
		}
		
	}

	public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroServicesException {
		
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
			
			DadosCliente clienteResponse = dadosClienteResponse.getBody();
			
			ResponseEntity<List<Cartao>> dadosCartaoResponse = cartaoClient.getCartoresRendaAte(renda);
			
			List<Cartao> cartaoResponse = dadosCartaoResponse.getBody();
			
			List<CartaoAprovado> cartoesAprovados = cartaoResponse.stream().map(item -> {
				DadosCliente dadosCliente = dadosClienteResponse.getBody();
				
				BigDecimal limiteBasico = item.getLimiteBasico();
				BigDecimal rendaBD = BigDecimal.valueOf(renda);
				BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
				
				BigDecimal limiteAprovado = idadeBD.divide(BigDecimal.valueOf(10)).multiply(limiteBasico);
				
				CartaoAprovado cartaoAprovado = new CartaoAprovado();
				cartaoAprovado.setCartao(item.getNome());
				cartaoAprovado.setBandeira(item.getBandeira());
				cartaoAprovado.setLimiteAprovado(limiteAprovado);
				
				return cartaoAprovado;
			}).collect(Collectors.toList());
			
			return new RetornoAvaliacaoCliente(cartoesAprovados);
			
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			
			if(HttpStatus.NOT_FOUND.equals(status)) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicroServicesException(e.getMessage(), status);
		}
		
	}
	
}
