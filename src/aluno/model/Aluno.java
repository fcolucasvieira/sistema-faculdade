package aluno.model;

public class Aluno {
	public enum tipo {graduacao, pos}
	
	private int matricula; // chave primária
	private String nome;
	private String endereco;
	private int id_curso; // FK para curso
	private tipo tipo_aluno;
	
	public Aluno(int matricula, String nome, String endereco, int id_curso, String tipo_aluno) {
		this.matricula = matricula;
		this.nome = nome;
		this.endereco = endereco;
		this.id_curso = id_curso;
		this.tipo_aluno = tipo.valueOf(tipo_aluno);
	}
	
	// Construtor para inserção (sem matrícula, pois é auto_increment)
	public Aluno(String nome, String endereco, int id_curso, String tipo_aluno) {
		this.nome = nome;
		this.endereco = endereco;
		this.id_curso = id_curso;
		this.tipo_aluno = tipo.valueOf(tipo_aluno);
	}


	// Getters e Setters
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

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

	public int getIdCurso() {
		return id_curso;
	}

	public void setIdCurso(int id_curso) {
		this.id_curso = id_curso;
	}

	public tipo getTipoAluno() {
		return tipo_aluno;
	}

	public void setTipoAluno(String tipo_aluno) {
		this.tipo_aluno = tipo.valueOf(tipo_aluno);
	}
	
	
	public String toString() {
		return "Aluno -> \nMatrícula - " + matricula + ";\nNome - " + nome + ";\nEndereço - " + endereco
				+ ";\nId(Curso) - " + id_curso + ";\nTipo - " + tipo_aluno + ".";
	}
}
