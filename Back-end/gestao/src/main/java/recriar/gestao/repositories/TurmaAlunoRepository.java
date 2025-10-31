package recriar.gestao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.TurmaAluno;
import recriar.gestao.entities.PK.TurmaAlunoPK;

public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, TurmaAlunoPK>{

	List<TurmaAluno> findByIdAlunoId(Long idAluno);
	List<TurmaAluno> findByIdTurmaId(Long turmaId);

}
