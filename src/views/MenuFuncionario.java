package views;

import java.util.Scanner;

import departamento.dao.DepartamentoDAO;
import departamento.model.Departamento;

public class MenuFuncionario {
	private static int idDepartamento;

	public static void setIdDepartamento(int idDepartamento) {
		MenuFuncionario.idDepartamento = idDepartamento;
	}

	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();

		int opcao;

		do {
			System.out.println("\n---> FUNCIONÁRIO MENU (Id Dept: " + idDepartamento + ")<---");
			System.out.println("1. Visualizar departamento.");
			System.out.println("2. Atualizar departamento.");
			System.out.println("0. Voltar");
			System.out.print("\nEscolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				Departamento dept = departamentoDAO.buscarPorCodigo(idDepartamento);
				if (dept != null) {
					System.out.println("Código do departamento: " + dept.getCodigo());
					System.out.println("Nome do departamento: " + dept.getNome());

				} else {
					System.out.println("Departamento não encontrado.");
				}
				break;
			case 2:
				Departamento deptAtual = departamentoDAO.buscarPorCodigo(idDepartamento);
				if (deptAtual != null) {
					System.out.println("Nome do departamento: ");
					String newNome = sc.nextLine();
					deptAtual.setNome(newNome);

					if (departamentoDAO.atualizar(deptAtual)) {
						System.out.println("Departamento atualizado com sucesso!");
					} else {
						System.out.println("Erro ao atualizar departamento");
					}
				} else {
					System.out.println("Departamento não encontrado.");
				}
				break;
			case 0:
				System.out.println("Saindo do sistema... ");
				break;
			default:
				System.out.println("Opção invalida.");
				break;
			}
		} while (opcao != 0);
	}
}
