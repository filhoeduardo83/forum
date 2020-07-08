package com.example.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.forum.model.Topico;
import com.example.forum.model.TopicoDTO;
import com.example.forum.model.TopicoForm;
import com.example.forum.repository.CursoRepository;
import com.example.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
//Para simplificar os repositórios foram injetados direto no Controller, mas o ideal é em uma classe service
@Autowired
private TopicoRepository topicoRepository;

@Autowired
private CursoRepository cursoRepository;
	
	@GetMapping
	//@ResponseBody - Só era preciso quando a classe está marcada com @Controller
	public List<TopicoDTO> findTopics(String nomeCurso) {
	
		if(nomeCurso==null) {
			return TopicoDTO.converter(topicoRepository.findAll());
		} else {	
			return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDTO> getTopic(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (topico.isPresent()){
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
		
		

	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> addTopic(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
		
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
		 //Monta a URI de resposta baseado no usuário criado
		 URI local = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
		 //retorna uma RespostaEntidy p retornar 201
		 return ResponseEntity.created(local).body(new TopicoDTO(topico));	
	}
	
	@PutMapping("/{id}")
	@Transactional //Commit da transação se o método executar sem erro
	public ResponseEntity<TopicoDTO> updateTopic(@RequestBody UpdateTopicoForm updateTopicoForm, @PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (topico.isPresent()){
			topico.get().setTitulo(updateTopicoForm.getTitulo());
			topico.get().setMensagem(updateTopicoForm.getTitulo());
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{id}")
	@Transactional //Commit da transação se o método executar sem erro
	public ResponseEntity<TopicoDTO> deleteTopic(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (topico.isPresent()){
			topicoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
			}
		
		return ResponseEntity.notFound().build();
	
	}
}
