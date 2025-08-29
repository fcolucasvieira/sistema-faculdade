package departamento.dao;

import java.util.List;

import curso.dao.CursoDAO;
import curso.model.Curso;
import departamento.model.Departamento;
import departamento.model.InformacoesDepartamento;

public class InformacoesDepartamentoDAO {
	private final CursoDAO cursoDAO = new CursoDAO();
	
	public InformacoesDepartamento buscarInformacoes(int cod_depart) {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			Departamento departamento = departamentoDAO.buscarPorCodigo(cod_depart);
			if (departamento == null) {
				System.err.println("InformacoesDepartamentoDAO -> Departamento não encontrado por código: " + cod_depart);
				return null;
			}
			
			List<Curso> cursos = cursoDAO.buscarPorDepartamento(cod_depart);

			return new InformacoesDepartamento(departamento.getCodigo(), departamento.getNome(), cursos);
			
		} catch (Exception e) {
			System.err.println("InformacoesDepartamentoDAO -> Erro ao buscar informações do aluno: " + e.getMessage());
			return null;
		}
	}
}
