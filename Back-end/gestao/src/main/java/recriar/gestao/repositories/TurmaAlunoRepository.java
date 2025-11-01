package recriar.gestao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import recriar.gestao.entities.TurmaAluno;
import recriar.gestao.entities.PK.TurmaAlunoPK;

public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, TurmaAlunoPK>{

	List<TurmaAluno> findByIdAlunoId(Long idAluno);
	List<TurmaAluno> findByIdTurmaId(Long turmaId);
	
	void deleteByIdTurmaId(Long id);
	void deleteByIdAlunoId(Long id);
	
	@Modifying
	@Query("DELETE FROM TurmaAluno ta WHERE ta.id.turmaId = :turmaId AND ta.id.alunoId = :alunoId")
	void deleteByTurmaIdAndAlunoId(@Param("turmaId") Long turmaId, @Param("alunoId") Long alunoId);


}
