package com.knight.usuarios.servicos.testes.integracao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.knight.usuarios.servicos.UsuariosServiceInterface;

public class UsuariosServiceIT {
	
	private static String SERVICES_CONTEXT = "http://localhost:8080/soa-cap05/services";
	
	private static String USUARIOS_CONTEXT = SERVICES_CONTEXT + "/usuarios";
	
	private byte[] imagemTeste;
	
	@Before
	public void setup() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(UsuariosServiceIT.class.getResourceAsStream("/imagem.jpg"));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
		this.imagemTeste = byteArrayOutputStream.toByteArray();
	}
	
	@Test
	public void testeRecepcaoImagens() throws Exception {
		ClientResponse<byte[]> clientResponse = new ClientRequest(USUARIOS_CONTEXT
				+ "/{id}").pathParameters(1).accept("image/*").get(byte[].class);
		
		byte[] image = clientResponse.getEntity();
		int status = clientResponse.getStatus();
		
		Assert.assertArrayEquals(imagemTeste, image);
		Assert.assertEquals(200, status);
		
		String descricaoImagem = clientResponse.getHeaders().getFirst(UsuariosServiceInterface.CAMPO_DESCRICAO_IMAGEM);
		
		Assert.assertEquals("skate", descricaoImagem);
	}

}
