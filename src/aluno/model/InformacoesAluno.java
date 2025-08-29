package aluno.model;

import java.util.List;

import disciplina.model.DisciplinaCursada;

public class InformacoesAluno {
	private String nome;
	private String endereco;
	private String tipo;
	private String nomeCurso;
	private List<DisciplinaCursada> disciplinasAndamento;
	private List<DisciplinaCursada> disciplinasConcluidas;

	public InformacoesAluno(String nome, String endereco, String tipo, String nomeCurso,
			List<DisciplinaCursada> disciplinasAndamento, List<DisciplinaCursada> disciplinasConcluidas) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.tipo = tipo;
		this.nomeCurso = nomeCurso;
		this.disciplinasAndamento = disciplinasAndamento;
		this.disciplinasConcluidas = disciplinasConcluidas;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<DisciplinaCursada> getDisciplinasAndamento() {
		return disciplinasAndamento;
	}

	public void setDisciplinasAndamento(List<DisciplinaCursada> disciplinasAndamento) {
		this.disciplinasAndamento = disciplinasAndamento;
	}

	public List<DisciplinaCursada> getDisciplinasConcluidas() {
		return disciplinasConcluidas;
	}

	public void setDisciplinasConcluidas(List<DisciplinaCursada> disciplinasConcluidas) {
		this.disciplinasConcluidas = disciplinasConcluidas;
	}

}
