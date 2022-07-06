package io.github.educastilho.mscartoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"io.github.educastilho"})
@EnableEurekaClient
@EnableRabbit
public class MscartoesApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MscartoesApplication.class);

	public static void main(String[] args) {
		logger.info("informação: {}", "teste info");
		logger.error("informação: {}", "teste error");
		logger.warn("informação: {}", "teste warning");
		SpringApplication.run(MscartoesApplication.class, args);
	}

}
