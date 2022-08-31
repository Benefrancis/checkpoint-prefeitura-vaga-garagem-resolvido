package br.com.fiap.ddd.model;

/**
 * Classe que modela um funcionário da prefeitura.
 * 
 * @author Francis
 *
 */
public class Funcionario {
	private long id;
	private String nome;
	private boolean chefe;

	public Funcionario(long id, String nome, boolean chefe) {
		super();
		this.id = id;
		this.nome = nome;
		this.chefe = chefe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isChefe() {
		return chefe;
	}

	public void setChefe(boolean chefe) {
		this.chefe = chefe;
	}

}