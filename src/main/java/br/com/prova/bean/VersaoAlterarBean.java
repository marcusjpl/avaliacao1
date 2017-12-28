package br.com.prova.bean;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.prova.bean.exception.VersaoException;
import br.com.prova.models.Item;
import br.com.prova.models.Versao;
import br.com.prova.service.VersaoService;


@ManagedBean
@SessionScoped
public class VersaoAlterarBean {
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private VersaoService versaoService;
	
	private Versao versao = new Versao();
	
	private String itemTitulo;
	private String itemDescricao;
	
	public String editar(Versao versao) {
		this.versao = versao;
		return "/versao/alterar?faces-redirect=true";
	}
	
	public String alterar() {
		
		try {
			setVersao(versaoService.alterar(versao));
			
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage("Versão alterada com sucesso!"));
		} catch (VersaoException ve) {
			facesContext.addMessage(null, new FacesMessage(ve.getMessage()));
		}
		
		return "";
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
	
	public String excluirItem(Item item) {
		
		versao.getItens().remove(item);
		
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
