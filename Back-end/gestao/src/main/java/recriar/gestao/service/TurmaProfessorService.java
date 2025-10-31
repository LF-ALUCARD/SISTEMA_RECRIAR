package recriar.gestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import recriar.gestao.entities.Professor;
import recriar.gestao.entities.Turma;
import recriar.gestao.entities.TurmaProfessor;
import recriar.gestao.entities.DTO.TurmaProfessorDTO;
import recriar.gestao.entities.PK.TurmaProfessorPK;
import recriar.gestao.repositories.ProfessorRepository;
import recriar.gestao.repositories.TurmaProfessorRepository;
import recriar.gestao.repositories.TurmaRepository;
import recriar.gestao.service.exceptions.CredenciaisInvalidasException;

@Service
public class TurmaProfessorService {

	@Autowired
	private TurmaProfessorRepository repositor;
	

	@Autowired
	private TurmaRepository repositor_turma;
	
	@Autowired
	private ProfessorRepository repositor_professor;


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
	
	public Professor findByIdProfessor(Long id) {
	
		Professor entidade = repositor_professor.findById(id).orElseThrow(() -> new CredenciaisInvalidasException("Professor Não encontrado"));
		
		return entidade;
	}
	/* ----------------------------------------------------------------------------------------------*/
	public TurmaProfessor findByIdTurmaProfessor(Long id) {
		
		TurmaProfessor entidade = repositor.findByIdTurmaId(id);
		
		return entidade;
	}
	/* ----------------------------------------------------------------------------------------------*/
	public Turma findByIdTurma(Long id) {
		
		Turma entidade = repositor_turma.findById(id).orElseThrow(() -> new CredenciaisInvalidasException("Professor Não encontrado"));
		
		return entidade;
	}
	/* ----------------------------------------------------------------------------------------------*/
	@Transactional
	public void delete(Long id) {
		
		repositor.deleteByIdTurmaId(id);
		
	}
	
}
