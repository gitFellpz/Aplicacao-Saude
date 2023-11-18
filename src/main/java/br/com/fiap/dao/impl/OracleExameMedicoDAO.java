package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.ExameMedicoDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.ExameMedico;

public class OracleExameMedicoDAO implements ExameMedicoDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(ExameMedico exame) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_GS_EXAME_MEDICO (ESPECIALIDADE, DATA, HORA, LOCAL, ID_MEDICO_ASSOCIADO, ID_PACIENTE_ASSOCIADO, ID_TECNOLOGIA_ASSOCIADA) VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, exame.getEspecialidade());
			stmt.setString(2, exame.getData());
			stmt.setString(3, exame.getHora());
			stmt.setString(4, exame.getLocal());
			stmt.setInt(5, exame.getIdMedicoAssociado());
			stmt.setInt(6, exame.getIdPacienteAssociado());
			stmt.setInt(6, exame.getIdTecnologiaAssociada());
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
			String sql = "DELETE FROM TB_GS_EXAME_MEDICO WHERE ID_EXAME = ?";
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
	public ExameMedico buscar(int id) {
		ExameMedico exame = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_GS_EXAME_MEDICO WHERE ID_EXAME = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {				
				String especialidade = rs.getString("ESPECIALIDADE");
				String data = rs.getString("DATA");
				String hora = rs.getString("HORA");
				String local = rs.getString("LOCAL");
				int idMedicoAssociado = rs.getInt("ID_MEDICO_ASSOCIADO");
				int idPacienteAssociado = rs.getInt("idPacienteAssociado");
				int idTecnologiaAssociada = rs.getInt("ID_TECNOLOGIA_ASSOCIADA");

				exame = new ExameMedico(especialidade, data, hora, local, idMedicoAssociado, idPacienteAssociado, idTecnologiaAssociada);
				exame.setId(id);
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
		return exame;
	}
	
	@Override
	public List<ExameMedico> listar() {
		List<ExameMedico> lista = new ArrayList<ExameMedico>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GS_EXAME_MEDICO");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int id = rs.getInt("ID_EXAME");
				
				String especialidade = rs.getString("ESPECIALIDADE");
				String data = rs.getString("DATA");
				String hora = rs.getString("HORA");
				String local = rs.getString("LOCAL");
				int idMedicoAssociado = rs.getInt("ID_MEDICO_ASSOCIADO");
				int idPacienteAssociado = rs.getInt("idPacienteAssociado");
				int idTecnologiaAssociada = rs.getInt("ID_TECNOLOGIA_ASSOCIADA");

				ExameMedico exame = new ExameMedico(especialidade, data, hora, local, idMedicoAssociado, idPacienteAssociado, idTecnologiaAssociada);
				exame.setId(id);
				
				lista.add(exame);
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
