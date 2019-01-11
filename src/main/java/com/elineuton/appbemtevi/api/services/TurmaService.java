package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Turma;
import com.elineuton.appbemtevi.api.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	
	
	public List<Turma> listar(){
		return turmaRepository.findAll();
	}
	
	public Turma consultarPorId(Long id) {
		Turma turma = turmaRepository.findById(id).orElse(null);
		return turma;
	}
	
	public Turma criar(Turma turma) {
		Turma turmaSalvo = turmaRepository.save(turma);
		return turmaSalvo;
	}
	
	public Turma atualizar(Long id, Turma turma) {
		Turma turmaSalvo = turmaRepository.findById(id).get();
		
		if(turmaSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(turma, turmaSalvo, "id");
		return turmaRepository.save(turmaSalvo);
	}
	 
	public void remover(Long id) {
		turmaRepository.deleteById(id);
	}
}
