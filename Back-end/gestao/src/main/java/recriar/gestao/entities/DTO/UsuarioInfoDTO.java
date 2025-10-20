package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Usuario;
import recriar.gestao.entities.enums.Tipo;

public class UsuarioInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private Tipo tipo;

	public UsuarioInfoDTO() {
	}

	public UsuarioInfoDTO(Usuario obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
