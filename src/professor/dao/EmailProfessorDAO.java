package professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import professor.model.EmailProfessor;
import utils.Conexao;

public class EmailProfessorDAO {
	public boolean inserir(EmailProfessor email_professor) {
		String sql = "INSERT INTO email_professor (email, siape) VALUES (?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, email_professor.getEmail());
			statement.setInt(2, email_professor.getSiape());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("EmailProfessorDAO -> Erro ao inserir email de professor: " + e.getMessage());
			return false;
		}
	}

	public List<EmailProfessor> listarTodos() {
		List<EmailProfessor> emails_professores = new ArrayList<>();
		String sql = "SELECT * FROM email_professor";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				EmailProfessor email_professor = new EmailProfessor(rs.getString("email"), rs.getInt("siape"));
				emails_professores.add(email_professor);
			}
		} catch (SQLException e) {
			System.err.println("TelefoneProfessorDAO -> Erro ao listar emails dos professores: " + e.getMessage());
		}

		return emails_professores;
	}

	public boolean atualizar(EmailProfessor email_professor) {
		String sql = "UPDATE email_professor SET email = ? WHERE email = ? AND siape = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, email_professor.getEmail());
			statement.setString(2, email_professor.getEmail());
			statement.setInt(3, email_professor.getSiape());
			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("TelefoneProfessorDAO -> Erro ao atualizar email de Professor: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(EmailProfessor email_professor) {
		String sql = "DELETE FROM email_professor WHERE email = ? AND siape = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, email_professor.getEmail());
			statement.setInt(1, email_professor.getSiape());
			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("EmailProfessorDAO -> Erro ao remover email de professores: " + e.getMessage());
			return false;
		}
	}
}
