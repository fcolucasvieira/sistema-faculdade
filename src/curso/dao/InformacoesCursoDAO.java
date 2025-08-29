package curso.dao;

import java.util.List;

import aluno.dao.AlunoDAO;
import aluno.model.Aluno;
import curso.model.InformacoesCurso;
import disciplina.dao.DisciplinaDAO;
import disciplina.model.Disciplina;

public class InformacoesCursoDAO {
	private final AlunoDAO alunoDAO = new AlunoDAO();
	private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private final CursoDAO cursoDAO = new CursoDAO();

	public InformacoesCurso buscarInformacoesCurso(int idCurso) {
		// Busca disciplinas do curso
		List<Disciplina> disciplinas = disciplinaDAO.buscarPorCurso(idCurso);
		List<Disciplina> disciplinasObrigatorias = disciplinas.stream()
				.filter(d -> "obrigatória".equalsIgnoreCase(d.getTipoDisciplina().name())).toList();
		List<Disciplina> disciplinasOptativas = disciplinas.stream()
				.filter(d -> "optativa".equalsIgnoreCase(d.getTipoDisciplina().name())).toList();

		// Busca alunos do curso
		List<Aluno> alunos = alunoDAO.buscarPorCurso(idCurso);

		// Busca alunos que fizeram todas disciplinas obrigatórias
		List<Aluno> alunosObrigatoria = alunoDAO.alunosTodasObrigatorias(idCurso);

		// Busca alunos que não fizeram nenhuma optativa
		List<Aluno> alunosOptativas = alunoDAO.alunosNenhumaOptativa(idCurso);

		return new InformacoesCurso(cursoDAO.buscarNomePorCodigo(idCurso),
				disciplinasObrigatorias, disciplinasOptativas, alunos, alunosObrigatoria, alunosOptativas);
	}

}
