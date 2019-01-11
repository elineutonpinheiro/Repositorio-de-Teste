package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Aluno;
import com.elineuton.appbemtevi.api.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	
	public List<Aluno> listar(){
		return alunoRepository.findAll();
	}
	
	public Aluno consultarPorId(Long id) {
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		return aluno;
	}
	
	public Aluno criar(Aluno aluno) {
		Aluno alunoSalvo = alunoRepository.save(aluno);
		return alunoSalvo;
	}
	
	public Aluno atualizar(Long id, Aluno aluno) {
		Aluno alunoSalvo = alunoRepository.findById(id).get();
		
		if(alunoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(aluno, alunoSalvo, "id");
		return alunoRepository.save(alunoSalvo);
	}
	 
	public void remover(Long id) {
		alunoRepository.deleteById(id);
	}
}
