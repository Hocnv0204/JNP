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
        String qCode = "oQbKBslD";
        int port = 2207 ;
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress inetAddress = InetAddress.getByName(server) ;
        String message = ";" + studentCode + ";" + qCode ;
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes() , 0 , message.getBytes().length ,inetAddress , port ) ;
        socket.send(sendPacket);
        byte[] receivedByte = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receivedByte , receivedByte.length) ;
        socket.receive(receivedPacket);
        String request = new String(receivedPacket.getData() ,0,  receivedPacket.getLength() ) ;
        String[] arr = request.split(";") ;
        String requestId = arr[0] ;
        List<String> list = Arrays.stream(arr[1].split(",")).toList() ;
        Map<Integer , String> mapResult = new TreeMap<>() ;
        for(String str : list){
            String[] tempArr = str.split(":") ;
            mapResult.put(Integer.parseInt(tempArr[1]), tempArr[0]) ;
        }
        List<String> resultList = mapResult.values().stream().toList() ;
        StringBuilder result = new StringBuilder() ;
        result.append(requestId).append(";").append(String.join(",",  resultList)) ;
        System.out.println(result.toString());
        DatagramPacket resultPacket = new DatagramPacket(result.toString().getBytes() , 0 ,result.toString().getBytes().length , inetAddress ,port) ;
        socket.send(resultPacket);
        socket.close();
        System.out.println(request);
    }

}