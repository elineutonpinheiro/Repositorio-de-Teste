package com.elineuton.appbemtevi.api.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "Responsavel")
public class Responsavel extends Pessoa{
	private static final long serialVersionUID = 1L;

	private String ocupacao;
	
	@OneToMany(mappedBy = "responsavel")
	private List<Aluno> alunos;

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
}
