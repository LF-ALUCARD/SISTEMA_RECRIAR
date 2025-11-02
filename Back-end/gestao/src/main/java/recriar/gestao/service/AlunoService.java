package recriar.gestao.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.Responsavel;
import recriar.gestao.entities.DTO.AlunoInfoDTO;
import recriar.gestao.entities.DTO.AlunoListDTO;
import recriar.gestao.entities.DTO.AlunoRegisterDTO;
import recriar.gestao.entities.DTO.AlunoRegisterResponseDTO;
import recriar.gestao.entities.DTO.AlunoUpdateDTO;
import recriar.gestao.entities.DTO.ResponsavelListDTO;
import recriar.gestao.entities.enums.Sexo;
import recriar.gestao.repositories.AlunoRepository;
import recriar.gestao.repositories.ResponsavelRepository;
import recriar.gestao.service.exceptions.CredenciaisInvalidasException;
import recriar.gestao.service.exceptions.CriacaoNegadaException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repositor;

	@Autowired
	private ResponsavelRepository repositorResponsavel;

	/*-------------------------------------------------------------------------*/
	public Aluno criarAlunoComResponsavel(AlunoRegisterDTO obj) {

		if (repositorResponsavel.existsByDocumento(obj.getDocumento())) {
			throw new CriacaoNegadaException("Documento existemte");
		}

		Responsavel responsavel = converterResponsavel(obj);
		Aluno entidade = converterAluno(obj, responsavel);

		entidade.setMatricula("TEMP");

		entidade = repositor.save(entidade);

		String ano = String.valueOf(LocalDate.now().getYear());
		String matricula = ano + "A" + String.format("%03d", entidade.getId());
		entidade.setMatricula(matricula);

		return repositor.save(entidade);
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

		aluno.setNome(dto.getNome());
		aluno.setSobrenome(dto.getSobrenome());
		aluno.setDocumento(dto.getDocumento());
		aluno.setIdade(dto.getIdade());
		aluno.setSexo(Sexo.valueof(dto.getSexo()));
		aluno.setResponsavel(responsavel);
		aluno.setData_matricula(dto.getData_matricula());

		return aluno;
	}
	/*-------------------------------------------------------------------------*/

	public Aluno criarAlunoComResponsavel(AlunoRegisterResponseDTO obj) {

		if (repositorResponsavel.existsByDocumento(obj.getDocumento())) {
			throw new CriacaoNegadaException("Documento existente");
		}

		Aluno entidade = converterAluno(obj);
		entidade.setMatricula("TEMP");

		entidade = repositor.save(entidade);

		String ano = String.valueOf(LocalDate.now().getYear());
		String matricula = ano + "A" + String.format("%03d", entidade.getId());
		entidade.setMatricula(matricula);

		return repositor.save(entidade);

	}

	private Aluno converterAluno(AlunoRegisterResponseDTO dto) {

		Aluno aluno = new Aluno();
		Responsavel responsavel = repositorResponsavel.findById(dto.getResponsavel())
				.orElseThrow(() -> new CriacaoNegadaException("Responsavél não encontrado"));

		aluno.setNome(dto.getNome());
		aluno.setSobrenome(dto.getSobrenome());
		aluno.setDocumento(dto.getDocumento());
		aluno.setIdade(dto.getIdade());
		aluno.setSexo(Sexo.valueof(dto.getSexo()));
		aluno.setResponsavel(responsavel);
		aluno.setData_matricula(dto.getData_matricula());

		return aluno;
	}
	/*-------------------------------------------------------------------------*/

	public List<AlunoListDTO> findAll() {

		List<Aluno> lista = repositor.findAll();
		List<AlunoListDTO> listagem = lista.stream().map(x -> new AlunoListDTO(x)).toList();

		return listagem;
	}
	/*-------------------------------------------------------------------------*/

	public List<ResponsavelListDTO> findAllResponsavel() {

		List<Responsavel> lista = repositorResponsavel.findAll();
		List<ResponsavelListDTO> listagem = lista.stream().map(x -> new ResponsavelListDTO(x)).toList();

		return listagem;
	}

	/*-------------------------------------------------------------------------*/
	@Transactional
	public void delete(Long id) {

		repositor.deleteById(id);

	}
	/*-------------------------------------------------------------------------*/

	public AlunoInfoDTO alunoInfo(Long id) {

		Aluno aluno = repositor.findById(id)
				.orElseThrow(() -> new CredenciaisInvalidasException("Aluno não existente"));
		AlunoInfoDTO entidade = new AlunoInfoDTO(aluno);

		return entidade;
	}
	/*-------------------------------------------------------------------------*/

	public AlunoInfoDTO atualizarDados(Long id, AlunoUpdateDTO entidade) {

		Aluno aluno = atualizarAluno(id, entidade);
		atualizarResponsavel(aluno.getResponsavel().getId(), entidade);
		
		AlunoInfoDTO salvar = new AlunoInfoDTO(aluno);
		
		return salvar;
	}

	private void atualizarResponsavel(Long id, AlunoUpdateDTO obj) {

		Responsavel entidade = repositorResponsavel.findById(id)
				.orElseThrow(() -> new CredenciaisInvalidasException("Usuário não existente"));

		entidade.setNome(obj.getResponsavel_nome());
		entidade.setEmail(obj.getEmail());
		entidade.setDocumento(obj.getResponsavel_documento());
		entidade.setTelefone(obj.getTelefone());
		entidade.setRua(obj.getRua());
		entidade.setNumero(obj.getNumero());
		entidade.setBairro(obj.getBairro());
		entidade.setCidade(obj.getCidade());

		repositorResponsavel.save(entidade);
	}
	
	private Aluno atualizarAluno(Long id, AlunoUpdateDTO obj) {
		
		Aluno entidade = repositor.findById(id).orElseThrow(() -> new CredenciaisInvalidasException("Usuário não existente"));
		
		entidade.setNome(obj.getNome());
		entidade.setSobrenome(obj.getSobrenome());
		entidade.setDocumento(obj.getDocumento());
		entidade.setData_nascimento(obj.getData_nascimeto());
		
		return repositor.save(entidade);
	}
}
