package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.TecnologiaSaude;

public interface TecnologiaSaudeDAO {
	
	void cadastrar(TecnologiaSaude tecnologia) throws DBException;
	void remover(int codigo) throws DBException;
	TecnologiaSaude buscar(int id);
	List<TecnologiaSaude> listar();
	void atualizar(TecnologiaSaude tecnologia) throws DBException;

}
