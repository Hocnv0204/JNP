import RMI.*;
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
        String studentCode = "B22DCCN244";
        String qCode = "rKtaQ6WN";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");
        Order order = (Order) objectService.requestObject(studentCode , qCode) ;
        System.out.println(order);
        StringBuilder orderCode = new StringBuilder(order.getShippingType().substring(0,2).toUpperCase()) ;
        orderCode.append(order.getCustomerCode().substring(order.getCustomerCode().length() - 3 , order.getCustomerCode().length() )) ;
        String[] date = order.getOrderDate().split("-") ;
        orderCode.append(date[2]).append(date[1]) ;
        order.setOrderCode(orderCode.toString());
        objectService.submitObject(studentCode , qCode, order);
    }

}