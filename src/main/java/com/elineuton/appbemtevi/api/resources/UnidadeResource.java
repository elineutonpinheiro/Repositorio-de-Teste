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

import com.elineuton.appbemtevi.api.domain.Unidade;
//import com.elineuton.appbemtevi.api.repositories.UnidadeRepository;
import com.elineuton.appbemtevi.api.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {
	
	@Autowired
	private UnidadeService unidadeService;
	
	@GetMapping
	public List<Unidade> listar(){
		return unidadeService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> consultaPorId(@PathVariable Long id) {
		Unidade unidade = unidadeService.consultarPorId(id);
		return unidade != null ? ResponseEntity.ok(unidade) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody @Valid Unidade unidade, HttpServletResponse response) {
		Unidade unidadeSalva = unidadeService.criar(unidade);
		
		//Mapear o recurso -> unidade+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(unidadeSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(unidadeSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Unidade> atualizar(@PathVariable Long id, @RequestBody @Valid Unidade unidade) {
		Unidade unidadeSalva = unidadeService.atualizar(id, unidade);
		return ResponseEntity.ok(unidadeSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		unidadeService.remover(id);
	}
	
	
}
