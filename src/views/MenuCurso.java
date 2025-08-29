package views;

import java.util.List;
import java.util.Scanner;

import curso.dao.CursoDAO;
import curso.model.Curso;
import departamento.dao.DepartamentoDAO;
import departamento.model.Departamento;

public class MenuCurso {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		CursoDAO cursoDAO = new CursoDAO();
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		int opcao;

		do {
			System.out.println("\n---> CURSO <---");
			System.out.println("1. Inserir curso.");
			System.out.println("2. Atualizar curso.");
			System.out.println("3. Remover curso.");
			System.out.println("4. Listar cursos.");
			System.out.println("0. Voltar");
			System.out.print("\nEscolha uma opção: ");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Nome do curso: ");
				String nome = sc.nextLine();

				System.out.println("Creditos mínimos do curso: ");
				int creditosMinimos = sc.nextInt();
				sc.nextLine();

				System.out.println("Código do departamento: ");
				int codigoDept = sc.nextInt();
				sc.nextLine();

				Departamento dept = departamentoDAO.buscarPorCodigo(codigoDept);
				if (dept == null) {
					System.out.println("Departamento não encontrado.");
					break;
				}

				Curso curso = new Curso(0, nome, creditosMinimos, codigoDept);
				if (cursoDAO.inserir(curso))
					System.out.println("Curso inserido com sucesso!");
				else
					System.out.println("Erro ao inserir curso.");
				break;

			case 2:
				System.out.println("Código do curso a atualizar: ");
				int codigoAt = sc.nextInt();
				sc.nextLine();

				Curso cursoExistente = CursoDAO.buscarPorCodigo(codigoAt);
				if (cursoExistente != null) {
					System.out.print("Nome: ");
					String newNome = sc.nextLine();

					System.out.println("Creditos mínimos: ");
					int newCreditosMinimos = sc.nextInt();
					sc.nextLine();

					System.out.println("Código do departamento: ");
					int newCodigoDept = sc.nextInt();

					Departamento deptNovo = departamentoDAO.buscarPorCodigo(newCodigoDept);
					if (deptNovo == null) {
						System.out.println("Departamento não encontrado.");
						break;
					}

					cursoExistente.setNome(newNome);
					cursoExistente.setCreditosMinimos(newCreditosMinimos);
					cursoExistente.setIdDepartamento(newCodigoDept);

					if (cursoDAO.atualizar(cursoExistente))
						System.out.println("Curso atualizado com sucesso!");
					else
						System.out.println("Erro ao atualizar curso.");
				} else {
					System.out.println("Curso não encontrado.");
				}

				break;

			case 3:
				System.out.print("Código do curso a remover: ");
				int codRe = sc.nextInt();
				sc.nextLine();

				Curso cursoRemover = CursoDAO.buscarPorCodigo(codRe);
				if (cursoRemover != null) {
					if (cursoDAO.remover(cursoRemover))
						System.out.println("Curso removido com sucesso!");
					else
						System.out.println("Erro ao remover curso.");
				} else {
					System.out.println("Curso não encontrado.");
				}
				break;

			case 4:
				List<Curso> lista = cursoDAO.listarTodos();
				System.out.println("----- Lista de Cursos -----");
				System.out.printf("%-10s | %-30s | %-15s | %-15s\n", "Código", "Nome", "Créd. Mínimos", "Cód. Dep.");
				System.out.println("--------------------------------------------------------------------------");
				for (Curso cursoLista : lista) {
					System.out.printf("%-10d | %-30s | %-15d | %-15d\n", cursoLista.getCodigo(), cursoLista.getNome(),
							cursoLista.getCreditosMinimos(), cursoLista.getIdDepartamento());
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