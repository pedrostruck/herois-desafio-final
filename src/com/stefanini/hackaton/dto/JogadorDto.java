package com.stefanini.hackaton.dto;

import java.io.Serializable;

public class JogadorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nickname;
	private Integer personagem;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Integer personagem) {
		this.personagem = personagem;
	}

}
