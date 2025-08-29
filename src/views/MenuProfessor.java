package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import professor.dao.ProfessorDAO;
import professor.model.Professor;

public class MenuProfessor {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		ProfessorDAO professorDAO = new ProfessorDAO();
		int opcao;

		try {
			do {
				System.out.println("\n---> PROFESSOR <---");
				System.out.println("1. Inserir professor.");
				System.out.println("2. Atualizar professor.");
				System.out.println("3. Remover professor.");
				System.out.println("4. Listar professores.");
				System.out.println("0. Voltar");
				System.out.print("\nEscolha uma opção: ");
				opcao = sc.nextInt();
				sc.nextLine();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String data_nascimento_string;
				java.util.Date data_nascimento;
				String data_ingresso_string;
				java.util.Date data_ingresso;

				switch (opcao) {
				case 1:
					System.out.println("Siape do professor: ");
					int siape = sc.nextInt();
					sc.nextLine();
					System.out.println("Nome do professor: ");
					String nome = sc.nextLine();

					System.out.println("Data de nascimento do professor (dd/mm/yyyy): ");
					data_nascimento_string = sc.nextLine();
					try {
						data_nascimento = dateFormat.parse(data_nascimento_string);
					} catch (ParseException e) {
						System.out.println("Formato de data errado.");
						break;
					}
					System.out.println("Data de ingresso do professor (dd/mm/yyyy): ");
					data_ingresso_string = sc.nextLine();
					
					try {
						data_ingresso = dateFormat.parse(data_ingresso_string);
					} catch (ParseException e) {
						System.out.println("Formato de data errado.");
						break;
					}
					System.out.println("Codigo do departamento: ");
					int id_depart = sc.nextInt();
					sc.nextLine();
					Professor novo = new Professor(siape, nome, new java.sql.Date(data_nascimento.getTime()),
							new java.sql.Date(data_ingresso.getTime()), id_depart);
					if (professorDAO.inserir(novo))
						System.out.println("Professor inserido com sucesso!");
					else
						System.out.println("Erro ao inserir professor.");
					break;

				case 2:
					System.out.print("Siape do professor a atualizar: ");
					int siapeAtualiza = sc.nextInt();
					sc.nextLine();
					Professor existente = ProfessorDAO.buscarPorSiape(siapeAtualiza);
					if (existente != null) {
						System.out.print("Novo nome: ");
						String novoNome = sc.nextLine();
						existente.setNome(novoNome);

						System.out.print("Nova data de nascimento do professor (dd/mm/yyyy): ");
						data_nascimento_string = sc.nextLine();
						try {
							data_nascimento = dateFormat.parse(data_nascimento_string);
						} catch (ParseException e) {
							System.out.println("Formato de data errado.");
							break;
						}
						existente.setDataNascimento(new java.sql.Date(data_nascimento.getTime()));

						System.out.print("Nova data de ingresso do professor (dd/mm/yyyy): ");
						data_ingresso_string = sc.nextLine();
						try {
							data_ingresso = dateFormat.parse(data_ingresso_string);
						} catch (ParseException e) {
							System.out.println("Formato de data errado.");
							break;
						}
						existente.setDataNascimento(new java.sql.Date(data_ingresso.getTime()));
						
						System.out.print("Novo id de departamento: ");
						int novoIdDepartamento = sc.nextInt();
						existente.setIdDepartamento(novoIdDepartamento);

						if (professorDAO.atualizar(existente))
							System.out.println("Professor atualizado com sucesso!");
						else
							System.out.println("Erro ao atualizar professor.");
					} else {
						System.out.println("Professor não encontrado.");
					}
					break;
				case 3:
					System.out.print("Siape do professor a remover: ");
					int siapeRemover = sc.nextInt();
					sc.nextLine();
					Professor sRemover = ProfessorDAO.buscarPorSiape(siapeRemover);
					if (sRemover != null) {
						if (professorDAO.remover(sRemover))
							System.out.println("Professor removido com sucesso!");
						else
							System.out.println("Erro ao remover professor.");
					} else {
						System.out.println("Professor não encontrado.");
					}
					break;

				case 4:
					List<Professor> professores = professorDAO.listarTodos();
					System.out.println("----- Lista de disciplinas -----");
					System.out.printf("%-10s | %-30s | %-10s | %-10s | %-10s\n", "Siape", "Nome", "Nascimento", "Ingresso",
							"Cód. Dept.");
					System.out.println("----------------------------------------------------------------------------------");
					for (Professor prof : professores) {
						System.out.printf("%-10d | %-30s | %-10s | %-10s | %-10d\n", prof.getSiape(), prof.getNome(),
								dateFormat.format(prof.getDataNascimento()), dateFormat.format(prof.getDataIngresso()),
								prof.getIdDepartamento());
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}