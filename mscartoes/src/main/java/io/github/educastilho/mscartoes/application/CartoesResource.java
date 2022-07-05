package io.github.educastilho.mscartoes.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.educastilho.mscartoes.application.representation.CartaoSaveRequest;
import io.github.educastilho.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.educastilho.mscartoes.domain.Cartao;
import io.github.educastilho.mscartoes.domain.ClienteCartao;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private ClienteCartaoService cartaoClienteService;
	
	@GetMapping
	public String getCartoes() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity cadastro(@RequestBody CartaoSaveRequest request) {
		Cartao cartao = request.toModel();
		cartaoService.save(cartao);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/rendaAte")
	public ResponseEntity<List<Cartao>>  getCartoresRendaAte(@RequestParam ("renda") Long renda) {
		List<Cartao> cartoes = cartaoService.getCartoesRendaMenorOuIgual(renda);
		return ResponseEntity.ok(cartoes);
	}
	
	@GetMapping("/listaClientesCartao")
	public ResponseEntity<List<CartoesPorClienteResponse>> getClientesCartao(@RequestParam("cpf") String cpf ) {
		List<ClienteCartao> lista = cartaoClienteService.listarCartoesByCpf(cpf);
		List<CartoesPorClienteResponse> listaCartoesPorCliente = lista.stream().map(item -> {
			CartoesPorClienteResponse response = new CartoesPorClienteResponse(item.getCartao().getNome(), item.getCartao().getBandeira().toString(), item.getLimite());
			return response;
		}).toList();
		return ResponseEntity.ok(listaCartoesPorCliente);
	}
}
