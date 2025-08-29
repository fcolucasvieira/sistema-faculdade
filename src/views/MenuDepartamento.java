package views;

import java.util.List;
import java.util.Scanner;

import departamento.dao.DepartamentoDAO;
import departamento.model.Departamento;

public class MenuDepartamento {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);

		DepartamentoDAO deptDAO = new DepartamentoDAO();
		int opcao;

		do {
			System.out.println("\n---> DEPARTAMENTO <---");
			System.out.println("1. Inserir departamento.");
			System.out.println("2. Atualizar departamento.");
			System.out.println("3. Remover departamento.");
			System.out.println("4. Listar departamentos.");
			System.out.println("0. Voltar");
			System.out.print("\nEscolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Nome do departamento: ");
				String nome = sc.nextLine();
				Departamento dept = new Departamento(0, nome);

				if (deptDAO.inserir(dept))
					System.out.println("Departamento inserido com sucesso!");
				else
					System.out.println("Erro ao inserir departamento.");
				break;

			case 2:
				System.out.println("Código do departamento a atualizar: ");
				int codigoAt = sc.nextInt();
				sc.nextLine();
				Departamento deptExistente = deptDAO.buscarPorCodigo(codigoAt);
				if (deptExistente != null) {
					System.out.println("Nome: ");
					String novoNome = sc.nextLine();
					deptExistente.setNome(novoNome);
					if (deptDAO.atualizar(deptExistente))
						System.out.println("Departamento atualizado com sucesso!");
					else
						System.out.println("Erro ao atualizar departamento");
				} else {
					System.out.println("Departamento não encontrado.");
				}
				break;

			case 3:
				System.out.println("Código do departamento a remover: ");
				int codigoRe = sc.nextInt();
				sc.nextLine();
				Departamento deptRemover = deptDAO.buscarPorCodigo(codigoRe);
				if (deptRemover != null) {
					if (deptDAO.remover(deptRemover))
						System.out.println("Departamento removido com sucesso!");
					else
						System.out.println("Erro ao remover departamento");
				} else {
					System.out.println("Departamento não encontrado.");
				}
				break;

			case 4:
				List<Departamento> lista = deptDAO.listarTodos();
				System.out.println("----- Lista de departamentos -----");
				System.out.printf("%-10s | %-30s\n", "Código", "Nome");
				System.out.println("----------------------------------------------");
				for (Departamento deptLista : lista) {
					System.out.printf("%-10d | %-30s\n", deptLista.getCodigo(), deptLista.getNome());
				}

				break;
			case 0:
				System.out.println("\nVoltando...");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 0);

	}
}
