package br.com.fiap.model;

public class AvaliacaoTecnologia {
	
	private int id;
	private int avaliacao;
	private int idPacienteAssociado;
	private int idTecnologiaAssociada;
	
	
	public AvaliacaoTecnologia(int avaliacao, int idPacienteAssociado, int idTecnologiaAssociada) {
		this.avaliacao = avaliacao;
		this.idPacienteAssociado = idPacienteAssociado;
		this.idTecnologiaAssociada = idTecnologiaAssociada;
	}
	
	public AvaliacaoTecnologia() {
		
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getIdPacienteAssociado() {
		return idPacienteAssociado;
	}

	public void setIdPacienteAssociado(int idPacienteAssociado) {
		this.idPacienteAssociado = idPacienteAssociado;
	}

	public int getIdTecnologiaAssociada() {
		return idTecnologiaAssociada;
	}

	public void setIdTecnologiaAssociada(int idTecnologiaAssociada) {
		this.idTecnologiaAssociada = idTecnologiaAssociada;
	}
	
	

}
