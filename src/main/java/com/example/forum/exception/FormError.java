package com.example.forum.exception;

public class FormError {

	private String campo;
	private String erro;
	
	public FormError(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
}
