package com.knight.estoque.adaptadores;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.knight.estoque.modelos.Autor;

/**
 * Classe adaptadora que faz a tradução de String para Autor e vice-versa
 * 
 * @author vagner
 * 
 */
public class AdaptadorAutores extends XmlAdapter<String, Autor> {

	@Override
	public Autor unmarshal(String autor) throws Exception {
		return new Autor(autor, null);
	}

	@Override
	public String marshal(Autor autor) throws Exception {
		return autor.getNome();
	}

}
