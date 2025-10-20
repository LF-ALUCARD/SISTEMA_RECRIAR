package recriar.gestao.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import recriar.gestao.entities.Professor;
import recriar.gestao.entities.DTO.ProfessorRegisterDTO;
import recriar.gestao.service.ProfessorService;

@RestController
@RequestMapping(value = "api/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService servico;
	
	@PostMapping(value = "register")
	public ResponseEntity<Professor> register(@RequestBody ProfessorRegisterDTO obj){
		
		Professor entidade = servico.register(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entidade.getId()).toUri();

		return ResponseEntity.created(uri).body(entidade);
	}
}
