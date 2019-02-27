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

import com.elineuton.appbemtevi.api.domain.Aluno;
import com.elineuton.appbemtevi.api.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> listar(){
		List<Aluno> alunos = alunoService.listar();
		return ResponseEntity.ok(alunos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> consultaPorId(@PathVariable Long id) {
		Aluno aluno = alunoService.consultarPorId(id);
		return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Aluno> criar(@RequestBody @Valid Aluno aluno, HttpServletResponse response) {
		Aluno alunoSalvo = alunoService.criar(aluno);
		
		//Mapear o recurso -> aluno+id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(alunoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(alunoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody @Valid Aluno aluno) {
		Aluno alunoSalvo = alunoService.atualizar(id, aluno);
		return ResponseEntity.ok(alunoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		alunoService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
