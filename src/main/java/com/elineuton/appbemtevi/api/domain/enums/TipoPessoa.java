package com.elineuton.appbemtevi.api.domain.enums;

public enum TipoPessoa {
	PROFISSIONAL(0, "Profissional"),
	RESPONSAVEL(1, "Responsável"), 
	ALUNO(2, "Aluno");
	
	private int cod;
	private String descricao;
	
	private TipoPessoa(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPessoa toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoPessoa p : TipoPessoa.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
	
}
