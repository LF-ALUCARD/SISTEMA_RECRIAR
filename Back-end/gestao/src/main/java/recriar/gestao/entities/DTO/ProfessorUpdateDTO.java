package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class ProfessorUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String documento;

	public ProfessorUpdateDTO() {
	}

	public ProfessorUpdateDTO(String nome, String email, String documento) {
		super();
		this.nome = nome;
		this.email = email;
		this.documento = documento;
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
