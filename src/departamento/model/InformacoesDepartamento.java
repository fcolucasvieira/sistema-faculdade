package departamento.model;

import java.util.List;

import curso.model.Curso;

public class InformacoesDepartamento {
	private int codigo;
	private String nome;
	private List<Curso> cursos;
	
	public InformacoesDepartamento(int codigo, String nome, List<Curso> cursos) {
		this.codigo = codigo;
		this.nome = nome;
		this.cursos = cursos;
	}

	// Getters e Setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
}
