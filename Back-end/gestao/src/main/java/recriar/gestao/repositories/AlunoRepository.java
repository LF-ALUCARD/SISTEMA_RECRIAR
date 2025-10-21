package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.Professor;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Professor findByNome(String email);

	boolean existsByNome(String email);
}