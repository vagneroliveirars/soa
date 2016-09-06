package com.knight.estoque.services.autoresservice.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.Addressing;

import com.knight.estoque.domain.v1.Autor;

/**
 * This class implements an author service
 * 
 * @author vagner
 *
 */
@WebService(endpointInterface = "com.knight.estoque.services.autoresservice.v1.AutoresService", portName = "AutoresServiceSOAP", serviceName = "AutoresService", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", wsdlLocation = "WEB-INF/contracts/AutoresService.wsdl")
public class AutoresServiceImpl implements AutoresService {

	/**
	 * Returns a list of authors
	 */
	@Override
	@WebMethod(action = "AutoresService/ListarAutores")
	@WebResult(name = "autor", targetNamespace = "")
	@RequestWrapper(localName = "listarAutores", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.ListarAutores")
	@ResponseWrapper(localName = "listarAutoresResponse", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.ListarAutoresResponse")
	public List<Autor> listarAutores() {
		Autor alexandre = new Autor();
		alexandre.setNome("Alexandre Saudate");

		Autor adriano = new Autor();
		adriano.setNome("Adriano Almeida");

		Autor paulo = new Autor();
		paulo.setNome("Paulo Silveira");

		return new ArrayList<Autor>(Arrays.asList(alexandre, adriano, paulo));
	}

	/**
	 * Returns a list of authors. This service can give a synchronous or
	 * asynchronous response (using WS-Addressing).
	 */
	@Override
	@WebMethod(action = "AutoresService/SolicitarRelacaoDeAutores")
	@WebResult(name = "autor", targetNamespace = "")
	@RequestWrapper(localName = "solicitarRelacaoDeAutores", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.SolicitarRelacaoDeAutores")
	@ResponseWrapper(localName = "solicitarRelacaoDeAutoresResponse", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.SolicitarRelacaoDeAutoresResponse")
	@Action(input = " ", output = "AutoresService/solicitarRelacaoDeAutoresResponse")
	@Addressing(enabled = true, required = true)
	public List<Autor> solicitarRelacaoDeAutores(@WebParam(name = "desde", targetNamespace = "") XMLGregorianCalendar desde) {
		Autor alexandre = new Autor();
		alexandre.setNome("Alexandre Saudate");

		Autor adriano = new Autor();
		adriano.setNome("Adriano Almeida");

		Autor paulo = new Autor();
		paulo.setNome("Paulo Silveira");

		return new ArrayList<Autor>(Arrays.asList(alexandre, adriano, paulo));
	}

}
