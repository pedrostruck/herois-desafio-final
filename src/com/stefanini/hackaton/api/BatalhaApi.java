package com.stefanini.hackaton.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.service.HeroiService;
import com.stefanini.hackaton.service.JogadorService;

@Path("/batalha")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BatalhaApi {

	private final static Random generator = new Random();

	public List<String> battleLog = new ArrayList<>();

	@Inject
	private HeroiService heroiService;

	@Inject
	private JogadorService jogadorService;

	// @Inject
	// TODO injetar o jogador aqui ap�s login
	// private Jogador jogadorLogado;

	@GET
	@Path("/contra-ia/{personagem}")
	public Response batalharContraIA(
					@PathParam("personagem") Integer personagem) {
		// TODO como acessar dados do jogador logado?
		Heroi heroiDoJogador = heroiService.getHeroiById(personagem);
		Heroi oponente = heroiService
						.getHeroiById(getRandomIntegerInRange(1, 249));
		return Response.ok(evaluateBattle(heroiDoJogador, oponente)).build();
	}

	/*
	 * @GET
	 * 
	 * @Path("/contra-jogador/{nickJogador}/{nickOponente}") public Response
	 * batalharContraJogador(
	 * 
	 * @PathParam("nickJogador") String nickJogador,
	 * 
	 * @PathParam("nickOponente") String nickOponente) { // TODO mudar de
	 * integer pra Objeto HEROI Heroi heroiDoJogador =
	 * heroiService.getHeroiById(jogadorService
	 * .getJogadorByNickname(nickJogador).getHeroi()); Heroi oponente =
	 * heroiService.getHeroiById(jogadorService
	 * .getJogadorByNickname(nickOponente).getPersonagem()); return
	 * Response.ok(evaluateBattle(heroiDoJogador, oponente)).build(); }
	 */
	private List<String> evaluateBattle(Heroi heroiDoJogador, Heroi oponente) {
		battleLog.add("*** Batalha entre " + heroiDoJogador.getNome() + " e "
						+ oponente.getNome() + " ***");
		if (isDraw(heroiDoJogador, oponente)) {
			battleLog.add(heroiDoJogador.getNome() + " e " + oponente.getNome()
							+ " EMPATARAM!");
		} else {
			while (isHeroesAlive(heroiDoJogador, oponente)) {
				Integer damageHeroiJogador = getDamage(heroiDoJogador,
								oponente);
				Integer damageOpponent = getDamage(oponente, heroiDoJogador);
				applyDamage(oponente, damageHeroiJogador);
				applyDamage(heroiDoJogador, damageOpponent);
				battleLog.add(heroiDoJogador.getNome() + " golpeia "
								+ damageHeroiJogador + " de dano");
				battleLog.add(oponente.getNome() + " golpeia " + damageOpponent
								+ " de dano");
				battleLog.add("Vidas: " + heroiDoJogador.getNome() + ":["
								+ heroiDoJogador.getVida() + "], "
								+ oponente.getNome() + ":[" + oponente.getVida()
								+ "]");
				battleLog.add("");
			}

			battleLog.add("*** O vitorioso foi "
							+ (heroiDoJogador.getVida() > oponente.getVida()
											? heroiDoJogador.getNome()
											: oponente.getNome())
							+ "! ***");
		}
		return battleLog;
	}

	private void applyDamage(Heroi heroi, Integer damage) {
		heroi.setVida(heroi.getVida() - damage);
	}

	private boolean isHeroesAlive(Heroi heroiDoJogador, Heroi oponente) {
		if (heroiDoJogador.getVida() > 0 && oponente.getVida() > 0) {
			return true;
		}
		return false;
	}

	private boolean isDraw(Heroi heroiDoJogador, Heroi oponente) {
		if ((getDamage(heroiDoJogador, oponente)
						.equals(getDamage(oponente, heroiDoJogador)))) {
			return true;
		}
		return false;
	}

	private Integer getDamage(Heroi attacker, Heroi defender) {
		Integer attack = (attacker.getAtaque() + 100)
						* (attacker.getForca() + 100)
						* (attacker.getVelocidade() + 100);
		Integer defense = (defender.getPoder() + 10)
						* (defender.getDefesa() + 10);
		return attack - defense;
	}

	private static Integer getRandomIntegerInRange(Integer min, Integer max) {
		return generator.nextInt((max - min) + 1) + min;
	}
}
