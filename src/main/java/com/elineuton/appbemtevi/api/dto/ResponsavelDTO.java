package com.elineuton.appbemtevi.api.dto;


import java.io.Serializable;

import com.elineuton.appbemtevi.api.domain.Responsavel;


public class ResponsavelDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String parentesco;

	private String nome;
	
	public ResponsavelDTO(Responsavel obj) {
		id = obj.getId();
		parentesco = obj.getParentesco();
		nome = obj.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
