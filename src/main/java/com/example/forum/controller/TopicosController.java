package com.example.forum.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.forum.model.Curso;
import com.example.forum.model.Topico;
import com.example.forum.model.TopicoDTO;

@RestController
public class TopicosController {
	
	@RequestMapping("/topicos")
	@ResponseBody
	public List<TopicoDTO> TopicosController() {
		
		
		Topico topico = new Topico("Dúvida", "Dúvida Spring", new Curso("Spring", "Progamação"));
		
		return TopicoDTO.converter(Arrays.asList(topico, topico, topico));
		
	}

}
