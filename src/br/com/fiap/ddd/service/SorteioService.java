package br.com.fiap.ddd.service;

import java.util.List;
import java.util.Random;

import br.com.fiap.ddd.model.Funcionario;
import br.com.fiap.ddd.repository.SorteioRepository;

/**
 * Servi�os para regras de neg�cio referente a sorteios
 * 
 * @author Francis
 *
 */
public class SorteioService {
	/**
	 * Realiza o sorteio entre os funcion�rios e determina aquele que usar� a vaga
	 * de garagem
	 * 
	 * @param ano
	 * @param mes
	 * @param funcionarios
	 * @param repoSorteio
	 * @return a posi��o na lista de funcion�rios
	 */
	public static int sortear(int ano, int mes, List<Funcionario> funcionarios, SorteioRepository repoSorteio) {

		Random r = new Random();

		int posicaoSorteada = r.nextInt(funcionarios.size());

		int countTentivasDeSorteio = 0;

		// Para evitar que o mesmo funcion�rio tenha duas vagas no mesmo mes e ano.
		// Fica no looping at� achar um funcion�rio que n�o foi sorteado neste m�s e ano
		// ou at� que se atinja o total de funcion�rios.
		while (repoSorteio.jaSorteado(funcionarios.get(posicaoSorteada).getId(), mes, ano) == true 
				&& countTentivasDeSorteio < funcionarios.size()) {
			posicaoSorteada = r.nextInt(funcionarios.size());
			countTentivasDeSorteio++;
		}

		return posicaoSorteada;
	}
}
