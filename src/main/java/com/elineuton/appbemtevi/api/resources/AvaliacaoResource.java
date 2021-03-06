package com.elineuton.appbemtevi.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elineuton.appbemtevi.api.domain.Avaliacao;
import com.elineuton.appbemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.appbemtevi.api.services.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Avaliacao>> listar(){
		List<Avaliacao> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Avaliacao> consultaPorId(@PathVariable Long id) {
		Avaliacao obj = service.consultarPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Avaliacao> criar(@Valid @RequestBody Avaliacao obj, HttpServletResponse response) {
		Avaliacao objSalvo = service.criar(obj);
		
		//Mapear o recurso -> avaliacao+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(objSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(objSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Avaliacao> atualizar(@Valid @RequestBody Avaliacao obj, @PathVariable Long id) {
		Avaliacao objSalvo = service.atualizar(id, obj);
		return ResponseEntity.ok(objSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page") //TODO Implementar o AvaliacaoDTO posteriormente
	public ResponseEntity<Page<AvaliacaoDTO>> listarAvaliacaosPage(
			@RequestParam(value="page", defaultValue="0") Integer pagina, 
			@RequestParam(value="size", defaultValue="24") Integer tamanho, 
			@RequestParam(value="orderBy", defaultValue="dataHora") String ordem, 
			@RequestParam(value="direction", defaultValue="ASC") String direcao) {
		Page<Avaliacao> lista = service.buscarPagina(pagina, tamanho, ordem, direcao);
		Page<AvaliacaoDTO> listDto = lista.map(obj -> new AvaliacaoDTO(obj));
		return ResponseEntity.ok(listDto);	
	}
	
	
}
