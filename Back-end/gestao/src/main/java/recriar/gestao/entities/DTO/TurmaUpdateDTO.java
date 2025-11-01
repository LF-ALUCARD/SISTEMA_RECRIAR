package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class TurmaUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String professor;
	private String descricao;

	public TurmaUpdateDTO() {
	}

	public TurmaUpdateDTO(String nome, String professor, String descricao) {
		super();
		this.nome = nome;
		this.professor = professor;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
