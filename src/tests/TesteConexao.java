package tests;
import java.sql.Connection;
import java.sql.SQLException;

import utils.Conexao;

public class TesteConexao {
	public static void main(String[] args) {
		try (Connection conn = Conexao.conectar()) {
			System.out.println("Conexão ao banco de dados realizada com sucesso!");
		} catch (RuntimeException e) {
			System.err.println("ERRO na tentativa de conexão: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("ERRO ao fechar a conexão: " + e.getMessage());
		}
	}
}