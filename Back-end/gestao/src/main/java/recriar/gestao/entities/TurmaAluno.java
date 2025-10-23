package recriar.gestao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import recriar.gestao.entities.PK.turma_aluno_PK;

@Entity
@Table(name = "turma_aluno")
public class TurmaAluno {

	@EmbeddedId
	@JsonBackReference
	private turma_aluno_PK id = new turma_aluno_PK();

	public TurmaAluno() {
	}

	public TurmaAluno(turma_aluno_PK id) {
		super();
		this.id = id;
	}

	public turma_aluno_PK getId() {
		return id;
	}

	public void setId(turma_aluno_PK id) {
		this.id = id;
	}

}
