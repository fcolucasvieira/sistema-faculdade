package curso.model;

public class Curso {
	private int codigo; // chave primaria
	private String nome;
	private int creditosMinimos;
	private int idDepartamento; // FK para departamento

	public Curso(int codigo, String nome, int creditosMinimos, int idDepartamento) {
		this.codigo = codigo;
		this.nome = nome;
		this.creditosMinimos = creditosMinimos;
		this.idDepartamento = idDepartamento;
	}

	// Construtor para inserção (sem código, pois é auto_increment)
	public Curso(String nome, int creditosMinimos, int idDepartamento) {
		this.nome = nome;
		this.creditosMinimos = creditosMinimos;
		this.idDepartamento = idDepartamento;
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

	public int getCreditosMinimos() {
		return creditosMinimos;
	}

	public void setCreditosMinimos(int creditosMinimos) {
		this.creditosMinimos = creditosMinimos;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return "Curso -> \nCódigo - " + codigo + ";\nNome - " + nome + ";\nCréditos mínimos - " + creditosMinimos
				+ ";\nId(Departamento) - " + idDepartamento + ".";
	}
}
