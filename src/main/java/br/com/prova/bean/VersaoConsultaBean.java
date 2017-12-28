package br.com.prova.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.prova.bean.dto.VersaoDTO;
import br.com.prova.models.Item;
import br.com.prova.models.Versao;
import br.com.prova.service.VersaoService;


@ManagedBean
@ViewScoped
public class VersaoConsultaBean {
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private VersaoService versaoService;
	
	private Versao versaoSearch = new Versao();
	
	private List<Versao> lista = new ArrayList<Versao>();
	
	private VersaoDTO versaoDTO = new VersaoDTO();
	
	
	public String consultar() {
		setLista(versaoService.consultar(versaoDTO));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		if (getVersaoSearch() != null) {
			facesContext.addMessage(null, new FacesMessage("Registro encontrado"));
		} else {
			facesContext.addMessage(null, new FacesMessage("Nenhum registro encontrado"));
			versaoSearch.setItens(new ArrayList<Item>());;
		}
		return "";
	}
	
	public String limpar() {
		setVersaoDTO(new VersaoDTO());
		lista = new ArrayList<Versao>();
		return "";
	}
	
	public Versao getVersaoSearch() {
		return versaoSearch;
	}

	public void setVersaoSearch(Versao versaoSearch) {
		this.versaoSearch = versaoSearch;
	}

	public VersaoDTO getVersaoDTO() {
		return versaoDTO;
	}

	public void setVersaoDTO(VersaoDTO versaoDTO) {
		this.versaoDTO = versaoDTO;
	}

	public List<Versao> getLista() {
		return lista;
	}

	public void setLista(List<Versao> lista) {
		this.lista = lista;
	}

}
