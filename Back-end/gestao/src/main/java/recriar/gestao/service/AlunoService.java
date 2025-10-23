package recriar.gestao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.Responsavel;
import recriar.gestao.entities.DTO.AlunoRegisterDTO;
import recriar.gestao.entities.enums.Sexo;
import recriar.gestao.repositories.AlunoRepository;
import recriar.gestao.repositories.ResponsavelRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repositorAluno;
	
	@Autowired
	private ResponsavelRepository repositorResponsavel;
	
	public Aluno criarAlunoComResponsavel(AlunoRegisterDTO obj) {
		
		Responsavel responsavel = converterResponsavel(obj);
		Aluno entidade = converterAluno(obj, responsavel);
		
		return repositorAluno.save(entidade);
	}
	
	private Responsavel converterResponsavel(AlunoRegisterDTO obj) {
		
		Responsavel entidade = new Responsavel();

		entidade.setNome(obj.getResponsavelNome());
		entidade.setDocumento(obj.getResponsavelDocumento());
		entidade.setEmail(obj.getResponsavelEmail());
		entidade.setTelefone(obj.getResponsavelTelefone());
		entidade.setRua(obj.getRua());
		entidade.setNumero(obj.getNumero());
		entidade.setBairro(obj.getBairro());
		entidade.setCidade(obj.getCidade());
		
		return repositorResponsavel.save(entidade);
	}
	
	private Aluno converterAluno(AlunoRegisterDTO dto, Responsavel responsavel) {
		
		Aluno aluno = new Aluno();
				

		aluno.setMatricula(dto.getMatricula());
	    aluno.setNome(dto.getNome());
	    aluno.setSobrenome(dto.getSobrenome());
	    aluno.setDocumento(dto.getDocumento());
	    aluno.setIdade(dto.getIdade());
	    aluno.setSexo(Sexo.valueof(dto.getSexo()));
	    aluno.setResponsavel(responsavel);
	    
	    return aluno;
	}
}
