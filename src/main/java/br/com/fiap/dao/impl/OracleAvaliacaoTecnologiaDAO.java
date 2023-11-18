package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.AvaliacaoTecnologiaDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.AvaliacaoTecnologia;

public class OracleAvaliacaoTecnologiaDAO implements AvaliacaoTecnologiaDAO{

	private Connection conexao;
	
	@Override
	public void cadastrar(AvaliacaoTecnologia avaliacao) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_GS_AVALIACAO (AVALIACAO, ID_PACIENTE_ASSOCIADO, ID_TECNOLOGIA_ASSOCIADA) VALUES (?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, avaliacao.getAvaliacao());
			stmt.setInt(2, avaliacao.getIdPacienteAssociado());
			stmt.setInt(3, avaliacao.getIdTecnologiaAssociada());
			stmt.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_GS_AVALIACAO WHERE ID_AVALIACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public AvaliacaoTecnologia buscar(int id) {
		AvaliacaoTecnologia avaliacao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_GS_AVALIACAO WHERE ID_AVALIACAO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int notaAvaliacao = rs.getInt("AVALIACAO");
				int idPacienteAssociado = rs.getInt("ID_PACIENTE_ASSOCIADO");
				int idTecnologiaAssociada = rs.getInt("ID_TECNOLOGIA_ASSOCIADA");

				avaliacao = new AvaliacaoTecnologia(notaAvaliacao, idPacienteAssociado, idTecnologiaAssociada);
				avaliacao.setId(id);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return avaliacao;
	}

	@Override
	public List<AvaliacaoTecnologia> listarAvaliacoes() {
		List<AvaliacaoTecnologia> lista = new ArrayList<AvaliacaoTecnologia>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GS_AVALIACAO");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int id = rs.getInt("ID_AVALIACAO");
				
				int notaAvaliacao = rs.getInt("AVALIACAO");
				int idPacienteAssociado = rs.getInt("ID_PACIENTE_ASSOCIADO");
				int idTecnologiaAssociada = rs.getInt("ID_TECNOLOGIA_ASSOCIADA");

				AvaliacaoTecnologia avaliacao = new AvaliacaoTecnologia(notaAvaliacao, idPacienteAssociado, idTecnologiaAssociada);
				avaliacao.setId(id);
			
				lista.add(avaliacao);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		
		} 
		finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}
	

}
