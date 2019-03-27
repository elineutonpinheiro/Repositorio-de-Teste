package com.elineuton.appbemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.elineuton.appbemtevi.api.domain.Avaliacao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AvaliacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora;

	//TODO Posteriormente também pegar a categoria para por como Título da AV
	
	//TODO Teste para separar DATA e HORA, ou terei que salvar separadamente na Classe?
	
	public AvaliacaoDTO(Avaliacao obj) {
		id = obj.getId();
		data = obj.getDataHora().toLocalDate();
		hora = obj.getDataHora().toLocalTime();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
}
