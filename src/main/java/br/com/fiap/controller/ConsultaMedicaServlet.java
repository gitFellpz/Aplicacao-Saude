package br.com.fiap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.dao.ConsultaMedicaDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.ConsultaMedica;

@WebServlet("/consulta")
public class ConsultaMedicaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ConsultaMedicaDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getConsultaMedicaDAO();
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
		request.getRequestDispatcher("cadastro-consulta.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<ConsultaMedica> lista = dao.listar();
		request.setAttribute("consultas", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		ConsultaMedica consulta = dao.buscar(id);
		
		System.out.println(consulta.getEspecialidade());
		
		request.setAttribute("consulta", consulta);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-consulta.jsp").forward(request, response);
	}
	

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ConsultaMedica> lista = dao.listar();
		request.setAttribute("consultas", lista);
		request.getRequestDispatcher("lista-consulta.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("codigo"));
			
			String area = request.getParameter("area");
			String especialidade = request.getParameter("especialidade");
			String data = request.getParameter("data");
			String hora = request.getParameter("hora");
			String local = request.getParameter("local");
			double mediaAvaliacoes = Double.parseDouble(request.getParameter("media"));
			int idMedicoAssociado = Integer.parseInt(request.getParameter("id-medico"));
			int idPacienteAssociado = Integer.parseInt(request.getParameter("id-paciente"));

			ConsultaMedica consulta = 
					new ConsultaMedica(area, especialidade, data, hora, local, mediaAvaliacoes, idMedicoAssociado, idPacienteAssociado);
			consulta.setId(id);
			
			dao.cadastrar(consulta);

			request.setAttribute("msg", "Consulta cadastrada!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar consulta");
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
			request.setAttribute("msg", "Consulta removida!");
		} 
		catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar consulta");
		}
		
		listar(request, response);
	}

}
