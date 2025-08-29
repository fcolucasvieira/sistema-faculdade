package views;

import java.util.Scanner;

import aluno.dao.InformacoesAlunoDAO;
import aluno.model.Aluno;
import aluno.model.InformacoesAluno;
import curso.dao.InformacoesCursoDAO;
import curso.model.Curso;
import curso.model.InformacoesCurso;
import departamento.dao.InformacoesDepartamentoDAO;
import departamento.model.InformacoesDepartamento;
import disciplina.dao.DisciplinaDAO;
import disciplina.dao.InformacoesDisciplinaDAO;
import disciplina.model.Disciplina;
import disciplina.model.DisciplinaCursada;
import disciplina.model.InformacoesDisciplina;
import disciplina.model.PreRequisito;
import professor.dao.InformacoesOrientadorDAO;
import professor.dao.ProfessorDAO;
import professor.model.InformacoesOrientador;
import professor.model.Professor;

public class MenuRelatorio {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		int opcao;

		try {
			do {
				System.out.println("\n---> RELATÓRIOS <---");
				System.out.println("1. Relatório departamento.");
				System.out.println("2. Relatório curso.");
				System.out.println("3. Relatório disciplina.");
				System.out.println("4. Relatório aluno.");
				System.out.println("5. Relatório orientador.");
				System.out.println("0. Voltar.");
				System.out.print("\nEscolha uma opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

				switch (opcao) {
				case 1:
					InformacoesDepartamentoDAO infosDepartamentoDAO = new InformacoesDepartamentoDAO();

					System.out.println("Código do departamento:");
					int cod_depart = sc.nextInt();

					InformacoesDepartamento infosDepartamento = infosDepartamentoDAO.buscarInformacoes(cod_depart);


					System.out.println("Código - " + infosDepartamento.getCodigo());
					System.out.println("Nome - " + infosDepartamento.getNome());

					System.out.println("\n----- Disciplinas do departamento -----");

					System.out.println("----- Cursos do Departamento -----");

					System.out.printf("%-6s | %-30s | %-16s\n", "Código", "Nome", "Créditos Mínimos");
					System.out.println(
							"------------------------------------------------------------------");
					for (Curso curso : infosDepartamento.getCursos()) {
						System.out.printf("%-6s | %-30s | %-16s\n", curso.getCodigo(), curso.getNome(), curso.getCreditosMinimos());
					}
					break;

				case 2:
					InformacoesCursoDAO infosCursoDAO = new InformacoesCursoDAO();

					System.out.println("Código do curso:");
					int cod_curso = sc.nextInt();

					InformacoesCurso infosCurso = infosCursoDAO.buscarInformacoesCurso(cod_curso);

					System.out.println("Nome - " + infosCurso.getNomeCurso());

					System.out.println("\n----- Disciplinas Obrigatórias -----");
					System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", "Código", "Nome", "Ementa", "Créditos", "Tipo");
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");

					for (Disciplina disciplina : infosCurso.getDiscplinasObrigatorias()) {
						System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", disciplina.getCodigo(), disciplina.getNome(), disciplina.getEmenta(), disciplina.getCreditos(), disciplina.getTipoDisciplina());
					}

					System.out.println("\n----- Disciplinas Optativas -----");
					System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", "Código", "Nome", "Ementa", "Créditos", "Tipo");
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");

					for (Disciplina disciplina : infosCurso.getDiscplinasOptativas()) {
						System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", disciplina.getCodigo(), disciplina.getNome(), disciplina.getEmenta(), disciplina.getCreditos(), disciplina.getTipoDisciplina());
					}

					System.out.println("\n----- Alunos do curso -----");
					System.out.printf("%-9s | %-30s | %-50s | %-9s\n", "Matrícula", "Nome", "Endereço", "Tipo");
					System.out.println(
							"------------------------------------------------------------------------------------------------------");
					for (Aluno aluno : infosCurso.getAlunosCurso()) {
						System.out.printf("%-9s | %-30s | %-50s | %-9s\n", aluno.getMatricula(), aluno.getNome(), aluno.getEndereco(), aluno.getTipoAluno());
					}
					break;

				case 3:
					InformacoesDisciplinaDAO infosDisciplinaDAO = new InformacoesDisciplinaDAO();
					
					System.out.println("Código da disciplina:");
					int cod_disciplina = sc.nextInt();
					Disciplina disciplina = disciplinaDAO.buscarPorCodigo(cod_disciplina);

					InformacoesDisciplina infosDisciplina = infosDisciplinaDAO.buscarInformacoes(disciplina);

					System.out.println("\n----- Alunos matriculados -----");
					System.out.printf("%-9s | %-30s | %-50s | %-9s\n", "Matrícula", "Nome", "Endereço", "Tipo");
					System.out.println(
							"------------------------------------------------------------------------------------------------------");
					for (Aluno aluno : infosDisciplina.getAlunos()) {
						System.out.printf("%-9s | %-30s | %-50s | %-9s\n", aluno.getMatricula(), aluno.getNome(), aluno.getEndereco(), aluno.getTipoAluno());
					}

					System.out.println("\n----- Pré-Requisitos -----");
					System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", "Código", "Nome", "Ementa", "Créditos", "Tipo");
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");
					for (PreRequisito preRequisito : infosDisciplina.getPre_requisitos()) {
						Disciplina disc = disciplinaDAO.buscarPorCodigo(preRequisito.getIdDisciplina());
						System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", disc.getCodigo(), disc.getNome(), disc.getEmenta(), disc.getCreditos(), disc.getTipoDisciplina());
					}

					System.out.println("\n----- Disciplinas as quais é requisitada -----");
					System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", "Código", "Nome", "Ementa", "Créditos", "Tipo");
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");
					for (PreRequisito preRequisito : infosDisciplina.getRequisitada()) {
						Disciplina disc = disciplinaDAO.buscarPorCodigo(preRequisito.getIdDisciplina());
						System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", disc.getCodigo(), disc.getNome(), disc.getEmenta(), disc.getCreditos(), disc.getTipoDisciplina());
					}
					break;

				case 4:
					InformacoesAlunoDAO infosAlunoDAO = new InformacoesAlunoDAO();
				
					
					System.out.println("Matrícula do aluno:");
					int matricula = sc.nextInt();

					InformacoesAluno infosAluno = infosAlunoDAO.buscarInformacoesAluno(matricula);

					System.out.println("Nome - " + infosAluno.getNome());
					System.out.println("Endereços - " + infosAluno.getEndereco());
					System.out.println("Tipo - " + infosAluno.getTipo());
					System.out.println("Curso - " + infosAluno.getNomeCurso());

					System.out.println("\n----- Disciplinas em andamento -----");
					System.out.printf("%-6s | %-30s | %-11s | %-10s", "Código", "Nome", "Média Final", "Frequência\n");
					System.out.println("------------------------------------------------------------------");
					for(DisciplinaCursada disciplinaCursada : infosAluno.getDisciplinasAndamento()) {
						Disciplina disc = disciplinaDAO.buscarPorCodigo(disciplinaCursada.getCodigoDisciplina());
						System.out.printf("%-6d | %-30s | %-11.2f | %-10.2f\n", disciplinaCursada.getCodigoDisciplina(), disc.getNome(), disciplinaCursada.getMediaFinal(), disciplinaCursada.getFrequencia());
					}

					System.out.println("\n----- Disciplinas concluídas -----");
					System.out.printf("%-6s | %-30s | %-11s | %-10s", "Código", "Nome", "Média Final", "Frequência\n");
					System.out.println("------------------------------------------------------------------");
					for(DisciplinaCursada disciplinaCursada : infosAluno.getDisciplinasConcluidas()) {
						Disciplina disc = disciplinaDAO.buscarPorCodigo(disciplinaCursada.getCodigoDisciplina());
						System.out.printf("%-6d | %-30s | %-11.2f | %-10.2f\n", disciplinaCursada.getCodigoDisciplina(), disc.getNome(), disciplinaCursada.getMediaFinal(), disciplinaCursada.getFrequencia());
					}
					break;

				case 5:
					InformacoesOrientadorDAO infosOrientadorDAO = new InformacoesOrientadorDAO();

					System.out.println("Siape do orientador:");
					int siape_orientador = sc.nextInt();
					Professor orientador = ProfessorDAO.buscarPorSiape(siape_orientador);

					InformacoesOrientador infosOrientador = infosOrientadorDAO.buscarInformacoes(orientador);
					
					System.out.println("Total de créditos do orientador - " + infosOrientador.getTotalCreditos());

					System.out.println("\n----- Alunos orientados -----");
					System.out.printf("%-9s | %-30s | %-50s | %-9s\n", "Matrícula", "Nome", "Endereço", "Tipo");
					System.out.println(
							"------------------------------------------------------------------------------------------------------");
					for (Aluno aluno : infosOrientador.getOrientandos()) {
						System.out.printf("%-9s | %-30s | %-50s | %-9s\n", aluno.getMatricula(), aluno.getNome(), aluno.getEndereco(), aluno.getTipoAluno());
					}

					System.out.println("\n----- Disciplinas Ministradas -----");
					System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", "Código", "Nome", "Ementa", "Créditos", "Tipo");
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");
					for (Disciplina disciplina_dada : infosOrientador.getDisciplinas()) {
						System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s\n", disciplina_dada.getCodigo(), disciplina_dada.getNome(), disciplina_dada.getEmenta(), disciplina_dada.getCreditos(), disciplina_dada.getTipoDisciplina());
					}
					break;

				case 0:
					System.out.println("Voltando...");
					break;

				default:
					System.out.println("Opção inválida.");
					break;
				}
			} while (opcao != 0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}