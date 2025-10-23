package recriar.gestao.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import recriar.gestao.entities.Turma;
import recriar.gestao.entities.DTO.TurmaProfessorDTO;
import recriar.gestao.service.TurmaProfessorService;

@RestController
@RequestMapping("api/turma-professor")
public class TurmaProfessorController {

	@Autowired
	private TurmaProfessorService service;

	@PostMapping("register")
	public ResponseEntity<Void> register(@RequestBody TurmaProfessorDTO obj) {
		service.associarProfessor(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{professorId}/{turmaId}")
				.buildAndExpand(obj.getProfessor_id(), obj.getTurma_id()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}/turmas")
	public ResponseEntity<List<Turma>> listarTurmas(@PathVariable Long id) {
	    List<Turma> turmas = service.listarTurmasPorProfessor(id);
	    return ResponseEntity.ok(turmas);
	}
}
