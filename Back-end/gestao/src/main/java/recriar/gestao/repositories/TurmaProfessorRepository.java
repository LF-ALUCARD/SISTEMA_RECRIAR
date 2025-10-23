package recriar.gestao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.TurmaProfessor;
import recriar.gestao.entities.PK.TurmaProfessorPK;

public interface TurmaProfessorRepository extends JpaRepository<TurmaProfessor, TurmaProfessorPK>{

	List<TurmaProfessor> findByIdProfessorId(Long professorId);
}
