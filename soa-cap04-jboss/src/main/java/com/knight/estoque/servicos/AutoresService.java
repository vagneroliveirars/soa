package com.knight.estoque.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knight.estoque.modelos.Autor;

@WebService
@Stateless
public class AutoresService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Autor> listaAutores() {
		return this.entityManager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

}
