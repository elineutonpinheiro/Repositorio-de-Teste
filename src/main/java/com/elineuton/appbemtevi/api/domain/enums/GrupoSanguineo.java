package com.elineuton.appbemtevi.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GrupoSanguineo {
	O_POSITIVO("O Positivo"),
	O_NEGATIVO("O Negativo"),
	A_POSITIVO("A Positivo"),
	A_NEGATIVO("A Negativo"),
	B_POSITIVO("B Positivo"),
	B_NEGATIVO("B Negativo"),
	AB_POSITIVO("AB Positivo"),
	AB_NEGATIVO("AB Negativo");
	
	private String descricao;
	
	private GrupoSanguineo(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
	public static GrupoSanguineo toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		for(GrupoSanguineo gs : GrupoSanguineo.values()) {
			if(descricao.equals(gs.getDescricao())) {
				return gs;
			}
		}
		
		throw new IllegalArgumentException("Descricao inválida: " + descricao);
	}
	
	
}
