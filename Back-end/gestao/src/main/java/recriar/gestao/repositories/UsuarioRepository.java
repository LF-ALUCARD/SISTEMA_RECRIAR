package recriar.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import recriar.gestao.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email);
	Usuario findByEmail(String email);
}
