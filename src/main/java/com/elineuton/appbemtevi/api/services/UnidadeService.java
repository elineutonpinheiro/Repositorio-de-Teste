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
import com.elineuton.appbemtevi.api.dto.UnidadeDTO;
import com.elineuton.appbemtevi.api.repositories.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository repositorio;
	
	
	public List<Unidade> listar(){
		return repositorio.findAll();
	}
	
	public Unidade consultarPorId(Long id) {
		Unidade obj = repositorio.findById(id).orElse(null);
		return obj;
	}
	
	public Unidade criar(Unidade obj) {
		Unidade objSalvo = repositorio.save(obj);
		return objSalvo;
	}
	
	public Unidade atualizar(Unidade obj, Long id) {
		Unidade objSalvo = repositorio.findById(id).get();
		
		if(objSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(obj, objSalvo, "id");
		return repositorio.save(objSalvo);
	}
	 
	public void remover(Long id) {
		repositorio.deleteById(id);
	}
	
	public Page<Unidade> buscarPagina(Integer pagina, Integer tamanho, String ordem, String direcao){
		PageRequest pageable = PageRequest.of(pagina, tamanho, Direction.valueOf(direcao), ordem);
		return repositorio.findAll(pageable);
	}
	
	public Unidade fromDTO(UnidadeDTO objDto) {
		return null; //TODO Implementar o método de conversão de uma UnidadeDTO em Unidade
	}
}
