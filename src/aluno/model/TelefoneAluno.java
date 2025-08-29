package aluno.model;

public class TelefoneAluno {
	private String numero;
	private String descricao;
	private int matricula; // FK para aluno
	
	public TelefoneAluno(String numero, String descricao, int matricula) {
		this.numero = numero;
		this.descricao = descricao;
		this.matricula = matricula;
	}

	
	// Getters e Setters
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	
	@Override
	public String toString() {
		return "Telefone Aluno -> \nNúmero - " + numero + ";\nDescrição - " + descricao
				+ ";\nMatrícula(Aluno) - " + matricula + ".";
	}
}
