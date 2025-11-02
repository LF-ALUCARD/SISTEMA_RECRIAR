package recriar.gestao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import recriar.gestao.entities.TurmaProfessor;
import recriar.gestao.entities.PK.TurmaProfessorPK;

public interface TurmaProfessorRepository extends JpaRepository<TurmaProfessor, TurmaProfessorPK> {

	List<TurmaProfessor> findByIdProfessorId(Long professorId);

	@Query("SELECT tp FROM TurmaProfessor tp WHERE tp.id.turmaId = :turmaId")
	List<TurmaProfessor> findAllByTurmaId(@Param("turmaId") Long turmaId);

	TurmaProfessor findByIdTurmaId(Long turmaId);

	boolean existsByIdTurmaId(Long id);
	boolean existsByIdProfessorId(Long id);

	void deleteByIdTurmaId(Long id);
}
