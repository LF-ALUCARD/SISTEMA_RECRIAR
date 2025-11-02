package recriar.gestao.entities.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class AlunoUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String sobrenome;
	private String documento;
	private LocalDate data_nascimeto;

	private String responsavel_nome;
	private String responsavel_documento;
	private String email;
	private String telefone;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;

	public AlunoUpdateDTO() {
	}

	public AlunoUpdateDTO(Long id, String matricula, String nome, String sobrenome, String documento, Integer idade,
			String responsavel_nome, String responsavel_documento, String email, String telefone, String rua,
			String numero, String bairro, String cidade, String enderecoCompleto) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.responsavel_nome = responsavel_nome;
		this.responsavel_documento = responsavel_documento;
		this.email = email;
		this.telefone = telefone;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getResponsavel_nome() {
		return responsavel_nome;
	}

	public void setResponsavel_nome(String responsavel_nome) {
		this.responsavel_nome = responsavel_nome;
	}

	public String getResponsavel_documento() {
		return responsavel_documento;
	}

	public void setResponsavel_documento(String responsavel_documento) {
		this.responsavel_documento = responsavel_documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public LocalDate getData_nascimeto() {
		return data_nascimeto;
	}

	public void setData_nascimeto(LocalDate data_nascimeto) {
		this.data_nascimeto = data_nascimeto;
	}

}
