package com.knight.estoque.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.soap.SOAPException;

import com.knight.estoque.modelos.Livro;

@WebService
@Stateless
public class LivrosService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@WebResult(name = "livro")
	public List<Livro> listarLivros() {
		return entityManager.createQuery(
				"select distinct l from Livro l left join FETCH l.autores",
				Livro.class).getResultList();
	}
	
	@WebResult(name="livro")
	public List<Livro> listarLivrosPaginacao(Integer numeroDaPagina, Integer tamanhoDaPagina) {
		TypedQuery<Livro> query = this.entityManager.createQuery(
				"select distinct l from Livro l left join FETCH l.autores",
				Livro.class);
		
		query.setFirstResult(numeroDaPagina * tamanhoDaPagina);
		query.setMaxResults(tamanhoDaPagina);
		
		return query.getResultList();
	}
	
	public void criarLivro(@WebParam(name = "livro") Livro livro)
			throws UsuarioNaoAutorizadoException, SOAPException {

		this.entityManager.persist(livro);
	}

}
