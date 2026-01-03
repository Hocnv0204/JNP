import RMI.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.* ;
import java.util.stream.Collectors;

public class GxxH0HSN{
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "GxxH0HSN" ;
        int port = 1099 ;
        String server = "203.162.10.109" ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService") ;
        Product product = (Product) objectService.requestObject(studentCode , qCode) ;
        System.out.println(product);
        product.setCode(product.getCode().toUpperCase());
        product.setExportPrice((double) product.getImportPrice() * 1.20);
        objectService.submitObject(studentCode , qCode , product);

    }
}