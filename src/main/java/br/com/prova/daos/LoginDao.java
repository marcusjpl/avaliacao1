package br.com.prova.daos;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.prova.models.Login;

public class LoginDao {

	@PersistenceContext
	private EntityManager manager;

	public Login consultarPorLogin(String login) throws NoResultException {
		String jpql = "select u from Login u where u.login = :login";
		return manager.createQuery(jpql, Login.class).setParameter("login", login).getSingleResult();
	}


}
