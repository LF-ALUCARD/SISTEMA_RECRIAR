package recriar.gestao.entities.DTO;

public class ProfessorRegisterDTO {

	private String nome;
	private String email;
	private String password;
	private String documento;

	public ProfessorRegisterDTO() {
	}

	public ProfessorRegisterDTO(String nome, String email, String password, String documento) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
