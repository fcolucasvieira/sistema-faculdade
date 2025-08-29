package disciplina.model;

public class DisciplinaProfessor {
	private int id_disciplina; // FK para disciplina
	private int siape_professor; // FK para professor
	
	public DisciplinaProfessor(int id_disciplina, int siape_professor) {
		this.id_disciplina = id_disciplina;
		this.siape_professor = siape_professor;
	}

	
	// Getters e Setters
	public int getIdDisciplina() {
		return id_disciplina;
	}

	public void setIdDisciplina(int id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public int getSiapeProfessor() {
		return siape_professor;
	}

	public void setSiapeProfessor(int siape_professor) {
		this.siape_professor = siape_professor;
	}
	
	
	@Override
	public String toString() {
		return "Disciplina Professor -> \nId(Disciplina) - " + id_disciplina 
				+ ";\nSiape(Professor) - " + siape_professor + ".";
	}
}
