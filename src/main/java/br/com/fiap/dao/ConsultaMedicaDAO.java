package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.ConsultaMedica;

public interface ConsultaMedicaDAO {

	void cadastrar(ConsultaMedica consulta) throws DBException;
	void remover(int codigo) throws DBException;
	ConsultaMedica buscar(int id);
	List<ConsultaMedica> listar();
}
