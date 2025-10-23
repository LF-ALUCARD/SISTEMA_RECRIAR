package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.TurmaProfessor;
import recriar.gestao.entities.PK.turma_professor_pk;

public interface TurmaProfessorRepository extends JpaRepository<TurmaProfessor, turma_professor_pk>{
}
