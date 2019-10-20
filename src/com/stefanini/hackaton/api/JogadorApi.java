package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.dto.LoginDTO;
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
	public Response cadastrarJogador(JogadorDTO jogadorDto)
					throws NegocioException {
		if (jogadorService.isValidRegister(jogadorDto)) {
			jogadorService.createJogador(jogadorDto);
		}
		return Response.ok().build();
	}

	@POST
	@Path("/login")
	public Response login(LoginDTO loginDto) throws NegocioException {
		System.out.println("Dentro de login.");
		JogadorDTO dto = jogadorService.efetuarLogin(loginDto);
		return Response.ok(dto).build();
	}

	@GET
	@Path("/listAll")
	public Response listar() {
		return Response.ok(jogadorService.listar()).build();
	}

}
