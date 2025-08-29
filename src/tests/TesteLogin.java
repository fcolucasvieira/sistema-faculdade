package tests;
import java.util.Scanner;

import acesso.dao.AcessoDAO;
import acesso.model.Acesso;
import departamento.dao.DepartamentoDAO;
import departamento.model.Departamento;
import utils.ScriptExecutor;

public class TesteLogin {
	public static void main(String[] args) {
		ScriptExecutor.executarScript("Criar_BD.sql");
		//ScriptExecutor.executarScript("Povoamento BD.sql");
		
		try {
			Scanner sc = new Scanner(System.in);
			AcessoDAO ad = new AcessoDAO();
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			
			System.out.println("Digite seu login:");
			String login = sc.nextLine();
			System.out.println("Digite sua senha:");
			String senha = sc.nextLine();
			
			if(ad.checarLogin(login, senha)) {
				System.out.println("Logado com sucesso!");
				Acesso a = ad.acessoPorLoginSenha(login, senha);
				System.out.println("Nível de acesso: " + a.getNivelAcesso());
				switch(a.getNivelAcesso()) {
					case DBA:
						break;
					case funcionario:
						Departamento d = departamentoDAO.buscarPorCodigo(a.getIdDeptFuncionario());
						System.out.println(d.toString());
						break;
					case comum:
						break;
				}
			}
			else
				System.out.println("Usuário ou senha incorretos");
				
			sc.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
