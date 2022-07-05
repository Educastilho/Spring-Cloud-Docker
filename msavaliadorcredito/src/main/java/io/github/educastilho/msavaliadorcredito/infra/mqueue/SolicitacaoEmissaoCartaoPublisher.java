package io.github.educastilho.msavaliadorcredito.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.educastilho.msavaliadorcredito.domain.model.DadosEmissaoCartao;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queueEmissaoCartoes;
	
	public void solicitarCartao(DadosEmissaoCartao dados) throws JsonProcessingException {
		String dadosJson = convertIntoJson(dados);
		rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), dadosJson);
	}
	
	private String convertIntoJson(DadosEmissaoCartao dados) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String dadosJson = mapper.writeValueAsString(dados);
		return dadosJson;
	}
}
