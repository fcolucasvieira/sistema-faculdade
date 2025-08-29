package views;

import java.util.Scanner;

import acesso.dao.AcessoDAO;
import acesso.model.Acesso;
import aluno.dao.AlunoDAO;
import professor.dao.ProfessorDAO;
import utils.ScriptExecutor;

public class Login {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		AcessoDAO acessoDAO = new AcessoDAO();
		
		ScriptExecutor.executarScript("resources/sql/Criar_BD.sql");
		if(!acessoDAO.checarLogin("Admin", "Root"))
			ScriptExecutor.executarScript("Povoamento BD.sql");
		

		System.out.println("----- Sistema Acadêmico -----");
		System.out.println("Login: ");
		String login = sc.nextLine();
		System.out.println("Senha: ");
		String senha = sc.nextLine();

		if (acessoDAO.checarLogin(login, senha)) {
			Acesso acesso = acessoDAO.acessoPorLoginSenha(login, senha);
			
			if(acesso == null) {
				System.out.println("Erro interno: não foi possível recuperar os dados do usuário. ");
			}
			
			switch (acesso.getNivelAcesso()) {
			case DBA:
				System.out.println("\nLogin DBA realizado com sucesso!");
				MenuDBA.exibir();
				break;
			case comum:
				Integer idAluno = acesso.getMatriculaAluno();
				Integer idProf = acesso.getSiapeProfessor();

				AlunoDAO alunoDAO = new AlunoDAO();
				ProfessorDAO profDAO = new ProfessorDAO();
				
				boolean isAluno = idAluno != null && alunoDAO.existeAluno(idAluno);
				boolean isProf = idProf != null && profDAO.existeProfessor(idProf);

				if (isAluno && !isProf) {
					System.out.println("\nLogin Aluno realizado com sucesso!");
					new MenuAlunoLogin(idAluno);
					break;
				} else if (isProf && !isAluno) {
					System.out.println("\nLogin Professor realizado com sucesso!");
					new MenuProfessorLogin(idProf);
					break;
				} else {
					System.out.println("\nERRO: Tipo não identificado (comum).");
				} 
				break;

			case funcionario:
				System.out.println("\nLogin Funcionário realizado com sucesso!");
				MenuFuncionario.setIdDepartamento(acesso.getIdDeptFuncionario());
				MenuFuncionario.exibir();
				break;

			default:
				System.out.println("Tipo de acesso desconhecido.");
				break;
			}
		} else {
			System.out.println("\nLogin ou senha incorretos.");
		}
		
		sc.close();
	}
}
