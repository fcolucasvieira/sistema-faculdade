package disciplina.model;

import java.util.List;

import aluno.model.Aluno;

public class InformacoesDisciplina {
	private List<Aluno> alunos;
	private List<PreRequisito> pre_requisitos;
	private List<PreRequisito> requisitada;
	
	public InformacoesDisciplina(List<Aluno> alunos, List<PreRequisito> pre_requisitos, List<PreRequisito> requisitada) {
		this.alunos = alunos;
		this.pre_requisitos = pre_requisitos;
		this.requisitada = requisitada;
	}

	// Getters e Setters
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<PreRequisito> getPre_requisitos() {
		return pre_requisitos;
	}

	public void setPre_requisitos(List<PreRequisito> pre_requisitos) {
		this.pre_requisitos = pre_requisitos;
	}

	public List<PreRequisito> getRequisitada() {
		return requisitada;
	}

	public void setRequisitada(List<PreRequisito> requisitada) {
		this.requisitada = requisitada;
	}
}
