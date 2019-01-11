package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Avaliacao;
import com.elineuton.appbemtevi.api.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	
	public List<Avaliacao> listar(){
		return avaliacaoRepository.findAll();
	}
	
	public Avaliacao consultarPorId(Long id) {
		Avaliacao avaliacao = avaliacaoRepository.findById(id).orElse(null);
		return avaliacao;
	}
	
	public Avaliacao criar(Avaliacao avaliacao) {
		Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
		return avaliacaoSalva;
	}
	
	public Avaliacao atualizar(Long id, Avaliacao avaliacao) {
		Avaliacao avaliacaoSalva = avaliacaoRepository.findById(id).get();
		
		if(avaliacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(avaliacao, avaliacaoSalva, "id");
		return avaliacaoRepository.save(avaliacaoSalva);
	}
	 
	public void remover(Long id) {
		avaliacaoRepository.deleteById(id);
	}
}
