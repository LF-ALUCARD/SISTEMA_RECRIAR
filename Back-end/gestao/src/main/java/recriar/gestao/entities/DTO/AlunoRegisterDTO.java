package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class AlunoRegisterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Dados do aluno
	private String matricula;
	private String nome;
	private String sobrenome;
	private String documento;
	private Integer idade;
	private Integer sexo;

	// Dados do responsável
	private String responsavelNome;
	private String responsavelDocumento;
	private String responsavelEmail;
	private String responsavelTelefone;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;

	public AlunoRegisterDTO() {
		super();
	}

	public AlunoRegisterDTO(String matricula, String nome, String sobrenome, String documento, Integer idade,
			Integer sexo, String responsavelNome, String responsavelDocumento, String responsavelEmail,
			String responsavelTelefone, String rua, String numero, String bairro, String cidade,
			String enderecoCompleto) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.idade = idade;
		this.sexo = sexo;
		this.responsavelNome = responsavelNome;
		this.responsavelDocumento = responsavelDocumento;
		this.responsavelEmail = responsavelEmail;
		this.responsavelTelefone = responsavelTelefone;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
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

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getResponsavelNome() {
		return responsavelNome;
	}

	public void setResponsavelNome(String responsavelNome) {
		this.responsavelNome = responsavelNome;
	}

	public String getResponsavelDocumento() {
		return responsavelDocumento;
	}

	public void setResponsavelDocumento(String responsavelDocumento) {
		this.responsavelDocumento = responsavelDocumento;
	}

	public String getResponsavelEmail() {
		return responsavelEmail;
	}

	public void setResponsavelEmail(String responsavelEmail) {
		this.responsavelEmail = responsavelEmail;
	}

	public String getResponsavelTelefone() {
		return responsavelTelefone;
	}

	public void setResponsavelTelefone(String responsavelTelefone) {
		this.responsavelTelefone = responsavelTelefone;
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

}
