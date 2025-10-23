package recriar.gestao.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import recriar.gestao.entities.PK.TurmaProfessorPK;

@Entity
@Table(name = "turma_professor")
public class TurmaProfessor {

	@EmbeddedId
	private TurmaProfessorPK id;

	public TurmaProfessor() {
	}

	public TurmaProfessor(TurmaProfessorPK id) {
		super();
		this.id = id;
	}

	public TurmaProfessorPK getId() {
		return id;
	}

	public void setId(TurmaProfessorPK id) {
		this.id = id;
	}

}
