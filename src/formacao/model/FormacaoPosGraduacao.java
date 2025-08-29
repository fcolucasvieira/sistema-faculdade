package formacao.model;

public class FormacaoPosGraduacao {
	private int matricula_aluno; // FK para aluno
	private int id_curso; // FK para curso
	
	public FormacaoPosGraduacao(int matricula_aluno, int id_curso) {
		this.matricula_aluno = matricula_aluno;
		this.id_curso = id_curso;
	}

	// Getters e Setters
	public int getMatriculaAluno() {
		return matricula_aluno;
	}

	public void setMatriculaAluno(int matricula_aluno) {
		this.matricula_aluno = matricula_aluno;
	}

	public int getIdCurso() {
		return id_curso;
	}

	public void setIdCurso(int id_curso) {
		this.id_curso = id_curso;
	}
	
	
	@Override
	public String toString() {
		return "Formação Pós Graduação -> \nMatricula(aluno) - " + matricula_aluno
				+ ";\nId(Curso) - " + id_curso + ".";
	}
}
