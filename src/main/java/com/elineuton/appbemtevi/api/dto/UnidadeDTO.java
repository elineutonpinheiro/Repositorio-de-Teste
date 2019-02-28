package com.elineuton.appbemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.appbemtevi.api.domain.Unidade;

public class UnidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String tipo;
	
	private String situacao;
	
	private String cidade;

	public UnidadeDTO(Unidade unidade) {
		this.id = unidade.getId();
		this.nome = unidade.getNome();
		this.tipo = unidade.getTipo();
		this.situacao = unidade.getSituacao();
		this.cidade = unidade.getEndereco().getCidade();
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}
