package disciplina.dao;

import java.util.List;

import aluno.dao.AlunoDAO;
import aluno.model.Aluno;
import disciplina.model.Disciplina;
import disciplina.model.InformacoesDisciplina;
import disciplina.model.PreRequisito;

public class InformacoesDisciplinaDAO {
	public InformacoesDisciplina buscarInformacoes(Disciplina disciplina) {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			PreRequisitoDAO preRequisitoDAO = new PreRequisitoDAO();
			
			List<Aluno> alunos = alunoDAO.buscarMatriculadosEmDisciplina(disciplina.getCodigo());
			
			List<PreRequisito> pre_requisitos = preRequisitoDAO.buscarPreRequisitos(disciplina.getCodigo());
			
			List<PreRequisito> requisitada = preRequisitoDAO.buscarRequisitadas(disciplina.getCodigo());
			
			return new InformacoesDisciplina(alunos, pre_requisitos, requisitada);
		} catch (Exception e) {
			System.err.println("InformacoesDisciplinaDAO -> Erro ao buscar alunos da disciplina: " + e.getMessage());
			return null;
		}
	}
}
