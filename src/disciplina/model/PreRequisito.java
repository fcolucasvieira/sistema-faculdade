package disciplina.model;

public class PreRequisito {
	private int id_disciplina; // FK para disciplina
	private int id_disciplina_requisito; // FK para disciplina
	
	public PreRequisito(int id_disciplina, int id_disciplina_requisito) {
		this.id_disciplina = id_disciplina;
		this.id_disciplina_requisito = id_disciplina_requisito;
	}

	// Getters e Setters
	public int getIdDisciplina() {
		return id_disciplina;
	}

	public void setIdDisciplina(int id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public int getIdDisciplinaRequisito() {
		return id_disciplina_requisito;
	}

	public void setIdDisciplinaRequisito(int id_disciplina_requisito) {
		this.id_disciplina_requisito = id_disciplina_requisito;
	}
	
	
	@Override
	public String toString() {
		return "Pré requisito -> \nCódigo da disciplina - " + id_disciplina
				+ ";\nCódigo da disciplina requisito - " + id_disciplina_requisito + ".";
	}
}
