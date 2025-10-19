package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Usuario;
import recriar.gestao.entities.enums.Tipo;

public class UsuarioInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private Tipo tipo;

	public UsuarioInfoDTO() {
	}

	public UsuarioInfoDTO(Usuario obj) {
		this.id = obj.getId();
		this.email = obj.getEmail();
		this.senha = obj.getSenha_hash();
		this.tipo = obj.getTipo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
