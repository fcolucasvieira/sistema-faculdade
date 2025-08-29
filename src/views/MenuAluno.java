package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aluno.dao.AlunoDAO;
import aluno.dao.AlunoGraduacaoDAO;
import aluno.dao.AlunoPosGraduacaoDAO;
import aluno.dao.TelefoneAlunoDAO;
import aluno.model.Aluno;
import aluno.model.AlunoGraduacao;
import aluno.model.AlunoPosGraduacao;
import aluno.model.TelefoneAluno;
import professor.dao.ProfessorDAO;

public class MenuAluno {

	public static void exibir() {
		Scanner sc = new Scanner(System.in);
		AlunoDAO dao = new AlunoDAO();
		TelefoneAlunoDAO telefoneDao = new TelefoneAlunoDAO();
		AlunoGraduacaoDAO alunoGraduacaoDAO = new AlunoGraduacaoDAO();
		AlunoPosGraduacaoDAO alunoPosGraduacaoDAO = new AlunoPosGraduacaoDAO();
		int opcao;

		try {

			do {
				System.out.println("\n---> ALUNO <---");
				System.out.println("1. Inserir aluno");
				System.out.println("2. Listar alunos");
				System.out.println("3. Atualizar aluno");
				System.out.println("4. Remover aluno");
				System.out.println("0. Voltar");
				System.out.print("\nEscolha uma opção: ");
				opcao = sc.nextInt();
				sc.nextLine();

				switch (opcao) {
				case 1:
					System.out.print("Nome do aluno: ");
					String nome = sc.nextLine();

					System.out.print("Endereço: ");
					String endereco = sc.nextLine();

					System.out.print("Código do curso: ");
					int idCurso = sc.nextInt();
					sc.nextLine();

					int tipoCurso = 0;
					do {
						System.out.print("Tipo de curso\n1 - Graduação.\n2 - Pós-Graduação.\nEscolha: ");
						tipoCurso = sc.nextInt();
						sc.nextLine();
					} while (tipoCurso != 1 && tipoCurso != 2);

					String tipoCursoString = (tipoCurso == 1) ? "graduacao" : "pos";

					Aluno novoAluno = new Aluno(nome, endereco, idCurso, tipoCursoString);

					if (dao.inserir(novoAluno)) {
						int matricula = dao.buscarMaiorMatricula();
						if(tipoCurso == 1) {
							System.out.println("Digite o ano de ingresso: ");
							int anoIngresso = sc.nextInt();
							sc.nextLine();
							AlunoGraduacao alunoGraduacao = new AlunoGraduacao(matricula,anoIngresso);
							alunoGraduacaoDAO.inserir(alunoGraduacao);
						} else {
							int siapeOrientador = 0;
							do {
								System.out.println("Digite o siape do orientador: ");
								siapeOrientador = sc.nextInt();
								sc.nextLine();
							} while(ProfessorDAO.buscarPorSiape(siapeOrientador) == null);
							
							AlunoPosGraduacao alunoPosGraduacao = new AlunoPosGraduacao(matricula, siapeOrientador);
							alunoPosGraduacaoDAO.inserir(alunoPosGraduacao);
						}
						
						System.out.println("Aluno inserido com sucesso!");

						List<TelefoneAluno> telefones = new ArrayList<>();
						int adicionarOutro = 0;

						do {
							System.out.print("Número de telefone: ");
							String numero = sc.nextLine();

							System.out.print("Descrição (ex: celular, fixo, etc.): ");
							String descricao = sc.nextLine();

							telefones.add(new TelefoneAluno(numero, descricao, matricula));

							System.out.print("Adicionar outro número? 0 - Sim / 1 - Não: ");
							adicionarOutro = sc.nextInt();
							sc.nextLine();
						} while (adicionarOutro == 0);

						boolean adicaoNumeros = true;

						for (TelefoneAluno tel : telefones) {
							if (!telefoneDao.inserir(tel)) {
								System.out.println("Erro ao inserir telefone: " + tel.getNumero());
								adicaoNumeros = false;
							}
						}

						if (adicaoNumeros) {
							System.out.println("Todos os telefones foram inseridos com sucesso!");
						} else {
							System.out.println("Alguns telefones não puderam ser inseridos.");
						}

					} else {
						System.out.println("Erro ao inserir aluno.");
					}
					break;

				case 2:
					List<Aluno> alunos = dao.listarTodos();
					System.out.println("\n--- Lista de Alunos ---");
					for (Aluno aluno : alunos) {
						System.out.println("Matrícula: " + aluno.getMatricula() + " | Nome: " + aluno.getNome()
								+ " | Endereço: " + aluno.getEndereco() + " | ID Curso: " + aluno.getIdCurso()
								+ " | Tipo de aluno: " + aluno.getTipoAluno());
						for (TelefoneAluno telefone : telefoneDao.listarPorMatricula(aluno.getMatricula())) {
							System.out.println("Número de telefone: " + telefone.getNumero() + " | Descrição: "
									+ telefone.getDescricao());
						}
						System.out.println();
					}
					break;

				case 3:
					System.out.print("Matrícula do aluno a atualizar: ");
					int matriculaAluno = sc.nextInt();
					sc.nextLine();

					Aluno alu = dao.buscarPorMatricula(matriculaAluno);

					if (alu != null) {
						System.out.print("Novo nome: ");
						String novoNome = sc.nextLine();

						System.out.print("Novo endereço: ");
						String novoEndereco = sc.nextLine();

						System.out.print("Novo código de curso: ");
						int novoCurso = sc.nextInt();
						sc.nextLine();

						int tipoCursoAt = 0;
						do {
							System.out.print("Tipo de curso\n1 - Graduação\n2 - Pós-Graduação\nEscolha: ");
							tipoCursoAt = sc.nextInt();
							sc.nextLine();
						} while (tipoCursoAt != 1 && tipoCursoAt != 2);

						String tipoCursoStringAt = (tipoCursoAt == 1) ? "graduacao" : "pos";

						alu.setNome(novoNome);
						alu.setEndereco(novoEndereco);
						alu.setIdCurso(novoCurso);
						alu.setTipoAluno(tipoCursoStringAt);

						if (dao.atualizar(alu)) {
							System.out.println("Aluno atualizado com sucesso!");

							List<TelefoneAluno> listaTelefones = telefoneDao.listarPorMatricula(matriculaAluno);

							if (!listaTelefones.isEmpty()) {
								System.out.println("\nTelefones atuais:");
								for (int i = 0; i < listaTelefones.size(); i++) {
									TelefoneAluno telefone = listaTelefones.get(i);
									System.out.println((i + 1) + ". Número: " + telefone.getNumero() + " | Descrição: "
											+ telefone.getDescricao());
								}

								System.out.print("\nDeseja remover algum número? 0 - Sim / 1 - Não: ");
								int removerAlgum = sc.nextInt();
								sc.nextLine();

								while (removerAlgum == 0 && !listaTelefones.isEmpty()) {
									System.out.print("Digite o número do item que deseja remover (1-"
											+ listaTelefones.size() + "): ");
									int indx = sc.nextInt();
									sc.nextLine();

									if (indx >= 1 && indx <= listaTelefones.size()) {
										TelefoneAluno telRemover = listaTelefones.get(indx - 1);
										if (telefoneDao.remover(telRemover)) {
											System.out.println("Telefone removido com sucesso!");
											listaTelefones.remove(indx - 1);
										} else {
											System.out.println("Erro ao remover telefone.");
										}
									} else {
										System.out.println("Opção inválida.");
									}

									if (!listaTelefones.isEmpty()) {
										System.out.print("Deseja remover outro número? 0 - Sim / 1 - Não: ");
										removerAlgum = sc.nextInt();
										sc.nextLine();
									} else {
										System.out.println("Nenhum telefone restante.");
										break;
									}
								}
							}

							System.out.print("Deseja adicionar novos números? 0 - Sim / 1 - Não: ");
							int adicionarNovos = sc.nextInt();
							sc.nextLine();

							if (adicionarNovos == 0) {
								int adicionarOutro;
								do {
									System.out.print("Número de telefone: ");
									String numero = sc.nextLine();

									System.out.print("Descrição: ");
									String descricao = sc.nextLine();

									TelefoneAluno novoTel = new TelefoneAluno(numero, descricao, matriculaAluno);
									if (telefoneDao.inserir(novoTel)) {
										System.out.println("Telefone adicionado com sucesso!");
									} else {
										System.out.println("Erro ao adicionar telefone.");
									}

									System.out.print("Adicionar outro número? 0 - Sim / 1 - Não: ");
									adicionarOutro = sc.nextInt();
									sc.nextLine();
								} while (adicionarOutro == 0);
							}
						} else {
							System.out.println("Erro ao atualizar aluno.");
						}
					} else {
						System.out.println("Aluno não encontrado.");
					}
					break;

				case 4:
					System.out.print("Matrícula do aluno a remover: ");
					int matricula_aluno = sc.nextInt();
					sc.nextLine();

					Aluno aluRemovido = dao.buscarPorMatricula(matricula_aluno);

					if (aluRemovido != null) {
						List<TelefoneAluno> telefonesRe = telefoneDao.listarPorMatricula(matricula_aluno);

						if (!telefonesRe.isEmpty()) {
							for (TelefoneAluno telefone : telefonesRe) {
								if (telefoneDao.remover(telefone)) {
									System.out.println("Telefone " + telefone.getNumero() + " removido com sucesso!");
								} else {
									System.out.println("Erro ao remover telefone: " + telefone.getNumero());
								}
							}
							System.out.println("Todos os telefones associados foram removidos.");
						} else {
							System.out.println("Nenhum telefone encontrado para este aluno.");
						}

						if (dao.remover(aluRemovido)) {
							System.out.println("Aluno removido com sucesso!");
						} else {
							System.out.println("Erro ao remover aluno.");
						}
					} else {
						System.out.println("Aluno não encontrado.");
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