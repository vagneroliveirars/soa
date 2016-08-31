
package com.knight.estoque.servicos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.knight.estoque.servicos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListaAutoresResponse_QNAME = new QName("http://servicos.estoque.knight.com/", "listaAutoresResponse");
    private final static QName _ListaAutores_QNAME = new QName("http://servicos.estoque.knight.com/", "listaAutores");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.knight.estoque.servicos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Livro }
     * 
     */
    public Livro createLivro() {
        return new Livro();
    }

    /**
     * Create an instance of {@link Autor }
     * 
     */
    public Autor createAutor() {
        return new Autor();
    }

    /**
     * Create an instance of {@link ListaAutoresResponse }
     * 
     */
    public ListaAutoresResponse createListaAutoresResponse() {
        return new ListaAutoresResponse();
    }

    /**
     * Create an instance of {@link ListaAutores }
     * 
     */
    public ListaAutores createListaAutores() {
        return new ListaAutores();
    }

    /**
     * Create an instance of {@link EBook }
     * 
     */
    public EBook createEBook() {
        return new EBook();
    }

    /**
     * Create an instance of {@link Livro.Autores }
     * 
     */
    public Livro.Autores createLivroAutores() {
        return new Livro.Autores();
    }

    /**
     * Create an instance of {@link Autor.Refs }
     * 
     */
    public Autor.Refs createAutorRefs() {
        return new Autor.Refs();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaAutoresResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.estoque.knight.com/", name = "listaAutoresResponse")
    public JAXBElement<ListaAutoresResponse> createListaAutoresResponse(ListaAutoresResponse value) {
        return new JAXBElement<ListaAutoresResponse>(_ListaAutoresResponse_QNAME, ListaAutoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaAutores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.estoque.knight.com/", name = "listaAutores")
    public JAXBElement<ListaAutores> createListaAutores(ListaAutores value) {
        return new JAXBElement<ListaAutores>(_ListaAutores_QNAME, ListaAutores.class, null, value);
    }

}
