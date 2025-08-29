package professor.model;

import java.util.List;

import aluno.model.Aluno;
import disciplina.model.Disciplina;

public class InformacoesOrientador {
	private List<Aluno> orientandos;
	private List<Disciplina> disciplinas;
	private int totalCreditos;
	
	public InformacoesOrientador(List<Aluno> orientandos, List<Disciplina> disciplinas, int totalCreditos) {
		this.orientandos = orientandos;
		this.disciplinas = disciplinas;
		this.totalCreditos = totalCreditos;
	}

	// Getters e Setters
	public List<Aluno> getOrientandos() {
		return orientandos;
	}

	public void setOrientandos(List<Aluno> orientandos) {
		this.orientandos = orientandos;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getTotalCreditos() {
		return totalCreditos;
	}

	public void setTotalCreditos(int totalCreditos) {
		this.totalCreditos = totalCreditos;
	}
}
