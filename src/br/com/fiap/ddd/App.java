package br.com.fiap.ddd;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.ddd.model.Funcionario;
import br.com.fiap.ddd.model.Sorteio;
import br.com.fiap.ddd.model.Vaga;
import br.com.fiap.ddd.repository.FuncionarioRepository;
import br.com.fiap.ddd.repository.SorteioRepository;
import br.com.fiap.ddd.repository.VagasRepository;
import br.com.fiap.ddd.service.SorteioService;

public class App {

	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		int opcaoSelecionada = 0;

		do {

			opcaoSelecionada = montaMenu();

			switch (opcaoSelecionada) {
			case 1:
				realizarSorteio();
				break;
			case 2:
				gerarRelatorio(0, 0);
				break;
			default:
				System.out.println("\n\n===================   Have a nice day!   =========================");
				sc.close();
				System.exit(0);
				break;
			}

		} while (opcaoSelecionada < 0 || opcaoSelecionada > 2);

		sc.close();

	}

	/**
	 * Método para montagem de menú de opções da Tela Principal
	 * 
	 * @param sc
	 * @return
	 */
	private static int montaMenu() {

		System.out.println("**************************************************************");
		System.out.println("                                                              ");
		System.out.println("                                                              ");
		System.out.println("      BEM VINDO AO SISTEMA DE SORTEIO DE VAGAS DE GARAGEM     ");
		System.out.println("                                                              ");
		System.out.println("               PREFEITURA DE FIAPLANDIA                       ");
		System.out.println("                                                              ");
		System.out.println("                                                              ");
		System.out.println("**************************************************************");
		System.out.println("                                                              ");
		System.out.println("      --    INFORME A OPERAÇÃO QUE DESEJA REALIZAR    --      ");
		System.out.println("                                                              ");
		System.out.println("[ 1 ]  -  REALIZAR O SORTEIO MENSAL DE VAGAS DE GARAGEM");
		System.out.println("[ 2 ]  -  GERAR RELATÓRIO DO SORTEIO MENSAL DE VAGAS DE GARAGEM");
		System.out.println("[ 0 ]  -  ENCERRAR O SISTEMA");
		System.out.println("                                                              ");

		int opcao = sc.nextInt();

		if (opcao < 0 || opcao > 2) {
			System.exit(1);
		}

		return opcao;

	}

	/**
	 * Realizando sorteio
	 * 
	 * @param sc
	 */
	public static boolean realizarSorteio() {

		System.out.print("Realizaremos o sorteio de vagas de garagem.\nPor favor,");

		int ano = 0;
		do {
			System.out.println("\nInforme o ano: \n");
			ano = sc.nextInt();
		} while (ano < 1900 || ano > 2999);

		int mes = 0;
		do {
			System.out.println("\nInforme o Mês: \n");
			mes = sc.nextInt();
		} while (mes < 1 || mes > 12);

		SorteioRepository repoSorteio = new SorteioRepository();

		boolean jaExisteSorteio = repoSorteio.jaSorteado(mes, ano);

		if (jaExisteSorteio) {

			String resposta = sc.nextLine();

			do {
				System.out.println();
				System.out.println("**************************************************************");
				System.out.println("                                                              ");
				System.out.println("     JÁEXISTE SORTEIO REALIZADO PAR O MÊS E ANO INFORMADO     ");
				System.out.println("     DESEJA REALIZAR UM NOVO SORTEIO PARA " + mes + "/" + ano + "   ");
				System.out.println("                                                              ");
				System.out.println("                   [ S ] SIM      [ N ] NÃO                   ");
				System.out.println("**************************************************************");

				System.out.println();
				resposta = sc.nextLine();
			} while (!resposta.toUpperCase().trim().contentEquals("S")
					&& !resposta.toUpperCase().trim().contentEquals("N"));

			if (resposta.toUpperCase().contentEquals("N")) {
				main(null);
				return false;
			} else {

				repoSorteio.delete(mes, ano);

			}

		}

		FuncionarioRepository repoFuncionario = new FuncionarioRepository();
		List<Funcionario> funcionarios = repoFuncionario.findByChefe(false);

		VagasRepository repoVaga = new VagasRepository();
		List<Vaga> vagas = repoVaga.findByDisponivel(true);

		// @formatter:off
 		System.out.println("Sortearemos " + vagas.size() + " vagas de garagem entre " + funcionarios.size() + " funcionários.\n\n");
 		// @formatter:on

		if (vagas.size() > 0) {

			for (Vaga vaga : vagas) {

			// @formatter:off
 			int posicaoSorteada = SorteioService.sortear(ano, mes, funcionarios, repoSorteio);
			
 			repoSorteio.save(funcionarios.get(posicaoSorteada).getId(), vaga.getId(), mes, ano);
 			
			// @formatter:on

			}
			gerarRelatorio(ano, mes);
		}
		return true;
	}

	/**
	 * Gera relatório dos Sorteios realizados
	 * 
	 * @param sc
	 */
	public static void gerarRelatorio(int ano, int mes) {

		SorteioRepository repoSorteio = new SorteioRepository();

		if (ano == 0 && mes == 0)
			System.out.print("Iremos Gerar o relatódio do sorteio de vagas de garagem.\nPor favor,");
		
		if (ano < 1900 || ano > 2999) {

			do {
				System.out.println("\nInforme o ano: \n");
				ano = sc.nextInt();
			} while (ano < 1900 || ano > 2999);

		}

		if (mes < 1 || mes > 12) {
			do {
				System.out.println("\nInforme o Mês: \n");
				mes = sc.nextInt();
			} while (mes < 1 || mes > 12);
		}

		List<Sorteio> sorteios = repoSorteio.findByAnoAndMes(ano, mes);
		
		if (sorteios.size() > 0) {

			System.out.println("\n\n+-----+-----+-------------------------------------------------+------+");
			System.out.printf("%s%s%-50s%s%n", "|ANO  ", "|MES  ", "|FUNCIONÁRIO", "|VAGA  |");
			System.out.println("'-----+-----+-------------------------------------------------+------'");

			for (Sorteio sorteio : sorteios) {
				System.out.println(sorteio);
			}
			System.out.println("'-----+-----+-------------------------------------------------+------'");
		}
	}

}