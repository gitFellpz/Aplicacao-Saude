package br.com.fiap.model;

public class ExameMedico {
	
	private int id;
	private String especialidade;
	private String data;
	private String hora;
	private String local;
	private int idMedicoAssociado;
	private int idPacienteAssociado;
	private int idTecnologiaAssociada;
	
	public ExameMedico(String especialidade, String data, String hora, String local, 
			int idMedicoAssociado, int idPacienteAssociado, int idTecnologiaAssociada) {
		this.especialidade = especialidade;
		this.data = data;
		this.hora = hora;
		this.local = local;
		this.idMedicoAssociado = idMedicoAssociado;
		this.idPacienteAssociado = idPacienteAssociado;
		this.idTecnologiaAssociada = idTecnologiaAssociada;
	}
	
	public ExameMedico() {

	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getData() {
		return data;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getHora() {
		return hora;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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
	
	public int getIdTecnologiaAssociada() {
		return idTecnologiaAssociada;
	}

	public void setIdTecnologiaAssociada(int idTecnologiaAssociada) {
		this.idTecnologiaAssociada = idTecnologiaAssociada;
	}
	
}


