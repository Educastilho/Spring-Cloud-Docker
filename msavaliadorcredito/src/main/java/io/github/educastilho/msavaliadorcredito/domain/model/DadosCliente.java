package io.github.educastilho.msavaliadorcredito.domain.model;

public class DadosCliente {

	private Long id;
	private String nome;
	private Integer idade;
	
	public DadosCliente() {
		super();
	}

	public DadosCliente(Long id, String nome, Integer idade) {
		super();
		this.id = id;
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "DadosCliente [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
	}

}
