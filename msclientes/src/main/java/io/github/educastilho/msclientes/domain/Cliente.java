package io.github.educastilho.msclientes.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String cpf;
	
	@Column
	private String nome;
		
	@Column
	private Integer idade;
	
	public Cliente() {
		
	}

	public Cliente(String cpf, String nome, Integer idade) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}

	public Cliente(Long id, String cpf, String nome, Integer idade) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + "]";
	}
	
	
}
