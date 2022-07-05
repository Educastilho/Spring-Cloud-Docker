package io.github.educastilho.msavaliadorcredito.application.ex;

public class DadosClienteNotFoundException extends Exception {

	public DadosClienteNotFoundException() {
		super("Dados do Cliente não encontrado para CPF informado!");
	}	
	
}
