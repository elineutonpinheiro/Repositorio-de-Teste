package com.elineuton.appbemtevi.api.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ContatoPessoal extends Contato{
	private static final long serialVersionUID = 1L;
	
	private String emailPessoal;
	
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Responsavel responsavel;

	public String getEmailPessoal() {
		return emailPessoal;
	}

	public void setEmailPessoal(String emailPessoal) {
		this.emailPessoal = emailPessoal;
	}

}
