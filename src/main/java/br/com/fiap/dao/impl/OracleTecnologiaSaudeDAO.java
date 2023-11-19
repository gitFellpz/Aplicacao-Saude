package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.TecnologiaSaudeDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.TecnologiaSaude;

public class OracleTecnologiaSaudeDAO implements TecnologiaSaudeDAO {

	private Connection conexao;
	
	@Override
	public void cadastrar(TecnologiaSaude tecnologia) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_GS_TECNOLOGIA_SAUDE (NOME_TECNOLOGIA, DESCRICAO, MEDIA_AVALIACOES) VALUES (?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, tecnologia.getNome());
			stmt.setString(2, tecnologia.getDescricao());
			stmt.setDouble(3, tecnologia.getMediaAvaliacoes());
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
	public void atualizar(TecnologiaSaude tecnologia) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_GS_TECNOLOGIA_SAUDE SET NOME_TECNOLOGIA = ?, DESCRICAO = ? WHERE ID_TECNOLOGIA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, tecnologia.getNome());
			stmt.setString(2, tecnologia.getDescricao());
			stmt.setInt(3, tecnologia.getId());

			stmt.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	


	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_GS_TECNOLOGIA_SAUDE WHERE ID_TECNOLOGIA = ?";
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
	public TecnologiaSaude buscar(int id) {
		TecnologiaSaude tecnologia = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_GS_TECNOLOGIA_SAUDE WHERE ID_TECNOLOGIA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("NOME_CATEGORIA");
				String descricao = rs.getString("DESCRICAO");
				Double mediaAvaliacoes = rs.getDouble("MEDIA_AVALIACOES");

				tecnologia = new TecnologiaSaude(nome, descricao);
				tecnologia.setId(id);
				tecnologia.setMediaAvaliacoes(mediaAvaliacoes);
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
		return tecnologia;
	}

	@Override
	public List<TecnologiaSaude> listar() {
		List<TecnologiaSaude> lista = new ArrayList<TecnologiaSaude>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GS_TECNOLOGIA_SAUDE");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int id = rs.getInt("ID_TECNOLOGIA");
				
				String nome = rs.getString("NOME_CATEGORIA");
				String descricao = rs.getString("DESCRICAO");
				Double mediaAvaliacoes = rs.getDouble("MEDIA_AVALIACOES");

				TecnologiaSaude tecnologia = new TecnologiaSaude(nome, descricao);
				tecnologia.setId(id);
				tecnologia.setMediaAvaliacoes(mediaAvaliacoes);
			
				lista.add(tecnologia);
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
