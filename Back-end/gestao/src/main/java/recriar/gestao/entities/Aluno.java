package recriar.gestao.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import recriar.gestao.entities.enums.Sexo;

@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String matricula;
	private String nome;
	private String sobrenome;
	private String documento;
	private Integer idade;
	private Sexo sexo;
	private LocalDate data_matricula;

	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	@JsonBackReference
	private Responsavel responsavel;

	public Aluno() {
	}

	public Aluno(Long id, String matricula, String nome, String sobrenome, String documento, Integer idade, Sexo sexo,
			LocalDate data_matricula, Responsavel responsavel) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.idade = idade;
		this.sexo = sexo;
		this.data_matricula = data_matricula;
		this.responsavel = responsavel;
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getData_matricula() {
		return data_matricula;
	}

	public void setData_matricula(LocalDate data_matricula) {
		this.data_matricula = data_matricula;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

}
