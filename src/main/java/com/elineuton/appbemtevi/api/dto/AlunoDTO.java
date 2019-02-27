package com.elineuton.appbemtevi.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.elineuton.appbemtevi.api.domain.Aluno;


public class AlunoDTO {

	private Long id;
	
	private String nome;
	
	private LocalDate data_nascimento;
	
	private Character sexo;
	
	private String cor;
	
	private String nacionalidade;
	
	private String naturalidade;
	
	private String cpf;
	
	private String cert_nascimento;
	
	private String endereco;
	

	private List<String> pesssoalAutorizado;
	
	private String unidade;
	
	private String turma;
	
	private boolean presenca;
	
	private String need;
	
	private String grupoSanguineo;

	
	public AlunoDTO(Aluno aluno) {
		id = aluno.getId();
		nome = aluno.getNome();
		data_nascimento = aluno.getData_nascimento();
		sexo = aluno.getSexo();
		cor = aluno.getCor();
		nacionalidade = aluno.getNacionalidade();
		naturalidade = aluno.getNaturalidade();
		cpf = aluno.getCpf();
		cert_nascimento = aluno.getCert_nascimento();
		endereco = aluno.getEndereco().getLogradouro() + "," + aluno.getEndereco().getNumero() + " " + aluno.getEndereco().getBairro();
		setUnidade(aluno.getUnidade().getNome());	
		setTurma(aluno.getTurma().getDescricao());
		presenca = aluno.isPresenca();
		need = aluno.getNeed();
		grupoSanguineo = aluno.getGrupoSanguineo().getDescricao();
		
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


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getNacionalidade() {
		return nacionalidade;
	}


	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}


	public String getNaturalidade() {
		return naturalidade;
	}


	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCert_nascimento() {
		return cert_nascimento;
	}


	public void setCert_nascimento(String cert_nascimento) {
		this.cert_nascimento = cert_nascimento;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<String> getPesssoalAutorizado() {
		return pesssoalAutorizado;
	}


	public void setPesssoalAutorizado(List<String> pesssoalAutorizado) {
		this.pesssoalAutorizado = pesssoalAutorizado;
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


	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}


	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}


	public String getUnidade() {
		return unidade;
	}


	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}


	public String getTurma() {
		return turma;
	}


	public void setTurma(String turma) {
		this.turma = turma;
	}
	
}
