import RMI.*;
import TCP.Customer;
import UDP.Employee;

import java.io.*;
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
        String studentCode = "B22DCCN508";
        String qCode = "6yguui9a";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ByteService byteService = (ByteService) registry.lookup("RMIByteService") ;
        byte[] request = byteService.requestData(studentCode , qCode) ;
        int dis = request.length ;
        for(int i = 0 ; i < dis ; i ++){
            request[i] += dis ;
        }
        byteService.submitData(studentCode , qCode , request);

    }

}