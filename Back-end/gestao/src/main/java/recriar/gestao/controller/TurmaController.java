package recriar.gestao.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import recriar.gestao.entities.Turma;
import recriar.gestao.entities.DTO.TurmaInfoDTO;
import recriar.gestao.entities.DTO.TurmaRegisterDTO;
import recriar.gestao.service.TurmaAlunoService;
import recriar.gestao.service.TurmaProfessorService;
import recriar.gestao.service.TurmaService;

@RestController
@RequestMapping(value = "api/turma")
public class TurmaController {

	@Autowired
	private TurmaService service;
	
	@Autowired
	private TurmaAlunoService serviceAluno;
	
	@Autowired
	private TurmaProfessorService serviceProfessor;
	
	@PostMapping(value = "register")
	public ResponseEntity<Turma> register(@RequestBody TurmaRegisterDTO obj){
		
		Turma entidade = service.insertTurma(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId()).toUri();
		
		return ResponseEntity.created(uri).body(entidade);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteTurma(@PathVariable Long id){
		
		serviceAluno.delete(id);
		serviceProfessor.delete(id);		
		service.apagarTurma(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("list")
	public ResponseEntity<List<Turma>> findAll(){
		
		List<Turma> lista = service.findAll();
		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("{id}/info")
	public ResponseEntity<TurmaInfoDTO> turmaInfo(@PathVariable Long id){
		
		TurmaInfoDTO entidade = service.findByTurma(id);
		
		return ResponseEntity.ok().body(entidade);
	}
}
