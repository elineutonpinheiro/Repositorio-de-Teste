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

import com.elineuton.appbemtevi.api.domain.Atividade;
import com.elineuton.appbemtevi.api.dto.AtividadeDTO;
import com.elineuton.appbemtevi.api.services.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeService service;
	
	@GetMapping
	public ResponseEntity<List<Atividade>> listar(){
		List<Atividade> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> consultaPorId(@PathVariable Long id) {
		Atividade obj = service.consultarPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Atividade> criar(@Valid @RequestBody Atividade obj, HttpServletResponse response) {
		Atividade objSalvo = service.criar(obj);
		
		//Mapear o recurso -> atividade+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(objSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(objSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@Valid @RequestBody AtividadeDTO objDto, @PathVariable Long id) {
		Atividade obj = service.fromDTO(objDto);
		Atividade objSalvo = service.atualizar(obj, id);
		return ResponseEntity.ok(objSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page") //TODO Implementar o AtividadeDTO posteriormente
	public ResponseEntity<Page<AtividadeDTO>> listarAtividadesPage(
			@RequestParam(value="page", defaultValue="0") Integer pagina, 
			@RequestParam(value="size", defaultValue="24") Integer tamanho, 
			@RequestParam(value="orderBy", defaultValue="titulo") String ordem, 
			@RequestParam(value="direction", defaultValue="ASC") String direcao) {
		Page<Atividade> lista = service.buscarPagina(pagina, tamanho, ordem, direcao);
		Page<AtividadeDTO> listDto = lista.map(obj -> new AtividadeDTO(obj));
		return ResponseEntity.ok(listDto);	
	}
	
}
