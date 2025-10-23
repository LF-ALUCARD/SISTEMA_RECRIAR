package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
	
}