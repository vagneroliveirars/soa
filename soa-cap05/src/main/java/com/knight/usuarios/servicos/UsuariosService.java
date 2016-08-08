package com.knight.usuarios.servicos;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.knight.usuarios.modelos.Imagem;
import com.knight.usuarios.modelos.Usuario;
import com.knight.usuarios.modelos.Usuarios;
import com.knight.usuarios.modelos.rest.Link;

@Stateless
@Path("/usuarios")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class UsuariosService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String CAMPO_DESCRICAO_IMAGEM = "Descricao";

	private static final String PARAM_INICIO = "inicio";

	private static final String PARAM_TAMANHO_PAGINA = "tamanhoPagina";
	
	@GET
	public Usuarios listarUsuarios(@HeaderParam("If-Modified-Since") Date modifiedSince,
			@QueryParam(PARAM_INICIO) @DefaultValue("0") Integer inicio,
			@QueryParam(PARAM_TAMANHO_PAGINA) @DefaultValue("20")
			Integer tamanhoPagina,
			@Context UriInfo uriInfo) {
		
		
		
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
		
		return Response.ok(imagem.getDados(), imagem.getTipo()).header(CAMPO_DESCRICAO_IMAGEM, imagem.getDescricao()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("image/*")
	public Response adicionarImagem(@HeaderParam(CAMPO_DESCRICAO_IMAGEM) String descricao,
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
	
	private Link[] criarLinksUsuarios(UriInfo uriInfo, Integer tamanhoPagina, Integer inicio, Long numeroUsuarios) {
		Collection<Link> links = new ArrayList<Link>();
		
		double numeroUsuariosDouble = numeroUsuarios;
		double tamanhoPaginaDouble = tamanhoPagina;
		
		// Arredondamento apra cima para fornecer o número certo de páginas
		Long numeroPaginas = (long) Math.ceil(numeroUsuariosDouble / tamanhoPaginaDouble);
		
		// O resultado da divisão será um int
		Long paginaAtual = new Long(inicio / tamanhoPagina);
		
		Link linkPrimeiraPagina = new Link(
	            UriBuilder.fromPath(uriInfo.getPath()).queryParam(PARAM_INICIO, 0)
	                  .queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina).build()
	                  .toString(), "primeiraPagina");
	
		links.add(linkPrimeiraPagina);
		
		if (paginaAtual > 0) {
			if (paginaAtual <= numeroPaginas) {
				Link linkPaginaAnterior = new Link(UriBuilder
						.fromPath(uriInfo.getPath())
						.queryParam(PARAM_INICIO, (paginaAtual - 1) * tamanhoPagina)
						.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina)
						.build().toString(), "paginaAnterior");

				links.add(linkPaginaAnterior);
			} else {
				Link linkPaginaAnterior = new Link(UriBuilder
						.fromPath(uriInfo.getPath())
						.queryParam(PARAM_INICIO, (numeroPaginas - 1) * tamanhoPagina)
						.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina)
						.build().toString(), "paginaAnterior");
				
				links.add(linkPaginaAnterior);
			}
		}
		
		if (paginaAtual < (numeroPaginas - 1)) {
			Link linkProximaPagina = new Link(UriBuilder
							.fromPath(uriInfo.getPath())
							.queryParam(PARAM_INICIO, (paginaAtual + 1) * tamanhoPagina)
							.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina)
							.build().toString(), "proximaPagina");
			
			links.add(linkProximaPagina);
		}
		
		Link linkUltimaPagina = new Link(UriBuilder.fromPath(uriInfo.getPath())
				.queryParam(PARAM_INICIO, (numeroPaginas - 1) * tamanhoPagina)
				.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina).build()
				.toString(), "ultimaPagina");
		
		links.add(linkUltimaPagina);
		
		return links.toArray(new Link[] {});
	}
	
}
