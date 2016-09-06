package com.knight.estoque.services.autoresservice.v1;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class adapts asynchronous requests
 *  
 * @author vagner
 *
 */
public class AddressingHandler implements SOAPHandler<SOAPMessageContext> {
	
	private QName responseQName;

	// Response address
	private String enderecoResposta;
	
	public AddressingHandler(String enderecoResposta) {
		this.enderecoResposta = enderecoResposta;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			if (context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY).equals(Boolean.TRUE)) {
				trataRequisicao(context);
			} else {
				trataResposta(context);
			}
		} catch (SOAPException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * Handles the response
	 * 
	 * @param context
	 * @throws SOAPException
	 */
	private void trataResposta(SOAPMessageContext context) throws SOAPException {
		SOAPMessage soapMessage = context.getMessage();
		soapMessage.getSOAPBody().addChildElement(this.responseQName);
	}

	/**
	 * Handles the request by entering the address of the service for
	 * asynchronous response
	 * 
	 * @param context
	 * @throws SOAPException
	 */
	private void trataRequisicao(SOAPMessageContext context) throws SOAPException {
		SOAPMessage soapMessage = context.getMessage();
		NodeList nodeList = soapMessage.getSOAPHeader().getElementsByTagName("Address");
		Node node = nodeList.item(0);
		node.setTextContent(enderecoResposta);
		
		Node requestNode = soapMessage.getSOAPBody().getFirstChild();
		String namespace = requestNode.getNamespaceURI();
		String nodeName = requestNode.getLocalName() + "Response";
		
		QName qName = new QName(namespace, nodeName);
		this.responseQName = qName;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
