package br.com.fiap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.dao.AvaliacaoTecnologiaDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.AvaliacaoTecnologia;

@WebServlet("/tecnologia")
public class AvaliacaoTecnologiaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AvaliacaoTecnologiaDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getAvaliacaoTecnologiaDAO();
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
		request.getRequestDispatcher("cadastro-avaliacao.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<AvaliacaoTecnologia> lista = dao.listarAvaliacoes();
		request.setAttribute("avaliacoes", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		AvaliacaoTecnologia avaliacao = dao.buscar(id);
		
		System.out.println(avaliacao.getAvaliacao());
		
		request.setAttribute("avaliacao", avaliacao);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-avaliacao.jsp").forward(request, response);
	}
	

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AvaliacaoTecnologia> lista = dao.listarAvaliacoes();
		request.setAttribute("avaliacoes", lista);
		request.getRequestDispatcher("lista-avalicao.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			int nota = Integer.parseInt(request.getParameter("avaliacao"));
			int idPacienteAssociado = Integer.parseInt(request.getParameter("id-paciente"));
			int idTecnologiaAssociado = Integer.parseInt(request.getParameter("id-tecnologia"));

			AvaliacaoTecnologia avaliacao = new AvaliacaoTecnologia(nota, idPacienteAssociado, idTecnologiaAssociado);
			avaliacao.setId(codigo);
			
			dao.cadastrar(avaliacao);

			request.setAttribute("msg", "Avaliação cadastrada!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar avaliação");
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
			request.setAttribute("msg", "Avaliação removida!");
		} 
		catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar avaliação");
		}
		
		listar(request, response);
	}

}
