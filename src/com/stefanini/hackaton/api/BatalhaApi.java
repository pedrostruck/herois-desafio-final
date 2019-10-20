package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.service.JogadorService;

@Path("/batalha")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BatalhaApi {

//	private final static Random generator = new Random();

	@Inject
	private JogadorService jogadorService;

	@POST
	@Path("/getOponentes")
	public Response listar(JogadorDTO jogadorLogado) {
		return Response.ok(jogadorService.getListaOponentes(jogadorLogado))
						.build();
	}

//	@GET
//	@Path("/contra-ia/{idHeroiJogador}")
//	public Response batalharContraIA(
//					@PathParam("idHeroiJogador") Integer idHeroiJogador) {
//		Heroi heroiDoJogador = heroiService.getHeroiById(idHeroiJogador);
//		Heroi oponente = heroiService
//						.getHeroiById(getRandomIntegerInRange(1, 249));
//		return Response.ok(
//						batalhaService.evaluateBattle(heroiDoJogador, oponente))
//						.build();
//	}

	/*
	 * 
	 * @Path("/contra-jogador/{nickJogador}/{nickOponente}") public Response
	 * batalharContraJogador(
	 * 
	 * @PathParam("nickJogador") String nickJogador,
	 * 
	 * @PathParam("nickOponente") String nickOponente) { Heroi heroiDoJogador =
	 * heroiService.getHeroiById(jogadorService
	 * .getJogadorByNickname(nickJogador).getHeroi()); Heroi oponente =
	 * heroiService.getHeroiById(jogadorService
	 * .getJogadorByNickname(nickOponente).getPersonagem()); return
	 * Response.ok(evaluateBattle(heroiDoJogador, oponente)).build(); }
	 */

//	private static Integer getRandomIntegerInRange(Integer min, Integer max) {
//		return generator.nextInt((max - min) + 1) + min;
//	}
}
