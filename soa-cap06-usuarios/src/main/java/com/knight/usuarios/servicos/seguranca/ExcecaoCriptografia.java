package com.knight.usuarios.servicos.seguranca;

/**
 * Exception that encapsulates the particular JCE (Java Cryptography Extension)
 * exceptions
 * 
 * @author vagner
 * 
 */
public class ExcecaoCriptografia extends Exception {

	private static final long serialVersionUID = -3761691435719763702L;

	public ExcecaoCriptografia() {
		super();
	}

	public ExcecaoCriptografia(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcecaoCriptografia(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcecaoCriptografia(String message) {
		super(message);
	}

	public ExcecaoCriptografia(Throwable cause) {
		super(cause);
	}

}
