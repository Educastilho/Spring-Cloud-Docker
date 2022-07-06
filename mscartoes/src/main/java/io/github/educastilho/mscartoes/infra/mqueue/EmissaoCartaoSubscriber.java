package io.github.educastilho.mscartoes.infra.mqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.educastilho.mscartoes.domain.Cartao;
import io.github.educastilho.mscartoes.domain.ClienteCartao;
import io.github.educastilho.mscartoes.domain.DadosEmissaoCartao;
import io.github.educastilho.mscartoes.infra.repository.CartaoRepository;
import io.github.educastilho.mscartoes.infra.repository.ClienteCartaoRepository;

@Component
public class EmissaoCartaoSubscriber {
	
	private static final Logger logger = LoggerFactory.getLogger(EmissaoCartaoSubscriber.class);
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteCartaoRepository clienteCartaoRepository;

	@RabbitListener(queues = "${mq.queues.emissao-cartoes}")
	public void receberEmissaoSolicitacao(@Payload String payload) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			DadosEmissaoCartao dadosEmissaoCartao = mapper.readValue(payload, DadosEmissaoCartao.class);
			Cartao cartao = cartaoRepository.findById(dadosEmissaoCartao.getIdCartao()).orElseThrow();
			ClienteCartao clienteCartao = new ClienteCartao();
			clienteCartao.setCartao(cartao);
			clienteCartao.setCpf(dadosEmissaoCartao.getCpf());
			clienteCartao.setLimite(dadosEmissaoCartao.getLimiteLiberado());
			clienteCartaoRepository.save(clienteCartao);
		} catch (JsonMappingException e) {
			logger.error("erro ao receber solicitação de emissão de cartão");
		} catch (JsonProcessingException e) {
			logger.error("erro ao receber solicitação de emissão de cartão");
		}
	}
}
