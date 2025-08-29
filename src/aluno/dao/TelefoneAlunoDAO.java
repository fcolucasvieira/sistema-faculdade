package aluno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aluno.model.TelefoneAluno;
import utils.Conexao;

public class TelefoneAlunoDAO {
	public boolean inserir(TelefoneAluno telefone_aluno) {
		String sql = "INSERT INTO telefone_aluno (matricula, numero, descricao) VALUES (?, ?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, telefone_aluno.getMatricula());
			statement.setString(2, telefone_aluno.getNumero());
			statement.setString(3, telefone_aluno.getDescricao());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("TelefoneAlunoDAO -> Erro ao inserir telefone de aluno: " + e.getMessage());
			return false;
		}
	}

	public List<TelefoneAluno> listarTodos() {
		List<TelefoneAluno> telefones_alunos = new ArrayList<>();
		String sql = "SELECT * FROM telefone_aluno";

		try (Connection connection = Conexao.conectar()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TelefoneAluno telefone_aluno = new TelefoneAluno(rs.getString("numero"), rs.getString("descricao"), rs.getInt("matricula"));
				telefones_alunos.add(telefone_aluno);
			}
		} catch (SQLException e) {
			System.err.println("TelefoneAlunoDAO -> Erro ao listar telefones dos alunos: " + e.getMessage());
		}

		return telefones_alunos;
	}

	public boolean atualizar(TelefoneAluno telefone_aluno) {
		String sql = "UPDATE telefone_aluno SET numero = ?, descricao = ? WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, telefone_aluno.getNumero());
			statement.setString(2, telefone_aluno.getDescricao());
			statement.setInt(3, telefone_aluno.getMatricula());

			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("TelefoneAlunoDAO -> Erro ao atualizar telefone de aluno: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(TelefoneAluno telefone_aluno) {
		String sql = "DELETE FROM telefone_aluno WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, telefone_aluno.getMatricula());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("TelefoneAlunoDAO -> Erro ao remover telefone de aluno: " + e.getMessage());
			return false;
		}
	}
	
	public List<TelefoneAluno> listarPorMatricula(int matricula) {
		List<TelefoneAluno> telefones_alunos = new ArrayList<>();
		String sql = "SELECT * FROM telefone_aluno WHERE matricula = ?";

		try (Connection connection = Conexao.conectar()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, matricula);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TelefoneAluno telefone_aluno = new TelefoneAluno(rs.getString("numero"), rs.getString("descricao"), rs.getInt("matricula"));
				telefones_alunos.add(telefone_aluno);
			}
		} catch (SQLException e) {
			System.err.println("TelefoneAlunoDAO -> Erro ao listar telefones dos alunos por matrícula: " + e.getMessage());
		}

		return telefones_alunos;
	}
}
