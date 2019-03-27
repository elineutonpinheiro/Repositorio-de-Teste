package com.elineuton.appbemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.appbemtevi.api.domain.Atividade;

public class AtividadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	public AtividadeDTO() {
		
	}
	
	public AtividadeDTO(Atividade obj) {
		id = obj.getId();
		titulo = obj.getTitulo();
		descricao = obj.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
