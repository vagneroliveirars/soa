package com.knight.usuarios.servicos.testes.integracao;

import org.junit.Before;
import org.junit.Test;

public class UsuariosServiceIT {
	
	private static String SERVICES_CONTEXT = "http://localhost:8080/soa-cap05/services";
	
	private static String USUARIOS_CONTEXT = SERVICES_CONTEXT + "/usuarios";
	
	private byte[] imagem;
	
	@Before
	public void setup() {
		// Initial setup
	}
	
	@Test
	public void testeRecepcaoImagens() {
		
	}

}
