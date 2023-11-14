package br.com.fiap.model;

public class TecnologiaSaude {
	
	private int id;
	private String nome;
	private String descricao;
	private double mediaAvaliacoes;
	
	public TecnologiaSaude(String nome, String descricao, double mediaAvaliacoes) {
		this.nome = nome;
		this.descricao = descricao;
		this.mediaAvaliacoes = mediaAvaliacoes;
	}
	
	public TecnologiaSaude() {
		
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getDescricao() { return descricao; }

	public void setDescricao(String descricao) { this.descricao = descricao; }

	public double getMediaAvaliacoes() { return mediaAvaliacoes; }

	public void setMediaAvaliacoes(double mediaAvaliacoes) { this.mediaAvaliacoes = mediaAvaliacoes; }
	
	
}
