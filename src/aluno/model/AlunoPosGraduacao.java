package aluno.model;

public class AlunoPosGraduacao {
	private int matricula; // FK para aluno
	private int orientador_siape; // FK para professor
	
	public AlunoPosGraduacao(int matricula, int orientador_siape) {
		this.matricula = matricula;
		this.orientador_siape = orientador_siape;
	}

	
	// Getters e Setters
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getOrientadorSiape() {
		return orientador_siape;
	}

	public void setOrientadorSiape(int orientador_siape) {
		this.orientador_siape = orientador_siape;
	}
	
	
	@Override
	public String toString() {
		return "Aluno Pos Graduacao -> \nMatrícula - " + matricula + ";\nSiape do orientador - " + orientador_siape + ".";
	}
}
