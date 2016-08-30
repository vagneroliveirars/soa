package br.com.casadocodigo.soaaplicado.cap06;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Generates a RSA key pair (public and private)
 * 
 * @author vagner
 * 
 */
public class GeradorChaves {

	public static void main(String[] args) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		Key publiKey = keyPair.getPublic();
		Key privateKey = keyPair.getPrivate();
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("public.key"));
		oos.writeObject(publiKey);
		oos.flush();
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream("private.key"));
		oos.writeObject(privateKey);
		oos.flush();
		oos.close();
	}

}
