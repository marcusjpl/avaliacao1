package br.com.prova.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "versao")
@SequenceGenerator(name="sq_versao", sequenceName="sq_versao")
public class Versao {
	
	
	@Id
	@Column(name="cod_versao")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_versao")
	private Long id;

	@NotNull
	@Column(name="nro_versao")
	private String nroVersao;
	
	@Column(name="dat_versao")
	@Temporal(TemporalType.DATE)
	private Calendar dataVersao;
	
	@NotNull
	@Column(name="ind_liberado", length=1)
	private String liberado;
	
	@NotNull
	@Column(name="ind_situacao", length=1)
	private String situacao;
	

	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="versao_item", 
    	joinColumns= {@JoinColumn(name="cod_versao")}, 
    	inverseJoinColumns= {@JoinColumn(name="cod_item")}
    )
	private List<Item> itens = new ArrayList<Item>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNroVersao() {
		return nroVersao;
	}

	public void setNroVersao(String nroVersao) {
		this.nroVersao = nroVersao;
	}

	public Calendar getDataVersao() {
		return dataVersao;
	}

	public void setDataVersao(Calendar dataVersao) {
		this.dataVersao = dataVersao;
	}

	public String getLiberado() {
		return liberado;
	}

	public void setLiberado(String liberado) {
		this.liberado = liberado;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	
}
