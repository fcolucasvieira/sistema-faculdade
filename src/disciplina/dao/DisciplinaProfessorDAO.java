package disciplina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import disciplina.model.DisciplinaProfessor;
import utils.Conexao;

public class DisciplinaProfessorDAO {
	public boolean inserir(DisciplinaProfessor disc_prof) {
		String sql = "INSERT INTO disciplina_professor (id_disciplina, siape_professor) VALUES (?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disc_prof.getIdDisciplina());
			statement.setInt(2, disc_prof.getSiapeProfessor());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("DisciplinaProfessorDAO -> Erro ao inserir a disciplina professor: " + e.getMessage());
			return false;
		}
	}

	public List<DisciplinaProfessor> listarTodos() {
		List<DisciplinaProfessor> disciplinas_professores = new ArrayList<>();
		String sql = "SELECT * FROM disciplina_professor";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				DisciplinaProfessor disc_professor = new DisciplinaProfessor(rs.getInt("id_disciplina"), rs.getInt("siape_professor"));
				disciplinas_professores.add(disc_professor);
			}
		} catch (SQLException e) {
			System.err.println("DisciplinaProfessorDAO -> Erro ao listar disciplinas professores: " + e.getMessage());
		}

		return disciplinas_professores;
	}
	
	public boolean atualizar(DisciplinaProfessor disc_professor) {
		String sql = "UPDATE disciplina_professor SET id_disciplina = ? WHERE id_disciplina = ? AND siape_professor = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disc_professor.getIdDisciplina());
			statement.setInt(2, disc_professor.getSiapeProfessor());
			statement.setInt(3, disc_professor.getIdDisciplina());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaProfessorDAO -> Erro ao atualizar disciplina professor: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(DisciplinaProfessor disc_professor) {
		String sql = "DELETE FROM disciplina_professor WHERE id_disciplina = ? AND professor_siape = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disc_professor.getIdDisciplina());
			statement.setInt(2, disc_professor.getSiapeProfessor());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaProfessorDAO -> Erro ao remover disciplina professor: " + e.getMessage());
			return false;
		}
	}
}
