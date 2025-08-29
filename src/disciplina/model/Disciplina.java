package disciplina.model;

public class Disciplina {
	public enum tipo {obrigatória, optativa};
	
	private int codigo; // chave primaria
	private String nome;
	private String ementa;
	private int creditos;
	private tipo tipo_disciplina;
	private int id_curso; // FK para curso
	
	public Disciplina(int codigo, String nome, String ementa, int creditos, String tipo_disciplina, int id_curso) {
		this.codigo = codigo;
		this.nome = nome;
		this.ementa = ementa;
		this.creditos = creditos;
		this.tipo_disciplina = tipo.valueOf(tipo_disciplina);
		this.id_curso = id_curso;
	}

	// Getters e setters
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

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	public tipo getTipoDisciplina() {
		return tipo_disciplina;
	}

	public void setTipoDisciplina(String tipo_disciplina) {
		this.tipo_disciplina = tipo.valueOf(tipo_disciplina);
	}
	
	public int getIdCurso() {
		return id_curso;
	}
	
	public void setIdcurso(int id_curso) {
		this.id_curso = id_curso;
	}

	@Override
	public String toString() {
		return "Disciplina -> \nCódigo - " + codigo + ";\nNome - " + nome + ";\nEmenta - " + ementa
				+ ";\nCréditos - " + creditos + ";\nTipo - " + tipo_disciplina + ";\nId(Curso) " + id_curso + ".";
	}
}
