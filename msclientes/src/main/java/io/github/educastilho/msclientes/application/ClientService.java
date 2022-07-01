package io.github.educastilho.msclientes.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educastilho.msclientes.domain.Cliente;
import io.github.educastilho.msclientes.infra.repository.ClienteRepository;

@Service
public class ClientService {

	@Autowired
	private ClienteRepository repository;
	
	@Transactional
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public Optional<Cliente> getByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
}
