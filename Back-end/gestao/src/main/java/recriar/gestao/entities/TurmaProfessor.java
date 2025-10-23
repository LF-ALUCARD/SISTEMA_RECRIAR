package recriar.gestao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import recriar.gestao.entities.PK.turma_professor_pk;

@Entity
@Table(name = "turma_professor")
public class TurmaProfessor {

	@EmbeddedId
	@JsonBackReference
	private turma_professor_pk id;

	public TurmaProfessor() {
	}

	public turma_professor_pk getId() {
		return id;
	}

	public void setId(turma_professor_pk id) {
		this.id = id;
	}

}
