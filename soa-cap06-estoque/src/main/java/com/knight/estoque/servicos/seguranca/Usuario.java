package com.knight.estoque.servicos.seguranca;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.Date;

import javax.crypto.Cipher;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.util.Base64;

/**
 * This class represents an user
 * 
 * @author vagner
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Usuario {

	private String nome;
	private String login;
	private String senha;
	private Date dataAtualizacao;
	
	private static Cipher cipher;
	
	/**
	 * Loads the private key and an instance of Cipher to decrypt the password
	 */
	static {
		try {
			InputStream keyStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("private.key");
			ObjectInputStream ois = new ObjectInputStream(keyStream);
			Key decodeKey = (Key) ois.readObject();
			ois.close();
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, decodeKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	/**
	 * Gets the decrypted password
	 * 
	 * @return a String with the password already decrypted
	 */
	public String getSenhaDecodificada() {
		try {
			return new String(cipher.doFinal(Base64.decode(senha.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

}
