package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Professor;

public class ProfessorInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String documento;

	public ProfessorInfoDTO() {
	}

	public ProfessorInfoDTO(Professor entidade) {
		this.nome = entidade.getNome();
		this.email = entidade.getEmail();
		this.documento = entidade.getDocumento();
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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
