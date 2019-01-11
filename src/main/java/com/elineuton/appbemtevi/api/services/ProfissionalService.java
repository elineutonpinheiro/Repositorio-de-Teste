package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Profissional;
import com.elineuton.appbemtevi.api.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	
	public List<Profissional> listar(){
		return profissionalRepository.findAll();
	}
	
	public Profissional consultarPorId(Long id) {
		Profissional profissional = profissionalRepository.findById(id).orElse(null);
		return profissional;
	}
	
	public Profissional criar(Profissional profissional) {
		Profissional profissionalSalvo = profissionalRepository.save(profissional);
		return profissionalSalvo;
	}
	
	public Profissional atualizar(Long id, Profissional profissional) {
		Profissional profissionalSalvo = profissionalRepository.findById(id).get();
		
		if(profissionalSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(profissional, profissionalSalvo, "id");
		return profissionalRepository.save(profissionalSalvo);
	}
	 
	public void remover(Long id) {
		profissionalRepository.deleteById(id);
	}
}
