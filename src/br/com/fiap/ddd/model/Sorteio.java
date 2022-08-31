package br.com.fiap.ddd.model;

public class Sorteio {
	
	private int ano;
	private int mes;
	private Funcionario funcionario;
	private Vaga vaga;

	public Sorteio(int ano, int mes, Funcionario funcionario, Vaga vaga) {
		super();
		this.ano = ano;
		this.mes = mes;
		this.funcionario = funcionario;
		this.vaga = vaga;
	}

	public Sorteio() {
		super();
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	@Override
	public String toString() {
		
		String ret = String.format("%s%s%-50s%s", "|" +  ano , " | " +mes, "   | " +funcionario.getNome(), "   | " +vaga.getNumero() +"  |" );
		return ret;
		//return "[" + ano + " | " + mes + " | " + funcionario.getNome() + " | " + vaga.getNumero() + "]";
	}

}
