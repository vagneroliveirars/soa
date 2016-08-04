package com.knight.usuarios.servicos;

import java.net.URI;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.knight.usuarios.modelos.Imagem;
import com.knight.usuarios.modelos.Usuario;
import com.knight.usuarios.modelos.Usuarios;

@Stateless
@Path("/usuarios")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class UsuariosService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	public Usuarios listarUsuarios() {
		return new Usuarios(this.entityManager.createQuery("select u from Usuario u", Usuario.class).getResultList());
	}
	
	@GET
	@Path("/{id}")
	public Response find(@PathParam("id") Long id, @HeaderParam("If-Modified-Since") Date modifiedSince) {
		Usuario usuario = this.entityManager.find(Usuario.class, id);
		
		if (usuario != null) {
			if (modifiedSince == null 
					|| (modifiedSince != null && usuario.getDataAtualizacao().after(modifiedSince))) {
				return Response.ok(usuario).build();
			}
			
			return Response.notModified().build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	public Response create(@Context UriInfo uriInfo, Usuario usuario) {
		this.entityManager.persist(usuario);
		
		// Build URL where the resource is available
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		URI location = uriBuilder.path("/{id}").build(usuario.getId());
		
		return Response.created(location).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("image/*")
	public Response recuperarImagem(@PathParam("id") Long id, @HeaderParam("If-Modified-Since") Date modifiedSince) {
		Usuario usuario = this.entityManager.find(Usuario.class, id);
		
		if (usuario == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Imagem imagem = usuario.getImagem();
		
		if (modifiedSince != null && imagem.getDataAtualizacao().before(modifiedSince)) {
			return Response.notModified().build();
		}
		
		return Response.ok(imagem.getDados(), imagem.getTipo()).header("Descricao", imagem.getDescricao()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("image/*")
	public Response adicionarImagem(@HeaderParam("Descricao") String descricao,
			@PathParam("id") Long idUsuario,
			@Context HttpServletRequest httpServletRequest, 
			byte[] dadosImagem) {
		
		Usuario usuario = this.entityManager.find(Usuario.class, idUsuario);
		
		if (usuario == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Imagem imagem = new Imagem();
		imagem.setDados(dadosImagem);
		imagem.setDescricao(descricao);
		imagem.setTipo(httpServletRequest.getContentType());
		
		usuario.setImagem(imagem);
		
		this.entityManager.merge(usuario);
		
		return Response.noContent().build();
	}
	
}
