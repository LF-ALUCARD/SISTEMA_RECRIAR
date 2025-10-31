package recriar.gestao.entities.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TurmaInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String descricao;
	private String professor;

	private List<AlunoListDTO> alunos = new ArrayList<>();

	public TurmaInfoDTO() {
	}

	public TurmaInfoDTO(String nome, String descricao, String professor) {
		this.nome = nome;
		this.descricao = descricao;
		this.professor = professor;
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

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public List<AlunoListDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoListDTO> alunos) {
		this.alunos = alunos;
	}

}
