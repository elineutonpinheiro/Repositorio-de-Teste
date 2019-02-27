package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Unidade;
import com.elineuton.appbemtevi.api.repositories.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository unidadeRepository;
	
	
	public List<Unidade> listar(){
		return unidadeRepository.findAll();
	}
	
	public Unidade consultarPorId(Long id) {
		Unidade unidade = unidadeRepository.findById(id).orElse(null);
		return unidade;
	}
	
	public Unidade criar(Unidade unidade) {
		Unidade unidadeSalva = unidadeRepository.save(unidade);
		return unidadeSalva;
	}
	
	public Unidade atualizar(Long id, Unidade unidade) {
		Unidade unidadeSalva = unidadeRepository.findById(id).get();
		
		if(unidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(unidade, unidadeSalva, "id");
		return unidadeRepository.save(unidadeSalva);
	}
	 
	public void remover(Long id) {
		unidadeRepository.deleteById(id);
	}
	
	public Page<Unidade> buscarPagina(Integer pagina, Integer tamanho, String orderBy, String direcao){
		PageRequest pageable = PageRequest.of(pagina, tamanho, Direction.valueOf(direcao), orderBy);
		return unidadeRepository.findAll(pageable);
	}
}
