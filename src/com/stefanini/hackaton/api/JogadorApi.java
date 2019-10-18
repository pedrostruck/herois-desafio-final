package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

	@POST
	@Path("/cadastrar")
	public Response cadastrarJogador(Jogador jogador) throws NegocioException {
		if (jogadorService.isIncomplete(jogador)) {
			throw new NegocioException(
							"Existem campos vazios! Preencha o formulário de jogador por completo!");
		}

		if (jogadorService.isDuplicateNickname(jogador.getNickname())) {
			throw new NegocioException(
							"Já existe jogador com este nickname! Escolha outro.");
		}

		if (jogadorService.isInvalidPassword(jogador.getSenha())) {
			throw new NegocioException(
							"Senha com tamanho inválido! A senha deve ter no mínimo 6 e no máximo 8 caracteres.");
		}
		jogadorService.createJogador(jogador);
		return Response.ok().build();
	}

	@POST
	@Path("/login")
	public Response login(LoginDto loginDto) throws NegocioException {
		if (jogadorService.efetuarLogin(loginDto)) {
			// TODO redirecionamento de p�ginas e lan�amento de exce��o
		}
		return Response.ok().build();
	}

	@GET
	@Path("/listAll")
	public Response listar() {
		return Response.ok(jogadorService.listar()).build();
	}

}
