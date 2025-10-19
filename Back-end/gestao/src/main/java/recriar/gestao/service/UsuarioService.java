package recriar.gestao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import recriar.gestao.entities.Usuario;
import recriar.gestao.entities.DTO.AuthResponseDTO;
import recriar.gestao.entities.DTO.UsuarioInfoDTO;
import recriar.gestao.entities.DTO.UsuarioRegisterDTO;
import recriar.gestao.entities.enums.Tipo;
import recriar.gestao.repositories.UsuarioRepository;
import recriar.gestao.repositories.exceptions.CriacaoNegadaException;
import recriar.gestao.security.GerarToken;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositor;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private GerarToken gerarToken;
	
	public AuthResponseDTO register(UsuarioRegisterDTO user) {
		
		Usuario entidade = converter(user);
		
		if (repositor.existsByEmail(entidade.getEmail())) {
			throw new CriacaoNegadaException("E-mail já cadastrado!");
		}
		
		Usuario userSalvo = repositor.save(entidade);
		UsuarioInfoDTO dados = new UsuarioInfoDTO(userSalvo);
		String token = gerarToken.gerarToken(userSalvo);
		
		return new AuthResponseDTO(token, dados);
	}
	
	private Usuario converter(UsuarioRegisterDTO obj) {
		Usuario entidade = new Usuario();
		
		entidade.setEmail(obj.getEmail());
		entidade.setSenha_hash(passwordEncoder.encode(obj.getPassword()));
		entidade.setTipo(Tipo.valueof(obj.getTipo()));
		
		return entidade;		
	}
	
	
	
}
