package br.com.fiap.ddd.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ddd.model.Funcionario;
import br.com.fiap.ddd.model.Sorteio;
import br.com.fiap.ddd.model.Vaga;

/**
 * Reposit�rio para registro dos sorteios realizados.
 * 
 * @author Francis
 *
 */
public class SorteioRepository extends Repository {

	public SorteioRepository() {
		super();
	}

	/**
	 * Verifica se um funcion�rio j� foi sorteado num m�s e ano determinado pelos
	 * par�metros:
	 * 
	 * Select * from SORTE_VAGA_GARAGEM where ANO =? and MES =? and FUNCIONARIO_ID
	 * =?
	 * 
	 * @param id_funcionario
	 * @param mes
	 * @param ano
	 * @return
	 */
	public boolean jaSorteado(long id_funcionario, int mes, int ano) {

		String sql = "SELECT * FROM SORTE_VAGA_GARAGEM where funcionario_id=? and mes=? and ano =?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setLong(1, id_funcionario);
			ps.setInt(2, mes);
			ps.setInt(3, ano);

			rs = ps.executeQuery();

			return rs.isBeforeFirst();

		} catch (SQLException e) {
			System.out.println("N�o conseguimos verificar se j� existe funcionario sorteado: " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
			}
			if (this.connection != null)
				this.closeConnection();
		}

		return false;
	}

	// @formatter:off
	// Select * from SORTE_VAGA_GARAGEM where ANO =? and MES =? 
	// @formatter:on
	public boolean jaSorteado(int mes, int ano) {
		
		// @formatter:off
		// TODO: Voc� dever� implementar este m�todo para que seja poss�vel saber se j� existe sorteio  na tabela SORTE VAGA GARAGEM com os par�metros informados.
		// @formatter:on

		
		
		System.out.println("\nM�todo n�o implementado");
		throw new RuntimeException("M�todo n�o implementado");

	}

	/**
	 * Metodo para salvar um sorteio realizado.
	 * 
	 * @param id_funcionario
	 * @param id_vaga
	 * @param mes
	 * @param ano
	 * @return
	 */
	public boolean save(long id_funcionario, long id_vaga, int mes, int ano) {

		String sql = "INSERT INTO SORTE_VAGA_GARAGEM (ANO, MES, FUNCIONARIO_ID, VAGA_ID) VALUES (?,?,?,?)";

		PreparedStatement ps = null;

		try {

			ps = getConnection().prepareStatement(sql);

			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setLong(3, id_funcionario);
			ps.setLong(4, id_vaga);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao salvar o sorteio o banco de dados: " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ");
			}

		}

		return false;

	}

	/**
	 * M�todo para deletar os sorteios realizados, tendo ocmo par�metros:
	 * 
	 * @param mes
	 * @param ano
	 * @return true se conseguir deletar e false caso ocorra algum problema.
	 */
	public boolean delete(int mes, int ano) {

		String sql = "DELETE FROM SORTE_VAGA_GARAGEM where ANO=? and MES= ?";

		PreparedStatement ps = null;

		try {

			ps = getConnection().prepareStatement(sql);

			ps.setInt(1, ano);
			ps.setInt(2, mes);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao deletar o sorteio no banco de dados: " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ");
			}

		}

		return false;
	}

	/**
	 * Consulta sorteios relalizados em determinado m�s e ano
	 *
	 * @param ano
	 * @param mes
	 * @return
	 */
	public List<Sorteio> findByAnoAndMes(int ano, int mes) {

		String sql = "SELECT * FROM SORTE_VAGA_GARAGEM where mes=? and ano =?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Sorteio> sorteios = new ArrayList<Sorteio>();

		try {

			ps = getConnection().prepareStatement(sql);

			ps.setInt(1, mes);
			ps.setInt(2, ano);

			rs = ps.executeQuery();

			while (rs.next()) {

				long idVaga = rs.getLong("VAGA_ID");
				long idFuncionario = rs.getLong("FUNCIONARIO_ID");

				Sorteio s = new Sorteio();
				s.setAno(rs.getInt("ANO"));
				s.setMes(rs.getInt("MES"));

				VagasRepository vagaRepo = new VagasRepository();
				Vaga vaga = vagaRepo.findById(idVaga);
				s.setVaga(vaga);

				FuncionarioRepository funcRepo = new FuncionarioRepository();
				Funcionario funcionario = funcRepo.findById(idFuncionario);
				s.setFuncionario(funcionario);

				sorteios.add(s);
			}

		} catch (SQLException e) {
			System.out.println("N�o conseguimos verificar se j� existe sorteio realizado: " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
			}

		}

		return sorteios;

	}

}
