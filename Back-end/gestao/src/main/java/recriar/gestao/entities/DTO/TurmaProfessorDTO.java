package recriar.gestao.entities.DTO;

public class TurmaProfessorDTO {

	private Long professor_id;
	private Long turma_id;

	public TurmaProfessorDTO() {
	}

	public TurmaProfessorDTO(Long professor_id, Long turma_id) {
		super();
		this.professor_id = professor_id;
		this.turma_id = turma_id;
	}

	public Long getProfessor_id() {
		return professor_id;
	}

	public void setProfessor_id(Long professor_id) {
		this.professor_id = professor_id;
	}

	public Long getTurma_id() {
		return turma_id;
	}

	public void setTurma_id(Long turma_id) {
		this.turma_id = turma_id;
	}

}
