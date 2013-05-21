package com.digiwin.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-05-17T16:57:55.704+08:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "EOSWebServiceImplService", 
                  wsdlLocation = "docs/EOSWebServiceImplPort.xml",
                  targetNamespace = "http://webservice.digiwin.com/") 
public class EOSWebServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservice.digiwin.com/", "EOSWebServiceImplService");
    public final static QName EOSWebServiceImplPort = new QName("http://webservice.digiwin.com/", "EOSWebServiceImplPort");
    static {
        URL url = EOSWebServiceImplService.class.getResource("docs/EOSWebServiceImplPort.xml");
        if (url == null) {
            java.util.logging.Logger.getLogger(EOSWebServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "docs/EOSWebServiceImplPort.xml");
        }       
        WSDL_LOCATION = url;
    }

    public EOSWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EOSWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EOSWebServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns EOSWebService
     */
    @WebEndpoint(name = "EOSWebServiceImplPort")
    public EOSWebService getEOSWebServiceImplPort() {
        return super.getPort(EOSWebServiceImplPort, EOSWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EOSWebService
     */
    @WebEndpoint(name = "EOSWebServiceImplPort")
    public EOSWebService getEOSWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(EOSWebServiceImplPort, EOSWebService.class, features);
    }

}
