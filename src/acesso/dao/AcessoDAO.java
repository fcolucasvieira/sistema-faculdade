package acesso.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import acesso.model.Acesso;
import utils.Conexao;

public class AcessoDAO {
	public boolean inserir(Acesso acesso) {
		String sql = "INSERT INTO acesso_sistema (login, senha, nome, nivel_acesso, matricula_aluno, siape_professor, id_departamento_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, acesso.getLogin());
			statement.setString(2, acesso.getSenha());
			statement.setString(3, acesso.getNome());
			statement.setString(4, acesso.getNivelAcesso().name());

			// Campo opcional (aluno)
			Integer matricula = acesso.getMatriculaAluno();
			if (matricula != null && matricula > 0)
				statement.setInt(5, acesso.getMatriculaAluno());
			else
				statement.setNull(5, Types.INTEGER);

			// Campo opcional (professor)
			Integer siape = acesso.getSiapeProfessor();
			if (siape != null && siape > 0)
				statement.setInt(6, acesso.getSiapeProfessor());
			else
				statement.setNull(6, Types.INTEGER);

			// Campo opcional (funcionario)
			Integer idDeptIns = acesso.getIdDeptFuncionario();
			if (idDeptIns != null && idDeptIns > 0)
				statement.setInt(7, acesso.getIdDeptFuncionario());
			else
				statement.setNull(7, Types.INTEGER);

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("AcessoDAO -> Erro ao inserir o acesso: " + e.getMessage());
			return false;
		}
	}

	public List<Acesso> listarTodos() {
		List<Acesso> acessos = new ArrayList<>();
		String sql = "SELECT * FROM acesso_sistema";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				Acesso acesso = new Acesso(rs.getInt("id_acesso"), rs.getString("nome"), rs.getString("login"), rs.getString("senha"),
						rs.getString("nivel_acesso"), rs.getObject("matricula_aluno", Integer.class),
						rs.getObject("siape_professor", Integer.class),
						rs.getObject("id_departamento_funcionario", Integer.class));
				acessos.add(acesso);
			}
		} catch (SQLException e) {
			System.err.println("AcessoDAO -> Erro ao listar acessos: " + e.getMessage());
		}

		return acessos;
	}

	public boolean atualizar(Acesso acesso) {
		String sql = "UPDATE acesso_sistema SET login = ?, senha = ?, nome = ?, nivel_acesso = ?, matricula_aluno = ?, siape_professor = ?, id_departamento_funcionario = ? WHERE id_acesso = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, acesso.getLogin());
			statement.setString(2, acesso.getSenha());
			statement.setString(3, acesso.getNome());
			statement.setString(4, acesso.getNivelAcesso().name());

			// Campo opcional (aluno)
			Integer matricula = acesso.getMatriculaAluno();
			if (matricula != null && matricula > 0)
				statement.setInt(5, acesso.getMatriculaAluno());
			else
				statement.setNull(5, Types.INTEGER);

			// Campo opcional (professor)
			Integer siape = acesso.getSiapeProfessor();
			if (siape != null && siape > 0)
				statement.setInt(6, acesso.getSiapeProfessor());
			else
				statement.setNull(6, Types.INTEGER);

			// Campo opcional (funcionario)
			Integer idDept = acesso.getIdDeptFuncionario();
			if (idDept != null && idDept > 0)
				statement.setInt(7, acesso.getIdDeptFuncionario());
			else
				statement.setNull(7, Types.INTEGER);

			statement.setInt(8, acesso.getId());

			int count = statement.executeUpdate();
			return count > 0;

		} catch (SQLException e) {
			System.err.println("AcessoDAO -> Erro ao atualizar acesso: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(Acesso acesso) {
		String sql = "DELETE FROM acesso_sistema WHERE id_acesso = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, acesso.getId());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AcessoDAO -> Erro ao remover acesso: " + e.getMessage());
			return false;
		}
	}

	public Acesso buscarPorId(int id) {
		String sql = "SELECT * FROM acesso_sistema WHERE id_acesso = ?";
		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Acesso(rs.getInt("id_acesso"), rs.getString("nome"), rs.getString("login"),
						rs.getString("senha"), rs.getString("nivel_acesso"),
						rs.getObject("matricula_aluno", Integer.class), rs.getObject("siape_professor", Integer.class),
						rs.getObject("id_departamento_funcionario", Integer.class));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar acesso por id: " + e.getMessage());
		}
		return null;
	}

	public boolean checarLogin(String login, String senha) {
		try (Connection conn = Conexao.conectar()) {
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT EXISTS(SELECT 1 FROM acesso_sistema WHERE login = ? AND senha = ?) AS Existe");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();

			rs.next();
			boolean existe = rs.getBoolean("Existe");

			stmt.close();
			rs.close();
			return existe;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Acesso acessoPorLoginSenha(String login, String senha) {
		String sql = "SELECT * FROM acesso_sistema WHERE login = ? AND senha = ?";
		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, login);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Acesso(rs.getInt("id_acesso"), rs.getString("nome"), rs.getString("login"),
						rs.getString("senha"), rs.getString("nivel_acesso"),
						rs.getObject("matricula_aluno", Integer.class), rs.getObject("siape_professor", Integer.class),
						rs.getObject("id_departamento_funcionario", Integer.class));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao verificar acesso: " + e.getMessage());
		}
		return null;
	}

}
