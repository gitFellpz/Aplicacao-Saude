package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.DadosBiometricos;

public interface DadosBiometricosDAO {

	void cadastrar(DadosBiometricos dados) throws DBException;
	void atualizar(DadosBiometricos dados) throws DBException;
	void remover(int codigo) throws DBException;
	DadosBiometricos buscarDadosPaciente(int idPaciente);
	List<DadosBiometricos> listar();
	
}
