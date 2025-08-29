package professor.model;

import java.sql.Date;

public class Professor {
	private int siape; // chave primaria
	private String nome;
	private Date dataNascimento;
	private Date dataIngresso;
	private int idDepartamento; // FK para departamento

	public Professor(int siape, String nome, Date dataNascimento, Date dataIngresso, int idDepartamento) {
		this.siape = siape;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataIngresso = dataIngresso;
		this.idDepartamento = idDepartamento;
	}

	// Getters e Setters
	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return "Professor -> \nSiape - " + siape + ";\nNome - " + nome + ";\nData de nascimento - " + dataNascimento
				+ ";\nData de ingresso - " + dataIngresso + ";\nId(Departamento) - " + idDepartamento + ".";
	}
}
