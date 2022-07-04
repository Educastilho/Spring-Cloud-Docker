package io.github.educastilho.msavaliadorcredito.domain.model;

import java.util.List;

public class RetornoAvaliacaoCliente {

	private List<CartaoAprovado> cartoes;

	public RetornoAvaliacaoCliente() {
	}

	public RetornoAvaliacaoCliente(List<CartaoAprovado> cartoes) {
		this.cartoes = cartoes;
	}

	public List<CartaoAprovado> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoAprovado> cartoes) {
		this.cartoes = cartoes;
	}
	
}
