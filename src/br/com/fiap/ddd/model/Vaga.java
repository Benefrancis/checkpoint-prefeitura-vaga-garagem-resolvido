package br.com.fiap.ddd.model;

/**
 * classe que modela uma vaga de garagem
 * 
 * @author Francis
 *
 */
public class Vaga {

	private long id;
	private String numero;
	private boolean disponivel;

	public Vaga(long id, String numero, boolean disponivel) {
		super();
		this.id = id;
		this.numero = numero;
		this.disponivel = disponivel;
	}

	public Vaga() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

}
