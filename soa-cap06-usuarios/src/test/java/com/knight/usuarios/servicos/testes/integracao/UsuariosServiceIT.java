package com.knight.usuarios.servicos.testes.integracao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.core.MediaType;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.util.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.knight.usuarios.modelos.Usuario;
import com.knight.usuarios.modelos.Usuarios;
import com.knight.usuarios.servicos.UsuariosServiceInterface;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UsuariosServiceIT {
	
	private static String SERVICES_CONTEXT = "https://localhost:8443/soa-cap06-usuarios/services";
	
	private static String USUARIOS_CONTEXT = SERVICES_CONTEXT + "/usuarios";
	
	private static String USUARIO_ADMIN = "admin";
	
	private static String SENHA_ADMIN = "admin";
	
	private byte[] imagemTeste;
	
	@Before
	public void setup() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(UsuariosServiceIT.class.getResourceAsStream("/imagem.jpg"));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
		this.imagemTeste = byteArrayOutputStream.toByteArray();
	}
	
	@Test
	public void testeCriacaoUsuarios() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Vagner");
		usuario.setLogin("admin");
		usuario.setSenha("admin");
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("usuario", Usuario.class);
		String xmlUsuario = stream.toXML(usuario);
		
		ClientResponse<?> clientResponse = new ClientRequest(USUARIOS_CONTEXT)
				.header("Authorization", criaAutenticacao(USUARIO_ADMIN, SENHA_ADMIN))
				.body(MediaType.APPLICATION_XML, xmlUsuario).post();
		
		Assert.assertEquals(201, clientResponse.getStatus());
	}
	
	@Test
	public void testeRecepcaoUsuarios() {
		Credentials credentials = new UsernamePasswordCredentials(USUARIO_ADMIN, SENHA_ADMIN);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
		
		ClientExecutor clientExecutor = new ApacheHttpClient4Executor(httpClient);
		
		UsuariosServiceInterface usuariosServiceInterface = ProxyFactory
				.create(UsuariosServiceInterface.class, SERVICES_CONTEXT, clientExecutor);
		
		@SuppressWarnings("unchecked")
		ClientResponse<Usuarios> response = (ClientResponse<Usuarios>) usuariosServiceInterface.listarUsuarios(null, null, null, null);
		
		Assert.assertEquals(200, response.getStatus());
		
		Usuarios usuarios = response.getEntity(Usuarios.class);
		
		Assert.assertNotNull(usuarios);
		Assert.assertNotNull(usuarios.getUsuarios());
		Assert.assertEquals(6, usuarios.getUsuarios().size());
	}
	
	@Test
	public void testeRecepcaoUsuariosClientRequest() throws Exception {
		ClientResponse<Usuario> response = new ClientRequest(USUARIOS_CONTEXT + "/{id}")
				.pathParameters(1)
				.header("Authorization", criaAutenticacao(USUARIO_ADMIN, SENHA_ADMIN))
				.get(Usuario.class);
		
		Usuario usuario = response.getEntity(Usuario.class);
		
		Assert.assertNotNull(usuario);
	}
	
	@Test
	public void testeCriacaoImagens() throws Exception {
		ClientResponse<?> clientResponse = new ClientRequest(USUARIOS_CONTEXT
				+ "/{id}")
				.pathParameters(1)
				.header(UsuariosServiceInterface.CAMPO_DESCRICAO_IMAGEM,
						"skate").body("image/jpg", imagemTeste).put();
		
		Assert.assertEquals(204, clientResponse.getStatus());
		
		clientResponse = new ClientRequest(USUARIOS_CONTEXT + "/{id}").pathParameters(1).accept("image/*").get(byte[].class);
		
		Assert.assertEquals(200, clientResponse.getStatus());
		Assert.assertArrayEquals(imagemTeste, (byte[]) clientResponse.getEntity());
		
		String descricaoImagem = clientResponse.getHeaders().getFirst(UsuariosServiceInterface.CAMPO_DESCRICAO_IMAGEM);
		
		Assert.assertEquals("skate", descricaoImagem);
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
	
	private static String criaAutenticacao(String usuario, String senha) {
		return "Basic " + encondeUsuarioSenha(usuario, senha);
	}

	private static String encondeUsuarioSenha(String usuario, String senha) {
		return Base64.encodeBytes((usuario + ":" + senha).getBytes());
	}

}
