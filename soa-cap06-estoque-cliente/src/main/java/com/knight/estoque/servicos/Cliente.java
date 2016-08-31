package com.knight.estoque.servicos;

import java.util.List;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

public class Cliente {

	public static void main(String[] args) {
		AutoresService service = new AutoresServiceService().getAutoresServicePort();
		
		BindingProvider bindingProvider = (BindingProvider) service;
		
		List<Handler> handlerChain = bindingProvider.getBinding().getHandlerChain();
		handlerChain.add(new WSSecurityHandler("alexandre", "alexandre"));
		bindingProvider.getBinding().setHandlerChain(handlerChain);
		
		List<Autor> autores = service.listaAutores();
		
		for (Autor autor : autores) {
			System.out.println(autor.getNome());
		}
	}

}
