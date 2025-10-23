package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class TurmaRegisterDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	
	public TurmaRegisterDTO () {}

	public TurmaRegisterDTO(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
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
	
	
}
