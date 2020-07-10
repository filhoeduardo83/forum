package com.example.forum.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.forum.model.Topico;

public class TopicoDTO {
	
	private Long id;
	private String mensagem;
	private String nomeCurso;
	
		public TopicoDTO(Topico topico) {
			this.id = topico.getId();
			this.mensagem = topico.getMensagem();
			this.nomeCurso = topico.getCurso().getNome();
			
		}
		public Long getId() {
			return id;
		}

		public String getMensagem() {
			return mensagem;
		}

		public String getNomeCurso() {	
			return nomeCurso;
		}
		
		public static Page<TopicoDTO> converter(Page<Topico> topicos){
			
			return topicos.map(TopicoDTO::new);
		}
}