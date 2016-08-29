package com.knight.estoque.servicos.seguranca;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

import com.knight.estoque.modelos.Usuario;

public class CallbackSeguranca implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof WSPasswordCallback) {
				WSPasswordCallback wsPasswordCallback = (WSPasswordCallback) callbacks[i];
				
				Usuario usuario = encontreUsuario(wsPasswordCallback.getIdentifier());
				if (usuario == null) {
					return;
				}	
				
				wsPasswordCallback.setPassword(usuario.getSenha());
			}
		}
	}

	private Usuario encontreUsuario(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
