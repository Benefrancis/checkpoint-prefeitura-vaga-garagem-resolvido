package br.com.fiap.ddd.repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe repository
 * 
 * Todo reposítório deverá extender esta classe, pois todo repositório deverá
 * saber se conectar ao banco de dados conforme parâmetros do arquivo
 * application.properties
 * 
 * @author Francis
 *
 */
public class Repository {

	Connection connection;

	/**
	 * Construtor padrão
	 */
	public Repository() {
		super();
		getConnection();
	}

	/**
	 * Consegue uma conexão com o banco de dados
	 * 
	 * @see ConnectionFactory#getInstance()
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {

			this.connection = ConnectionFactory.getInstance().getConnection();
			return this.connection;
		} catch (SQLException e) {
			System.out.println("Erro nos parâmetros da conexão com o banco de dados :" + e.getMessage());
		}
		return null;
	}

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void closeConnection() {

		try {
			// se a conexão não estiver fechada, feche-a
			if (!this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao encerrar conexão:" + " \n" + ex.getMessage());
		}
	}

}
