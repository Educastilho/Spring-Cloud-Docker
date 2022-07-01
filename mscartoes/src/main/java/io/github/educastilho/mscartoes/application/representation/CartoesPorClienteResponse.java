package io.github.educastilho.mscartoes.application.representation;

import java.math.BigDecimal;

public class CartoesPorClienteResponse {

	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
	public CartoesPorClienteResponse() {
		super();
	}
	
	public CartoesPorClienteResponse(String nome, String bandeira, BigDecimal limiteLiberado) {
		super();
		this.nome = nome;
		this.bandeira = bandeira;
		this.limiteLiberado = limiteLiberado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public BigDecimal getLimiteLiberado() {
		return limiteLiberado;
	}

	public void setLimiteLiberado(BigDecimal limiteLiberado) {
		this.limiteLiberado = limiteLiberado;
	}

	@Override
	public String toString() {
		return "CartoesPorClienteResponse [nome=" + nome + ", bandeira=" + bandeira + ", limiteLiberado="
				+ limiteLiberado + "]";
	}
	
}
