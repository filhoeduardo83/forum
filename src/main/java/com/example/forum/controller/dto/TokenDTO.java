package com.example.forum.controller.dto;

public class TokenDTO {

	private String token;
	private String protocolo;
	
	
	public TokenDTO(String token, String protocolo) {
		super();
		this.setToken(token);
		this.setProtocolo(protocolo);
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getProtocolo() {
		return protocolo;
	}


	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	
	
	
}
