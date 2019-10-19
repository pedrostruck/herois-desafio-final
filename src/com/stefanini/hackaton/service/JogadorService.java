package com.stefanini.hackaton.service;

import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.stefanini.hackaton.dto.JogadorDto;
import com.stefanini.hackaton.dto.LoginDto;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.persistence.JogadorDAO;
import com.stefanini.hackaton.rest.exceptions.NegocioException;

//TODO adicionar tags Transactional nos métodos

public class JogadorService {

	@Inject
	private JogadorParserDTO parser;

	@Inject
	private JogadorDAO dao;

	public List<JogadorDto> listar() {
		return parser.toDTO(dao.list());
	}

	public boolean efetuarLogin(LoginDto loginDto) throws NegocioException {
		Jogador jogador = dao.findById(loginDto.getNickname());
		if (jogador != null) {
			byte[] decodedPasswordBytes = Base64.getDecoder()
							.decode(loginDto.getSenha());
			String decodedPassword = new String(decodedPasswordBytes);
//			String encoded = Base64.getEncoder()
//							.encodeToString(original.getBytes());
			if (jogador.getSenha() == decodedPassword) {
				// TODO logar jogador e redirecionar pra home
				return true;
			}
		}
		throw new NegocioException(
						"Ops! Seu nickname ou senha estão incorretos!");
	}

	public Jogador getJogadorByNickname(String nickname) {
		return dao.findById(nickname);
	}

	@Transactional
	public void createJogador(Jogador jogador) {
		dao.insert(jogador);
	}

	public boolean isIncomplete(Jogador jogador) {
		if (jogador.getNickname() == null || jogador.getSenha() == null
						|| jogador.getHeroi() == null) {
			return true;
		}
		return false;
	}

	public boolean isDuplicateNickname(String nickname) {
		if (dao.findById(nickname) != null) {
			return true;
		}
		return false;
	}

	public boolean isInvalidPassword(String senha) {
		if (senha.length() >= 6 && senha.length() <= 8) {
			return false;
		}
		return true;
	}

}
