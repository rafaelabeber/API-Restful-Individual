package org.serratec.backend.projeto_individual.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.projeto_individual.model.Livro;
import org.serratec.backend.projeto_individual.repository.LivroRepository;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping
	public List<Livro> listar(){
		return livroRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> pesquisar(@PathVariable Long id){
		Optional<Livro> livroOpt = livroRepository.findById(id);
			if (livroOpt.isPresent()) {
				Livro livro = livroOpt.get();
				return ResponseEntity.ok(livro);
			}
				return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Livro inserir (@Valid @RequestBody Livro livro) {
		Livro livroSalvo = livroRepository.save(livro);
		return livroSalvo;
		
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro){
		if (!livroRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
			
		}
		livro.setId(id);
		livro = livroRepository.save(livro);
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!livroRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
