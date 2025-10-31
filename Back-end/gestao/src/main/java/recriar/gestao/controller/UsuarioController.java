package recriar.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import recriar.gestao.entities.Usuario;
import recriar.gestao.entities.DTO.UsuarioInfoDTO;
import recriar.gestao.entities.DTO.UsuarioListDTO;
import recriar.gestao.entities.DTO.UsuarioPasswordDTO;
import recriar.gestao.entities.DTO.UsuarioProfileDTO;
import recriar.gestao.service.UsuarioService;

@RestController
@RequestMapping(value = "api/user")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	
	@GetMapping(value = "info/{id}")
	public ResponseEntity<UsuarioInfoDTO> accont_info(@PathVariable Long id){
		UsuarioInfoDTO entidade = service.profile(id);
		return ResponseEntity.ok().body(entidade);
	}
	
	@GetMapping("list")
	public ResponseEntity<List<UsuarioListDTO>> findAll(){
		
		List<UsuarioListDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value = "/profile/{id}")
	public ResponseEntity<UsuarioInfoDTO> updateProfile(@PathVariable Long id, @RequestBody UsuarioProfileDTO obj){
		
		Usuario entidade = service.updateProfile(id, obj);
		UsuarioInfoDTO info =  new UsuarioInfoDTO(entidade);
		
		return ResponseEntity.ok().body(info);
	}
	
	@PostMapping(value = "password/{id}")
	public ResponseEntity<UsuarioInfoDTO> updatePassword(@PathVariable Long id, @RequestBody UsuarioPasswordDTO obj){
		
		Usuario entidade = service.updatePassword(id, obj);
		UsuarioInfoDTO info = new UsuarioInfoDTO(entidade);
		
		return ResponseEntity.ok().body(info);				
	}
	
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<Void> apagarUsuario(@PathVariable Long id){
		service.apagarUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
