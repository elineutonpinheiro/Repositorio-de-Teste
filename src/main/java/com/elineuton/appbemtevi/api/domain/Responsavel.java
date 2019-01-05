package com.elineuton.appbemtevi.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Responsavel extends Pessoa{
	private static final long serialVersionUID = 1L;

	private String ocupacao;
	
	@OneToMany(mappedBy = "responsavel")
	private List<ContatoPessoal> contatos = new ArrayList<>();

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}
	
}
