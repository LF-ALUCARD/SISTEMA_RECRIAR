package recriar.gestao.entities.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.enums.Sexo;

public class AlunoListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private String nomeCompleto;
	private Integer idade;
	private Sexo sexo;
	private LocalDate dataMatricul;

	public AlunoListDTO() {
	}

	public AlunoListDTO(Aluno entidade) {
		matricula = entidade.getMatricula();
		nomeCompleto = entidade.getNome() + " " + entidade.getSobrenome();
		idade = entidade.getIdade();
		sexo = entidade.getSexo();
		dataMatricul = entidade.getData_matricula();
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

	public LocalDate getDataMatricul() {
		return dataMatricul;
	}

	public void setDataMatricul(LocalDate dataMatricul) {
		this.dataMatricul = dataMatricul;
	}

}
