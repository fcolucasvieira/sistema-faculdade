package disciplina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import disciplina.model.PreRequisito;
import utils.Conexao;

public class PreRequisitoDAO {
	public boolean inserir(PreRequisito pre_requisito) {
		String sql = "INSERT INTO pre_requisito (id_disciplina, id_disciplina_requisito) VALUES (?, ?)";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, pre_requisito.getIdDisciplina());
			statement.setInt(2, pre_requisito.getIdDisciplinaRequisito());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("PreRequisitoDAO -> Erro ao inserir o pré requisito: " + e.getMessage());
			return false;
		}
	}

	public List<PreRequisito> listarTodos() {
		List<PreRequisito> pre_requisitos = new ArrayList<>();
		String sql = "SELECT * FROM pre_requisito";

		try (Connection connection = Conexao.conectar();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				PreRequisito pre_requisito = new PreRequisito(rs.getInt("id_disciplina"), rs.getInt("id_disciplina_requisito"));
				pre_requisitos.add(pre_requisito);
			}
		} catch (SQLException e) {
			System.err.println("PreRequisitoDAO -> Erro ao listar disciplinas professores: " + e.getMessage());
		}

		return pre_requisitos;
	}

	public boolean remover(PreRequisito pre_requisito) {
		String sql = "DELETE FROM pre_requisito WHERE id_disciplina = ? AND id_disciplina_requisito = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, pre_requisito.getIdDisciplina());
			statement.setInt(2, pre_requisito.getIdDisciplinaRequisito());

			int count = statement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			System.err.println("PreRequisitoDAO -> Erro ao remover pré-requisito: " + e.getMessage());
			return false;
		}
	}
	
	public List<PreRequisito> buscarPreRequisitos(int cod_disciplina) {
		String sql = "SELECT PR.id_disciplina, PR.id_disciplina_requisito FROM disciplina D1 JOIN pre_requisito PR "
				+ "ON D1.codigo = PR.id_disciplina JOIN disciplina D2 ON D2.codigo = PR.id_disciplina_requisito "
				+ "WHERE D1.codigo = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, cod_disciplina);
			ResultSet rs = stmt.executeQuery();
			
			List<PreRequisito> pre_requisitos = new ArrayList<>();
			
			while(rs.next()) {
				pre_requisitos.add(new PreRequisito(rs.getInt("id_disciplina"), rs.getInt("id_disciplina_requisito")));
			}
			
			rs.close();
			stmt.close();
			return pre_requisitos;
		} catch (SQLException e) {
			System.err.println("PreRequisitoDAO -> Erro ao buscar pré-requisitos de disciplina: " + e.getMessage());
			return null;
		}
	}
	
	public List<PreRequisito> buscarRequisitadas(int cod_disciplina) {
		String sql = "SELECT PR.id_disciplina, PR.id_disciplina_requisito FROM disciplina D1 JOIN pre_requisito PR "
				+ "ON D1.codigo = PR.id_disciplina JOIN disciplina D2 ON D2.codigo = PR.id_disciplina_requisito "
				+ "WHERE D2.codigo = ?";

		try (Connection connection = Conexao.conectar();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, cod_disciplina);
			ResultSet rs = stmt.executeQuery();
			
			List<PreRequisito> requisitadas = new ArrayList<>();
			
			while(rs.next()) {
				requisitadas.add(new PreRequisito(rs.getInt("id_disciplina"), rs.getInt("id_disciplina_requisito")));
			}
			
			rs.close();
			stmt.close();
			return requisitadas;
		} catch (SQLException e) {
			System.err.println("PreRequisitoDAO -> Erro ao buscar disciplinas requisitadas: " + e.getMessage());
			return null;
		}
	}
}
