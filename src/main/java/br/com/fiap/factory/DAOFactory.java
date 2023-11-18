package br.com.fiap.factory;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.OracleUsuarioDAO;
import br.com.fiap.dao.TecnologiaSaudeDAO;
import br.com.fiap.dao.impl.OracleTecnologiaSaudeDAO;
import br.com.fiap.dao.ExameMedicoDAO;
import br.com.fiap.dao.impl.OracleExameMedicoDAO;
import br.com.fiap.dao.DadosBiometricosDAO;
import br.com.fiap.dao.impl.OracleDadosBiometricosDAO;
import br.com.fiap.dao.ConsultaMedicaDAO;
import br.com.fiap.dao.impl.OracleConsultaMedicaDAO;
import br.com.fiap.dao.AvaliacaoTecnologiaDAO;
import br.com.fiap.dao.impl.OracleAvaliacaoTecnologiaDAO;

public class DAOFactory {
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static TecnologiaSaudeDAO getTecnologiaSaudeDAO() {
		return new OracleTecnologiaSaudeDAO();
	}
	
	public static ExameMedicoDAO getExameMedicoDAO() {
		return new OracleExameMedicoDAO();
	}
	
	public static DadosBiometricosDAO getDadosBiometricosDAO() {
		return new OracleDadosBiometricosDAO();
	}
	
	public static ConsultaMedicaDAO getConsultaMedicaDAO() {
		return new OracleConsultaMedicaDAO();
	}
	
	public static AvaliacaoTecnologiaDAO getAvaliacaoTecnologiaDAO() {
		return new OracleAvaliacaoTecnologiaDAO();
	}

}
