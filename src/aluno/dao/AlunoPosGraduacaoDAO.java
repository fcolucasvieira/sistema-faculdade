package aluno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aluno.model.AlunoPosGraduacao;
import utils.Conexao;

public class AlunoPosGraduacaoDAO {
	public boolean inserir(AlunoPosGraduacao aluno_pos_graduacao) {
		String sql = "INSERT INTO aluno_pos_graduacao (matricula, orientador_siape) VALUES (?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno_pos_graduacao.getMatricula());
			statement.setInt(2, aluno_pos_graduacao.getOrientadorSiape());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("AlunoPosGraduacaoDAO -> Erro ao inserir o aluno de pós graduação: " + e.getMessage());
			return false;
		}
	}

	public List<AlunoPosGraduacao> listarTodos() {
		List<AlunoPosGraduacao> alunos_pos_graduacao = new ArrayList<>();
		String sql = "SELECT * FROM aluno_pos_graduacao";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				AlunoPosGraduacao aluno_pos_graduacao = new AlunoPosGraduacao(rs.getInt("id_disciplina"), rs.getInt("siape_professor"));
				alunos_pos_graduacao.add(aluno_pos_graduacao);
			}
		} catch (SQLException e) {
			System.err.println("AlunoPosGraduacaoDAO -> Erro ao listar disciplinas professores: " + e.getMessage());
		}

		return alunos_pos_graduacao;
	}
	
	public boolean atualizar(AlunoPosGraduacao aluno_pos_graduacao) {
		String sql = "UPDATE aluno_pos_graduacao SET orientador_siape = ? WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno_pos_graduacao.getOrientadorSiape());
			statement.setInt(2, aluno_pos_graduacao.getMatricula());

			
			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AlunoPosGraduacaoDAO -> Erro ao atualizar aluno de pós gradruação: " + e.getMessage());
			return false;
		}
	}

	public boolean remover(AlunoPosGraduacao aluno_pos_graduacao) {
		String sql = "DELETE FROM aluno_pos_graduacao WHERE matricula = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, aluno_pos_graduacao.getMatricula());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("AlunoPosGraduacaoDAO -> Erro ao remover aluno de pós graduação: " + e.getMessage());
			return false;
		}
	}
}
