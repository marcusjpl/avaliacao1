package br.com.prova.service;

import java.util.List;

import javax.inject.Inject;

import br.com.prova.bean.dto.VersaoDTO;
import br.com.prova.bean.exception.VersaoException;
import br.com.prova.daos.VersaoDao;
import br.com.prova.models.Versao;

public class VersaoService {
	
	@Inject
	private VersaoDao versaoDao;
	
	public Versao salvar(Versao versao) throws VersaoException {
		
		versao.setSituacao("A");
		versao.setLiberado("N");
		
		return versaoDao.salvar(versao);
	}

	public Versao alterar(Versao versao) throws VersaoException {
		Versao versaoAlterada = versaoDao.atualizar(versao);
		versaoDao.removerTabelaTemporaria();
		return versaoAlterada;
	}
	
	public void exluir(Versao versao) throws VersaoException {
		versaoDao.excluir(versao);
	}

	public List<Versao> consultar(VersaoDTO versaoDTO) {
		return versaoDao.consultar(versaoDTO);
	}



}
