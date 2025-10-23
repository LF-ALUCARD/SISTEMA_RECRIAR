package recriar.gestao.entities.PK;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TurmaProfessorPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long professorId;

	private Long turmaId;

	public TurmaProfessorPK() {
	}

	public TurmaProfessorPK(Long professorId, Long turmaId) {
		super();
		this.professorId = professorId;
		this.turmaId = turmaId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Long getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Long turmaId) {
		this.turmaId = turmaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(professorId, turmaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurmaProfessorPK other = (TurmaProfessorPK) obj;
		return Objects.equals(professorId, other.professorId) && Objects.equals(turmaId, other.turmaId);
	}

}
