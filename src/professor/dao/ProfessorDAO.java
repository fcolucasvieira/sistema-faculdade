package professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import professor.model.Professor;
import utils.Conexao;

public class ProfessorDAO {

	public boolean inserir(Professor professor) {
		String sql = "INSERT INTO professor (siape, nome, data_nascimento, data_ingresso, id_departamento) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, professor.getSiape());
			statement.setString(2, professor.getNome());
			statement.setDate(3, professor.getDataNascimento());
			statement.setDate(4, professor.getDataIngresso());
			statement.setInt(5, professor.getIdDepartamento());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("Professor DAO -> Erro ao inserir o professor: " + e.getMessage());
			return false;
		}
	}

	public List<Professor> listarTodos() {
		List<Professor> professores = new ArrayList<>();
		String sql = "SELECT * FROM professor";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				Professor professor = new Professor(rs.getInt("siape"), rs.getString("nome"),
						rs.getDate("data_nascimento"), rs.getDate("data_ingresso"), rs.getInt("id_departamento"));

				professores.add(professor);
			}
		} catch (SQLException e) {
			System.err.println("ProfessorDAO -> Erro ao listar professores: " + e.getMessage());
		}

		return professores;
	}

	public boolean atualizar(Professor professor) {
		String sql = "UPDATE professor SET nome = ?, data_nascimento = ?, data_ingresso = ?, id_departamento = ? WHERE siape = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, professor.getNome());
			statement.setDate(2, professor.getDataNascimento());
			statement.setDate(3, professor.getDataIngresso());
			statement.setInt(4, professor.getIdDepartamento());
			statement.setInt(5, professor.getSiape());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("ProfessorDAO -> Erro ao atualizar professor: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(Professor professor) {
		String sql = "DELETE FROM professor WHERE siape = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, professor.getSiape());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("ProfessorDAO -> Erro ao remover professor: " + e.getMessage());
			return false;
		}
	}

	public int totalCreditos(int siape) {
		String sql = "SELECT P.siape, sum(D.creditos) total FROM professor P JOIN disciplina_professor DP ON P.siape = DP.siape_professor "
				+ "JOIN disciplina D ON DP.id_disciplina = D.codigo WHERE P.siape = ? GROUP BY P.siape";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, siape);
			ResultSet rs = statement.executeQuery();

			rs.next();
			int total = rs.getInt("total");

			rs.close();
			statement.close();
			return total;
		} catch (SQLException e) {
			System.err.println("ProfessorDAO -> Erro ao buscar total de créditos: " + e.getMessage());
			return 0;
		}
	}

	public boolean existeProfessor(int siape) {
		String sql = "SELECT 1 FROM professor WHERE siape = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, siape);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println("ProfessorDAO -> Erro ao buscar siape: " + e.getMessage());
			return false;
		}
	}
	
	public static Professor buscarPorSiape(int siape) throws SQLException {
		String sql = "SELECT * FROM professor WHERE siape = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, siape);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Professor(rs.getInt("siape"), rs.getString("nome"), rs.getDate("data_nascimento"),
						rs.getDate("data_ingresso"), rs.getInt("id_departamento"));

			}
		} catch (SQLException e) {
			System.err.println("ProfessorDAO -> Erro ao buscar professor por siape: " + e.getMessage());
			return null;
		}
		return null;
	}
}