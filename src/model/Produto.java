package model;

public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		//return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
		return String.format("Produto [id='%d', nome='%s', descricao='%s']", this.id, this.nome, this.descricao);
	}
}
