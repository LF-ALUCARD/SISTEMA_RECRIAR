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
}
