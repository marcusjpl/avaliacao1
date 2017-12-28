package br.com.prova.conf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {
	
	@RequestScoped
	@Produces
	public FacesContext getFaceContext() {
		return FacesContext.getCurrentInstance();
	}

}
