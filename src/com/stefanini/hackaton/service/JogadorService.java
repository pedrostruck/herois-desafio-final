package com.stefanini.hackaton.service;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.JogadorDto;
import com.stefanini.hackaton.dto.LoginDto;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.persistence.JogadorDAO;

//TODO adicionar tags Transactional nos métodos

public class JogadorService {

	@Inject
	private JogadorParserDTO parser;

	@Inject
	private JogadorDAO jogadorDao;

	public List<JogadorDto> listar() {
		return parser.toDTO(jogadorDao.list());
	}

	public boolean efetuarLogin(LoginDto loginDto) {
		Jogador j = jogadorDao.findById(loginDto.getNickname());
		if (j != null) {
			// TODO jogador existe. verificar senha correta
			if (j.getSenha() == loginDto.getSenha()) {
				// TODO logar jogador e redirecionar pra home
			}
		}
		// TODO lan�ar exce��o e voltar pra tela de login. nickname e/ou senha
		// incorreta.
		System.out.println("Ops! Seu nickname ou senha est�o incorretos!");

		return true;
	}

	public void createJogador(Jogador jogador) {
		jogadorDao.insert(jogador);
	}

	public boolean isIncomplete(Jogador jogador) {
		if (jogador.getNickname() == null || jogador.getSenha() == null
				|| jogador.getPersonagem() == null) {
			return true;
		}
		return false;
	}

	public boolean isDuplicateNickname(String nickname) {
		if (jogadorDao.findById(nickname) != null) {
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
