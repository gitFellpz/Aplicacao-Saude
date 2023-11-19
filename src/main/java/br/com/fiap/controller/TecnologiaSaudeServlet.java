package br.com.fiap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.dao.TecnologiaSaudeDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.TecnologiaSaude;

@WebServlet("/tecnologia")
public class TecnologiaSaudeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TecnologiaSaudeDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getTecnologiaSaudeDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request, response);
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
		request.getRequestDispatcher("cadastro-tecnologia.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<TecnologiaSaude> lista = dao.listar();
		request.setAttribute("tecnologias", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		TecnologiaSaude tecnologia = dao.buscar(id);
		
		System.out.println(tecnologia.getNome());
		
		request.setAttribute("tecnologia", tecnologia);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-tecnologia.jsp").forward(request, response);
	}
	

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TecnologiaSaude> lista = dao.listar();
		request.setAttribute("tecnologias", lista);
		request.getRequestDispatcher("lista-tecnologia.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");

			TecnologiaSaude tecnologia = new TecnologiaSaude();
			tecnologia.setNome(nome);

			dao.cadastrar(tecnologia);

			request.setAttribute("msg", "Tecnologia cadastrado!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar tecnologia");
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		abrirFormCadastro(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			double mediaAvaliacoes = Double.parseDouble(request.getParameter("media-avaliacoes"));

			TecnologiaSaude tecnologia = new TecnologiaSaude(nome, descricao);
			tecnologia.setId(codigo);
			tecnologia.setMediaAvaliacoes(mediaAvaliacoes);

			dao.atualizar(tecnologia);

			request.setAttribute("msg", "Tecnologia atualizado!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar tecnologia");
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		listar(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Tecnologia removida!");
		} 
		catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar tecnologia");
		}
		
		listar(request, response);
	}

}
