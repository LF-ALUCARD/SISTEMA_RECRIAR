package recriar.gestao.entities.PK;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TurmaAlunoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long turmaId;
	private Long alunoId;

	public TurmaAlunoPK() {
	}

	public TurmaAlunoPK(Long turmaId, Long alunoId) {
		this.turmaId = turmaId;
		this.alunoId = alunoId;
	}

	public Long getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Long turmaId) {
		this.turmaId = turmaId;
	}

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

}
