package br.com.prova.service;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.com.prova.daos.LoginDao;
import br.com.prova.models.Login;

public class LoginService {
	
	@Inject
	private LoginDao loginDao;


	public Login consultar(String login) throws NoResultException  {
		return loginDao.consultarPorLogin(login);
	}


}
