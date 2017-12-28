package br.com.prova.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import br.com.prova.bean.dto.VersaoDTO;
import br.com.prova.bean.exception.VersaoException;
import br.com.prova.conf.SessionContext;
import br.com.prova.models.Item;
import br.com.prova.models.Versao;

public class VersaoDao {

	@PersistenceContext
	private EntityManager manager;
	
	private static Logger log = Logger.getLogger(VersaoDao.class);

	@Transactional
	public Versao salvar(Versao versao) throws VersaoException {

		try {
			manager.persist(versao);
		} catch (Exception e) {
			throw new VersaoException(e.getMessage());
		}
		return versao;
	}
	
	/**
	 * Para salvar o usuario corrente dessa transacao, foi necessario inluir ele em uma tabela temporaria
	 * Assim a Tabela de auditoria pode ser Popula pela Trigger
	 * @param versao
	 * @return
	 * @throws VersaoException
	 */
	@Transactional
	public Versao atualizar(Versao versao) throws VersaoException {
		
		try {
			manager.createNativeQuery("create global temporary table PROVA.LOGIN_TEMP(login varchar2(60))").executeUpdate();
			manager.createNativeQuery("INSERT INTO prova.LOGIN_TEMP(login) VALUES (:usuario)").
			setParameter("usuario", SessionContext.getInstance().getUsuarioLogado().getLogin()).executeUpdate();
			
			Versao versaoAtualizada = manager.merge(versao);
			
			return versaoAtualizada;
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e);
			throw new VersaoException(e.getMessage());
		}
	}
	
	/**
	 * Para Remover a tabela Temporaria criada na outra transacao
	 * @throws VersaoException
	 */
	@Transactional
	public void removerTabelaTemporaria() throws VersaoException {
		try {
			manager.createNativeQuery("drop table prova.login_temp").executeUpdate();
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e);
		}
	}
	
	@Transactional
	public void excluir(Versao versao) throws VersaoException {
		if (versao.getId() != null) {
			Versao versaoCorrente = findById(versao.getId());
			manager.remove(versaoCorrente);
		} else {
			throw new VersaoException("Versão nao encontrada");
		}
	}

	public Versao findById(Long id) throws NoResultException {
		String jpql = "select v from Versao v where v.id = :id";
		return manager.createQuery(jpql, Versao.class).setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Versao> consultar(VersaoDTO versaoDTO) {


		StringBuffer sb = new StringBuffer("select distinct(v) from Versao v ");
		sb.append(" left join fetch v.itens itens");
		sb.append(" where 1=1 ");

		if (StringUtils.isNotBlank(versaoDTO.getNumero())) {
			sb.append(" and v.nroVersao = :nroVersao");
		}

		if (versaoDTO.getCodigo() != null) {
			sb.append(" and v.id = :id");
		}

		if (StringUtils.isNotBlank(versaoDTO.getTituloItem())) {
			sb.append(" and itens.titulo = :titulo");
		}

		Query query = manager.createQuery(sb.toString());

		if (StringUtils.isNotBlank(versaoDTO.getNumero())) {
			query.setParameter("nroVersao", versaoDTO.getNumero());
		}
		if (versaoDTO.getCodigo() != null) {
			query.setParameter("id", versaoDTO.getCodigo());
		}
		if (StringUtils.isNotBlank(versaoDTO.getTituloItem())) {
			sb.append(" and itens.titulo = :titulo");
			query.setParameter("titulo", versaoDTO.getTituloItem());
		}

		return query.getResultList();
	}

	@Transactional
	public Versao removerItem(Versao versao, Item item) {
		versao.getItens().remove(item);
		manager.merge(versao);
		
		return findById(versao.getId());
		
	}


}
