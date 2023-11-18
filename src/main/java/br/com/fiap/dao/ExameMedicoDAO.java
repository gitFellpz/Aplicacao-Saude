package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.ExameMedico;

public interface ExameMedicoDAO {
	
	void cadastrar(ExameMedico exame) throws DBException;
	void remover(int codigo) throws DBException;
	ExameMedico buscar(int id);
	List<ExameMedico> listar();
}
