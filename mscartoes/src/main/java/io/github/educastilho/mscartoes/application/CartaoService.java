package io.github.educastilho.mscartoes.application;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educastilho.mscartoes.domain.Cartao;
import io.github.educastilho.mscartoes.infra.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;
	
	@Transactional
	public Cartao save(Cartao cartao) {
		return repository.save(cartao);
	}
	
	public List<Cartao> getCartoesRendaMenorOuIgual(Long renda) {
		BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
		return repository.findByRendaLessThanEqual(rendaBigDecimal);
		//return repository.findAll();
	}
}
