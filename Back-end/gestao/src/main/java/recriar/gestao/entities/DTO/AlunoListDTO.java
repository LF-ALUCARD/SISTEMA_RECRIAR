package recriar.gestao.entities.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.enums.Sexo;

public class AlunoListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String matricula;
	private String nomeCompleto;
	private Integer idade;
	private Sexo sexo;
	private LocalDate dataMatricula;
	private LocalDate dataNascimento;

	public AlunoListDTO() {
	}

	public AlunoListDTO(Aluno entidade) {
		id = entidade.getId();
		matricula = entidade.getMatricula();
		nomeCompleto = entidade.getNome() + " " + entidade.getSobrenome();
		idade = entidade.getIdade();
		sexo = entidade.getSexo();
		dataMatricula = entidade.getData_matricula();
		dataNascimento = entidade.getData_nascimento();
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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricul) {
		this.dataMatricula = dataMatricul;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
