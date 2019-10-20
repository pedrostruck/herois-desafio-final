package com.stefanini.hackaton.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jogador")
@NamedQueries({ @NamedQuery(
	name = "Jogador.getAll",
	query = "SELECT j FROM Jogador j") })
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String nickname;

	private String senha;

	@OneToOne
	@JoinColumn(name = "idHeroi")
	private Heroi personagem;

	public Jogador() {

	}

	public Jogador(String nickname, Heroi personagem) {
		this.nickname = nickname;
		this.personagem = personagem;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Heroi getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Heroi personagem) {
		this.personagem = personagem;
	}

}
