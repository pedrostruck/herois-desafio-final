package com.stefanini.hackaton.service;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.hackaton.entities.Heroi;

public class BatalhaService {

	public List<String> battleLog = new ArrayList<>();

	public List<String> evaluateBattle(Heroi heroiDoJogador, Heroi oponente) {
		battleLog.add("*** Batalha entre " + heroiDoJogador.getNome() + " e "
						+ oponente.getNome() + " ***");
		if (isDraw(heroiDoJogador, oponente)) {
			battleLog.add(heroiDoJogador.getNome() + " e " + oponente.getNome()
							+ " EMPATARAM!");
		} else {
			while (isBothHeroesAlive(heroiDoJogador, oponente)) {
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

}
