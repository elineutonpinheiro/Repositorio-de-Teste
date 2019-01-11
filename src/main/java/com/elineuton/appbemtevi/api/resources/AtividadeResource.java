package com.elineuton.appbemtevi.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elineuton.appbemtevi.api.domain.Atividade;
import com.elineuton.appbemtevi.api.services.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeService atividadeService;
	
	@GetMapping
	public List<Atividade> listar(){
		return atividadeService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> consultaPorId(@PathVariable Long id) {
		Atividade atividade = atividadeService.consultarPorId(id);
		return atividade != null ? ResponseEntity.ok(atividade) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Atividade> criar(@RequestBody @Valid Atividade atividade, HttpServletResponse response) {
		Atividade atividadeSalva = atividadeService.criar(atividade);
		
		//Mapear o recurso -> atividade+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(atividadeSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(atividadeSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@PathVariable Long id, @RequestBody @Valid Atividade atividade) {
		Atividade atividadeSalva = atividadeService.atualizar(id, atividade);
		return ResponseEntity.ok(atividadeSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		atividadeService.remover(id);
	}
	
	
}
