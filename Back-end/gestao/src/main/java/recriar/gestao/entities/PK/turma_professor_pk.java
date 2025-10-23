package recriar.gestao.entities.PK;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import recriar.gestao.entities.Professor;
import recriar.gestao.entities.Turma;

@Embeddable
public class turma_professor_pk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "professor_id")
	@JsonBackReference
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "turma_id")
	@JsonBackReference
	private Turma turma;

	public turma_professor_pk() {
	}

	public turma_professor_pk(Professor professor, Turma turma) {
		super();
		this.professor = professor;
		this.turma = turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
