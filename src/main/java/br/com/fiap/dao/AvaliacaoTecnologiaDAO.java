package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.AvaliacaoTecnologia;

public interface AvaliacaoTecnologiaDAO {
	
	void cadastrar(AvaliacaoTecnologia avaliacao) throws DBException;
	void remover(int codigo) throws DBException;
	AvaliacaoTecnologia buscar(int id);
	List<AvaliacaoTecnologia> listarAvaliacoes();

}
