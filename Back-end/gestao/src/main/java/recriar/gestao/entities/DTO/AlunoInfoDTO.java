package recriar.gestao.entities.DTO;

import java.io.Serializable;

import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.enums.Sexo;

public class AlunoInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String matricula;
	private String nome;
	private String sobrenome;
	private String documento;
	private Integer idade;
	private Sexo sexo;

	private Long responsavel_id;
	private String responsavel_nome;
	private String responsavel_documento;
	private String email;
	private String telefone;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String enderecoCompleto;

	public AlunoInfoDTO() {
	}

	public AlunoInfoDTO(Aluno entidade) {
		this.id = entidade.getId();
		this.matricula = entidade.getMatricula();
		this.nome = entidade.getNome();
		this.sobrenome = entidade.getSobrenome();
		this.documento = entidade.getDocumento();
		this.idade = entidade.getIdade();
		this.sexo = entidade.getSexo();

		this.responsavel_id = entidade.getResponsavel().getId();
		this.responsavel_nome = entidade.getResponsavel().getNome();
		this.responsavel_documento = entidade.getResponsavel().getDocumento();
		this.email = entidade.getResponsavel().getEmail();
		this.telefone = entidade.getResponsavel().getTelefone();
		this.rua = entidade.getResponsavel().getRua();
		this.numero = entidade.getResponsavel().getNumero();
		this.bairro = entidade.getResponsavel().getBairro();
		this.cidade = entidade.getResponsavel().getCidade();
		this.enderecoCompleto = rua + " " + numero + ", " + bairro + ", " + cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Long getResponsavel_id() {
		return responsavel_id;
	}

	public void setResponsavel_id(Long responsavel_id) {
		this.responsavel_id = responsavel_id;
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

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
