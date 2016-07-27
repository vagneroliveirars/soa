package com.knight.estoque.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.SOAPFaultException;

import com.knight.estoque.modelos.Livro;
import com.knight.estoque.modelos.Usuario;

@WebService
@Stateless
public class LivrosService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@WebResult(name = "livro")
	public List<Livro> listarLivros() {
		return entityManager.createQuery(
				"select distinct 1 from Livro 1 left join FETCH 1.autores",
				Livro.class).getResultList();
	}
	
	@RequestWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacao", localName = "listarLivrosPaginacao")
	@ResponseWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacaoResponse", localName = "listarLivrosPaginacaoResponse")
	@WebResult(name="livro")
	@WebMethod(operationName="listarLivrosPaginacao")
	public List<Livro> listarLivros(Integer numeroDaPagina, Integer tamanhoDaPagina) {
		TypedQuery<Livro> query = this.entityManager.createQuery(
				"select distinct l from Livro l left join FETCH l.autores",
				Livro.class);
		
		query.setFirstResult(numeroDaPagina * tamanhoDaPagina);
		query.setMaxResults(tamanhoDaPagina);
		
		return query.getResultList();
	}
	
	public void criarLivro(@WebParam(name = "livro") Livro livro,
			@WebParam(name = "usuario", header = true) Usuario usuario)
			throws UsuarioNaoAutorizadoException, SOAPException {

		if (usuario.getLogin().equals("soa") && usuario.getSenha().equals("soa")) {
			this.entityManager.persist(livro);
		} else if (usuario.getNome().equals("faultCode")) {
			SOAPFault soapFault = SOAPFactory.newInstance().createFault("Usuário não autorizado", 
					new QName(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE, "Client.autorizacao"));
			
			soapFault.setFaultActor("http://servicos.estoque.knight.com/ListagemLivros");
			
			throw new SOAPFaultException(soapFault);
		} else {
			throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
		}
	}

}
