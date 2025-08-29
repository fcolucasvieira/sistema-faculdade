package aluno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aluno.model.AlunoGraduacao;
import utils.Conexao;

public class AlunoGraduacaoDAO {
	public boolean inserir(AlunoGraduacao aluno_graduacao) {
		String sql = "INSERT INTO aluno_graduacao (matricula, ano_ingresso) VALUES (?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno_graduacao.getMatricula());
			statement.setInt(2, aluno_graduacao.getAnoIngresso());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("AlunoGraduacaoDAO -> Erro ao inserir o aluno de graduação: " + e.getMessage());
			return false;
		}
	}

	public List<AlunoGraduacao> listarTodos() {
		List<AlunoGraduacao> alunos_graduacao = new ArrayList<>();
		String sql = "SELECT * FROM aluno_graduacao";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				AlunoGraduacao aluno_graduacao = new AlunoGraduacao(rs.getInt("id_disciplina"), rs.getInt("siape_professor"));
				alunos_graduacao.add(aluno_graduacao);
			}
		} catch (SQLException e) {
			System.err.println("AlunoGraduacaoDAO -> Erro ao listar alunos de graduação: " + e.getMessage());
		}

		return alunos_graduacao;
	}
	
	public boolean atualizar(AlunoGraduacao aluno_graduacao) {
		String sql = "UPDATE aluno_graduacao SET ano_ingresso = ? WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno_graduacao.getAnoIngresso());
			statement.setInt(2, aluno_graduacao.getMatricula());

			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AlunoGraduacaoDAO -> Erro ao atualizar aluno de graduação: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(AlunoGraduacao aluno_graduacao) {
		String sql = "DELETE FROM aluno_graduacao WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno_graduacao.getMatricula());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AlunoGraduacaoDAO -> Erro ao remover aluno de graduação: " + e.getMessage());
			return false;
		}
	}
}
