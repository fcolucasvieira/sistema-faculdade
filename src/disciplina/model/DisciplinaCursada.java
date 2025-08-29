package disciplina.model;

public class DisciplinaCursada {
	private int matricula_aluno; // FK para aluno
	private int codigo_disciplina; // FK para disciplina
	private float media_final;
	private float frequencia;
	
	public DisciplinaCursada(int matricula_aluno, int codigo_disciplina, float media_final, float frequencia) {
		this.matricula_aluno = matricula_aluno;
		this.codigo_disciplina = codigo_disciplina;
		this.media_final = media_final;
		this.frequencia = frequencia;
	}
	
	
	// Verifica se a disciplina foi concluída ou não
	public boolean Concluida() {
		return media_final >= 7.0 && frequencia >= 0.75;
	}
	
	// Getters e Setters
	public int getMatriculaAluno() {
		return matricula_aluno;
	}

	public void setMatriculaAluno(int matricula_aluno) {
		this.matricula_aluno = matricula_aluno;
	}

	public int getCodigoDisciplina() {
		return codigo_disciplina;
	}

	public void setCodigoDisciplina(int codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}

	public float getMediaFinal() {
		return media_final;
	}

	public void setMediaFinal(float media_final) {
		this.media_final = media_final;
	}

	public float getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(float frequencia) {
		this.frequencia = frequencia;
	}
	
	
	@Override
	public String toString() {
		return "Disciplina Cursa -> \nMatrícula aluno - " + matricula_aluno 
				+ ";\nCódigo da disciplina - " + codigo_disciplina + ";\nMédia final - " + media_final  
				+ ";\nFrequência - " + frequencia + ".";
	}
}
