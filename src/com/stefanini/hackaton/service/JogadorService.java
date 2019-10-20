package com.stefanini.hackaton.service;

import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.dto.LoginDTO;
import com.stefanini.hackaton.parsers.JogadorParser;
import com.stefanini.hackaton.persistence.JogadorDAO;
import com.stefanini.hackaton.rest.exceptions.NegocioException;

public class JogadorService {

	@Inject
	private JogadorParser parser;

	@Inject
	private JogadorDAO dao;

	@Transactional
	public void createJogador(JogadorDTO jogadorDto) {
		dao.insert(parser.toEntity(jogadorDto));
	}

	public List<JogadorDTO> listar() {
		return parser.toDTO(dao.list());
	}

	public List<JogadorDTO> getListaOponentes(JogadorDTO jogadorLogado) {
		return parser.toDTO(dao.getOponentes(jogadorLogado.getNickname()));
	}

	public JogadorDTO getJogadorByNickname(String nickname) {
		return parser.toDTO(dao.findById(nickname));
	}

	public JogadorDTO efetuarLogin(LoginDTO loginDto) throws NegocioException {
		LoginDTO loginDtoFetched = parser
						.toLoginDto(dao.findById(loginDto.getNickname()));
		if (loginDtoFetched != null) {
			if (loginDtoFetched.getSenha().equals(loginDto.getSenha())) {
				JogadorDTO dto = parser
								.toDTO(dao.findById(loginDto.getNickname()));
				dto.setSenha(null);
				return dto;
			}
		}
		throw new NegocioException(
						"Ops! Seu nickname ou senha estão incorretos!");
	}

	public boolean isValidRegister(JogadorDTO jogadorDto)
					throws NegocioException {
		if (isIncomplete(jogadorDto)) {
			throw new NegocioException(
							"Existem campos vazios! Preencha o formulário de jogador por completo!");
		}

		if (isDuplicateNickname(jogadorDto.getNickname())) {
			throw new NegocioException(
							"Já existe jogador com este nickname! Escolha outro.");
		}

		if (isPasswordOutOfSizeRange(jogadorDto.getSenha())) {
			throw new NegocioException(
							"Senha com tamanho inválido! A senha deve ter no mínimo 6 e no máximo 8 caracteres.");
		}
		return true;
	}

	public boolean isIncomplete(JogadorDTO jogadorDto) {
		if (jogadorDto.getNickname() == null || jogadorDto.getSenha() == null
						|| jogadorDto.getPersonagem() == null) {
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

	public boolean isPasswordOutOfSizeRange(String encodedPassword) {
		String decodedPassword = decodePassword(encodedPassword);
		if (decodedPassword.length() >= 6 && decodedPassword.length() <= 8) {
			return false;
		}
		return true;
	}

	private String decodePassword(String senha) {
		byte[] decodedPasswordBytes = Base64.getDecoder().decode(senha);
		return new String(decodedPasswordBytes);
	}

}
