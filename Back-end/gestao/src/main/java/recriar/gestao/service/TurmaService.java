package recriar.gestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import recriar.gestao.entities.Professor;
import recriar.gestao.entities.Turma;
import recriar.gestao.entities.TurmaProfessor;
import recriar.gestao.entities.DTO.TurmaInfoDTO;
import recriar.gestao.entities.DTO.TurmaRegisterDTO;
import recriar.gestao.entities.DTO.TurmaUpdateDTO;
import recriar.gestao.entities.PK.TurmaProfessorPK;
import recriar.gestao.repositories.ProfessorRepository;
import recriar.gestao.repositories.TurmaProfessorRepository;
import recriar.gestao.repositories.TurmaRepository;
import recriar.gestao.service.exceptions.BancoDeDadosExceptions;
import recriar.gestao.service.exceptions.CredenciaisInvalidasException;
import recriar.gestao.service.exceptions.CriacaoNegadaException;
import recriar.gestao.service.exceptions.UsuarioInexistenteException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repositor;

	@Autowired
	private TurmaProfessorRepository repositorTurmaProfessor;

	@Autowired
	private TurmaAlunoService serviceAlunos;

	@Autowired
	private ProfessorRepository repositorProfessor;

	/*----------------------------------------------------------------------------*/

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
	/*----------------------------------------------------------------------------*/

	public void apagarTurma(Long id) {

		try {
			repositor.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BancoDeDadosExceptions(e.getMessage());
		}
	}
	/*----------------------------------------------------------------------------*/

	public List<Turma> findAll() {

		List<Turma> lista = repositor.findAll();

		return lista;
	}
	/*----------------------------------------------------------------------------*/

	public TurmaInfoDTO findByTurma(Long id) {

		TurmaInfoDTO entidade = buscar(id);

		return entidade;
	}

	private TurmaInfoDTO buscar(Long id) {

		Turma turma = repositor.findById(id)
				.orElseThrow(() -> new CredenciaisInvalidasException("Turma Não encontrado"));
		TurmaProfessor turmaProfessor = repositorTurmaProfessor.findByIdTurmaId(turma.getId());

		if (turmaProfessor == null) {
			throw new UsuarioInexistenteException("Associação não encontrada");
		}

		Professor professor = repositorProfessor.findById(turmaProfessor.getId().getProfessorId())
				.orElseThrow(() -> new CredenciaisInvalidasException("Professor Não encontrado"));
		;

		TurmaInfoDTO entidade = new TurmaInfoDTO();
		entidade.setNome(turma.getNome());
		entidade.setDescricao(turma.getDescricao());
		entidade.setProfessor(professor.getNome());

		entidade.setAlunos(serviceAlunos.listarAlunosPorTurma(id));

		return entidade;
	}
	/*----------------------------------------------------------------------------*/

	public TurmaInfoDTO atualizarDados(Long id, TurmaUpdateDTO entidade) {

		atualizarTurma(id, entidade.getNome(), entidade.getDescricao());
		atualizarProfessor(id, entidade.getProfessor());

		TurmaInfoDTO obj = buscar(id);

		return obj;
	}

	private void atualizarTurma(Long id, String nome, String descricao) {

		Turma turma = repositor.findById(id)
				.orElseThrow(() -> new UsuarioInexistenteException("Turma não encontrada!"));

		turma.setNome(nome);
		turma.setDescricao(descricao);

		repositor.save(turma);
	}

	@Transactional
	public void atualizarProfessor(Long turmaId, String nomeProfessor) {

		Professor professor = repositorProfessor.findByNome(nomeProfessor);
		if (professor == null) {
			throw new UsuarioInexistenteException("Professor não encontrado!");
		}

		// Remove todas as associações antigas dessa turma
		List<TurmaProfessor> relacoes = repositorTurmaProfessor.findAllByTurmaId(turmaId);
		if (!relacoes.isEmpty()) {
			repositorTurmaProfessor.deleteAll(relacoes);
		}

		// Adiciona a nova associação
		TurmaProfessor nova = new TurmaProfessor(new TurmaProfessorPK(professor.getId(), turmaId));
		repositorTurmaProfessor.save(nova);
	}

	/*----------------------------------------------------------------------------*/
}
