package departamento.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import departamento.model.Departamento;
import utils.Conexao;

public class DepartamentoDAO {

	public boolean inserir(Departamento departamento) {
		String sql = "INSERT INTO departamento (nome) VALUES (?)";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, departamento.getNome());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("DepartamentoDAO -> Erro ao inserir o departamento: " + e.getMessage());
			return false;
		}
	}

	public List<Departamento> listarTodos() {
		String sql = "SELECT * FROM departamento";
		List<Departamento> departamentos = new ArrayList<>();

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				Departamento departamento = new Departamento(rs.getInt("codigo"), rs.getString("nome"));
				departamentos.add(departamento);
			}
		} catch (SQLException e) {
			System.err.println("DepartamentoDAO -> Erro ao listar departamentos: " + e.getMessage());
		}

		return departamentos;
	}

	public boolean atualizar(Departamento departamento) {
		String sql = "UPDATE departamento SET nome = ? WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, departamento.getNome());
			statement.setInt(2, departamento.getCodigo());

			int count = statement.executeUpdate(); // Quantidade de linhas afetadas (atualizadas)
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DepartamentoDAO -> Erro ao atualizar departamento: " + e.getMessage());
			return false;
		}

	}

	public boolean remover(Departamento departamento) {
		String sql = "DELETE FROM departamento WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, departamento.getCodigo());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("DepartamentoDAO -> Erro ao deletar departamento: " + e.getMessage());
			return false;
		}
	}

	public Departamento buscarPorCodigo(int codigo) {
		String sql = "SELECT * FROM departamento WHERE codigo = ?";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, codigo);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next())
					return new Departamento(rs.getInt("codigo"), rs.getString("nome"));
				else
					return null;
			}

		} catch (SQLException e) {
			System.err.println("DepartamentoDAO -> Erro ao buscar departamento por código: " + e.getMessage());
			return null;
		}
	}

}