package views;

import java.util.List;
import java.util.Scanner;

import aluno.dao.AlunoDAO;
import aluno.model.Aluno;
import disciplina.dao.DisciplinaCursadaDAO;
import disciplina.dao.DisciplinaDAO;
import disciplina.model.Disciplina;
import disciplina.model.DisciplinaCursada;
import professor.dao.ProfessorDAO;
import professor.model.Professor;

public class MenuProfessorLogin {
	public MenuProfessorLogin(int siape) {
		Scanner sc = new Scanner(System.in);
		int opcao;
		
		try {
			Professor p = ProfessorDAO.buscarPorSiape(siape);
			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
			DisciplinaCursadaDAO disciplinaCursadaDAO = new DisciplinaCursadaDAO();
			AlunoDAO alunoDAO = new AlunoDAO();
			
			disciplinaCursadaDAO.criarViewProfessor(siape);
			
			do {
				
				System.out.println("\n--- MENU PROFESSOR ---");
				System.out.println("1. Ver informações pessoais");
				System.out.println("2. Ver disciplinas que ministra");
				System.out.println("3. Ver alunos de uma disciplina");
				System.out.println("4. Alterar média final de aluno");
				System.out.println("5. Alterar frequência de aluno");
				System.out.println("0. Sair");
				System.out.print("Escolha uma opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
	
				
				
				switch (opcao) {
					case 1:
						System.out.println("Siape: " + p.getSiape() + " | Nome: " + p.getNome() + " | Data de nascimento: " + p.getDataNascimento() + " | Data de ingresso: " + p.getDataIngresso() + " | Id departamento: " + p.getIdDepartamento());
						break;
					case 2:
						List<Disciplina> disciplinas = disciplinaDAO.buscarPorProfessor(siape);
											
						System.out.println("----- Disciplinas que ministra -----");
						System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s | %-20s\n", "Código", "Nome", "Ementa", "Créditos",
								"Tipo", "ID Curso");
						System.out.println(
								"--------------------------------------------------------------------------------------------------------------------------------");
						for(Disciplina disciplina : disciplinas) {
							System.out.printf("%-6d | %-30s | %-50s | %-8d | %-11s | %-20d\n", disciplina.getCodigo(), disciplina.getNome(),
									disciplina.getEmenta(), disciplina.getCreditos(), disciplina.getTipoDisciplina(), disciplina.getIdCurso());
						}
						
						break;
					case 3:
						
						System.out.println("Código da disciplina:");
						int cod_disciplina = sc.nextInt();
						
						List<Aluno> alunos = alunoDAO.buscarPorDisciplinaView(cod_disciplina);

						if(disciplinaCursadaDAO.buscarPorCodigoView(cod_disciplina) != null) {
							System.out.printf("----- Alunos de %s -----\n", disciplinaDAO.buscarPorCodigo(cod_disciplina).getNome());
							System.out.printf("%-9s | %-30s | %-30s | %-8s | %-9s | %-11s | %-10s\n", "Matrícula", "Nome", "Endereço", "ID Curso", "Tipo", "Média Final", "Frequência");
							System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
							for(Aluno aluno : alunos) {
								DisciplinaCursada disc_cursada = disciplinaCursadaDAO.buscarDisciplinasPorMatriculaECodigo(aluno.getMatricula(), cod_disciplina);
								if(alunoDAO.checarMatriculado(aluno.getMatricula(), cod_disciplina)) 
									System.out.printf("%-9d | %-30s | %-30s | %-8s | %-9s | %-11s | %-10s\n", aluno.getMatricula(), aluno.getNome(), aluno.getEndereco(),
										aluno.getIdCurso(), aluno.getTipoAluno(), "NULL", disc_cursada.getFrequencia());				
								else 
									System.out.printf("%-9d | %-30s | %-30s | %-8s | %-9s | %-11s | %-10s\n", aluno.getMatricula(), aluno.getNome(), aluno.getEndereco(),
										aluno.getIdCurso(), aluno.getTipoAluno(), disc_cursada.getMediaFinal(), disc_cursada.getFrequencia());				
							}
						}
						else {
							System.out.println("Esse professor não ministra essa disciplina.");
						}
						
						break;
					case 4:
						System.out.println("Código da disciplina:");
						int cod_disc = sc.nextInt();
						
						if(disciplinaCursadaDAO.buscarPorCodigoView(cod_disc) != null) {
							System.out.println("Matrícula do aluno:");
							int mat_aluno = sc.nextInt();
							sc.nextLine();
							
							DisciplinaCursada disc_cursada = disciplinaCursadaDAO.buscarDisciplinasPorMatriculaECodigo(mat_aluno, cod_disc);
							
							if(disc_cursada != null) {
								System.out.println("Nova média:");
								float nova_media = sc.nextFloat();
							
								disciplinaCursadaDAO.atualizarMediaFinal(disc_cursada, nova_media);
							} else {
								System.out.println("Esse aluno não está matriculado nessa disciplina.");
							}
						} else {
							System.out.println("Esse professor não ministra essa disciplina.");
						}
						break;
					case 5:
						System.out.println("Código da disciplina:");
						int codigo_disc = sc.nextInt();
						
						if(disciplinaCursadaDAO.buscarPorCodigoView(codigo_disc) != null) {
							System.out.println("Matrícula do aluno:");
							int matr_aluno = sc.nextInt();
							sc.nextLine();
							
							DisciplinaCursada disc_cursada = disciplinaCursadaDAO.buscarDisciplinasPorMatriculaECodigo(matr_aluno, codigo_disc);
							
							if(disc_cursada != null) {
								System.out.println("Nova frequencia:");
								float nova_frequencia = sc.nextFloat();
							
								disciplinaCursadaDAO.atualizarFrequencia(disc_cursada, nova_frequencia);
							} else {
								System.out.println("Esse aluno não está matriculado nessa disciplina.");
							}
						} else {
							System.out.println("Esse professor não ministra essa disciplina.");
						}
						break;

	
					case 0:
						System.out.println("Saindo do sistema... ");
						break;
					default:
						System.out.println("Opção inválida.");
						break;
				}	
			} while (opcao != 0);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
