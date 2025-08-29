package views;

import java.util.List;
import java.util.Scanner;

import disciplina.dao.DisciplinaDAO;
import disciplina.model.Disciplina;

public class MenuDisciplina {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);

		DisciplinaDAO discDAO = new DisciplinaDAO();
		int opcao;

		do {
			System.out.println("\n---> DISCIPLINA <---");
			System.out.println("1. Inserir disciplina.");
			System.out.println("2. Atualizar disciplina.");
			System.out.println("3. Remover disciplina.");
			System.out.println("4. Listar disciplinas.");
			System.out.println("0. Voltar");
			System.out.println("\nEscolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Código da disciplina: ");
				int codigo = sc.nextInt();
				sc.nextLine();

				System.out.println("Nome da disciplina: ");
				String nome = sc.nextLine();

				System.out.println("Ementa da disciplina: ");
				String ementa = sc.nextLine();

				System.out.println("Créditos da disciplina: ");
				int creditos = sc.nextInt();
				sc.nextLine();

				int tipo = 0;
				while (tipo != 1 && tipo != 2) {
					System.out.println("Tipo de disciplina\n1 - Obrigatória.\n2 - Optativa.\nEscolha: ");
					tipo = sc.nextInt();
					sc.nextLine();
				}

				String tipo_string = "";

				switch (tipo) {
				case 1:
					tipo_string = "obrigatória";
					break;
				case 2:
					tipo_string = "optativa";
					break;
				}

				System.out.println("Código do curso: ");
				int cod_curso = sc.nextInt();

				Disciplina disc = new Disciplina(codigo, nome, ementa, creditos, tipo_string, cod_curso);

				if (discDAO.inserir(disc))
					System.out.println("Disciplina inserida com sucesso!");
				else
					System.out.println("Erro ao inserir disciplina.");
				break;

			case 2:
				System.out.println("Código da disciplina a atualizar: ");
				int codigoAt = sc.nextInt();
				sc.nextLine();
				Disciplina discExistente = discDAO.buscarPorCodigo(codigoAt);
				if (discExistente != null) {
					System.out.println("Nome: ");
					String novoNome = sc.nextLine();
					discExistente.setNome(novoNome);

					System.out.println("Ementa: ");
					String novaEmenta = sc.nextLine();
					discExistente.setEmenta(novaEmenta);

					System.out.println("Créditos: ");
					int novoQuantCreditos = sc.nextInt();
					discExistente.setCreditos(novoQuantCreditos);

					int novoTipo = 0;

					while (novoTipo != 1 && novoTipo != 2) {
						System.out.println("Tipo de disciplina\n1 - Obrigatória.\n2 - Optativa.\nEscolha: ");
						novoTipo = sc.nextInt();
						sc.nextLine();
					}

					switch (novoTipo) {
					case 1:
						discExistente.setTipoDisciplina("obrigatória");
						break;
					case 2:
						discExistente.setTipoDisciplina("optativa");
						break;
					}

					System.out.println("Código do curso: ");
					int novoCodigoCurso = sc.nextInt();
					discExistente.setIdcurso(novoCodigoCurso);

					if (discDAO.atualizar(discExistente))
						System.out.println("Disciplina atualizada com sucesso!");
					else
						System.out.println("Erro ao atualizar disciplina.");
				} else {
					System.out.println("Disciplina não encontrada.");
				}
				break;

			case 3:
				System.out.println("Código da disciplina a remover: ");
				int codigoRe = sc.nextInt();
				sc.nextLine();
				Disciplina discRemover = discDAO.buscarPorCodigo(codigoRe);
				if (discRemover != null) {
					if (discDAO.remover(discRemover))
						System.out.println("Disciplina removida com sucesso!");
					else
						System.out.println("Erro ao remover disciplina.");
				} else {
					System.out.println("Disciplina não encontrada.");
				}
				break;

			case 4:
				List<Disciplina> lista = discDAO.listarTodos();
				System.out.println("----- Lista de disciplinas -----");
				System.out.printf("%-6s | %-30s | %-50s | %-8s | %-11s | %-20s\n", "Código", "Nome", "Ementa", "Créditos",
						"Tipo", "ID Curso");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");

				for (Disciplina d : lista) {
					System.out.printf("%-6d | %-30s | %-50s | %-8d | %-11s | %-20d\n", d.getCodigo(), d.getNome(),
							d.getEmenta(), d.getCreditos(), d.getTipoDisciplina(), d.getIdCurso());
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
