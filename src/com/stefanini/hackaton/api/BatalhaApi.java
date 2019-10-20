package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.service.BatalhaService;
import com.stefanini.hackaton.service.JogadorService;

@Path("/batalha")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BatalhaApi {

	@Inject
	private BatalhaService batalhaService;

	@Inject
	private JogadorService jogadorService;

	@POST
	@Path("/getOponentes")
	public Response listar(JogadorDTO jogadorLogado) {
		return Response.ok(jogadorService.getListaOponentes(jogadorLogado))
						.build();
	}

	@POST
	@Path("/batalhar/{nickJogador}/{nickOponente}")
	public Response batalharContraJogador(
					@PathParam("nickJogador") String nickJogador,
					@PathParam("nickOponente") String nickOponente) {
		if (nickOponente.equals("null")) {
			nickOponente = null;
		}
		return Response.ok(batalhaService.evaluateBattle(nickJogador,
						nickOponente)).build();
	}

}
