package professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import professor.model.TelefoneProfessor;
import utils.Conexao;

public class TelefoneProfessorDAO {
	public boolean inserir(TelefoneProfessor telefone_professor) {
		String sql = "INSERT INTO telefone_professor (numero, descricao, siape) VALUES (?, ?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, telefone_professor.getNumero());
			statement.setString(2, telefone_professor.getDescricao());
			statement.setInt(3, telefone_professor.getSiape());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("TelefoneProfessorDAO -> Erro ao inserir telefone de professor: " + e.getMessage());
			return false;
		}
	}

	public List<TelefoneProfessor> listarTodos() {
		List<TelefoneProfessor> telefones_professores = new ArrayList<>();
		String sql = "SELECT * FROM telefone_professor";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				TelefoneProfessor telefone_professor = new TelefoneProfessor(rs.getString("numero"), rs.getString("descricao"), rs.getInt("matricula"));
				telefones_professores.add(telefone_professor);
			}
		} catch (SQLException e) {
			System.err.println("TelefoneProfessorDAO -> Erro ao listar telefones dos professores: " + e.getMessage());
		}

		return telefones_professores;
	}

	public boolean atualizar(TelefoneProfessor telefone_aluno) {
		String sql = "UPDATE telefone_professor SET numero = ?, descricao = ? WHERE siape = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, telefone_aluno.getNumero());
			statement.setString(2, telefone_aluno.getDescricao());
			statement.setInt(3, telefone_aluno.getSiape());
			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("TelefoneProfessorDAO -> Erro ao atualizar telefone de Professor: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(TelefoneProfessor telefone_professor) {
		String sql = "DELETE FROM telefone_professor WHERE numero = ? AND siape = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, telefone_professor.getNumero());
			statement.setInt(1, telefone_professor.getSiape());
			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("TelefoneProfessorDAO -> Erro ao remover telefone de professores: " + e.getMessage());
			return false;
		}
	}
}
