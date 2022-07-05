package io.github.educastilho.msavaliadorcredito.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.educastilho.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.educastilho.msavaliadorcredito.application.ex.ErroComunicacaoMicroServicesException;
import io.github.educastilho.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import io.github.educastilho.msavaliadorcredito.domain.model.DadosAvaliacao;
import io.github.educastilho.msavaliadorcredito.domain.model.DadosEmissaoCartao;
import io.github.educastilho.msavaliadorcredito.domain.model.ProtocoloSolicitacaoCartao;
import io.github.educastilho.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.github.educastilho.msavaliadorcredito.domain.model.SituacaoCliente;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {
	
	@Autowired
	private AvaliadorCreditoService avaliadorCreditoService;
	
	@GetMapping
	public String getAvaliadorCredito() {
		return "ok";
	}
	
	@GetMapping("/situacao-cliente")
	public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
		try {			
			SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
			
			return ResponseEntity.ok(situacaoCliente); 
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicacaoMicroServicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
		
	}
	
	@PostMapping("/cartoes-aprovados")
	public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
		
		try {			
			RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
			return ResponseEntity.ok(retornoAvaliacaoCliente); 
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicacaoMicroServicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}
	
	@PostMapping("/solicitacoes-cartao")
	public ResponseEntity solicitarCartao(@RequestBody DadosEmissaoCartao dados) {
		try {
			ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService.solicitarEmissaoCartao(dados);
			return ResponseEntity.ok(protocoloSolicitacaoCartao);
		} catch (ErroSolicitacaoCartaoException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
