package curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.model.Curso;
import utils.Conexao;

public class CursoDAO {

	public boolean inserir(Curso curso) {
		String sql = "INSERT INTO curso (nome, creditos_minimos, id_departamento) VALUES (?, ?, ?)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, curso.getNome());
			statement.setInt(2, curso.getCreditosMinimos());
			statement.setInt(3, curso.getIdDepartamento());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("Curso DAO -> Erro ao inserir curso: " + e.getMessage());
			return false;
		}
	}

	public List<Curso> listarTodos() {
		List<Curso> cursos = new ArrayList<>();
		String sql = "SELECT * FROM curso";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				Curso curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("creditos_minimos"),
						rs.getInt("id_departamento"));
				cursos.add(curso);
			}
		} catch (SQLException e) {
			System.err.println(" CursoDAO -> Erro ao listar cursos: " + e.getMessage());
		}

		return cursos;
	}

	public boolean atualizar(Curso curso) {
		String sql = "UPDATE curso SET nome = ?, creditos_minimos = ?, id_departamento = ? WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, curso.getNome());
			statement.setInt(2, curso.getCreditosMinimos());
			statement.setInt(3, curso.getIdDepartamento());
			statement.setInt(4, curso.getCodigo());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("CursoDAO -> Erro ao atualizar curso: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(Curso curso) {
		String sql = "DELETE FROM curso WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, curso.getCodigo());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("CursoDAO -> Erro ao remover curso: " + e.getMessage());
			return false;
		}
	}

	public static Curso buscarPorCodigo(int codigo) {
		String sql = "SELECT * FROM curso WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, codigo);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next())
					return new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("creditos_minimos"),
							rs.getInt("id_departamento"));
				else
					return null;
			}

		} catch (SQLException e) {
			System.err.println("CursoDAO -> Erro ao buscar curso por código: " + e.getMessage());
			return null;
		}
	}

	public String buscarNomePorCodigo(int codigo) {
		String sql = "SELECT nome FROM curso WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getString("nome");
			}
		} catch (SQLException e) {
			System.err.println("CursoDAO -> Erro ao buscar nome do curso: " + e.getMessage());
		}
		return null;
	}
	
	public List<Curso> buscarPorDepartamento(int cod_depart) {
		String sql = "SELECT * FROM curso WHERE id_departamento = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, cod_depart);
			ResultSet rs = statement.executeQuery();
			
			List<Curso> cursos = new ArrayList<>();

			while (rs.next()) {
				cursos.add(new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("creditos_minimos"),
						rs.getInt("id_departamento")));
			}
			
			return cursos;
		} catch (SQLException e) {
			System.err.println("CursoDAO -> Erro ao buscar nome do curso: " + e.getMessage());
		}
		return null;
	}
}
