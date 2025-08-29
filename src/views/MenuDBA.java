package views;

import java.util.Scanner;

public class MenuDBA {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("----- DBA MENU -----");
			System.out.println("1. Gerenciar departamentos.");
			System.out.println("2. Gerenciar cursos.");
			System.out.println("3. Gerenciar disciplinas.");
			System.out.println("4. Gerenciar alunos.");
			System.out.println("5. Gerenciar professores.");
			System.out.println("6. Gerenciar acessos.");
			System.out.println("7. Relatórios.");
			System.out.println("0. Sair");
			System.out.println("\nEscolha uma opção: ");
			
			opcao = sc.nextInt();
			sc.nextLine();
			
			
			switch (opcao) {
			case 1:
				MenuDepartamento.exibir();
				break;
			case 2:
				MenuCurso.exibir();
				break;
			case 3:
				MenuDisciplina.exibir();
				break;
			case 4:
				MenuAluno.exibir();
				break;
			case 5:
				MenuProfessor.exibir();
				break;
			case 6:
				MenuAcesso.exibir();
				break;
			case 7:
				MenuRelatorio.exibir();
				break;
			case 0:
				System.out.println("Saindo do sistema... ");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 0);

		sc.close();
	}
}
