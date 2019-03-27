package com.elineuton.appbemtevi.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.elineuton.appbemtevi.api.domain.Unidade;

public class UnidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="")
	@Size(min=3, max=240)
	private String nome;
	
	@NotEmpty
	private String tipo;
	
	@NotEmpty
	private String situacao;
	
	@NotEmpty
	private String cidade;

	public UnidadeDTO(Unidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.tipo = obj.getTipo();
		this.situacao = obj.getSituacao();
		this.cidade = obj.getEndereco().getCidade()+"/"+obj.getEndereco().getEstado();
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
