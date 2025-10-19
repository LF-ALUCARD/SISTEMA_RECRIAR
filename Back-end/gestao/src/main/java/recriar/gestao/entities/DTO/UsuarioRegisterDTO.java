package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class UsuarioRegisterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private Integer tipo;

	public UsuarioRegisterDTO() {
	}

	public UsuarioRegisterDTO(String email, String password, Integer tipo) {
		super();
		this.email = email;
		this.password = password;
		this.tipo = tipo;
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
