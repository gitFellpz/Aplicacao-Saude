package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.DadosBiometricosDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.DadosBiometricos;

public class OracleDadosBiometricosDAO implements DadosBiometricosDAO{

	private Connection conexao;
	
	@Override
	public void cadastrar(DadosBiometricos dados) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_GS_DADOS_BIOMETRICOS (TIPO_SANGUE, FREQUENCIA_CARDIACA, ID_PACIENTE_ASSOCIADO) VALUES (?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dados.getTipoSangue());
			stmt.setString(2, dados.getFrequenciaCardiaca());
			stmt.setDouble(3, dados.getIdPacienteAssociado());
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
	public void atualizar(DadosBiometricos dados) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_GS_DADOS_BIOMETRICOS SET TIPO_SANGUE = ?, FREQUENCIA_CARDIACA = ? WHERE ID_DADOS = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dados.getTipoSangue());
			stmt.setString(2, dados.getFrequenciaCardiaca());
			stmt.setInt(3, dados.getId());

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
			String sql = "DELETE FROM TB_GS_DADOS_BIOMETRICOS WHERE ID_DADOS = ?";
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
	public DadosBiometricos buscarDadosPaciente(int id) {
		DadosBiometricos dados = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_GS_DADOS_BIOMETRICOS WHERE ID_DADOS = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String tipoSangue = rs.getString("TIPO_SANGUE");
				String frequenciaCardiaca = rs.getString("FREQUENCIA_CARDIACA");
				int idPacienteAssociado = rs.getInt("ID_PACIENTE_ASSOCIADO");

				dados = new DadosBiometricos(tipoSangue, frequenciaCardiaca, idPacienteAssociado);
				dados.setId(id);
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
		return dados;
	}

	@Override
	public List<DadosBiometricos> listar() {
		List<DadosBiometricos> lista = new ArrayList<DadosBiometricos>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GS_DADOS_BIOMETRICOS");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int id = rs.getInt("ID_DADOS");
				
				String tipoSangue = rs.getString("TIPO_SANGUE");
				String frequenciaCardiaca = rs.getString("FREQUENCIA_CARDIACA");
				int idPacienteAssociado = rs.getInt("ID_PACIENTE_ASSOCIADO");

				DadosBiometricos dados = new DadosBiometricos(tipoSangue, frequenciaCardiaca, idPacienteAssociado);
				dados.setId(id);

			
				lista.add(dados);
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
