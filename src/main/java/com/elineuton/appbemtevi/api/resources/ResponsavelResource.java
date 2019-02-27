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

import com.elineuton.appbemtevi.api.domain.Responsavel;
import com.elineuton.appbemtevi.api.services.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelResource {
	
	@Autowired
	private ResponsavelService responsavelService;
	
	@GetMapping
	public ResponseEntity<List<Responsavel>> listar(){
		List<Responsavel> responsaveis = responsavelService.listar();
		return ResponseEntity.ok(responsaveis);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Responsavel> consultaPorId(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.consultarPorId(id);
		return responsavel != null ? ResponseEntity.ok(responsavel) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Responsavel> criar(@RequestBody @Valid Responsavel responsavel, HttpServletResponse response) {
		Responsavel responsavelSalvo = responsavelService.criar(responsavel);
		
		//Mapear o recurso -> responsavel+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(responsavelSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(responsavelSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Responsavel> atualizar(@PathVariable Long id, @RequestBody @Valid Responsavel responsavel) {
		Responsavel responsavelSalvo = responsavelService.atualizar(id, responsavel);
		return ResponseEntity.ok(responsavelSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		responsavelService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
