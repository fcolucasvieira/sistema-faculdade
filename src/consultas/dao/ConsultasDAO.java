package consultas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import curso.model.Curso;
import departamento.dao.DepartamentoDAO;
import departamento.model.Departamento;
import disciplina.model.Disciplina;
import utils.Conexao;

public class ConsultasDAO {
	public static void func01(int matricula) {
		String sql = "SELECT * FROM aluno A WHERE A.matricula = ?";
		
		try(Connection conn = Conexao.conectar()) {
			System.out.println("Dados Pessoais ->");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, matricula);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			System.out.println("Nome: " + rs.getString("nome"));
			System.out.println("Endereço: " + rs.getString("endereco"));
			System.out.println("Tipo: " + rs.getString("tipo"));
			
			sql = "SELECT C.nome FROM aluno A JOIN curso C ON A.id_curso = C.codigo WHERE A.matricula = ?";
			
			System.out.println("\nCurso ->");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, matricula);
			
			rs = stmt.executeQuery();
			
			rs.next();
			
			System.out.println(rs.getString("C.nome"));
			
			
			sql = "SELECT D.nome FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno "
					+ "JOIN disciplina D ON DC.codigo_disciplina = D.codigo "
					+ "WHERE A.matricula = ? AND DC.media_final IS NULL";
			
			System.out.println("\nDisciplinas matriculadas ->");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, matricula);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("D.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("D.nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			
			sql = "SELECT D.nome FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno "
					+ "JOIN disciplina D ON DC.codigo_disciplina = D.codigo "
					+ "WHERE A.matricula = ? AND DC.media_final IS NOT NULL AND DC.media_final >= 7.0 AND DC.frequencia > 0.75";
			
			System.out.println("\nDisciplinas concluídas ->");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, matricula);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("D.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("D.nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			
			rs.close();
			stmt.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void func02(int cod_depart) {
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		String sql = "SELECT C.nome FROM curso C JOIN departamento D ON D.codigo = C.id_departamento "
				+ "WHERE D.codigo = ?";
		try(Connection conn = Conexao.conectar()) {
			Departamento depart = departamentoDAO.buscarPorCodigo(cod_depart);
			
			System.out.println(depart.toString());
			System.out.println("\nCursos sob responsabilidade do departamento ->");
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cod_depart);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("C.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("C.nome"));
				}
			} else { System.out.println("Nenhum"); }
			
			rs.close();
			stmt.close();
			System.out.println();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void func03(Curso curso) {
		String sql = "SELECT nome FROM disciplina D WHERE id_curso = ? AND tipo = 'obrigatória'";
	
		try(Connection conn = Conexao.conectar()) {
			System.out.println("Disciplinas Obrigatórias ->");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getCodigo());
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("nome"));
				while(rs.next()) {
					System.out.println(rs.getString("nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			
			sql = "SELECT nome FROM disciplina D WHERE id_curso = ? AND tipo = 'optativa'";
			
			System.out.println("\nDisciplinas Optativas ->");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getCodigo());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("D.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("D.nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			
			sql = "SELECT A.nome FROM aluno A JOIN curso C ON A.id_curso = C.codigo WHERE C.codigo = ?";
			
			System.out.println("\nAlunos do curso ->");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getCodigo());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("A.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("A.nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			
			sql = "SELECT A.nome FROM (SELECT matricula_aluno FROM disciplina_cursada DC JOIN disciplina D "
					+ "ON DC.codigo_disciplina = D.codigo WHERE D.tipo = 'obrigatória' AND D.id_curso = ? GROUP BY matricula_aluno "
					+ "HAVING count(D.codigo) = (SELECT count(*) FROM disciplina D "
					+ "WHERE D.id_curso = ? AND D.tipo = 'obrigatória')) DCO JOIN aluno A ON DCO.matricula_aluno = A.matricula";
			
			System.out.println("\nAlunos do curso ->");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getCodigo());
			stmt.setInt(2, curso.getCodigo());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("A.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("A.nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			stmt.close();
			rs.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void func04(Disciplina disciplina) {
		String sql = "SELECT A.nome FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno "
				+ "JOIN disciplina D ON DC.codigo_disciplina = D.codigo "
				+ "WHERE D.codigo = ? AND DC.media_final IS NULL";
		
		try(Connection conn = Conexao.conectar()) {
			System.out.println("\nAlunos matriculados na disciplina ->");
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, disciplina.getCodigo());
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("A.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("A.nome"));
				}
			} else { System.out.println("Nenhum"); }
			
			System.out.println("\nPré-Requisitos: ->");
			
			sql = "SELECT DR.nome FROM disciplina D JOIN pre_requisito PR ON D.codigo = PR.id_disciplina "
					+ "JOIN disciplina DR ON DR.codigo = PR.id_disciplina_requisito WHERE D.codigo = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, disciplina.getCodigo());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("DR.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("DR.nome"));
				}
			} else { System.out.println("Nenhum"); }
			
			System.out.println("\nDisciplinas para as quais é pré-requisito ->");
			
			sql = "SELECT D.nome FROM disciplina D JOIN pre_requisito PR ON D.codigo = PR.id_disciplina "
					+ "JOIN disciplina DR ON DR.codigo = PR.id_disciplina_requisito WHERE DR.codigo = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, disciplina.getCodigo());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("D.nome"));
				while(rs.next()) {
					System.out.println(rs.getString("D.nome"));
				}
			} else { System.out.println("Nenhuma"); }
			
			rs.close();
			stmt.close();
			System.out.println();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}	
