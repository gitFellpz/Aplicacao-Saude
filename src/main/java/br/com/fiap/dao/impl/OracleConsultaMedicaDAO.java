package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.ConsultaMedicaDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.ConsultaMedica;

public class OracleConsultaMedicaDAO implements ConsultaMedicaDAO{

private Connection conexao;
	
	@Override
	public void cadastrar(ConsultaMedica consulta) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_GS_CONSULTA (AREA_MEDICA, ESPECIALIDADE, DATA, HORA, LOCAL, AVALIACOES, ID_MEDICO_ASSOCIADO, ID_PACIENTE_ASSOCIADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, consulta.getAreaMedica());
			stmt.setString(2, consulta.getEspecialidade());
			stmt.setString(3, consulta.getDataConsulta());
			stmt.setString(4, consulta.getHoraConsulta());
			stmt.setString(5, consulta.getLocalDeAtendimento());
			stmt.setDouble(6, consulta.getMediaAvaliacoes());
			stmt.setInt(7, consulta.getIdMedicoAssociado());
			stmt.setInt(8, consulta.getIdPacienteAssociado());
			
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
			String sql = "DELETE FROM TB_GS_CONSULTA WHERE ID_CONSULTA = ?";
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
	public ConsultaMedica buscar(int id) {
		ConsultaMedica consulta = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_GS_CONSULTA WHERE ID_CONSULTA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String areaMedica = rs.getString("AREA_MEDICA");
				String especialidade = rs.getString("ESPECIALIDADE");
				String dataConsulta = rs.getString("DATA");
				String horaConsulta = rs.getString("HORA");
				String localDeAtendimento = rs.getString("LOCAL");
				double mediaAvaliacoes = rs.getDouble("AVALIACOES");
				int idMedicoAssociado = rs.getInt("ID_MEDICO_ASSOCIADO");
				int idPacienteAssociado = rs.getInt("ID_PACIENTE_ASSOCIADO");

				consulta = new ConsultaMedica(areaMedica, especialidade, dataConsulta, horaConsulta,
						localDeAtendimento, mediaAvaliacoes, idMedicoAssociado, idPacienteAssociado);
				
				consulta.setId(id);
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
		return consulta;
	}

	@Override
	public List<ConsultaMedica> listar() {
		List<ConsultaMedica> lista = new ArrayList<ConsultaMedica>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GS_CONSULTA");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int id = rs.getInt("ID_CONSULTA");
				
				String areaMedica = rs.getString("AREA_MEDICA");
				String especialidade = rs.getString("ESPECIALIDADE");
				String dataConsulta = rs.getString("DATA");
				String horaConsulta = rs.getString("HORA");
				String localDeAtendimento = rs.getString("LOCAL");
				double mediaAvaliacoes = rs.getDouble("AVALIACOES");
				int idMedicoAssociado = rs.getInt("ID_MEDICO_ASSOCIADO");
				int idPacienteAssociado = rs.getInt("ID_PACIENTE_ASSOCIADO");

				ConsultaMedica consulta = new ConsultaMedica(areaMedica, especialidade, dataConsulta, horaConsulta,
						localDeAtendimento, mediaAvaliacoes, idMedicoAssociado, idPacienteAssociado);
				
				consulta.setId(id);

			
				lista.add(consulta);
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
