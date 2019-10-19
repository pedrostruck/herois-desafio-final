package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.JogadorDto;
import com.stefanini.hackaton.dto.LoginDto;
import com.stefanini.hackaton.entities.Jogador;

public class JogadorParserDTO extends AbstractParser<JogadorDto, Jogador> {

	@Override
	public JogadorDto toDTO(Jogador entity) {
		JogadorDto dto = new JogadorDto();
		dto.setNickname(entity.getNickname());
		dto.setHeroi(entity.getHeroi());
		return dto;
	}

	@Override
	public Jogador toEntity(JogadorDto dto) {
		Jogador entity = new Jogador();
		entity.setNickname(dto.getNickname());
		entity.setHeroi(dto.getHeroi());
		return entity;
	}

	public LoginDto toLoginDto(Jogador entity) {
		LoginDto dto = new LoginDto();
		dto.setNickname(entity.getNickname());
		dto.setSenha(entity.getSenha());
		return dto;
	}

}
