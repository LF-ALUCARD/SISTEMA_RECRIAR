package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Responsavel;

public class ResponsavelListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String documento;

	public ResponsavelListDTO() {
	}

	public ResponsavelListDTO(Responsavel entidade) {
		super();
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.documento = entidade.getDocumento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
