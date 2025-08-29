package professor.dao;

import java.util.List;

import aluno.dao.AlunoDAO;
import aluno.model.Aluno;
import disciplina.dao.DisciplinaDAO;
import disciplina.model.Disciplina;
import professor.model.InformacoesOrientador;
import professor.model.Professor;

public class InformacoesOrientadorDAO {
	public InformacoesOrientador buscarInformacoes(Professor orientador) {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
			ProfessorDAO professorDAO = new ProfessorDAO();
			
			List<Aluno> alunos = alunoDAO.buscarPorOrientador(orientador.getSiape());
			
			List<Disciplina> disciplinas = disciplinaDAO.buscarPorProfessor(orientador.getSiape());
			
			int total = professorDAO.totalCreditos(orientador.getSiape());
			
			return new InformacoesOrientador(alunos, disciplinas, total);
		} catch (Exception e) {
			System.err.println("InformacoesDisciplinaDAO -> Erro ao buscar alunos da disciplina: " + e.getMessage());
			return null;
		}
	}
}
