package br.com.fiap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.dao.DadosBiometricosDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.DadosBiometricos;

@WebServlet("/dados")
public class DadosBiometricosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DadosBiometricosDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getDadosBiometricosDAO();
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
		request.getRequestDispatcher("cadastro-dado.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<DadosBiometricos> lista = dao.listar();
		request.setAttribute("dados", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		DadosBiometricos dadosB = dao.buscarDadosPaciente(id);
		
		System.out.println(dadosB.getFrequenciaCardiaca());
		
		request.setAttribute("dado", dadosB);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-dado.jsp").forward(request, response);
	}
	

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DadosBiometricos> lista = dao.listar();
		request.setAttribute("dados", lista);
		request.getRequestDispatcher("lista-dado.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("codigo"));
			
			String tipoSangue = request.getParameter("tipo-sangue");
			String frequenciaCardiaca = request.getParameter("frequencia");
			int idPacienteAssociado = Integer.parseInt(request.getParameter("paciente"));

			DadosBiometricos dados = new DadosBiometricos(tipoSangue, frequenciaCardiaca, idPacienteAssociado);
			dados.setId(id);
			
			dao.cadastrar(dados);

			request.setAttribute("msg", "Dados biometricos cadastrados!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar dados biometricos");
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		abrirFormCadastro(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("codigo"));
			
			String tipoSangue = request.getParameter("tipo-sangue");
			String frequenciaCardiaca = request.getParameter("frequencia");
			int idPacienteAssociado = Integer.parseInt(request.getParameter("paciente"));

			DadosBiometricos dados = new DadosBiometricos(tipoSangue, frequenciaCardiaca, idPacienteAssociado);
			dados.setId(id);

			dao.atualizar(dados);

			request.setAttribute("msg", "Dados biometricos atualizados!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar dados biometricos");
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
			request.setAttribute("msg", "Dados biometricos removidos!");
		} 
		catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar dados biometricos");
		}
		
		listar(request, response);
	}

}
