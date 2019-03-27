package com.elineuton.appbemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.appbemtevi.api.domain.Profissional;

public class ProfissionalDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String perfil; //TODO Relacionar nas permissões -> Valor = Cuidador, Professor;
	

	public ProfissionalDTO(Profissional obj) {
		id = obj.getId();
		nome = obj.getNome();
		perfil = obj.getPerfil();
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
