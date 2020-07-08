package com.example.forum.controller;

public class UpdateTopicoForm {

	private String titulo;
	private String Mensagem;
	
	public UpdateTopicoForm(String titulo, String mensagem) {
		super();
		this.titulo = titulo;
		Mensagem = mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return Mensagem;
	}
	

}
