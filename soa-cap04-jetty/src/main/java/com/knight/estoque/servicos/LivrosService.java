package com.knight.estoque.servicos;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.SOAPFaultException;

import com.knight.estoque.daos.LivroDAO;
import com.knight.estoque.modelos.Livro;
import com.knight.estoque.modelos.Usuario;

@WebService
public class LivrosService {
	
	@WebResult(name = "livro")
	public List<Livro> listarLivros() {
		LivroDAO livroDAO = obterDAO();
		return livroDAO.listarLivros();
	}
	
	@RequestWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacao", localName = "listarLivrosPaginacao")
	@ResponseWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacaoResponse", localName = "listarLivrosPaginacaoResponse")
	@WebResult(name="livro")
	@WebMethod(operationName="listarLivrosPaginacao")
	public List<Livro> listarLivros(Integer numeroDaPagina, Integer tamanhoDaPagina) {
		LivroDAO livroDAO = obterDAO();
		return livroDAO.listarLivros(numeroDaPagina, tamanhoDaPagina);
	}
	
	public void criarLivro(@WebParam(name = "livro") Livro livro,
			@WebParam(name = "usuario", header = true) Usuario usuario)
			throws UsuarioNaoAutorizadoException, SOAPException {

		if (usuario.getLogin().equals("soa") && usuario.getSenha().equals("soa")) {
			obterDAO().criarLivro(livro);
		} else if (usuario.getNome().equals("faultCode")) {
			SOAPFault soapFault = SOAPFactory.newInstance().createFault("Usuário não autorizado", 
					new QName(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE, "Client.autorizacao"));
			
			soapFault.setFaultActor("http://servicos.estoque.knight.com/ListagemLivros");
			
			throw new SOAPFaultException(soapFault);
		} else {
			throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
		}
	}
	
	private LivroDAO obterDAO() {
		return new LivroDAO();
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/livros", new LivrosService());
		System.out.println("Serviço inicializado!");
	}

}
