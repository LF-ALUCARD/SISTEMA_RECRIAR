package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
	boolean existsByNome(String nome);
}