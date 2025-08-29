package disciplina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import disciplina.model.DisciplinaCursada;
import utils.Conexao;

public class DisciplinaCursadaDAO {

	public boolean inserir(DisciplinaCursada disc_cursada) {
		String sql = "INSERT INTO disciplina_cursada (matricula_aluno, codigo_disciplina,"
				+ " media_final, frequencia) VALUES (?, ?, ?, ?)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disc_cursada.getMatriculaAluno());
			statement.setInt(2, disc_cursada.getCodigoDisciplina());
			statement.setFloat(3, disc_cursada.getMediaFinal());
			statement.setFloat(4, disc_cursada.getFrequencia());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao inserir a disciplina cursada: " + e.getMessage());
			return false;
		}
	}

	public List<DisciplinaCursada> listarTodos() {
		List<DisciplinaCursada> disc_cursadas = new ArrayList<>();
		String sql = "SELECT * FROM disciplina_cursada";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				DisciplinaCursada disc_cursada = new DisciplinaCursada(rs.getInt("matricula_aluno"),
						rs.getInt("codigo_disciplina"), rs.getFloat("media_final"), rs.getFloat("frequencia"));
				disc_cursadas.add(disc_cursada);
			}
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao listar disciplinas cursadas: " + e.getMessage());
		}

		return disc_cursadas;
	}

	public boolean atualizar(DisciplinaCursada disciplina_cursada) {
		String sql = "UPDATE disciplina_cursada SET media_final = ?, frequencia = ?"
				+ " WHERE matricula_aluno = ? AND codigo_disciplina = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setFloat(1, disciplina_cursada.getMediaFinal());
			statement.setFloat(2, disciplina_cursada.getFrequencia());
			statement.setInt(3, disciplina_cursada.getMatriculaAluno());
			statement.setInt(4, disciplina_cursada.getCodigoDisciplina());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao atualizar disciplina cursada: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(DisciplinaCursada disciplina_cursada) {
		String sql = "DELETE FROM disciplina_cursada WHERE matricula_aluno = ? AND codigo_disciplina = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disciplina_cursada.getMatriculaAluno());
			statement.setInt(2, disciplina_cursada.getCodigoDisciplina());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao remover disciplina cursada: " + e.getMessage());
			return false;
		}
	}

	public List<DisciplinaCursada> buscarDisciplinasPorMatricula(int matricula) {
		List<DisciplinaCursada> disciplinasCursadas = new ArrayList<>();

		String sql = "SELECT matricula_aluno, codigo_disciplina, media_final, frequencia FROM disciplina_cursada WHERE matricula_aluno = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, matricula);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				DisciplinaCursada disciplina = new DisciplinaCursada(rs.getInt("matricula_aluno"),
						rs.getInt("codigo_disciplina"), rs.getFloat("media_final"), rs.getFloat("frequencia"));
				disciplinasCursadas.add(disciplina);
			}
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao buscar disciplinas: " + e.getMessage());
		}
		return disciplinasCursadas;
	}
	
	public DisciplinaCursada buscarDisciplinasPorMatriculaECodigo(int matricula, int codigo) {
		String sql = "SELECT * FROM disciplina_cursada WHERE matricula_aluno = ? AND codigo_disciplina = ?";

		try (Connection connection = Conexao.conectar()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, matricula);
			statement.setInt(2, codigo);
			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
				return new DisciplinaCursada(rs.getInt("matricula_aluno"), rs.getInt("codigo_disciplina"),
						 rs.getFloat("media_final"), rs.getFloat("frequencia"));
			} else
				return null;
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao buscar disciplinas: " + e.getMessage());
			return null;
		}
	}
	
	public boolean atualizarMediaFinal(DisciplinaCursada disciplina_cursada, float novaMedia) {
		if(novaMedia < 0 || novaMedia > 10) {
			System.out.println("A média deve estar entre 0 e 10");
			return false;
		}
		
		String sql = "UPDATE disciplina_cursada SET media_final = ?, frequencia = ?"
				+ " WHERE matricula_aluno = ? AND codigo_disciplina = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setFloat(1, novaMedia);
			statement.setFloat(2, disciplina_cursada.getFrequencia());
			statement.setInt(3, disciplina_cursada.getMatriculaAluno());
			statement.setInt(4, disciplina_cursada.getCodigoDisciplina());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao atualizar disciplina cursada: " + e.getMessage());
			return false;
		}
	}
	
	public boolean atualizarFrequencia(DisciplinaCursada disciplina_cursada, float novaFrequencia) {
		if(novaFrequencia < 0 || novaFrequencia > 1) {
			System.out.println("A frequência deve estar entre 0 e 1");
			return false;
		}

		
		String sql = "UPDATE disciplina_cursada SET media_final = ?, frequencia = ?"
				+ " WHERE matricula_aluno = ? AND codigo_disciplina = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setFloat(1, disciplina_cursada.getMediaFinal());
			statement.setFloat(2, novaFrequencia);
			statement.setInt(3, disciplina_cursada.getMatriculaAluno());
			statement.setInt(4, disciplina_cursada.getCodigoDisciplina());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao atualizar disciplina cursada: " + e.getMessage());
			return false;
		}
	}
	
	public void criarViewProfessor(int siape) {
		String sql = "CREATE OR REPLACE VIEW v_professor AS "
				+ "SELECT * FROM disciplina_cursada WHERE codigo_disciplina IN (SELECT id_disciplina FROM disciplina_professor WHERE siape_professor = ?)";
		
		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, siape);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao criar view de professor: " + e.getMessage());
		}
	}
	
	public DisciplinaCursada buscarPorCodigoView(int codigo) {
		String sql = "SELECT * FROM v_professor WHERE codigo_disciplina = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, codigo);

			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new DisciplinaCursada(rs.getInt("matricula_aluno"), rs.getInt("codigo_disciplina"), rs.getFloat("media_final"),
						rs.getFloat("frequencia"));
			} else
				return null;
		} catch (SQLException e) {
			System.err.println("DisciplinaCursadaDAO -> Erro ao buscar disciplina por código: " + e.getMessage());
			return null;
		}
	}
}
