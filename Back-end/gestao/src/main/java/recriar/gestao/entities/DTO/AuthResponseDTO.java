package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class AuthResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String token;
	private UsuarioInfoDTO usuario;

	public AuthResponseDTO() {
	}

	public AuthResponseDTO(String token, UsuarioInfoDTO usuario) {
		this.token = token;
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioInfoDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioInfoDTO usuario) {
		this.usuario = usuario;
	}

}
