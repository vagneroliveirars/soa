package com.knight.usuarios.servicos.seguranca;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.codec.binary.Base64;

/**
 * This class represents a RSA public key
 * 
 * @author vagner
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RSA")
public class RSAPublica {
	
	private BigInteger modulus;
	
	private BigInteger publicExponent;
	
	/**
	 * Receives as parameter an array of bytes (the data to be encrypted) and
	 * returns a String with the password already encrypted and encoded with the
	 * Base64 algorithm.
	 * 
	 * @param bytes
	 * @return a String with the password already encrypted and encoded with the
	 *         Base64 algorithm.
	 * @throws ExcecaoCriptografia
	 */
	public String encripta(byte[] bytes) throws ExcecaoCriptografia {
		try {
			PublicKey publicKey = criaChave();
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return Base64.encodeBase64String(bytes);
		} catch (Exception e) {
			throw new ExcecaoCriptografia(e);
		}
	}

	/**
	 * Creates a RSA public key
	 * 
	 * @return a RSA public key
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	protected PublicKey criaChave() throws InvalidKeySpecException, NoSuchAlgorithmException {
		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
		return KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
	}

}
