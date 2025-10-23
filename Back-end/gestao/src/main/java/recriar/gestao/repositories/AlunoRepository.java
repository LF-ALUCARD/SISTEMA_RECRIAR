package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Aluno findByNome(String nome);

	boolean existsByNome(String email);
}