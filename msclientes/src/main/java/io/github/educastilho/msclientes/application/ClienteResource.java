package io.github.educastilho.msclientes.application;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.educastilho.msclientes.application.representation.ClientSaveRequest;
import io.github.educastilho.msclientes.domain.Cliente;

@RestController
@RequestMapping("clientes")
public class ClienteResource {
	
	@Autowired
	private ClientService service;
	
	Logger logger = LoggerFactory.getLogger(ClienteResource.class);

	@GetMapping
	public String status() {
		logger.info("obtendo o status do microservico de clientes");
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody ClientSaveRequest request) {
		Cliente cliente = request.toModel();
		service.save(cliente);
		URI headerLocation = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.query("cpf={cpf}")
				.buildAndExpand(cliente.getCpf())
				.toUri();
		return ResponseEntity.created(headerLocation).build();
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
		Optional<Cliente> cliente = service.getByCpf(cpf);
		
		if(cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
}
