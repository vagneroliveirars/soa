package com.knight.estoque.servicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://servicos.estoque.knight.com/excecoes/", name = "UsuarioNaoAutorizado")
public class UsuarioNaoAutorizadoException extends Exception {

	private static final long serialVersionUID = -2150236222424028472L;
	
	public UsuarioNaoAutorizadoException() {

	}

	public UsuarioNaoAutorizadoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioNaoAutorizadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNaoAutorizadoException(String message) {
		super(message);
	}

	public UsuarioNaoAutorizadoException(Throwable cause) {
		super(cause);
	}
	
	public UsuarioFaultInfo getFaultInfo() {
		return new UsuarioFaultInfo(getMessage());
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class UsuarioFaultInfo {
		
		@XmlAttribute
		private String mensagem;
		
		public UsuarioFaultInfo() {

		}

		public UsuarioFaultInfo(String mensagem) {
			this.mensagem = mensagem;
		}
		
	}
	
}
