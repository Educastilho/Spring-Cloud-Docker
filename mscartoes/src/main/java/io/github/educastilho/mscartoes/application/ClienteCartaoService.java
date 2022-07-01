package io.github.educastilho.mscartoes.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educastilho.mscartoes.application.representation.ClienteCartao;
import io.github.educastilho.mscartoes.infra.repository.ClienteCartaoRepository;

@Service
public class ClienteCartaoService {

	@Autowired
	private ClienteCartaoRepository repository;
	
	public List<ClienteCartao> listarCartoesByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
}
