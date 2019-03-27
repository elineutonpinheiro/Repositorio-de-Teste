package com.elineuton.appbemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.elineuton.appbemtevi.api.domain.enums.GrupoSanguineo;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_nascimento;
	
	private Character sexo;
	
	//private String cor;
	
	//private String nacionalidade;
	
	//private String naturalidade;
	
	//private String cpf;
	
	//private String cert_nascimento;
	
	@Embedded
	private Endereco endereco;
	
	@OneToMany(cascade = CascadeType.ALL) //TODO CRIAR COM MÉTODO POST DO ALUNO
	private List<Responsavel> responsaveis;

	@OneToMany //TODO CRIAR COM MÉTODO POST DO ALUNO
	private List<PessoalAutorizado> pesssoalAutorizado;
	
	@OneToOne
	private Unidade unidade;
	
	@ManyToOne
	private Turma turma;
	
	private boolean presenca;
	
	private String need;
	
	private String grupoSanguineo;

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

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public List<PessoalAutorizado> getPesssoalAutorizado() {
		return pesssoalAutorizado;
	}

	public void setPesssoalAutorizado(List<PessoalAutorizado> pesssoalAutorizado) {
		this.pesssoalAutorizado = pesssoalAutorizado;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public boolean isPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}
	
	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}
	
	public GrupoSanguineo getGrupoSanguineo() {
		return GrupoSanguineo.toEnum(grupoSanguineo);
	}

	public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo.getDescricao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
