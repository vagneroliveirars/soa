package com.knight.estoque.services.autoresservice.v1;

import java.util.List;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.AddressingFeature;

import com.knight.estoque.domain.v1.Autor;

/**
 * This class implement a client for an author service
 * 
 * @author vagner
 *
 */
public class Cliente {
	
	private static boolean ehAssincrono = true;
	private static String enderecoResposta = "http://localhost:8080/soa-cap07-wsaddressing-servidor/AutoresServiceCallback";

	public static void main(String[] args) {
		AutoresService service = null;
		
		if (ehAssincrono) {
			// Asynchronous request
			service = new AutoresService_Service().getAutoresServiceSOAP(new AddressingFeature());
			List<Handler> handlerChain = ((BindingProvider) service).getBinding().getHandlerChain();

			// Informs the address for response by the handler instance
			handlerChain.add(new AddressingHandler(enderecoResposta));
			((BindingProvider) service).getBinding().setHandlerChain(handlerChain);
		} else {
			// Synchronous request
			service = new AutoresService_Service().getAutoresServiceSOAP();
		}

		// Gets a list of authors
		List<Autor> autores = service.solicitarRelacaoDeAutores(null);
		for (Autor autor : autores) {
			System.out.println(autor.getNome());
		}
	}

}
