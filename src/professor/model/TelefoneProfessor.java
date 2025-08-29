package professor.model;

public class TelefoneProfessor {
	private String numero;
	private String descricao;
	private int siape; // FK para professor
	
	public TelefoneProfessor(String numero, String descricao, int siape) {
		this.numero = numero;
		this.descricao = descricao;
		this.siape = siape;
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

	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
	}
	
	
	@Override
	public String toString() {
		return "Telefone Professor -> \nNúmero - " + numero + ";\nDescrição - " + descricao 
				+ ";\nSiape(Professor) - " + siape + ".";
	}
}
