package recriar.gestao.entities.PK;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.Turma;

public class turma_aluno_PK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "turma_id")
	@JsonManagedReference
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	@JsonManagedReference
	private Aluno aluno;

	public turma_aluno_PK() {
	}

	public turma_aluno_PK(Turma turma, Aluno aluno) {
		super();
		this.turma = turma;
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
