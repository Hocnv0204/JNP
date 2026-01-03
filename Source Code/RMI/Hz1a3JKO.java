import RMI.ByteService;
import RMI.CharacterService;
import RMI.ObjectService;
import RMI.ProductX;
import TCP.Customer;
import UDP.Employee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.* ;
public class Main{
    public static void main(String[] args) throws Exception {
        String server = "203.162.10.109";
        String studentCode = "B22DCCN352";
        String qCode = "Hz1a3JKO";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService") ;
        ProductX productX = (ProductX) objectService.requestObject(studentCode , qCode) ;
        System.out.println(productX);
        productX.setDiscount(discount(productX.getDiscountCode()));
        System.out.println(productX);
        objectService.submitObject(studentCode , qCode , productX);
    }
    private static int discount(String discountCode){
        int discount = 0 ;
        for(char c : discountCode.toCharArray()){
            if(Character.isDigit(c)){
                discount += (c - '0') ;
            }
        }
        return discount ;
    }
}