package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.Usuario;

public class OracleUsuarioDAO implements UsuarioDAO {

	private Connection conexao;

	@Override
	public boolean validarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GS_USUARIO WHERE EMAIL = ? AND SENHA = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void cadastrar(Usuario Usuario) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_GS_USUARIO (NOME, EMAIL, SENHA, CPF, DATA_NASC, IDADE, ENDERECO, SEXO, ESTADO_CIVIL, TIPO_USER, ID_DADOS_ASSOCIADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, Usuario.getNome());
			stmt.setString(2, Usuario.getEmail());
			stmt.setString(3, Usuario.getSenha());
			stmt.setString(4, Usuario.getCpf());
			stmt.setString(5, Usuario.getDataNasc());
			stmt.setInt(6, Usuario.getIdade());
			stmt.setString(7, Usuario.getEndereco());
			stmt.setString(8, Usuario.getSexo());
			stmt.setString(9, Usuario.getEstadoCivil());
			stmt.setInt(10, Usuario.getTipoUsuario());
			stmt.setInt(11, Usuario.getIdDadosUsuario());
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

}