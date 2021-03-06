package com.elineuton.appbemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.appbemtevi.api.domain.Aluno;


public class AlunoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	public AlunoDTO(Aluno obj) {
		id = obj.getId();
		nome = obj.getNome();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
