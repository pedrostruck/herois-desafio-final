package com.stefanini.hackaton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.persistence.HeroiDAO;
import com.stefanini.hackaton.persistence.JogadorDAO;

public class BatalhaService {

	private final static Random generator = new Random();

	@Inject
	private JogadorDAO jogadorDao;

	@Inject
	private HeroiDAO heroiDao;

	public List<String> battleLog = new ArrayList<>();

	public List<String> evaluateBattle(String nickJogador,
					String nickOponente) {
		Jogador jogLogado = jogadorDao.getByNickname(nickJogador).get(0);
		Jogador jogOponente = null;
		if (nickOponente == null) {
			jogOponente = new Jogador(
							"Máquina",
								heroiDao.findById(getRandomIntegerInRange(1,
												249)));
		} else {
			jogOponente = jogadorDao.getByNickname(nickOponente).get(0);
		}
		Heroi heroJog = jogLogado.getPersonagem();
		Heroi heroOp = jogOponente.getPersonagem();

		if (isDraw(heroJog, heroOp)) {
			battleLog.add("EMPATE!");
		} else {
			int i = 0;
			while (isBothHeroesAlive(heroJog, heroOp)) {
				battleLog.add("|--- Turno " + ++i + " ---|");
				Integer damageJog = getDamage(heroJog, heroOp);
				Integer damageOpponent = getDamage(heroOp, heroJog);
				applyDamage(heroOp, damageJog);
				applyDamage(heroJog, damageOpponent);
				battleLog.add(heroJog.getNome() + " golpeia " + damageJog
								+ " de dano");
				battleLog.add(heroOp.getNome() + " golpeia " + damageOpponent
								+ " de dano");
				battleLog.add("Vidas: " + heroJog.getNome() + ":["
								+ heroJog.getVida() + "], " + heroOp.getNome()
								+ ":[" + heroOp.getVida() + "]");
			}
			battleLog.add("*** O vitorioso foi "
							+ (heroJog.getVida() > heroOp.getVida()
											? "(" + jogLogado.getNickname()
															+ ") "
															+ heroJog.getNome()
											: "(" + jogOponente.getNickname()
															+ ") "
															+ heroOp.getNome())
							+ "! ***");
		}
		return battleLog;
	}

	private void applyDamage(Heroi heroi, Integer damage) {
		heroi.setVida(heroi.getVida() - damage);
	}

	private boolean isBothHeroesAlive(Heroi heroiDoJogador, Heroi oponente) {
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
