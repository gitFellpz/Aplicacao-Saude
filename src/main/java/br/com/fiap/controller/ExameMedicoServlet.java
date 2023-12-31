package br.com.fiap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.dao.ExameMedicoDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.ExameMedico;

@WebServlet("/exame")
public class ExameMedicoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ExameMedicoDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getExameMedicoDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {
		case "listar":
			listar(request, response);
			break;
			
		case "abrir-form-cadastro":
			abrirFormCadastro(request, response);
			break;
			
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		}
	}

	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastro-exame.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<ExameMedico> lista = dao.listar();
		request.setAttribute("exames", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		ExameMedico exame = dao.buscar(id);
		
		System.out.println(exame.getEspecialidade());
		
		request.setAttribute("exame", exame);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-exame.jsp").forward(request, response);
	}
	

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ExameMedico> lista = dao.listar();
		request.setAttribute("exames", lista);
		request.getRequestDispatcher("lista-exame.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("codigo"));
			
			String especialidade = request.getParameter("especialidade");
			String data = request.getParameter("data");
			String hora = request.getParameter("hora");
			String local = request.getParameter("local");
			int idMedicoAssociado = Integer.parseInt(request.getParameter("id-medico"));
			int idPacienteAssociado = Integer.parseInt(request.getParameter("id-paciente"));
			int idTecnologiaAssociado = Integer.parseInt(request.getParameter("id-tecnologia"));


			ExameMedico exame = 
					new ExameMedico(especialidade, data, hora, local, idMedicoAssociado, idPacienteAssociado, idTecnologiaAssociado);
			exame.setId(id);
			
			dao.cadastrar(exame);

			request.setAttribute("msg", "Exame cadastrado!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar exame");
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		abrirFormCadastro(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Exame removido!");
		} 
		catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar exame");
		}
		
		listar(request, response);
	}

}
