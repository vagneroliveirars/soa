package com.knight.estoque.services.autoresservice.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.knight.estoque.domain.v1.Autor;

/**
 * This class implements an Autor service
 * 
 * @author vagner
 *
 */
@WebService(endpointInterface = "com.knight.estoque.services.autoresservice.v1.AutoresService", 
	portName = "AutoresServiceSOAP", 
	serviceName = "AutoresService", 
	targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", 
	wsdlLocation = "WEB-INF/contracts/AutoresService.wsdl")
public class AutoresServiceImpl implements AutoresService {

	/**
	 * Retrives a list of {@link Autor}
	 */
	@WebMethod(action = "AutoresService/ListarAutores")
	@WebResult(name = "autor", targetNamespace = "")
	@RequestWrapper(localName = "listarAutores", 
		targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", 
		className = "com.knight.estoque.services.autoresservice.v1.ListarAutores")
	@ResponseWrapper(localName = "listarAutoresResponse",
		targetNamespace = "http://knight.com/estoque/services/AutoresService/v1",
		className = "com.knight.estoque.services.autoresservice.v1.ListarAutoresResponse")	
	@Override
	public List<Autor> listarAutores() {
		Autor alexandre = new Autor();
		alexandre.setNome("Alexandre");
		
		return new ArrayList<Autor>(Arrays.asList(alexandre));
	}

}
