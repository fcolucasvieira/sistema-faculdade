package curso.model;

import java.util.List;

import aluno.model.Aluno;
import disciplina.model.Disciplina;


public class InformacoesCurso {
	private String nomeCurso;
	private List<Disciplina> DiscplinasObrigatorias;
	private List<Disciplina> DiscplinasOptativas;
	private List<Aluno> AlunosCurso;
	private List<Aluno> AlunosTodasObrigatorias;
	private List<Aluno> AlunosNenhumaOptativa;

	public InformacoesCurso(String nomeCurso, List<Disciplina> discplinasObrigatorias,
			List<Disciplina> discplinasOptativas, List<Aluno> alunosCurso, List<Aluno> alunosTodasObrigatorias,
			List<Aluno> alunosNenhumaOptativa) {
		this.nomeCurso = nomeCurso;
		DiscplinasObrigatorias = discplinasObrigatorias;
		DiscplinasOptativas = discplinasOptativas;
		AlunosCurso = alunosCurso;
		AlunosTodasObrigatorias = alunosTodasObrigatorias;
		AlunosNenhumaOptativa = alunosNenhumaOptativa;

	}

	// Getters e Setters
	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<Disciplina> getDiscplinasObrigatorias() {
		return DiscplinasObrigatorias;
	}

	public void setDiscplinasObrigatorias(List<Disciplina> discplinasObrigatorias) {
		DiscplinasObrigatorias = discplinasObrigatorias;
	}

	public List<Disciplina> getDiscplinasOptativas() {
		return DiscplinasOptativas;
	}

	public void setDiscplinasOptativas(List<Disciplina> discplinasOptativas) {
		DiscplinasOptativas = discplinasOptativas;
	}

	public List<Aluno> getAlunosCurso() {
		return AlunosCurso;
	}

	public void setAlunosCurso(List<Aluno> alunosCurso) {
		AlunosCurso = alunosCurso;
	}

	public List<Aluno> getAlunosTodasObrigatorias() {
		return AlunosTodasObrigatorias;
	}

	public void setAlunosTodasObrigatorias(List<Aluno> alunosTodasObrigatorias) {
		AlunosTodasObrigatorias = alunosTodasObrigatorias;
	}

	public List<Aluno> getAlunosNenhumaOptativa() {
		return AlunosNenhumaOptativa;
	}

	public void setAlunosNenhumaOptativa(List<Aluno> alunosNenhumaOptativa) {
		AlunosNenhumaOptativa = alunosNenhumaOptativa;
	}

}
