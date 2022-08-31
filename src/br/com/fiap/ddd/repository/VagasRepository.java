package br.com.fiap.ddd.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ddd.model.Vaga;

/**
 * Repositório das vagas de garagem
 * 
 * @author Francis
 *
 */
public class VagasRepository extends Repository {

	public VagasRepository() {
		super();
	}

	/**
	 * Consulta as vagas disponíveis no banco de dados
	 * 
	 * @param disponivel
	 * @return
	 */
	public List<Vaga> findByDisponivel(boolean disponivel) {

		List<Vaga> retorno = new ArrayList<>();

		String sql = "SELECT * from VAGA_GARAGEM where disponivel =?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setBoolean(1, disponivel);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					retorno.add(new Vaga(rs.getLong("ID"), rs.getString("NUMERO"), rs.getBoolean("DISPONIVEL")));
				}
			} else {
				System.out.println("Não temos vagas disponíveis para sorteio");
			}
			return retorno;
		} catch (SQLException e) {
			System.out.println("Não foi possível consultar as vagas disponíveis: " + e.getMessage());
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

		return retorno;
	}

	/**
	 * Consulta vaga com o id informado:
	 * 
	 * @param id
	 * @return
	 */
	public Vaga findById(Long id) {

		String sql = "SELECT * from VAGA_GARAGEM where id  =?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					return new Vaga(rs.getLong("ID"), rs.getString("NUMERO"), rs.getBoolean("DISPONIVEL"));
				}
			} else {
				System.out.println("Vaga não encontrada");
			}

			return null;

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a vaga: " + e.getMessage());
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
