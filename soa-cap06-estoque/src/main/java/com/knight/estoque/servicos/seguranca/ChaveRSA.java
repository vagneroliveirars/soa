package com.knight.estoque.servicos.seguranca;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents a RSA public key
 * 
 * @author vagner
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RSA")
public class ChaveRSA {
	
	private BigInteger modulus;
	
	private BigInteger publicExponent;
	
	/**
	 * Loads a RSA public key
	 * 
	 * @return {@link ChaveRSA}
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ChaveRSA carregar() throws IOException, ClassNotFoundException {
		try (InputStream inputStream = ChaveRSA.class.getResourceAsStream("/public.key")) {
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			RSAPublicKey rsaPublicKey = (RSAPublicKey) ois.readObject();
			ChaveRSA chaveRSA = new ChaveRSA();
			chaveRSA.modulus = rsaPublicKey.getModulus();
			chaveRSA.publicExponent = rsaPublicKey.getPublicExponent();
			return chaveRSA;
		}
	}

	public BigInteger getModulus() {
		return modulus;
	}

	public BigInteger getPublicExponent() {
		return publicExponent;
	}

}
