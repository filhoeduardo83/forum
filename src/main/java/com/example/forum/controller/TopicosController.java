package com.example.forum.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.forum.model.Curso;
import com.example.forum.model.Topico;
import com.example.forum.model.TopicoDTO;
import com.example.forum.repository.TopicoRepository;
import com.example.forum.repository.CursoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
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
	
	@PostMapping
	public void addTopic(@RequestBody TopicoForm topicoForm) {
		
		Topico topico = topicoForm.converter(cursoRepository);
		
		topicoRepository.save(topico);
		
	}

}
