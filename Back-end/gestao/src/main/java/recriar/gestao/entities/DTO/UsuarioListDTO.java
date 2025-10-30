package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Usuario;
import recriar.gestao.entities.enums.Tipo;

public class UsuarioListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Tipo tipo;

	public UsuarioListDTO() {
	}

	public UsuarioListDTO(Usuario entidade) {
		this.nome = entidade.getNome();
		this.tipo = entidade.getTipo();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
