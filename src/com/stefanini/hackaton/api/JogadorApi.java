package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.dto.LoginDto;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.rest.exceptions.NegocioException;
import com.stefanini.hackaton.service.JogadorService;

@Path("/jogador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JogadorApi {

	@Inject
	private JogadorService jogadorService;

	@Path("/cadastrar")
	@POST
	public Response cadastrarJogador(Jogador jogador) throws NegocioException {
		// TODO pegar dados do front-end do formul�rio de cadastro do jogador
		// TODO ideia: m�todo �nico no estilo "validateCadastro"?
		if (jogadorService.isIncomplete(jogador)) {
			throw new NegocioException(
					"Existem campos vazios! Preencha o formul�rio de jogador por completo!");
		}

		if (jogadorService.isDuplicateNickname(jogador.getNickname())) {
			throw new NegocioException(
					"J� existe jogador com este nickname! Escolha outro.");
		}

		if (jogadorService.isInvalidPassword(jogador.getSenha())) {
			throw new NegocioException(
					"Senha com tamanho inv�lido! A senha deve ter no m�nimo 6 e no m�ximo 8 caracteres.");
		}

		jogadorService.createJogador(jogador);
		return Response.ok().build();
	}

	@Path("/login")
	@POST
	public Response login(LoginDto loginDto) {
		if (jogadorService.efetuarLogin(loginDto)) {
			// TODO redirecionamento de p�ginas e lan�amento de exce��o
		}
		return Response.ok().build();
	}

}
