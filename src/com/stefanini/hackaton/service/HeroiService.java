package com.stefanini.hackaton.service;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.HeroiDTO;
import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.parsers.HeroiParser;
import com.stefanini.hackaton.persistence.HeroiDAO;

public class HeroiService {

	@Inject
	HeroiParser parser;

	@Inject
	HeroiDAO heroiDao;
	
	public List<HeroiDTO> listar() {
		return parser.toDTO(heroiDao.list());
	}

	public Heroi getHeroiById(Integer id) {
		return heroiDao.findById(id);
	}

}
