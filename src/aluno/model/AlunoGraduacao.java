package aluno.model;

public class AlunoGraduacao {
	private int matricula; // chave primária e FK para aluno
	private int ano_ingresso;

	public AlunoGraduacao(int matricula, int ano_ingresso) {
		this.matricula = matricula;
		this.ano_ingresso = ano_ingresso;
	}

	
	//Getters e Setters
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getAnoIngresso() {
		return ano_ingresso;
	}

	public void setAnoIngresso(int ano_ingresso) {
		this.ano_ingresso = ano_ingresso;
	}
	
	
	@Override
	public String toString() {
		return "Aluno Graduacao -> \nMatrícula - " + matricula + ";\nAno de ingresso - " + ano_ingresso + ".";
	}
}
