package io.github.educastilho.msavaliadorcredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.educastilho.msavaliadorcredito.domain.model.Cartao;
import io.github.educastilho.msavaliadorcredito.domain.model.CartaoCliente;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoResourceClient {
	
	@GetMapping("/listaClientesCartao")
	ResponseEntity<List<CartaoCliente>> getClientesCartao(@RequestParam("cpf") String cpf );
	
	@GetMapping("/rendaAte")
	public ResponseEntity<List<Cartao>>  getCartoresRendaAte(@RequestParam ("renda") Long renda);
}

