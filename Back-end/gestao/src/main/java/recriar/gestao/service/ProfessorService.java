package recriar.gestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import recriar.gestao.entities.Professor;
import recriar.gestao.entities.DTO.ProfessorInfoDTO;
import recriar.gestao.entities.DTO.ProfessorListDTO;
import recriar.gestao.entities.DTO.ProfessorRegisterDTO;
import recriar.gestao.entities.DTO.ProfessorUpdateDTO;
import recriar.gestao.repositories.ProfessorRepository;
import recriar.gestao.repositories.TurmaProfessorRepository;
import recriar.gestao.service.exceptions.BloqueioDeleteException;
import recriar.gestao.service.exceptions.CredenciaisInvalidasException;
import recriar.gestao.service.exceptions.CriacaoNegadaException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository repositor;

	@Autowired
	private UsuarioService servico;

	@Autowired
	private TurmaProfessorRepository repositorProfessor;

	/*-------------------------------------------------------------------------*/
	public Professor register(ProfessorRegisterDTO obj) {
		Professor prof = converter(obj);

		if (repositor.existsByEmail(prof.getEmail())) {
			throw new CriacaoNegadaException("E-mail já cadastrado!");
		}

		if (repositor.existsByDocumento(prof.getDocumento())) {
			throw new CriacaoNegadaException("Documento já cadastrado!");
		}

		prof = repositor.save(prof);
		servico.register(obj);

		return prof;
	}

	private Professor converter(ProfessorRegisterDTO obj) {
		Professor entidade = new Professor();

		entidade.setNome(obj.getNome());
		entidade.setEmail(obj.getEmail());
		entidade.setDocumento(obj.getDocumento());

		return entidade;
	}
	/*-------------------------------------------------------------------------*/

	@Transactional
	public void apagarProfessor(Long id) {

		if (repositorProfessor.existsByIdProfessorId(id)) {
			throw new BloqueioDeleteException("Professor vinculado a uma Turma");
		}

		Professor entidade = repositor.getReferenceById(id);

		repositor.delete(entidade);
	}
	/*-------------------------------------------------------------------------*/

	public List<ProfessorListDTO> findAll() {

		List<Professor> lista = repositor.findAll();
		List<ProfessorListDTO> listagem = lista.stream().map(x -> new ProfessorListDTO(x)).toList();

		return listagem;
	}
	/*-------------------------------------------------------------------------*/
	
	public ProfessorInfoDTO infoProfessor(Long id) {
		
		Professor entidade = repositor.findById(id).orElseThrow(() -> new CredenciaisInvalidasException("Professor não encontrado"));
		ProfessorInfoDTO obj = new ProfessorInfoDTO(entidade);
		
		return obj;
	}
	/*-------------------------------------------------------------------------*/
	
	public ProfessorInfoDTO updateProfessor(Long id, ProfessorUpdateDTO entidade) {
		
		Professor professor = atualizarDados( id, entidade);
		ProfessorInfoDTO salvar = new ProfessorInfoDTO(professor);
		
		return salvar;
	}
	
	private Professor atualizarDados(Long id, ProfessorUpdateDTO entidade) {
		
		Professor professor = repositor.findById(id).orElseThrow(() -> new CredenciaisInvalidasException("Professor não encontrado"));
		
		professor.setNome(entidade.getNome());
		professor.setEmail(entidade.getEmail());
		professor.setDocumento(entidade.getDocumento());
		
		return repositor.save(professor);
	}
	/*-------------------------------------------------------------------------*/
}
