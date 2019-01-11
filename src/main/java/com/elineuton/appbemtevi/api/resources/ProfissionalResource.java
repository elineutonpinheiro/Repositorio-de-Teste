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

import com.elineuton.appbemtevi.api.domain.Profissional;
import com.elineuton.appbemtevi.api.services.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalResource {
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@GetMapping
	public List<Profissional> listar(){
		return profissionalService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Profissional> consultaPorId(@PathVariable Long id) {
		Profissional profissional = profissionalService.consultarPorId(id);
		return profissional != null ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Profissional> criar(@RequestBody @Valid Profissional profissional, HttpServletResponse response) {
		Profissional profissionalSalvo = profissionalService.criar(profissional);
		
		//Mapear o recurso -> profissional+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(profissionalSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(profissionalSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @RequestBody @Valid Profissional profissional) {
		Profissional profissionalSalvo = profissionalService.atualizar(id, profissional);
		return ResponseEntity.ok(profissionalSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		profissionalService.remover(id);
	}
	
	
}
