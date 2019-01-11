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

import com.elineuton.appbemtevi.api.domain.Avaliacao;
import com.elineuton.appbemtevi.api.services.AvaliacaoService;

@RestController
@RequestMapping("/avaliacaos")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping
	public List<Avaliacao> listar(){
		return avaliacaoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Avaliacao> consultaPorId(@PathVariable Long id) {
		Avaliacao avaliacao = avaliacaoService.consultarPorId(id);
		return avaliacao != null ? ResponseEntity.ok(avaliacao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Avaliacao> criar(@RequestBody @Valid Avaliacao avaliacao, HttpServletResponse response) {
		Avaliacao avaliacaoSalva = avaliacaoService.criar(avaliacao);
		
		//Mapear o recurso -> avaliacao+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(avaliacaoSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(avaliacaoSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @RequestBody @Valid Avaliacao avaliacao) {
		Avaliacao avaliacaoSalva = avaliacaoService.atualizar(id, avaliacao);
		return ResponseEntity.ok(avaliacaoSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		avaliacaoService.remover(id);
	}
	
	
}
