package recriar.gestao.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import recriar.gestao.entities.PK.TurmaAlunoPK;

@Entity
@Table(name = "turma_aluno")
public class TurmaAluno {

	@EmbeddedId
	private TurmaAlunoPK id;

	public TurmaAluno() {
	}

	public TurmaAluno(TurmaAlunoPK id) {
		super();
		this.id = id;
	}

	public TurmaAlunoPK getId() {
		return id;
	}

	public void setId(TurmaAlunoPK id) {
		this.id = id;
	}

}
