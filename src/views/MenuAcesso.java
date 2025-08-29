package views;

import java.util.List;
import java.util.Scanner;

import acesso.dao.AcessoDAO;
import acesso.model.Acesso;

public class MenuAcesso {
	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		AcessoDAO acessoDAO = new AcessoDAO();

		int opcao;

		do {
			System.out.println("\n---> ACESSO <---");
			System.out.println("1. Inserir acesso.");
			System.out.println("2. Atualizar acesso.");
			System.out.println("3. Remover acesso.");
			System.out.println("4. Listar acessos.");
			System.out.println("0. Voltar");
			System.out.println("\nEscolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();
			switch (opcao) {
			case 1:
				System.out.println("Nome: ");
				String nome = sc.nextLine();
				System.out.println("Login: ");
				String login = sc.nextLine();
				System.out.println("Senha: ");
				String senha = sc.nextLine();

				String nivelAcesso;
				do {
					System.out.println("Nível de acesso (DBA, comum, funcionário): ");
					nivelAcesso = sc.nextLine();
				} while (!nivelAcesso.equals("DBA") && !nivelAcesso.equals("comum")
						&& !nivelAcesso.equals("funcionário"));

				Integer matricula = null, siape = null, idDept = null;
				if (nivelAcesso.equals("comum")) {
					System.out.println("Tipo de acesso (1 - Aluno, 2 - Professor):  ");
					int tipo = sc.nextInt();
					sc.nextLine();

					if (tipo == 1) {
						System.out.println("Matrícula do aluno: ");
						matricula = sc.nextInt();
						sc.nextLine();
					} else if (tipo == 2) {
						System.out.println("Siape do professor: ");
						siape = sc.nextInt();
						sc.nextLine();
					}
				} else if (nivelAcesso.equals("funcionario")) {
					System.out.println("Código do departamento: ");
					idDept = sc.nextInt();
					sc.nextLine();
				}

				Acesso acesso = new Acesso(nome, login, senha, nivelAcesso, matricula, siape, idDept);
				if (acessoDAO.inserir(acesso))
					System.out.println("Acesso inserido com sucesso!");
				else
					System.out.println("Erro ao inserir acesso.");
				break;
			case 2:
				System.out.println("Id do acesso a atualizar: ");
				int idAcesso = sc.nextInt();
				sc.nextLine();

				Acesso acessoAt = acessoDAO.buscarPorId(idAcesso);
				if (acessoAt != null) {
					System.out.println("Nome: ");
					String newNome = sc.nextLine();
					System.out.println("Login: ");
					String newLogin = sc.nextLine();
					System.out.println("Senha: ");
					String newSenha = sc.nextLine();

					String newNivel;
					do {
						System.out.println("Nível de acesso (DBA, comum, funcionário): ");
						newNivel = sc.nextLine();
					} while (!newNivel.equals("DBA") && !newNivel.equals("comum") && !newNivel.equals("funcionário"));

					Integer matriculaNew = null, siapeNew = null, idDeptNew = null;
					if (newNivel.equals("comum")) {
						System.out.println("Tipo de acesso (1 - Aluno, 2 - Professor):  ");
						int tipo = sc.nextInt();
						sc.nextLine();

						if (tipo == 1) {
							System.out.println("Matrícula do aluno: ");
							matriculaNew = sc.nextInt();
							sc.nextLine();
						} else if (tipo == 2) {
							System.out.println("Siape do professor: ");
							siapeNew = sc.nextInt();
							sc.nextLine();
						}
					} else if (newNivel.equals("funcionario")) {
						System.out.println("Código do departamento: ");
						idDeptNew = sc.nextInt();
						sc.nextLine();
					}

					acessoAt.setNome(newNome);
					acessoAt.setLogin(newLogin);
					acessoAt.setSenha(newSenha);
					acessoAt.setNivelAcesso(Acesso.nivel.valueOf(newNivel));
					acessoAt.setMatriculaAluno(matriculaNew);
					acessoAt.setSiapeProfessor(siapeNew);
					acessoAt.setIdDeptFuncionario(idDeptNew);
					if (acessoDAO.atualizar(acessoAt))
						System.out.println("Acesso atualizado com sucesso!");
					else
						System.out.println("Erro ao atualizar acesso.");
				} else {
					System.out.println("Erro ao buscar acesso por id.");
				}
				break;
			case 3:
				System.out.println("Id do acesso a remover: ");
				int idRemover = sc.nextInt();
				sc.nextLine();

				Acesso acessoRe = acessoDAO.buscarPorId(idRemover);

				if (acessoRe != null) {
					if (acessoDAO.remover(acessoRe))
						System.out.println("Acesso removido com sucesso!");
					else
						System.out.println("Erro ao remover acesso.");
				} else {
					System.out.println("Acesso não encontrado.");
				}
				break;
			case 4:
				List<Acesso> acessos = acessoDAO.listarTodos();
				System.out.println("----- Lista de acessos -----");
				System.out.printf("%-6s | %-30s | %-15s | %-15s | %-15s | %-10s | %-10s | %-10s\n", "Id", "Nome",
						"Login", "Senha", "Nível de acesso", "Aluno", "Professor", "Cód. Dep.");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
				for (Acesso acessoLista : acessos) {
					System.out.printf("%-6d | %-30s | %-15s | %-15s | %-15s | %-10s | %-10s | %-10s\n",
							acessoLista.getId(), acessoLista.getNome(), acessoLista.getLogin(), acessoLista.getSenha(),
							acessoLista.getNivelAcesso().name(), acessoLista.getMatriculaAluno(),
							acessoLista.getSiapeProfessor(), acessoLista.getIdDeptFuncionario());
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
	}
}