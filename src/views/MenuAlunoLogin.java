package views;

import java.util.Scanner;

import aluno.dao.InformacoesAlunoDAO;
import aluno.model.InformacoesAluno;
import disciplina.dao.DisciplinaDAO;
import disciplina.model.Disciplina;
import disciplina.model.DisciplinaCursada;

public class MenuAlunoLogin {
	public MenuAlunoLogin(int matricula) {
		Scanner sc = new Scanner(System.in);
		int opcao;

		try {
		
			do {
				System.out.println("\n----- ALUNO MENU -----");
				System.out.println("1. Ver informações pessoais");
				System.out.println("2. Ver disciplinas em andamento");
				System.out.println("3. Ver disciplinas concluídas");
				System.out.println("0. Sair");
				System.out.print("Escolha uma opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
	
				InformacoesAlunoDAO infosAlunoDAO = new InformacoesAlunoDAO();
				InformacoesAluno infosAluno = infosAlunoDAO.buscarInformacoesAluno(matricula);
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
			
				
				switch (opcao) {
					case 1:
						System.out.println("Nome - " + infosAluno.getNome());
						System.out.println("Endereço - " + infosAluno.getEndereco());
						System.out.println("Tipo - " + infosAluno.getTipo());
						System.out.println("Curso - " + infosAluno.getNomeCurso());
						break;
	
					case 2:
						System.out.println("----- Disciplinas em andamento -----");
						System.out.printf("%-6s | %-30s | %-11s | %-10s", "Código", "Nome", "Média Final", "Frequência\n");
						System.out.println("------------------------------------------------------------------");
						for(DisciplinaCursada disciplina : infosAluno.getDisciplinasAndamento()) {
							Disciplina disc = disciplinaDAO.buscarPorCodigo(disciplina.getCodigoDisciplina());
							System.out.printf("%-6d | %-30s | %-11.2f | %-10.2f\n", disciplina.getCodigoDisciplina(), disc.getNome(), disciplina.getMediaFinal(), disciplina.getFrequencia());
						}
						break;
	
					case 3:
						System.out.println("----- Disciplinas concluídas -----");
						System.out.printf("%-6s | %-30s | %-11s | %-10s", "Código", "Nome", "Média Final", "Frequência\n");
						System.out.println("------------------------------------------------------------------");
						for(DisciplinaCursada disciplina : infosAluno.getDisciplinasConcluidas()) {
							Disciplina disc = disciplinaDAO.buscarPorCodigo(disciplina.getCodigoDisciplina());
							System.out.printf("%-6d | %-30s | %-11.2f | %-10.2f\n", disciplina.getCodigoDisciplina(), disc.getNome(), disciplina.getMediaFinal(), disciplina.getFrequencia());
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
