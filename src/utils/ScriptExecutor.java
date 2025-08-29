package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class ScriptExecutor {

	public static void executarScript(String caminho) {
		try (Connection connection = Conexao.conectar();
			Statement statement = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(caminho));) {
			String linha;
			StringBuilder comando = new StringBuilder();

			while ((linha = br.readLine()) != null) {
				linha = linha.trim();
				if (linha.isEmpty() || linha.startsWith("#") || linha.startsWith("--"))
					continue;

				comando.append(linha + "\n");
				if (linha.endsWith(";")) {
					statement.execute(comando.toString());
					comando.setLength(0);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao executar script: " + e.getMessage());
		}
	}

}
