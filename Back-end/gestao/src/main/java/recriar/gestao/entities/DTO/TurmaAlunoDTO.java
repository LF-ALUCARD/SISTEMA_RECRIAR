package recriar.gestao.entities.DTO;

import java.io.Serializable;

public class TurmaAlunoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long aluno_id;
	private Long turma_id;

	public TurmaAlunoDTO() {
	}

	public TurmaAlunoDTO(Long aluno_id, Long turma_id) {
		super();
		this.aluno_id = aluno_id;
		this.turma_id = turma_id;
	}

	public Long getAluno_id() {
		return aluno_id;
	}

	public void setAluno_id(Long aluno_id) {
		this.aluno_id = aluno_id;
	}

	public Long getTurma_id() {
		return turma_id;
	}

	public void setTurma_id(Long turma_id) {
		this.turma_id = turma_id;
	}

}
