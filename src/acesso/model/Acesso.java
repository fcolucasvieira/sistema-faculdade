package acesso.model;

public class Acesso {
	public enum nivel {
		DBA, funcionario, comum
	};

	private int id; // chave primaria
	private String nome;
	private String login;
	private String senha;
	private nivel nivelAcesso;

	// Identificadores opcionais
	private Integer matriculaAluno; // Nível - comum (aluno)
	private Integer siapeProfessor; // Nível -> comum (professor)
	private Integer idDeptFuncionario; // Nível -> funcionário

	// Construtor completo (recuperação)
	public Acesso(int id, String nome, String login, String senha, String nivelAcesso, Integer matriculaAluno,
			Integer siapeProfessor, Integer idDeptFuncionario) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.nivelAcesso = nivel.valueOf(nivelAcesso);
		this.matriculaAluno = matriculaAluno;
		this.siapeProfessor = siapeProfessor;
		this.idDeptFuncionario = idDeptFuncionario;
	}

	// Construtor para inserção (sem id, pois é auto_increment)
	public Acesso(String nome, String login, String senha, String nivelAcesso, Integer matriculaAluno,
			Integer siapeProfessor, Integer idDeptFuncionario) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.nivelAcesso = nivel.valueOf(nivelAcesso);
		this.matriculaAluno = matriculaAluno;
		this.siapeProfessor = siapeProfessor;
		this.idDeptFuncionario = idDeptFuncionario;
	}

	// Getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public nivel getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(nivel nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public Integer getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(Integer matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public Integer getSiapeProfessor() {
		return siapeProfessor;
	}

	public void setSiapeProfessor(Integer siapeProfessor) {
		this.siapeProfessor = siapeProfessor;
	}

	public Integer getIdDeptFuncionario() {
		return idDeptFuncionario;
	}

	public void setIdDeptFuncionario(Integer idDeptFuncionario) {
		this.idDeptFuncionario = idDeptFuncionario;
	}

	@Override
	public String toString() {
		return "Acesso ->" + "\nId - " + id + "\nNome - " + nome + "\nLogin - " + login + "\nSenha:" + senha
				+ "\nNível de acesso - " + nivelAcesso + "\nMatrícula(aluno) - "
				+ (matriculaAluno != null ? matriculaAluno : "N/A") + "\nSiape(Professor) - "
				+ (siapeProfessor != null ? siapeProfessor : "N/A") + "\nId do departamento(Funcionário) - "
				+ (idDeptFuncionario != null ? idDeptFuncionario : "N/A");
	}

}