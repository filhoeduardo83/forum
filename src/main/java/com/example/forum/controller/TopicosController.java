package com.example.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.forum.controller.dto.TopicoDTO;
import com.example.forum.controller.form.TopicoForm;
import com.example.forum.controller.form.UpdateTopicoForm;
import com.example.forum.model.Topico;
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
	public Page<TopicoDTO> listTopics(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(sort = "dataCriacao", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		//Ex: localhost:8080/topicos/?page=1&&size=2
		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDTO.converter(topicos);
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
	public ResponseEntity<TopicoDTO> addTopic(@RequestBody @Valid TopicoForm topicoForm, 
			UriComponentsBuilder uriBuilder) {
		
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
		 //Monta a URI de resposta baseado no usuário criado
		 URI local = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
		 //retorna uma RespostaEntidy p retornar 201
		 return ResponseEntity.created(local).body(new TopicoDTO(topico));	
	}
	
	@PutMapping("/{id}")
	@Transactional //Commit da transação se o método executar sem erro
	public ResponseEntity<TopicoDTO> updateTopic(@RequestBody UpdateTopicoForm updateTopicoForm, 
			@PathVariable Long id) {
		
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
