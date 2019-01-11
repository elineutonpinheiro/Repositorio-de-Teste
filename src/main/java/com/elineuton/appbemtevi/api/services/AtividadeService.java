package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Atividade;
import com.elineuton.appbemtevi.api.repositories.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;
	
	
	public List<Atividade> listar(){
		return atividadeRepository.findAll();
	}
	
	public Atividade consultarPorId(Long id) {
		Atividade atividade = atividadeRepository.findById(id).orElse(null);
		return atividade;
	}
	
	public Atividade criar(Atividade atividade) {
		Atividade atividadeSalva = atividadeRepository.save(atividade);
		return atividadeSalva;
	}
	
	public Atividade atualizar(Long id, Atividade atividade) {
		Atividade atividadeSalva = atividadeRepository.findById(id).get();
		
		if(atividadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(atividade, atividadeSalva, "id");
		return atividadeRepository.save(atividadeSalva);
	}
	 
	public void remover(Long id) {
		atividadeRepository.deleteById(id);
	}
}
