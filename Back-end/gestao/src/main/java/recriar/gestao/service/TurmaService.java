package recriar.gestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import recriar.gestao.entities.Turma;
import recriar.gestao.entities.DTO.TurmaRegisterDTO;
import recriar.gestao.repositories.TurmaRepository;
import recriar.gestao.service.exceptions.BancoDeDadosExceptions;
import recriar.gestao.service.exceptions.CriacaoNegadaException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repositor;
	
	/* ----------------------------------------------------------------------------------------------*/
	
	public Turma insertTurma(TurmaRegisterDTO obj) {
		
		if (repositor.existsByNome(obj.getNome())) {
			throw new CriacaoNegadaException("Turma existente");
		}
		
		Turma entidade = converter(obj);
		
		return repositor.save(entidade);
	}
	
	private Turma converter(TurmaRegisterDTO obj) {
		
		Turma entidade = new Turma();
		
		entidade.setNome(obj.getNome());
		entidade.setDescricao(obj.getDescricao());
		
		return entidade;
	}
	/* ----------------------------------------------------------------------------------------------*/
	
	public void apagarTurma(Long id) {
		
		try {
			repositor.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new BancoDeDadosExceptions(e.getMessage());
		}
	}	
	/* ----------------------------------------------------------------------------------------------*/
	
	public List<Turma> findAll(){
		
		List<Turma> lista = repositor.findAll();
		
		return lista;
	}
}
