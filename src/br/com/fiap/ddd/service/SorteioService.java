package br.com.fiap.ddd.service;

import java.util.List;
import java.util.Random;

import br.com.fiap.ddd.model.Funcionario;
import br.com.fiap.ddd.repository.SorteioRepository;

/**
 * Serviços para regras de negócio referente a sorteios
 * 
 * @author Francis
 *
 */
public class SorteioService {
	/**
	 * Realiza o sorteio entre os funcionários e determina aquele que usará a vaga
	 * de garagem
	 * 
	 * @param ano
	 * @param mes
	 * @param funcionarios
	 * @param repoSorteio
	 * @return a posição na lista de funcionários
	 */
	public static int sortear(int ano, int mes, List<Funcionario> funcionarios, SorteioRepository repoSorteio) {

		Random r = new Random();

		int posicaoSorteada = r.nextInt(funcionarios.size());

		int countTentivasDeSorteio = 0;

		// Para evitar que o mesmo funcionário tenha duas vagas no mesmo mes e ano.
		// Fica no looping até achar um funcionário que não foi sorteado neste mês e ano
		// ou até que se atinja o total de funcionários.
		while (repoSorteio.jaSorteado(funcionarios.get(posicaoSorteada).getId(), mes, ano) == true 
				&& countTentivasDeSorteio < funcionarios.size()) {
			posicaoSorteada = r.nextInt(funcionarios.size());
			countTentivasDeSorteio++;
		}

		return posicaoSorteada;
	}
}
