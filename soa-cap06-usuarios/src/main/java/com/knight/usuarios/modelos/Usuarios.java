package com.knight.usuarios.modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.knight.usuarios.modelos.rest.Link;
import com.knight.usuarios.modelos.rest.RESTEntity;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios implements RESTEntity {
	
	@XmlElement(name = "usuario")
	private Collection<Usuario> usuarios;
	
	@XmlElement(name = "link")
	private Collection<Link> links;

	public Usuarios() {

	}

	public Usuarios(Collection<Usuario> usuarios, Link... link) {
		this.usuarios = usuarios;
		this.links = new ArrayList<Link>(Arrays.asList(link));
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public void adicionarLink(Link link) {
		if (this.links == null) {
			this.links = new ArrayList<Link>();
		}
		
		this.links.add(link);
	}
	
}
