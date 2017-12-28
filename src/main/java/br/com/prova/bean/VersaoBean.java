package br.com.prova.bean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.prova.bean.exception.VersaoException;
import br.com.prova.models.Item;
import br.com.prova.models.Versao;
import br.com.prova.service.VersaoService;


@ManagedBean
@ViewScoped
public class VersaoBean {
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private VersaoService versaoService;
	
	private Versao versao = new Versao();
	
	private String itemTitulo;
	private String itemDescricao;
	
	
	public String salvar() {
		
		try {
			setVersao(versaoService.salvar(versao));
			
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage("Versão salva com sucesso!"));
		} catch (VersaoException ve) {
			versao.setItens(new ArrayList<Item>());
			facesContext.addMessage(null, new FacesMessage(ve.getMessage()));
		}
		
		return "";
//		return "incluir?faces-redirect=true";
	}
	
	public String inserirItem() {
		Item itemCorrente = new Item();
		itemCorrente.setTitulo(itemTitulo);
		itemCorrente.setDescricao(itemDescricao);
		versao.getItens().add(itemCorrente);
		this.itemTitulo = "";
		this.itemDescricao = "";
		return "";
	}
	
	public String novo() {
		this.versao = new Versao();
		this.itemTitulo = "";
		this.itemDescricao = "";
		return "";
	}
	
	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public String getItemTitulo() {
		return itemTitulo;
	}

	public void setItemTitulo(String itemTitulo) {
		this.itemTitulo = itemTitulo;
	}

	public String getItemDescricao() {
		return itemDescricao;
	}

	public void setItemDescricao(String itemDescricao) {
		this.itemDescricao = itemDescricao;
	}


}
