package io.github.educastilho.mscartoes.domain;

import java.math.BigDecimal;

public class DadosEmissaoCartao {
	
	private Long idCartao;
	private String cpf;
	private String endereco;
	private BigDecimal limiteLiberado;
	
	public DadosEmissaoCartao() {

	}

	public DadosEmissaoCartao(Long idCartao, String cpf, String endereco, BigDecimal limiteLiberado) {
		this.idCartao = idCartao;
		this.cpf = cpf;
		this.endereco = endereco;
		this.limiteLiberado = limiteLiberado;
	}

	public Long getIdCartao() {
		return idCartao;
	}

	public void setIdCartao(Long idCartao) {
		this.idCartao = idCartao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getLimiteLiberado() {
		return limiteLiberado;
	}

	public void setLimiteLiberado(BigDecimal limiteLiberado) {
		this.limiteLiberado = limiteLiberado;
	}

	@Override
	public String toString() {
		return "DadosEmissaoCartao [idCartao=" + idCartao + ", cpf=" + cpf + ", endereco=" + endereco
				+ ", limiteLiberado=" + limiteLiberado + "]";
	}
	
}
