package disciplina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import disciplina.model.Disciplina;
import utils.Conexao;

public class DisciplinaDAO {
	public boolean inserir(Disciplina disciplina) {
		String sql = "INSERT INTO disciplina (codigo, nome, ementa, creditos, tipo, id_curso) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disciplina.getCodigo());
			statement.setString(2, disciplina.getNome());
			statement.setString(3, disciplina.getEmenta());
			statement.setInt(4, disciplina.getCreditos());
			statement.setString(5, disciplina.getTipoDisciplina().name());
			statement.setInt(6, disciplina.getIdCurso());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("DisciplinaDAO -> Erro ao inserir disciplina: " + e.getMessage());
			return false;
		}
	}

	public List<Disciplina> listarTodos() {
		List<Disciplina> disciplinas = new ArrayList<>();
		String sql = "SELECT * FROM disciplina";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				Disciplina disciplina = new Disciplina(rs.getInt("codigo"), rs.getString("nome"),
						rs.getString("ementa"), rs.getInt("creditos"), rs.getString("tipo"), rs.getInt("id_Curso"));
				disciplinas.add(disciplina);
			}
		} catch (SQLException e) {
			System.err.println("DisciplinaDAO -> Erro ao listar disciplinas: " + e.getMessage());
		}

		return disciplinas;
	}

	public boolean atualizar(Disciplina disciplina) {
		String sql = "UPDATE disciplina SET nome = ?, ementa = ?, creditos = ?,"
				+ "tipo = ?, id_curso = ? WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, disciplina.getNome());
			statement.setString(2, disciplina.getEmenta());
			statement.setInt(3, disciplina.getCreditos());
			statement.setString(4, disciplina.getTipoDisciplina().name());
			statement.setInt(5, disciplina.getIdCurso());
			statement.setInt(6, disciplina.getCodigo());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaDAO -> Erro ao atualizar disciplina: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(Disciplina disciplina) {
		String sql = "DELETE FROM disciplina WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, disciplina.getCodigo());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DisciplinaDAO -> Erro ao remover disciplina: " + e.getMessage());
			return false;
		}
	}

	public Disciplina buscarPorCodigo(int codigo) {
		String sql = "SELECT * FROM disciplina WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, codigo);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next())
					return new Disciplina(rs.getInt("codigo"), rs.getString("nome"), rs.getString("Ementa"),
							rs.getInt("Creditos"), rs.getString("tipo"), rs.getInt("id_curso"));
				else
					return null;
			}

		} catch (SQLException e) {
			System.err.println("DisciplinaDAO -> Erro ao buscar disciplina por código: " + e.getMessage());
			return null;
		}
	}
	
	public List<Disciplina> buscarPorProfessor(int siape) {
		String sql = "SELECT * FROM disciplina D JOIN disciplina_professor DP ON D.codigo = DP.id_disciplina "
				+ "JOIN professor P ON P.siape = DP.siape_professor WHERE P.siape = ?";
		
		try(Connection conn = Conexao.conectar()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, siape);
			ResultSet rs = stmt.executeQuery();
			
			List<Disciplina> disciplinas  = new ArrayList<>();
			
			while(rs.next()) {
				disciplinas.add(new Disciplina(rs.getInt("codigo"), rs.getString("nome"), rs.getString("ementa"),
						rs.getInt("creditos"), rs.getString("tipo"), rs.getInt("id_curso")));
			}
			
			rs.close();
			stmt.close();
			
			return disciplinas;
		} catch (SQLException e) {
			System.err.println("AlunoDAO -> Erro ao buscar disciplina por professor: " + e.getMessage());
			return null;
		}
	}

	public List<Disciplina> buscarPorCurso(int idCurso) {
		List<Disciplina> disciplinas = new ArrayList<>();
		String sql = "SELECT * FROM disciplina WHERE id_curso = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idCurso);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Disciplina disciplina = new Disciplina(rs.getInt("codigo"), rs.getString("nome"),
						rs.getString("ementa"), rs.getInt("creditos"), rs.getString("tipo"), rs.getInt("id_curso"));
				disciplinas.add(disciplina);
			}
		} catch (SQLException e) {
			System.err.println("DisciplinaDAO -> Erro ao buscar disciplinas: " + e.getMessage());
		}
		return disciplinas;
	}
}
