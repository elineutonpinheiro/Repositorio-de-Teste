package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Avaliacao;
import com.elineuton.appbemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.appbemtevi.api.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository repositorio;
	
	
	public List<Avaliacao> listar(){
		return repositorio.findAll();
	}
	
	public Avaliacao consultarPorId(Long id) {
		Avaliacao obj = repositorio.findById(id).orElse(null);
		return obj;
	}
	
	public Avaliacao criar(Avaliacao obj) {
		Avaliacao objSalvo = repositorio.save(obj);
		return objSalvo;
	}
	
	public Avaliacao atualizar(Long id, Avaliacao obj) {
		Avaliacao objSalvo = repositorio.findById(id).get();
		
		if(objSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(obj, objSalvo, "id");
		return repositorio.save(objSalvo);
	}
	 
	public void remover(Long id) {
		repositorio.deleteById(id);
	}
	
	public Page<Avaliacao> buscarPagina(Integer pagina, Integer tamanho, String ordem, String direcao){
		PageRequest pageable = PageRequest.of(pagina, tamanho, Direction.valueOf(direcao), ordem);
		return repositorio.findAll(pageable);
	}
	
	public Avaliacao fromDTO(AvaliacaoDTO objDto) {
		return null; //TODO Implementar o método de conversão de uma AvaliacaoDTO em Avaliacao
	}
	
}
