package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Atividade;
import com.elineuton.appbemtevi.api.dto.AtividadeDTO;
import com.elineuton.appbemtevi.api.repositories.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository repositorio;
	
	
	public List<Atividade> listar(){
		return repositorio.findAll();
	}
	
	public Atividade consultarPorId(Long id) {
		Atividade obj = repositorio.findById(id).orElse(null);
		return obj;
	}
	
	public Atividade criar(Atividade obj) {
		Atividade objSalvo = repositorio.save(obj);
		return objSalvo;
	}
	
	public Atividade atualizar(Atividade obj, Long id) {
		Atividade objSalvo = repositorio.findById(id).get();
		atualizarDados(objSalvo, obj);
		
		if(objSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		//Copia os dados do objeto do parâmentro para o salvo no BD
		//BeanUtils.copyProperties(obj, objSalvo, "id");
		return repositorio.save(objSalvo);
	}

	public void remover(Long id) {
		repositorio.deleteById(id);
	}
	
	public Page<Atividade> buscarPagina(Integer pagina, Integer tamanho, String ordem, String direcao){
		PageRequest pageable = PageRequest.of(pagina, tamanho, Direction.valueOf(direcao), ordem);
		return repositorio.findAll(pageable);
	}
	
	public Atividade fromDTO(AtividadeDTO objDto) {
		return new Atividade(objDto.getId(),objDto.getTitulo(),objDto.getDescricao());
		//return null; //TODO Implementar o método de conversão de uma AtividadeDTO em Atividade
	}
	
	private void atualizarDados(Atividade objSalvo, Atividade obj) {
		objSalvo.setTitulo(obj.getTitulo());
		objSalvo.setDescricao(obj.getDescricao());
	}
}
