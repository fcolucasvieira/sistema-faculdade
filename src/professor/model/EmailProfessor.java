package professor.model;

public class EmailProfessor {
	private String email;
	private int siape;  // FK para professor
	
	public EmailProfessor(String email, int siape) {
		this.email = email;
		this.siape = siape;
	}

	// Getters e Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
	}
	
	
	@Override
	public String toString() {
		return "Email Professor -> \nEmail - " + email + ";\nSiape(Professor) - " + siape + ".";
	}
}
