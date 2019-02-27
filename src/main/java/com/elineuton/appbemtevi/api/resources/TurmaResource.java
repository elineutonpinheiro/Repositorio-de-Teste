package com.elineuton.appbemtevi.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elineuton.appbemtevi.api.domain.Turma;
import com.elineuton.appbemtevi.api.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {
	
	@Autowired
	private TurmaService turmaService;
	
	@GetMapping
	public ResponseEntity<List<Turma>> listar(){
		List<Turma> turmas = turmaService.listar();
		return ResponseEntity.ok(turmas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> consultaPorId(@PathVariable Long id) {
		Turma turma = turmaService.consultarPorId(id);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Turma> criar(@RequestBody @Valid Turma turma, HttpServletResponse response) {
		Turma turmaSalva = turmaService.criar(turma);
		
		//Mapear o recurso -> turma+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(turmaSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(turmaSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody @Valid Turma turma) {
		Turma turmaSalva = turmaService.atualizar(id, turma);
		return ResponseEntity.ok(turmaSalva);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		turmaService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
