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

import com.elineuton.appbemtevi.api.domain.Unidade;
import com.elineuton.appbemtevi.api.dto.UnidadeDTO;
//import com.elineuton.appbemtevi.api.repositories.UnidadeRepository;
import com.elineuton.appbemtevi.api.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {
	
	@Autowired
	private UnidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Unidade>> listarUnidades(){
		List<Unidade> listaUnidades = service.listar();
		return ResponseEntity.ok(listaUnidades);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> consultaPorId(@PathVariable Long id) {
		Unidade unidade = service.consultarPorId(id);
		return unidade != null ? ResponseEntity.ok(unidade) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody @Valid Unidade unidade, HttpServletResponse response) {
		Unidade unidadeSalva = service.criar(unidade);
		
		//Mapear o recurso -> unidade+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(unidadeSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(unidadeSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Unidade> atualizar(@PathVariable Long id, @RequestBody @Valid Unidade unidade) {
		Unidade unidadeSalva = service.atualizar(id, unidade);
		return ResponseEntity.ok(unidadeSalva);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page") //TODO Implementar o UnidadeDTO posteriormente
	public ResponseEntity<Page<UnidadeDTO>> listarUnidadesPage(
			@RequestParam(value="page", defaultValue="0") Integer pagina, 
			@RequestParam(value="size", defaultValue="24") Integer tamanho, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direcao) {
		Page<Unidade> listaUnidades = service.buscarPagina(pagina, tamanho, orderBy, direcao);
		Page<UnidadeDTO> listDto = listaUnidades.map(unidade -> new UnidadeDTO(unidade));
		return ResponseEntity.ok(listDto);	
	}
	
	
}
