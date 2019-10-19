package com.stefanini.hackaton.dto;

import java.io.Serializable;

import com.stefanini.hackaton.entities.Heroi;

public class JogadorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nickname;
	private Heroi heroi;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Heroi getHeroi() {
		return heroi;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

}
