package com.stefanini.hackaton.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.service.HeroiService;

@Path("/batalha")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BatalhaApi {

	private final static Random generator = new Random();

	private List<List<String>> finishedBattles = new ArrayList<>();
	private List<String> currentBattleLog = new ArrayList<>();

	@Inject
	private HeroiService heroiService;

	@Inject
	// TODO injetar o jogador aqui após login
	private Jogador jogadorLogado;

	@Path("/contra-ia")
	@GET
	public Response batalharContraIA() {
		// TODO como acessar dados do jogador logado?
		Heroi heroiDoJogador = heroiService
				.getHeroiById(jogadorLogado.getPersonagem());
		// TODO parametrizar o minId e maxId
		Heroi oponente = heroiService
				.getHeroiById(getRandomIntegerInRange(1, 249));

		// TODO tratar caso de batalhas entre heróis com id igual
		Heroi vitorioso = evaluateBattle(heroiDoJogador, oponente);
		System.out.println(vitorioso == null ? currentBattleLog.add("Empate")
				: currentBattleLog
						.add("O vitorioso foi " + vitorioso.getNome() + "!"));
		finishedBattles.add(currentBattleLog);
		currentBattleLog.clear();
		return Response.ok().build();
	}

	private Heroi evaluateBattle(Heroi heroiDoJogador, Heroi oponente) {
		currentBattleLog.add("Batalha entre " + heroiDoJogador.getNome() + " e "
				+ oponente.getNome() + ".");
		if (isDraw(heroiDoJogador, oponente)) {
			currentBattleLog.add(heroiDoJogador.getNome() + " e "
					+ oponente.getNome() + " empataram!");
		} else {
			while (isHeroesAlive(heroiDoJogador, oponente)) {
				Integer damageHeroiJogador = getDamage(heroiDoJogador,
						oponente);
				Integer damageOpponent = getDamage(oponente, heroiDoJogador);
				applyDamage(oponente, damageHeroiJogador);
				applyDamage(heroiDoJogador, damageOpponent);
				currentBattleLog.add(heroiDoJogador.getNome()
						+ " dá um golpe de " + damageHeroiJogador
						+ " de dano no " + oponente.getNome());
				currentBattleLog.add(
						oponente.getNome() + " dá um golpe de " + damageOpponent
								+ " de dano no " + heroiDoJogador.getNome());
				currentBattleLog.add("Vida: ");
				currentBattleLog.add(heroiDoJogador.getNome() + ":["
						+ heroiDoJogador.getVida() + "], " + oponente.getNome()
						+ ":[" + oponente.getVida() + "]\n");
			}
			return (heroiDoJogador.getVida() > oponente.getVida())
					? heroiDoJogador
					: oponente;
		}
		return null;
	}

	private void applyDamage(Heroi heroi, Integer damage) {
		heroi.setVida(heroi.getVida() - damage);
	}

	@Path("/contra-jogador")
	@GET
	public Response batalharContraJogador() {
		return Response.ok().build();
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
				* (defender.getDefesa() * 10);
		return attack - defense;
	}

	private static Integer getRandomIntegerInRange(Integer min, Integer max) {
		return generator.nextInt((max - min) + 1) + min;
	}
}
