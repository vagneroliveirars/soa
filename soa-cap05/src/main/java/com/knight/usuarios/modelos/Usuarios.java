package com.knight.usuarios.modelos;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios {
	
	@XmlElement(name = "usuario")
	private Collection<Usuario> usuarios;

	public Usuarios() {

	}

	public Usuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
