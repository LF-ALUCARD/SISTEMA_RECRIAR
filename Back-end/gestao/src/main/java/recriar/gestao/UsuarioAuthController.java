package recriar.gestao;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import recriar.gestao.entities.DTO.AuthResponseDTO;
import recriar.gestao.entities.DTO.UsuarioRegisterDTO;
import recriar.gestao.service.UsuarioService;

@RestController
@RequestMapping(value = "api/auth")
public class UsuarioAuthController {

	@Autowired
	private UsuarioService servico;

	@PostMapping(value = "register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody UsuarioRegisterDTO obj) {

		AuthResponseDTO resposta = servico.register(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(resposta.getUsuario().getId()).toUri();

		return ResponseEntity.created(uri).body(resposta);
	}
}
