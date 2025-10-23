import RMI.*;

import javax.xml.namespace.QName;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Provider;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.* ;
import java.util.stream.Collectors;

public class Main {
    private static final String studentCode = "B22DCCN352" ;
    private static final String qCode = "waf2Nblo" ;
    private static final String wsdlUrl = "http://203.162.10.109:8080/JNPWS/CharacterService?wsdl";
    private static final String nameSpace = "http://server/" ;
    private static final String serviceName = "CharacterService" ;
    public static void main(String[] args) throws Exception {
        URL url = new URL(wsdlUrl) ;
        QName qname = new QName(nameSpace , serviceName) ;


    }
}