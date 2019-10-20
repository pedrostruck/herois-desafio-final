package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.dto.LoginDTO;
import com.stefanini.hackaton.entities.Jogador;

public class JogadorParser extends AbstractParser<JogadorDTO, Jogador> {

	@Override
	public JogadorDTO toDTO(Jogador entity) {
		JogadorDTO dto = new JogadorDTO();
		dto.setNickname(entity.getNickname());
		dto.setPersonagem(entity.getPersonagem());
		return dto;
	}

	@Override
	public Jogador toEntity(JogadorDTO dto) {
		Jogador entity = new Jogador();
		entity.setNickname(dto.getNickname());
		entity.setPersonagem(dto.getPersonagem());
		entity.setSenha(dto.getSenha());
		return entity;
	}

	public LoginDTO toLoginDto(Jogador entity) {
		LoginDTO dto = new LoginDTO();
		dto.setNickname(entity.getNickname());
		dto.setSenha(entity.getSenha());
		return dto;
	}

}
