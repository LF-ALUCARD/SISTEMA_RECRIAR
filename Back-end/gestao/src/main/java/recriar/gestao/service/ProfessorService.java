package recriar.gestao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import recriar.gestao.entities.Professor;
import recriar.gestao.entities.DTO.ProfessorRegisterDTO;
import recriar.gestao.repositories.ProfessorRepository;
import recriar.gestao.service.exceptions.BancoDeDadosExceptions;
import recriar.gestao.service.exceptions.CriacaoNegadaException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository repositor;
	
	@Autowired
	private UsuarioService servico;
	
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
	
	public void apagarProfessor(Long id) {
		try {
			Professor entidade = repositor.getReferenceById(id);
			repositor.deleteById(id);
			servico.apagarUsuario(entidade.getEmail());
		}
		catch (DataIntegrityViolationException e) {
			throw new BancoDeDadosExceptions(e.getMessage());
		}
	}
	/*-------------------------------------------------------------------------*/
}
