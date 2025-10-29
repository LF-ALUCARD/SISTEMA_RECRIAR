package recriar.gestao.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.DTO.AlunoListDTO;
import recriar.gestao.entities.DTO.AlunoRegisterDTO;
import recriar.gestao.entities.DTO.AlunoRegisterResponseDTO;
import recriar.gestao.service.AlunoService;

@RestController
@RequestMapping(value = "api/aluno")
public class AlunoController {

	@Autowired
	private AlunoService service;

	@PostMapping(value = "register")
	public ResponseEntity<Aluno> register(@RequestBody AlunoRegisterDTO obj) {

		Aluno entidade = service.criarAlunoComResponsavel(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId())
				.toUri();

		return ResponseEntity.created(uri).body(entidade);
	}
	
	@PostMapping(value = "register_responsavel")
	public ResponseEntity<Aluno> register(@RequestBody AlunoRegisterResponseDTO obj) {

		Aluno entidade = service.criarAlunoComResponsavel(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId())
				.toUri();

		return ResponseEntity.created(uri).body(entidade);
	}
	
	@GetMapping("list")
	public ResponseEntity<List<AlunoListDTO>> findAll(){
		
		List<AlunoListDTO> lista = service.findAll();
		
		return ResponseEntity.ok().body(lista);
	}

}
