package aluno.dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aluno.model.Aluno;
import utils.Conexao;

public class AlunoDAO {
	public boolean inserir(Aluno aluno) {
		String sql = "INSERT INTO aluno (nome, endereco, id_curso, tipo) VALUES (?, ?, ?, ?)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getEndereco());
			statement.setInt(3, aluno.getIdCurso());
			statement.setString(4, aluno.getTipoAluno().name());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao inserir aluno: " + e.getMessage());
			return false;
		}
	}

	public List<Aluno> listarTodos() {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT * FROM aluno";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				Aluno aluno = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("endereco"),
						rs.getInt("id_curso"), rs.getString("tipo"));
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao listar alunos: " + e.getMessage());
		}

		return alunos;
	}

	public boolean atualizar(Aluno aluno) {
		String sql = "UPDATE aluno SET nome = ?, endereco = ?, id_curso = ?, tipo = ? WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getEndereco());
			statement.setInt(3, aluno.getIdCurso());
			statement.setString(4, aluno.getTipoAluno().name());
			statement.setInt(5, aluno.getMatricula());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao atualizar aluno: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(Aluno aluno) {
		String sql = "DELETE FROM aluno WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno.getMatricula());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao remover aluno: " + e.getMessage());
			return false;
		}
	}

	public Aluno buscarPorMatricula(int matricula) throws SQLException {
		String sql = "SELECT * FROM aluno WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, matricula);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("endereco"),
						rs.getInt("id_curso"), rs.getString("tipo"));

			}
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar aluno por matrícula: " + e.getMessage());

		}

		return null;
	}

	public List<Aluno> buscarMatriculadosEmDisciplina(int cod_disciplina) {
		String sql = "SELECT A.matricula, A.nome, A.endereco, A.id_curso, A.tipo "
				+ "FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno "
				+ "JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE D.codigo = ? AND media_final IS NULL";

		try (Connection conn = Conexao.conectar()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cod_disciplina);
			ResultSet rs = stmt.executeQuery();

			List<Aluno> alunos = new ArrayList<>();

			while (rs.next()) {
				alunos.add(new Aluno(rs.getInt("A.matricula"), rs.getString("A.nome"), rs.getString("A.endereco"),
						rs.getInt("A.id_curso"), rs.getString("A.tipo")));
			}

			rs.close();
			stmt.close();

			return alunos;
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar alunos matriculados na disciplina: " + e.getMessage());
			return null;
		}
	}
	
	public List<Aluno> buscarPorDisciplinaView(int cod_disciplina) {
		String sql = "SELECT A.matricula, A.nome, A.endereco, A.id_curso, A.tipo FROM aluno A JOIN v_professor DC ON A.matricula = DC.matricula_aluno JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE D.codigo = ?";

		try (Connection conn = Conexao.conectar()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cod_disciplina);
			ResultSet rs = stmt.executeQuery();

			List<Aluno> alunos = new ArrayList<>();

			while (rs.next()) {
				alunos.add(new Aluno(rs.getInt("A.matricula"), rs.getString("A.nome"), rs.getString("A.endereco"),
						rs.getInt("A.id_curso"), rs.getString("A.tipo")));
			}

			rs.close();
			stmt.close();

			return alunos;
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar alunos matriculados na disciplina: " + e.getMessage());
			return null;
		}
	}

	public List<Aluno> buscarPorOrientador(int siape) {
		String sql = "SELECT * FROM aluno A JOIN aluno_pos_graduacao APG ON A.matricula = APG.matricula "
				+ "JOIN professor ON APG.orientador_siape = professor.siape WHERE professor.siape = ?";

		try (Connection conn = Conexao.conectar()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, siape);
			ResultSet rs = stmt.executeQuery();

			List<Aluno> alunos = new ArrayList<>();

			while (rs.next()) {
				alunos.add(new Aluno(rs.getInt("A.matricula"), rs.getString("A.nome"), rs.getString("A.endereco"),
						rs.getInt("A.id_curso"), rs.getString("A.tipo")));
			}

			rs.close();
			stmt.close();

			return alunos;
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar alunos por orientador: " + e.getMessage());
			return null;
		}
	}

	public List<Aluno> buscarPorCurso(int idCurso) {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT * FROM aluno WHERE id_curso = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idCurso);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("endereco"),
						rs.getInt("id_curso"), rs.getString("tipo"));
				alunos.add(aluno);

			}
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar alunos: " + e.getMessage());
		}

		return alunos;
	}

	public List<Aluno> alunosTodasObrigatorias(int idCurso) {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT a.matricula, a.nome, a.endereco, a.id_curso, a.tipo FROM aluno a JOIN disciplina_cursada dc ON a.matricula = dc.matricula_aluno JOIN disciplina d ON dc.codigo_disciplina = d.codigo WHERE a.id_curso = ? AND d.tipo = 'obrigatória' GROUP BY a.matricula, a.nome, a.endereco, a.id_curso, a.tipo HAVING COUNT(DISTINCT d.codigo) = (SELECT COUNT(*) FROM disciplina WHERE id_curso = ? AND tipo = 'obrigatória')";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idCurso);
			statement.setInt(2, idCurso);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("endereco"),
						rs.getInt("id_curso"), rs.getString("tipo"));
				alunos.add(aluno);

			}
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar alunos que fizeram todas obrigatórias: " + e.getMessage());
		}

		return alunos;
	}

	public List<Aluno> alunosNenhumaOptativa(int idCurso) {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT a.matricula, a.nome, a.endereco, a.id_curso, a.tipo FROM aluno a WHERE id_curso = ? AND NOT EXISTS (SELECT 1 FROM disciplina_cursada dc JOIN disciplina d ON dc.codigo_disciplina = d.codigo WHERE dc.matricula_aluno = a.matricula AND d.tipo = 'optativa' AND d.id_curso = a.id_curso)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idCurso);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("endereco"),
						rs.getInt("id_curso"), rs.getString("tipo"));
				alunos.add(aluno);

			}
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar alunos que não fizeram nenhuma optativa: " + e.getMessage());
		}

		return alunos;
	}

	public boolean existeAluno(int matricula) {
		String sql = "SELECT 1 FROM aluno WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, matricula);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar matrícula: " + e.getMessage());
			return false;
		}
	}
	
	public boolean checarMatriculado(int matricula, int codigo_disciplina) {
		String sql = "SELECT A.matricula, A.nome, A.endereco, A.id_curso, A.tipo "
				+ "FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno "
				+ "JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE A.matricula = ? AND D.codigo = ? AND media_final IS NULL";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, matricula);
			statement.setInt(2, codigo_disciplina);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao checar se aluno está matriculado: " + e.getMessage());
			return false;
		}
	}
	
	public int buscarMaiorMatricula() {
		String sql = "SELECT max(matricula) AS maiorMatricula FROM Aluno;";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			rs.next();
			
			return rs.getInt("maiorMatricula");
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao checar se aluno está matriculado: " + e.getMessage());
			return 0;
		}
	}
}
