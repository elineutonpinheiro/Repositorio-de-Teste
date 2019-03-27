package com.elineuton.appbemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.elineuton.appbemtevi.api.domain.Turma;
import com.elineuton.appbemtevi.api.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repositorio;
	
	
	public List<Turma> listar(){
		return repositorio.findAll();
	}
	
	public Turma consultarPorId(Long id) {
		Turma obj = repositorio.findById(id).orElse(null);
		return obj;
	}
	
	public Turma criar(Turma obj) {
		Turma objSalvo = repositorio.save(obj);
		return objSalvo;
	}
	
	public Turma atualizar(Turma obj, Long id) {
		Turma objSalvo = repositorio.findById(id).get();
		
		if(objSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(obj, objSalvo, "id");
		return repositorio.save(objSalvo);
	}
	 
	public void remover(Long id) {
		repositorio.deleteById(id);
	}
	
	public Page<Turma> buscarPagina(Integer pagina, Integer tamanho, String ordem, String direcao){
		PageRequest pageable = PageRequest.of(pagina, tamanho, Direction.valueOf(direcao), ordem);
		return repositorio.findAll(pageable);
	}
	
	//SEM TURMA DTO
	//public Turma fromDTO(TurmaDTO objDto) {
	//	return null; //TODO Implementar o método de conversão de uma TurmaDTO em Turma
	//}
}
