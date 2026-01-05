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
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws Exception {
        String server = "203.162.10.109";
        String studentCode = "B22DCCN244";
        String qCode = "We8NUarM";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ByteService byteService = (ByteService) registry.lookup("RMIByteService") ;
        byte[] request = byteService.requestData(studentCode , qCode);
        System.out.println(request.length);
        byte[] refer = new byte[4] ;
        String strConst = "PTIT" ;
        for(int i = 0 ; i < strConst.length() ; i ++){
            refer[i] = (byte) strConst.charAt(i) ;
        }
        byte[] result = new byte[request.length] ;
        for(int i = 0 ; i < request.length ; i ++){
            int index = i % 4 ;
            result[i] = (byte)( request[i] ^ refer[index]) ;
        }
        byteService.submitData(studentCode , qCode , result);
    }

}