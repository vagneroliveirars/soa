
package com.knight.estoque.services.autoresservice.v1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.knight.estoque.domain.v1.Autor;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AutoresService", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1")
@XmlSeeAlso({
    com.knight.estoque.domain.v1.ObjectFactory.class,
    com.knight.estoque.services.autoresservice.v1.ObjectFactory.class,
    com.knight.usuarios.domain.v1.ObjectFactory.class
})
public interface AutoresService {


    /**
     * 
     * @return
     *     returns java.util.List<com.knight.estoque.domain.v1.Autor>
     */
    @WebMethod
    @WebResult(name = "autor", targetNamespace = "")
    @RequestWrapper(localName = "listarAutores", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.ListarAutores")
    @ResponseWrapper(localName = "listarAutoresResponse", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.ListarAutoresResponse")
    public List<Autor> listarAutores();

    /**
     * 
     * @param desde
     * @return
     *     returns java.util.List<com.knight.estoque.domain.v1.Autor>
     */
    @WebMethod
    @WebResult(name = "autor", targetNamespace = "")
    @RequestWrapper(localName = "solicitarRelacaoDeAutores", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.SolicitarRelacaoDeAutores")
    @ResponseWrapper(localName = "solicitarRelacaoDeAutoresResponse", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", className = "com.knight.estoque.services.autoresservice.v1.SolicitarRelacaoDeAutoresResponse")
    @Action(input = "AutoresService/solicitarRelacaoDeAutores", output = "AutoresService/solicitarRelacaoDeAutoresResponse")
    public List<Autor> solicitarRelacaoDeAutores(
        @WebParam(name = "desde", targetNamespace = "")
        XMLGregorianCalendar desde);

}
