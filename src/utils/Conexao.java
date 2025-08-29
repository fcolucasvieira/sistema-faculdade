package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String url = "jdbc:mysql://localhost:3306/Equipe558446?serverTimezone=UTC&createDatabaseIfNotExist=true";
	private static final String user = "Admin";
	private static final String password = "Root";

	public static Connection conectar() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}
}
