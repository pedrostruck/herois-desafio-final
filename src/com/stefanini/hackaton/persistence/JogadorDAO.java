package com.stefanini.hackaton.persistence;

import java.util.List;

import javax.persistence.Query;

import com.stefanini.hackaton.entities.Jogador;

public class JogadorDAO extends GenericDAO<String, Jogador> {

	@SuppressWarnings("unchecked")
	public List<Jogador> getOponentes(String nicknameLogado) {
		Query query = getEntityManager().createQuery(
						"SELECT j FROM Jogador j WHERE j.nickname != :nickname");
		query.setParameter("nickname", nicknameLogado);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<Jogador> getByNickname(String nicknameLogado) {
        Query query = getEntityManager().createQuery("SELECT j FROM Jogador j WHERE j.nickname = :nickname");
        query.setParameter("nickname", nicknameLogado);
        return query.getResultList();
    }

}
