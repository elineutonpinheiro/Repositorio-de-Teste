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

import com.elineuton.appbemtevi.api.domain.Responsavel;
import com.elineuton.appbemtevi.api.dto.ResponsavelDTO;
import com.elineuton.appbemtevi.api.services.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelResource {
	
	@Autowired
	private ResponsavelService service;
	
	@GetMapping
	public ResponseEntity<List<Responsavel>> listar(){
		List<Responsavel> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Responsavel> consultaPorId(@PathVariable Long id) {
		Responsavel obj = service.consultarPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Responsavel> criar(@Valid @RequestBody Responsavel obj, HttpServletResponse response) {
		Responsavel objSalvo = service.criar(obj);
		
		//Mapear o recurso -> responsavel+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(objSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(objSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Responsavel> atualizar(@Valid @RequestBody Responsavel obj, @PathVariable Long id) {
		//Responsavel obj = service.fromDTO(objDto);
		Responsavel objSalvo = service.atualizar(obj, id);
		return ResponseEntity.ok(objSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page") //TODO Implementar o ResponsavelDTO posteriormente
	public ResponseEntity<Page<ResponsavelDTO>> listarResponsavelsPage(
			@RequestParam(value="page", defaultValue="0") Integer pagina, 
			@RequestParam(value="size", defaultValue="24") Integer tamanho, 
			@RequestParam(value="orderBy", defaultValue="nome") String ordem, 
			@RequestParam(value="direction", defaultValue="ASC") String direcao) {
		Page<Responsavel> lista = service.buscarPagina(pagina, tamanho, ordem, direcao);
		Page<ResponsavelDTO> listDto = lista.map(obj -> new ResponsavelDTO(obj));
		return ResponseEntity.ok(listDto);	
	}
	
	
}
