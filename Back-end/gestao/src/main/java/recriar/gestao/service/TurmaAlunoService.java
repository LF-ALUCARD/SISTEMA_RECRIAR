package recriar.gestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.Turma;
import recriar.gestao.entities.TurmaAluno;
import recriar.gestao.entities.DTO.AlunoListDTO;
import recriar.gestao.entities.DTO.TurmaAlunoDTO;
import recriar.gestao.entities.PK.TurmaAlunoPK;
import recriar.gestao.repositories.AlunoRepository;
import recriar.gestao.repositories.TurmaAlunoRepository;
import recriar.gestao.repositories.TurmaRepository;

@Service
public class TurmaAlunoService {

	@Autowired
	private TurmaAlunoRepository repositor;
	
	@Autowired
	private TurmaRepository repositor_turma;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	/* ----------------------------------------------------------------------------------------------*/
	@Transactional
	public void associarAluno(TurmaAlunoDTO dto) {

		TurmaAlunoPK pk = new TurmaAlunoPK(dto.getTurma_id(), dto.getAluno_id());
		TurmaAluno entidade = new TurmaAluno(pk);
		repositor.save(entidade);
	}
	/* ----------------------------------------------------------------------------------------------*/
	
	public List<Turma> listarTurmasPorAluno(Long alunoId) {
	    List<TurmaAluno> associacoes = repositor.findByIdAlunoId(alunoId);
	    List<Long> turmaIds = associacoes.stream()
	        .map(tp -> tp.getId().getTurmaId())
	        .toList();
	    return repositor_turma.findAllById(turmaIds); // Este método existe no JpaRepository padrão
	}
	/* ----------------------------------------------------------------------------------------------*/

	public List<AlunoListDTO> listarAlunosPorTurma(Long turmaId) {
	    List<TurmaAluno> associacoes = repositor.findByIdTurmaId(turmaId);
	    List<Long> alunoIds = associacoes.stream()
	        .map(ta -> ta.getId().getAlunoId())
	        .toList();
	    
	    List<Aluno> entidade = alunoRepository.findAllById(alunoIds);
	    List<AlunoListDTO> lista = entidade.stream().map(x -> new AlunoListDTO(x)).toList();
	    return lista;
	}
	/* ----------------------------------------------------------------------------------------------*/
	@Transactional
	public void delete(Long id) {
		
		repositor.deleteByIdTurmaId(id);
		
	}
	/* ----------------------------------------------------------------------------------------------*/
	@Transactional
	public void deleteAluno(Long id) {
		
		repositor.deleteByIdAlunoId(id);
		
	}
	/* ----------------------------------------------------------------------------------------------*/

}
