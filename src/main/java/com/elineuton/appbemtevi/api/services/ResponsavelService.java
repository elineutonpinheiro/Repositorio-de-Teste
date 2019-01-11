package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Responsavel;
import com.elineuton.appbemtevi.api.repositories.ResponsavelRepository;

@Service
public class ResponsavelService {

	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	
	public List<Responsavel> listar(){
		return responsavelRepository.findAll();
	}
	
	public Responsavel consultarPorId(Long id) {
		Responsavel responsavel = responsavelRepository.findById(id).orElse(null);
		return responsavel;
	}
	
	public Responsavel criar(Responsavel responsavel) {
		Responsavel responsavelSalvo = responsavelRepository.save(responsavel);
		return responsavelSalvo;
	}
	
	public Responsavel atualizar(Long id, Responsavel responsavel) {
		Responsavel responsavelSalvo = responsavelRepository.findById(id).get();
		
		if(responsavelSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(responsavel, responsavelSalvo, "id");
		return responsavelRepository.save(responsavelSalvo);
	}
	 
	public void remover(Long id) {
		responsavelRepository.deleteById(id);
	}
}
