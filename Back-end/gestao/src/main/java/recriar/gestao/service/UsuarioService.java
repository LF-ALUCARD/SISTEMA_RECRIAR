package recriar.gestao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import recriar.gestao.entities.Usuario;
import recriar.gestao.entities.DTO.AuthResponseDTO;
import recriar.gestao.entities.DTO.UsuarioInfoDTO;
import recriar.gestao.entities.DTO.UsuarioLoginDTO;
import recriar.gestao.entities.DTO.UsuarioRegisterDTO;
import recriar.gestao.entities.enums.Tipo;
import recriar.gestao.repositories.UsuarioRepository;
import recriar.gestao.security.GerarToken;
import recriar.gestao.service.exceptions.CredenciaisInvalidasException;
import recriar.gestao.service.exceptions.CriacaoNegadaException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositor;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private GerarToken gerarToken;
	
	/* ----------------------------------------------------------------------------------------------*/
	
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
	
	/* ----------------------------------------------------------------------------------------------*/
	
	public AuthResponseDTO login(UsuarioLoginDTO obj) {
		Usuario user = repositor.findByEmail(obj.getEmail());
		
		if(!passwordEncoder.matches(obj.getPassword(), user.getSenha_hash())) {
			throw new CredenciaisInvalidasException("E-mail ou Senha inválidos");
		}
		
		String token = gerarToken.gerarToken(user);
		UsuarioInfoDTO entidade = new UsuarioInfoDTO(user);
		
		return new AuthResponseDTO(token, entidade);
	}
	
	/* ----------------------------------------------------------------------------------------------*/
}
