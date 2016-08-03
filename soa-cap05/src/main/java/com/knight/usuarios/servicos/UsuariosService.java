package com.knight.usuarios.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	public Response find(@PathParam("id") Long id) {
		Usuario usuario = this.entityManager.find(Usuario.class, id);
		
		if (usuario != null) {
			return Response.ok(usuario).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
}
