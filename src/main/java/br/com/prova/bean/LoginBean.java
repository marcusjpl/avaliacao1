package br.com.prova.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import br.com.prova.conf.SessionContext;
import br.com.prova.models.Login;
import br.com.prova.service.LoginService;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	@Inject
	private LoginService loginService;
	
	@Inject
	private FacesContext facesContext;
	
	private static Logger log = Logger.getLogger(LoginBean.class);

	private String login;

	public String logar() {

		if (StringUtils.isNotBlank(login)) {
			
			try {
				Login usuarioLogado = loginService.consultar(login);
				SessionContext.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				return "/versao/inicial?faces-redirect=true";
			} catch (NoResultException nre) {
				facesContext.addMessage(null, new FacesMessage("Usuário não encontrado"));
				return "";
			} catch (Exception e) {
				log.error(e.getMessage());
				return "";
			}
		}

		return "";
	}
	
	
	public String logout() {
        SessionContext.getInstance().encerrarSessao();
        return "/login.xhtml?faces-redirect=true";
     }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
