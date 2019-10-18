package com.stefanini.hackaton.service;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.HeroiDto;
import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.parsers.HeroiParserDTO;
import com.stefanini.hackaton.persistence.HeroiDAO;

public class HeroiService {

	@Inject
	HeroiParserDTO parser;

	@Inject
	HeroiDAO heroiDao;
	
	public List<HeroiDto> listar() {
		return parser.toDTO(heroiDao.list());
	}

	public Heroi getHeroiById(Integer id) {
		return heroiDao.findById(id);
	}

}
