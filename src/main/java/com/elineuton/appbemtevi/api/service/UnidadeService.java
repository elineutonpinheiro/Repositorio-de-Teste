package com.elineuton.appbemtevi.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.model.Unidade;
import com.elineuton.appbemtevi.api.repository.Unidades;

@Service
public class UnidadeService {

	@Autowired
	private Unidades unidades;
	
	public Unidade atualizar(Long id, Unidade unidade) {
		Unidade unidadeSalva = unidades.findById(id).get();
		
		if(unidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(unidade, unidadeSalva, "id");
		return unidades.save(unidadeSalva);
	}
	 
}
