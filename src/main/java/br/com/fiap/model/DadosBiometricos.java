package br.com.fiap.model;

public class DadosBiometricos {
	private int id;
	private String tipoSangue;
	private String frequenciaCardiaca;
	private int idPacienteAssociado;
	
	public DadosBiometricos(String tipoSangue, String frequenciaCardiaca, int idPacienteAssociado) {
		this.tipoSangue = tipoSangue;
		this.frequenciaCardiaca = frequenciaCardiaca;
		this.idPacienteAssociado = idPacienteAssociado;
	}
	
	public DadosBiometricos() {
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public String getFrequenciaCardiaca() {
		return frequenciaCardiaca;
	}

	public void setFrequenciaCardiaca(String frequenciaCardiaca) {
		this.frequenciaCardiaca = frequenciaCardiaca;
	}
	
	public int getIdPacienteAssociado() {
		return idPacienteAssociado;
	}

	public void setIdPacienteAssociado(int idPacienteAssociado) {
		this.idPacienteAssociado = idPacienteAssociado;
	}
	
}
