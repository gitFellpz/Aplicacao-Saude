package br.com.fiap.model;

public class Usuario {
	
	private String nome;
	private int    idDadosUsuario;
	private String email;
	private String senha;
	private String cpf;
	private String dataNasc;
	private int    idade;
	private String endereco;
	private String   sexo;
	private String estadoCivil;
	private int tipoUsuario;
	
	public Usuario (String nome, int idDadosUsuario, String email, String senha, String cpf, String dataNasc, int idade, 
					String endereco, String sexo, String estadoCivil, int tipoUsuario) {
		
		this.nome = nome;
		this.idDadosUsuario = idDadosUsuario;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.idade = idade;
		this.endereco = endereco;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario (String email, String senha, int tipoUsuario) {
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	

	public Usuario () {

	}
	
	public String getNome() { return nome; }
	
	public void setNome(String nome) { this.nome = nome; }
	
	public int getIdDadosUsuario() { return idDadosUsuario; }

	public void setIdDadosUsuario(int idDadosUsuario) { this.idDadosUsuario = idDadosUsuario; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public String getSenha() { return senha; }
	
	public void setSenha(String senha) { this.senha = senha; }
	
	public String getCpf() { return cpf; }
	
	public void setCpf(String cpf) { this.cpf = cpf; }
	
	public String getDataNasc() { return dataNasc; }
	
	public void setDataNasc(String dataNasc) { this.dataNasc = dataNasc; }
	
	public int getIdade() { return idade; }
	
	public void setIdade(int idade) { this.idade = idade; }
	
	public String getEndereco() { return endereco; }
	
	public void setEndereco(String endereco) { this.endereco = endereco; }
	
	public String getSexo() { return sexo; }
	
	public void setSexo(String sexo) { this.sexo = sexo; }
	
	public String getEstadoCivil() { return estadoCivil; }
	
	public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
	
	public int getTipoUsuario() { return tipoUsuario; }

	public void setTipoUsuario(int tipoUsuario) { this.tipoUsuario = tipoUsuario; }
	

}
