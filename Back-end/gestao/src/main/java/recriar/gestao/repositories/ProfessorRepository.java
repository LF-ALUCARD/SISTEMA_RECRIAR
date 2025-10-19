package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	Professor findByEmail(String email);
}
