package aluno.dao;

import java.util.ArrayList;
import java.util.List;

import aluno.model.Aluno;
import aluno.model.InformacoesAluno;
import curso.dao.CursoDAO;
import disciplina.dao.DisciplinaCursadaDAO;
import disciplina.model.DisciplinaCursada;

public class InformacoesAlunoDAO {
	private final AlunoDAO alunoDAO = new AlunoDAO();
	private final CursoDAO cursoDAO = new CursoDAO();
	private final DisciplinaCursadaDAO disciplinaCursadaDAO = new DisciplinaCursadaDAO();

	public InformacoesAluno buscarInformacoesAluno(int matricula) {
		try {
			Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
			if (aluno == null) {
				System.err.println("InformacoesAlunoDAO -> Aluno não encontrado pela matrícula: " + matricula);
				return null;
			}

			String nomeCurso = cursoDAO.buscarNomePorCodigo(aluno.getIdCurso());
			List<DisciplinaCursada> disciplinas = disciplinaCursadaDAO.buscarDisciplinasPorMatricula(matricula); // Todas
																													

			List<DisciplinaCursada> disciplinasConcluidas = new ArrayList<>();
			List<DisciplinaCursada> disciplinasAndamento = new ArrayList<>();

			for (DisciplinaCursada dc : disciplinas) {
				if (dc.Concluida()) {
					disciplinasConcluidas.add(dc);
				} else {
					disciplinasAndamento.add(dc);
				}
			}

			return new InformacoesAluno(aluno.getNome(), aluno.getEndereco(), aluno.getTipoAluno().name(), nomeCurso,
					disciplinasAndamento, disciplinasConcluidas);
		} catch (Exception e) {
			System.err.println("AlunoInformacoesDAO -> Erro ao buscar informações do aluno: " + e.getMessage());
			return null;
		}
	}
}
