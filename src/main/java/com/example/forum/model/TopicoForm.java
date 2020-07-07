package com.example.forum.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.forum.repository.CursoRepository;

public class TopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	String titulo;
	@NotNull @NotEmpty @Length(min = 10)
	String mensagem;
	@NotNull @NotEmpty
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
