package br.com.fiap.ddd.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ddd.model.Funcionario;

/**
 * Repositório de Funcionarios
 * 
 * @see Repository
 * @see ConnectionFactory#getInstance()
 * @author Francis
 *
 */
public class FuncionarioRepository extends Repository {

	public FuncionarioRepository() {
		super();
	}

	/**
	 * Consulta funcionários filtrando se é chefe ou não
	 * 
	 * @param chefe
	 * @return
	 */
	public List<Funcionario> findByChefe(boolean chefe) {

		List<Funcionario> funcionarios = new ArrayList<>();
		String sql = "SELECT * FROM Funcionario where chefe = ?";

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			ps = this.getConnection().prepareStatement(sql);
			ps.setBoolean(1, chefe);
			rs = ps.executeQuery();

			while (rs.next()) {
				funcionarios.add(new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getBoolean("chefe")));
			}

			return funcionarios;

		} catch (SQLException e) {
			System.out.println("Erro na execução do SQL: " + e.getMessage());
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

		return funcionarios;
	}

	/**
	 * Consulta funcionário com o ID
	 * 
	 * @param id
	 * @return
	 */
	public Funcionario findById(long id) {

		String sql = "SELECT * FROM Funcionario where id = ?";

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			ps = this.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getBoolean("chefe"));
				}

			} else {
				System.out.println("Funcionario não encontrado");
			}
		} catch (SQLException e) {
			System.out.println("Erro na execução do SQL: " + e.getMessage());
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

		return null;
	}

}
