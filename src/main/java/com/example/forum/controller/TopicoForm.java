package com.example.forum.controller;

import com.example.forum.model.Curso;
import com.example.forum.model.Topico;
import com.example.forum.repository.CursoRepository;
import com.example.forum.repository.TopicoRepository;

public class TopicoForm {

	
	String titulo;
	String mensagem;
	String nomeCurso;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public void setNomeCurso(String curso) {
		this.nomeCurso = curso;
	}
	
	public TopicoForm() {
		super();
	}
	
	public Topico converter(CursoRepository cursoRepository) {
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		return new Topico (titulo, mensagem, curso);
	}
	
}
