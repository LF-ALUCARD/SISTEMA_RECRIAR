package recriar.gestao.entities.PK;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import recriar.gestao.entities.Aluno;
import recriar.gestao.entities.Chamada;

public class chamada_aluno_PK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "chamada_id")
	@JsonManagedReference
	private Chamada chamada;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	@JsonManagedReference
	private Aluno aluno;
}
