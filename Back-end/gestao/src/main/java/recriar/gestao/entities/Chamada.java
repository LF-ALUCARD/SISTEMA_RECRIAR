package recriar.gestao.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chamadas")
public class Chamada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "turma_id")
	@JsonManagedReference
	private Turma turma;
	private LocalDate data_chamada;

	public Chamada() {
	}

	public Chamada(Long id, Turma turma, LocalDate data_chamada) {
		this.id = id;
		this.turma = turma;
		this.data_chamada = data_chamada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public LocalDate getData_chamada() {
		return data_chamada;
	}

	public void setData_chamada(LocalDate data_chamada) {
		this.data_chamada = data_chamada;
	}

}
