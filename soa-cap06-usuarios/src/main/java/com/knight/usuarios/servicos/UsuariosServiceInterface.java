package com.knight.usuarios.servicos;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.ws.rs.core.UriInfo;

import com.knight.usuarios.modelos.Usuario;
import com.knight.usuarios.servicos.seguranca.RSAPublica;

/**
 * The interface to an user service
 * 
 * @author vagner
 *
 */
@Path("/usuarios")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public interface UsuariosServiceInterface {
	
	static final String PARAM_INICIO = "inicio";

	static final String PARAM_TAMANHO_PAGINA = "tamanhoPagina";
	
	static final String CAMPO_DESCRICAO_IMAGEM = "descricao";
	
	/**
	 * Lists the users
	 * 
	 * @param modifiedSince
	 * @param inicio
	 * @param tamanhoPagina
	 * @param uriInfo
	 * @return {@link Response}
	 */
	@GET
	public Response listarUsuarios(@HeaderParam("If-Modified-Since") Date modifiedSince,
			@QueryParam(PARAM_INICIO) @DefaultValue("0") Integer inicio,
			@QueryParam(PARAM_TAMANHO_PAGINA) @DefaultValue("20")
			Integer tamanhoPagina,
			@Context UriInfo uriInfo);
	
	/**
	 * Finds the user by id
	 * 
	 * @param id
	 * @param modifiedSince
	 * @return {@link Response}
	 */
	@GET
	@Path("/{id}")
	public Response find(@PathParam("id") Long id, @HeaderParam("If-Modified-Since") Date modifiedSince);
	
	/**
	 * Finds the user by login
	 * 
	 * @param login
	 * @param modifiedSince
	 * @param chaveCriptografica
	 * @return {@link Response}
	 */
	@POST
	@Path("/{login}")
	public Response find(@PathParam("login") String login,
			@HeaderParam("If-Modified-Since") Date modifiedSince,
			RSAPublica chaveCriptografica);
	
	/**
	 * Creates an user
	 * 
	 * @param uriInfo
	 * @param usuario
	 * @return {@link Response}
	 */
	@POST
	public Response create(@Context UriInfo uriInfo, Usuario usuario);
	
	/**
	 * Updates an user
	 * 
	 * @param usuario
	 * @return {@link Response}
	 */
	@PUT
	public Response update(Usuario usuario);
	
	
	/**
	 * Updates an user
	 * 
	 * @param id
	 * @param usuario
	 * @return {@link Response}
	 */
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, Usuario usuario);
	
	/**
	 * Deletes an user
	 * 
	 * @param usuario
	 * @return {@link Response}
	 */
	@DELETE
	public Response delete(Usuario usuario);
	
	/**
	 * Deletes an user by id
	 * 
	 * @param id
	 * @return {@link Response}
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id);
	
	/**
	 * Adds an user image
	 * 
	 * @param descricao
	 * @param idUsuario
	 * @param httpServletRequest
	 * @param dadosImagem
	 * @return {@link Response}
	 */
	@PUT
	@Path("/{id}")
	@Consumes("image/*")
	public Response adicionarImagem(@HeaderParam(CAMPO_DESCRICAO_IMAGEM) String descricao,
			@PathParam("id") Long idUsuario,
			@Context HttpServletRequest httpServletRequest, 
			byte[] dadosImagem);
	
	/**
	 * Get an user image by id
	 * 
	 * @param id
	 * @param modifiedSince
	 * @return {@link Response}
	 */
	@GET
	@Path("/{id}")
	@Produces("image/*")
	public Response recuperarImagem(@PathParam("id") Long id, @HeaderParam("If-Modified-Since") Date modifiedSince);
	
}
