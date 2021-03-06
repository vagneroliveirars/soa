
package com.knight.estoque.services.autoresservice.v1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AutoresServiceCallback", targetNamespace = "http://knight.com/estoque/services/AutoresService/v1", wsdlLocation = "file:/home/vagner/git/soa/soa-cap07-wsaddressing-servidor/src/main/webapp/WEB-INF/contracts/AutoresServiceCallback.wsdl")
public class AutoresServiceCallback_Service
    extends Service
{

    private final static URL AUTORESSERVICECALLBACK_WSDL_LOCATION;
    private final static WebServiceException AUTORESSERVICECALLBACK_EXCEPTION;
    private final static QName AUTORESSERVICECALLBACK_QNAME = new QName("http://knight.com/estoque/services/AutoresService/v1", "AutoresServiceCallback");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/home/vagner/git/soa/soa-cap07-wsaddressing-servidor/src/main/webapp/WEB-INF/contracts/AutoresServiceCallback.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AUTORESSERVICECALLBACK_WSDL_LOCATION = url;
        AUTORESSERVICECALLBACK_EXCEPTION = e;
    }

    public AutoresServiceCallback_Service() {
        super(__getWsdlLocation(), AUTORESSERVICECALLBACK_QNAME);
    }

    public AutoresServiceCallback_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), AUTORESSERVICECALLBACK_QNAME, features);
    }

    public AutoresServiceCallback_Service(URL wsdlLocation) {
        super(wsdlLocation, AUTORESSERVICECALLBACK_QNAME);
    }

    public AutoresServiceCallback_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AUTORESSERVICECALLBACK_QNAME, features);
    }

    public AutoresServiceCallback_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AutoresServiceCallback_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AutoresServiceCallback
     */
    @WebEndpoint(name = "AutoresServiceCallbackSOAP")
    public AutoresServiceCallback getAutoresServiceCallbackSOAP() {
        return super.getPort(new QName("http://knight.com/estoque/services/AutoresService/v1", "AutoresServiceCallbackSOAP"), AutoresServiceCallback.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AutoresServiceCallback
     */
    @WebEndpoint(name = "AutoresServiceCallbackSOAP")
    public AutoresServiceCallback getAutoresServiceCallbackSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://knight.com/estoque/services/AutoresService/v1", "AutoresServiceCallbackSOAP"), AutoresServiceCallback.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AUTORESSERVICECALLBACK_EXCEPTION!= null) {
            throw AUTORESSERVICECALLBACK_EXCEPTION;
        }
        return AUTORESSERVICECALLBACK_WSDL_LOCATION;
    }

}
