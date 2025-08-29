package departamento.model;

public class Departamento {
	private int codigo;  // chave primaria
	private String nome;

	public Departamento(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	// Construtor para inserção (sem código, pois é auto_increment)
	public Departamento(String nome) {
		this.nome = nome;
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

	@Override
	public String toString() {
		return "Departamento -> \nCódigo - " + codigo + ";\nNome - " + nome + ".";
	}
}