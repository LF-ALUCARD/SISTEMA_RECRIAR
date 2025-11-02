package recriar.gestao.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import recriar.gestao.entities.Professor;
import recriar.gestao.entities.DTO.ProfessorInfoDTO;
import recriar.gestao.entities.DTO.ProfessorListDTO;
import recriar.gestao.entities.DTO.ProfessorRegisterDTO;
import recriar.gestao.entities.DTO.ProfessorUpdateDTO;
import recriar.gestao.service.ProfessorService;

@RestController
@RequestMapping(value = "api/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService servico;

	@PostMapping(value = "register")
	public ResponseEntity<Professor> register(@RequestBody ProfessorRegisterDTO obj) {

		Professor entidade = servico.register(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId())
				.toUri();

		return ResponseEntity.created(uri).body(entidade);
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<Void> apagarProfessor(@PathVariable Long id) {

		servico.apagarProfessor(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("list")
	public ResponseEntity<List<ProfessorListDTO>> findAll() {

		List<ProfessorListDTO> list = servico.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("info/{id}")
	public ResponseEntity<ProfessorInfoDTO> infoProfessor(@PathVariable Long id) {

		ProfessorInfoDTO entidade = servico.infoProfessor(id);

		return ResponseEntity.ok().body(entidade);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<ProfessorInfoDTO> updateProfessor(@PathVariable Long id,
			@RequestBody ProfessorUpdateDTO entidade) {

		ProfessorInfoDTO professor = servico.updateProfessor(id, entidade);

		return ResponseEntity.ok().body(professor);
	}

}
