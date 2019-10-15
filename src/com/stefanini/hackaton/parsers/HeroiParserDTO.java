package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.HeroiDto;
import com.stefanini.hackaton.entities.Heroi;

public class HeroiParserDTO extends AbstractParser<HeroiDto, Heroi> {

	@Override
	public HeroiDto toDTO(Heroi entity) {
		HeroiDto dto = new HeroiDto();
		dto.setAtaque(null);
		dto.setDefesa(null);
		dto.setForca(null);
		dto.setId(null);
		dto.setInteligencia(null);
		dto.setNome(entity.getNome());
		dto.setPoder(null);
		dto.setVelocidade(null);
		dto.setVida(null);
		return dto;
	}

	@Override
	public Heroi toEntity(HeroiDto dto) {
		Heroi entity = new Heroi();
		entity.setAtaque(null);
		entity.setDefesa(null);
		entity.setForca(null);
		entity.setId(null);
		entity.setInteligencia(null);
		entity.setNome(dto.getNome());
		entity.setPoder(null);
		entity.setVelocidade(null);
		entity.setVida(null);
		return entity;
	}

}
