package br.com.fiap.dao;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Usuario;

public interface UsuarioDAO {
	boolean validarUsuario(Usuario usuario);
	void cadastrar(Usuario Usuario) throws DBException;
}
