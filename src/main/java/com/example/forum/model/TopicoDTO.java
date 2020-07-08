package com.example.forum.model;

import java.util.List;
import java.util.stream.Collectors;

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
		
		public static List<TopicoDTO> converter(List<Topico> topicos){
			
			return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList())
					
;		}
}
