package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Professor;

public class ProfessorListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;

	public ProfessorListDTO() {
	}

	public ProfessorListDTO(Professor obj) {
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
