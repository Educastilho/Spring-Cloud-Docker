package io.github.educastilho.msclientes.application.representation;

import java.util.Objects;

import io.github.educastilho.msclientes.domain.Cliente;
import lombok.Data;

public class ClientSaveRequest {
	private String cpf;
	private String nome;
	private Integer idade;
	
	public Cliente toModel() {
		return new Cliente(cpf, nome, idade);
	}

	public ClientSaveRequest(String cpf, String nome, Integer idade) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, idade, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientSaveRequest other = (ClientSaveRequest) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(idade, other.idade) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "ClientSaveRequest [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + "]";
	}
	
	
}
