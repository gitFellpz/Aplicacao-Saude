package br.com.fiap.model;

public class ConsultaMedica {
	
	private int id;
	private String areaMedica;
	private String especialidade;
	private String dataConsulta;
	private String horaConsulta;
	private String localDeAtendimento;
	private double mediaAvaliacoes;
	private int idMedicoAssociado;
	private int idPacienteAssociado;
	
	public ConsultaMedica(String areaMedica, String especialidade, String dataConsulta, String horaConsulta,
			String localDeAtendimento, double mediaAvaliacoes, int idMedicoAssociado, int idPacienteAssociado) {

		this.areaMedica = areaMedica;
		this.especialidade = especialidade;
		this.dataConsulta = dataConsulta;
		this.horaConsulta = horaConsulta;
		this.localDeAtendimento = localDeAtendimento;
		this.mediaAvaliacoes = mediaAvaliacoes;
		this.idMedicoAssociado = idMedicoAssociado;
		this.idPacienteAssociado = idPacienteAssociado;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getAreaMedica() { return areaMedica; }

	public void setAreaMedica(String areaMedica) { this.areaMedica = areaMedica; }

	public String getEspecialidade() { return especialidade; }

	public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

	public String getDataConsulta() { return dataConsulta; }

	public void setDataConsulta(String dataConsulta) { this.dataConsulta = dataConsulta; }

	public String getHoraConsulta() { return horaConsulta; }

	public void setHoraConsulta(String horaConsulta) { this.horaConsulta = horaConsulta; }

	public String getLocalDeAtendimento() { return localDeAtendimento; }

	public void setLocalDeAtendimento(String localDeAtendimento) { this.localDeAtendimento = localDeAtendimento; }

	public double getMediaAvaliacoes() {
		return mediaAvaliacoes;
	}

	public void setMediaAvaliacoes(double mediaAvaliacoes) {
		this.mediaAvaliacoes = mediaAvaliacoes;
	}

	public int getIdMedicoAssociado() {
		return idMedicoAssociado;
	}

	public void setIdMedicoAssociado(int idMedicoAssociado) {
		this.idMedicoAssociado = idMedicoAssociado;
	}

	public int getIdPacienteAssociado() {
		return idPacienteAssociado;
	}

	public void setIdPacienteAssociado(int idPacienteAssociado) {
		this.idPacienteAssociado = idPacienteAssociado;
	}
	

}
