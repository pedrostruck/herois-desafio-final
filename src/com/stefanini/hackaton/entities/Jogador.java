package com.stefanini.hackaton.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "jogador")
@NamedQueries({
		@NamedQuery(name = "Jogador.getAll", query = "SELECT j FROM Jogador j") })
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO deve ser único
	@Id
	private String nickname;

	// TODO deve ter no min. 6 e máx. 8 char. e deve ser criptografada
	private String senha;

	// TODO somente 1 personagem. armazena Id do Personagem/Heroi
	@Column(name = "idHeroi")
	private Integer personagem;

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

	public Integer getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Integer personagem) {
		this.personagem = personagem;
	}

}
