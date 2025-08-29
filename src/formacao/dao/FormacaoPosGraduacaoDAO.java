package formacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import formacao.model.FormacaoPosGraduacao;
import utils.Conexao;

public class FormacaoPosGraduacaoDAO {
	public boolean inserir(FormacaoPosGraduacao formacao) {
		String sql = "INSERT INTO formacao_pos_graudacao (matricula_aluno, id_curso) VALUES (?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, formacao.getMatriculaAluno());
			statement.setInt(2, formacao.getIdCurso());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("FormacaoPosGraudacaoDAO -> Erro ao inserir a formação pós graduação: " + e.getMessage());
			return false;
		}
	}

	public List<FormacaoPosGraduacao> listarTodos() {
		List<FormacaoPosGraduacao> formacoes = new ArrayList<>();
		String sql = "SELECT * FROM formacao_pos_graduacao";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				FormacaoPosGraduacao formacao = new FormacaoPosGraduacao(rs.getInt("id_disciplina"), rs.getInt("siape_professor"));
				formacoes.add(formacao);
			}
		} catch (SQLException e) {
			System.err.println("FormacaoPosGraduacaoDAO -> Erro ao listar formações de pós graduação: " + e.getMessage());
		}

		return formacoes;
	}
	
	public boolean atualizar(FormacaoPosGraduacao formacao) {
		String sql = "UPDATE formacao_pos_graduacao SET id_curso = ? WHERE matricula_aluno = ? AND id_curso = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, formacao.getIdCurso());
			statement.setInt(2, formacao.getMatriculaAluno());
			statement.setInt(1, formacao.getIdCurso());
			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("FormacaoPosGraduacaoDAO -> Erro ao atualizar formação de pós graduação: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(FormacaoPosGraduacao formacao) {
		String sql = "DELETE FROM formacao_pos_graduacao WHERE matricula_aluno = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, formacao.getMatriculaAluno());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("FormacaoPosGraduacaoDAO -> Erro ao remover formação de pós graduação: " + e.getMessage());
			return false;
		}
	}
}
