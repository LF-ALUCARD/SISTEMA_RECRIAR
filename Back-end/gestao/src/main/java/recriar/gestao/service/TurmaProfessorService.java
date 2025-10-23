package recriar.gestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import recriar.gestao.entities.Turma;
import recriar.gestao.entities.TurmaProfessor;
import recriar.gestao.entities.DTO.TurmaProfessorDTO;
import recriar.gestao.entities.PK.TurmaProfessorPK;
import recriar.gestao.repositories.TurmaProfessorRepository;
import recriar.gestao.repositories.TurmaRepository;

@Service
public class TurmaProfessorService {

	@Autowired
	private TurmaProfessorRepository repositor;
	

	@Autowired
	private TurmaRepository repositor_turma;


	@Transactional
	public void associarProfessor(TurmaProfessorDTO dto) {

		TurmaProfessorPK pk = new TurmaProfessorPK(dto.getProfessor_id(), dto.getTurma_id());
		TurmaProfessor entidade = new TurmaProfessor(pk);
		repositor.save(entidade);
	}

	/* ----------------------------------------------------------------------------------------------*/
	
	public List<Turma> listarTurmasPorProfessor(Long professorId) {
	    List<TurmaProfessor> associacoes = repositor.findByIdProfessorId(professorId);
	    List<Long> turmaIds = associacoes.stream()
	        .map(tp -> tp.getId().getTurmaId())
	        .toList();
	    return repositor_turma.findAllById(turmaIds); // Este método existe no JpaRepository padrão
	}
	
	/* ----------------------------------------------------------------------------------------------*/
}
